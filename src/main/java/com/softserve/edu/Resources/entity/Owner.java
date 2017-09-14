package com.softserve.edu.Resources.entity;


import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "abstract_owner")
/**
 * Common abstraction to all kind(particularly only two) of owners.
 */
public abstract class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "owner_id")
    private int id;

    @Column(name = "owner_type")
    private String ownerType;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "phone")
    private String phone;

    public Owner setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Owner setOwnerType(String ownerType) {
        this.ownerType = ownerType;
        return this;
    }

    public Owner setAddress(Address address) {
        this.address = address;
        return this;
    }

    @Override
    public String toString() {
        return String.format("Owner{ownerType='%s', phone='%s'}", ownerType, phone);
    }
}
