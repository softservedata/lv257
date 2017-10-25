package com.softserve.edu.Resources.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.softserve.edu.Resources.Constants;

@Entity
@Table(name = "RESOURCE")
public class Resource {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    @Column(name = "Id")
    private Long id;
    
    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;
    
    @OneToMany(mappedBy = "resource", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List <ResourceOwning> resourceOwnings = new ArrayList<>();
    
    
    public Resource() {
        // TODO Auto-generated constructor stub
    }

    public Resource(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    public void addResourceOwning(ResourceOwning resourceOwning){
        resourceOwnings.add(resourceOwning);
        resourceOwning.setResource(this);
    }
    
    public void removeResourceOwning(ResourceOwning resourceOwning){
        resourceOwning.setResource(null);
        this.resourceOwnings.remove(resourceOwning);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Resource other = (Resource) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Resource [id=" + id + ", address=" + address + "]";
    }
    
    
    
    
}
