package com.example.invoice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.invoice.entity.Custentity;
import com.example.invoice.entity.Item;
import com.example.invoice.entity.Items;
import com.example.invoice.entity.Invoice;
import com.example.invoice.service.Custservice;


@CrossOrigin("*")
@RestController
@RequestMapping("customer")
public class Custcontroller {
	private final Custservice customerService;

	@Autowired

	public Custcontroller(Custservice customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/getcust")
	public List<Custentity> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@PostMapping("/addcust")
    public ResponseEntity<String> addcustomer(@RequestBody Custentity cust){
        if (customerService.customerExistsByName(cust.getCustomerName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Customer with the same name already exists");
        }
        customerService.addcustomer(cust);
        return ResponseEntity.ok("Customer added successfully");
    }

	@GetMapping("/getitem")
	public List<Items> getAllItems() {
		return customerService.getAllItems();
	}

	@PostMapping("/additem")
	public ResponseEntity<String> additem(@RequestBody Items item) {
		if(customerService.itemExistsByName(item.getName())) {
		     return ResponseEntity.status(HttpStatus.CONFLICT).body("Item with the same name already exists");
        }
		customerService.additem(item);
		return ResponseEntity.ok("item added Successfully");
	}

	@GetMapping("/getinvoices")
	public List<Invoice> getAllInvoices() {
		return customerService.getAllInvoices();

	}

	@PostMapping("/addinvoices")
	public void addInvoice(@RequestBody Invoice invoice) {
		customerService.saveInvoice(invoice, invoice.getItem());
	}

	@GetMapping("/getInItem")
	public List<Item> getAllInItem() {
		return customerService.getAllInItem();
	}

	@PostMapping("/addInItem")
	public void addInItem(@RequestBody Item item) {
		customerService.addInItem(item);
	}

	@DeleteMapping("/deleteInvoice/{invoiceid}")
	public ResponseEntity<String> deleteInvoice(@PathVariable Long invoiceid) {
		customerService.deleteInvoice(invoiceid);
		return ResponseEntity.ok("Invoice deleted successfully");
	}

	@PutMapping("/updateinvoice/{invoiceId}")
	public ResponseEntity<String> updateInvoice(@PathVariable Long invoiceId, @RequestBody Invoice updatedInvoice) {
		customerService.updateInvoice(invoiceId, updatedInvoice, updatedInvoice.getItem());
		return ResponseEntity.ok("Invoice updated successfully");

	}
	
	@GetMapping("invoicebyid/{invoiceid}")
	public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long invoiceid) {
	    Invoice invoice = customerService.getInvoiceById(invoiceid);
	    if (invoice != null) {
	        return ResponseEntity.ok(invoice);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

}
