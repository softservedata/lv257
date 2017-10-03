package com.softserve.edu.Resources.entity;import com.softserve.edu.Resources.Constants;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "abstract_owner")
@JsonTypeInfo(
        use= JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Person.class, name = "person"),
        @JsonSubTypes.Type(value = Company.class, name = "company")
})
/**
 * Common abstraction to all kind(particularly only two) of owners.
 */
public abstract class Owner {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)

    @Column(name = "owner_id")
    private long id;

//    @JoinColumn(name = "address_id")
    @Valid
    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "owner")
    private Address address;

    @NotEmpty
    @JsonProperty("phone")
    private String phone;

    public Owner() {
    }

    public Owner setId(long id) {
        this.id = id;
        return this;
    }

    public long getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public Owner setAddress(Address address) {
        this.address = address;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Owner setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Owner)) return false;

        Owner owner = (Owner) o;

        if (id != owner.id) return false;
        if (address != null ? !address.equals(owner.address) : owner.address != null) return false;
        return phone != null ? phone.equals(owner.phone) : owner.phone == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String addressInfo(){
        return address.customToString();
    }

    public String customToString(){
        return "";
    }

    public abstract String ownerType();
}
