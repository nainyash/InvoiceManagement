package com.example.invoice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.invoice.dao.Custdao;
import com.example.invoice.dao.Invoicedao;
import com.example.invoice.dao.Itemdao;
import com.example.invoice.dao.Itemsdao;
import com.example.invoice.entity.Custentity;
import com.example.invoice.entity.Item;
import com.example.invoice.entity.Items;
import com.example.invoice.entity.Invoice;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class Custservice {
	@Autowired
	private final Custdao customerDao;
	private final Itemsdao itemdao;
	private final Invoicedao invdao;
	private final Itemdao item;

	public Custservice(Custdao customerDao, Itemsdao itemdao, Invoicedao invdao, Itemdao item) {
		this.customerDao = customerDao;
		this.itemdao = itemdao;
		this.invdao = invdao;
		this.item = item;
	}

	public List<Custentity> getAllCustomers() {
		return customerDao.getAllCustomers();
	}

	public Custentity addcustomer(Custentity cust) {
		customerDao.addcustomer(cust);
		return cust;
	}
	
	public boolean customerExistsByName(String customerName) {
        return customerDao.existsByCustomerName(customerName);
    }

	public List<Items> getAllItems() {
		return itemdao.getAllItems();
	}

	public Items additem(Items item) {
		itemdao.additem(item);
		return item;
	}
	
	public boolean itemExistsByName(String name) {
        return itemdao.existsByItemName(name);
    }

	public void saveInvoice(Invoice invoice, List<Item> items) {
		for (Item item : items) {
			item.setInvoice(invoice);
		}
		invoice.setItem(items);
		invdao.saveInvoice(invoice);
	}

	public List<Invoice> getAllInvoices() {
		return invdao.getAllInvoices();
	}

	public void updateInvoice(Long invoiceId, Invoice updatedInvoice, List<Item> updatedItems) {
		Invoice existingInvoice = invdao.getInvoiceById(invoiceId);
		if (existingInvoice != null) {
			List<Item> existingItems = existingInvoice.getItem();
			for (Item updatedItem : updatedItems) {
				if (updatedItem.getId() != null) {
					Item existingItem = existingItems.stream().filter(item -> item.getId().equals(updatedItem.getId()))
							.findFirst().orElse(null);
					if (existingItem != null) {
						existingItem.setItemName(updatedItem.getItemName());
						existingItem.setQuantity(updatedItem.getQuantity());
						existingItem.setPrice(updatedItem.getPrice());
						existingItem.setSubtotal(updatedItem.getSubtotal());
						existingItem.setGstAmount(updatedItem.getGstAmount());
						existingItem.setTotalAmount(updatedItem.getTotalAmount());
					}
				} else {
					updatedItem.setInvoice(existingInvoice);
					existingItems.add(updatedItem);
				}
			}
			existingItems.removeIf(existingItem -> !updatedItems.contains(existingItem));
			existingInvoice.setCustomerName(updatedInvoice.getCustomerName());
			existingInvoice.setInvoiceNumber(updatedInvoice.getInvoiceNumber());
			existingInvoice.setCurDate(updatedInvoice.getCurDate());
			invdao.updateInvoice(existingInvoice);
		} else {
			throw new IllegalArgumentException("Invoice with ID " + invoiceId + " not found.");
		}
	}

	 public Invoice getInvoiceById(Long id) {
	        return invdao.getInvoiceById(id);
	    }
	
	public List<Item> getAllInItem() {
		return item.getAllInItem();
	}

	public Item addInItem(Item itm) {
		item.addInItem(itm);
		return itm;
	}

	public void deleteInvoice(Long invoiceid) {
		Invoice invoice = invdao.getInvoiceById(invoiceid);
		if (invoice != null) {
			invdao.deleteInvoice(invoice);
		}
	}
}
