package com.softserve.edu.dao;

import java.util.List;
import com.softserve.edu.entity.Contact;

public interface ContactDAO {
    
    public void addContact(Contact contact);

    public List<Contact> listContact();

    public void removeContact(Integer id);
    
}
