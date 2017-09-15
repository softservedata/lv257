package com.softserve.edu.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softserve.edu.entity.Contact;

@Repository
public class ContactDAOImpl implements ContactDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addContact(Contact contact) {
		sessionFactory.getCurrentSession().save(contact);
	}

	//@SuppressWarnings({"unchecked", "deprecation"})
	public List<Contact> listContact() {
		return sessionFactory.getCurrentSession()
		        .createQuery("from Contact", Contact.class).getResultList();
	}

	public void removeContact(Integer id) {
		//Contact contact = (Contact) sessionFactory.getCurrentSession().load(Contact.class, id);
        Contact contact = (Contact) sessionFactory.getCurrentSession().get(Contact.class, id);
		if (contact != null) {
			sessionFactory.getCurrentSession().delete(contact);
		}
	}

}
