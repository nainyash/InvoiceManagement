package com.example.invoice.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.example.invoice.entity.Item;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class Itemdao {

	@PersistenceContext

	private EntityManager entityManager;

	public Itemdao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<Item> getAllInItem() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Item> cq = cb.createQuery(Item.class);
		Root<Item> root = cq.from(Item.class);
		cq.select(root);
		return entityManager.createQuery(cq).getResultList();
	}

	public Item addInItem(Item item) {
		entityManager.persist(item);
		return item;
	}
}
