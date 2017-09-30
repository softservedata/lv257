package com.softserve.edu.Resources.security.entity;


import com.softserve.edu.Resources.Constants;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "generic_privilege")
public abstract class GenericPrivilege {

    @Id()
    @Column(name = "Id")
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    long id;

    @Column(name = "Name")
    @NotEmpty(message = "Cannot be empty")
    String name;

    @Column(name = "Description")
    @NotEmpty(message = "Cannot be empty")
    String description;

    public GenericPrivilege() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenericPrivilege that = (GenericPrivilege) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GenericPrivilege{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
