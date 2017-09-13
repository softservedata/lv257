package edu.softserve.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder

@Entity
@Table(name = "company_owner")
/**
 * Represents Company entity as a resource owner
 */
public class CompanyOwner extends Owner {

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "organization_form")
    private String organizationForm;

    @Column(name = "CEO")
    private String ceo;

    public CompanyOwner setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public CompanyOwner setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public CompanyOwner setOrganizationForm(String organizationForm) {
        this.organizationForm = organizationForm;
        return this;
    }

    public CompanyOwner setCeo(String ceo) {
        this.ceo = ceo;
        return this;
    }

    @Override
    public String toString() {
        return "CompanyOwner{" +
                "fullName='" + fullName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", organizationForm='" + organizationForm + '\'' +
                ", CEO='" + ceo + '\'' +
                "} " + super.toString();
    }

}
