package com.softserve.edu.service;

import java.util.List;
import com.softserve.edu.entity.Contact;

public interface ContactService {
    
    public void addContact(Contact contact);

    public List<Contact> listContact();

    public void removeContact(Integer id);
    
}
