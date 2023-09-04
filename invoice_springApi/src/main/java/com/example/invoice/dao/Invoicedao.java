package com.example.invoice.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.example.invoice.entity.Invoice;
import com.example.invoice.entity.Item;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

@Repository
public class Invoicedao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Invoice> getAllInvoices() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Invoice> query = cb.createQuery(Invoice.class);
		Root<Invoice> root = query.from(Invoice.class);

		Fetch<Invoice, List<Item>> itemFetch = root.fetch("item", JoinType.LEFT);
		query.select(root).distinct(true);
		// query.select(root);
		return entityManager.createQuery(query).getResultList();
	}

	public void saveInvoice(Invoice invoice) {
		entityManager.persist(invoice);
	}

	public Invoice getInvoiceById(Long id) {
		return entityManager.find(Invoice.class, id);
	}

	public void deleteInvoice(Invoice invoice) {
		entityManager.remove(invoice);
	}

	public Invoice updateInvoice(Invoice updatedInvoice) {
		Invoice existingInvoice = entityManager.find(Invoice.class, updatedInvoice.getId());
		if (existingInvoice != null) {
			existingInvoice.setCustomerName(updatedInvoice.getCustomerName());
			existingInvoice.setInvoiceNumber(updatedInvoice.getInvoiceNumber());
			existingInvoice.setCurDate(updatedInvoice.getCurDate());
			existingInvoice.setItem(updatedInvoice.getItem());
			return existingInvoice; 
		} else {
			throw new IllegalArgumentException("Invoice with ID " + updatedInvoice.getId() + " not found.");
		}
	}
}
