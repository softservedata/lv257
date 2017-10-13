package com.softserve.edu.Resources.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "company_owner")
/**
 * Represents Company entity as a resource owner
 */
public class Company extends Owner {

    @Column(name = "full_name")
    @JsonProperty("full_name")
    @NotEmpty
    private String fullName;

    @Column(name = "short_name")
    @JsonProperty("short_name")
    @NotEmpty
    private String shortName;

    @Column(name = "organization_form")
    @JsonProperty("organization_form")
    @NotEmpty
    private String organizationForm;

    @Column(name = "CEO")
    @JsonProperty("ceo")
    @NotEmpty
    private String ceo;

    public Company() {
    }

    public Company setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Company setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public Company setOrganizationForm(String organizationForm) {
        this.organizationForm = organizationForm;
        return this;
    }

    public Company setCeo(String ceo) {
        this.ceo = ceo;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getOrganizationForm() {
        return organizationForm;
    }

    public String getCeo() {
        return ceo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        if (!super.equals(o)) return false;

        Company company = (Company) o;

        if (fullName != null ? !fullName.equals(company.fullName) : company.fullName != null) return false;
        if (shortName != null ? !shortName.equals(company.shortName) : company.shortName != null) return false;
        if (organizationForm != null ? !organizationForm.equals(company.organizationForm) : company.organizationForm != null)
            return false;
        return ceo != null ? ceo.equals(company.ceo) : company.ceo == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (organizationForm != null ? organizationForm.hashCode() : 0);
        result = 31 * result + (ceo != null ? ceo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Company{" +
                "fullName='" + fullName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", organizationForm='" + organizationForm + '\'' +
                ", CEO='" + ceo + '\'' +
                "} " + super.toString();
    }

    @Override
    public String ownerType() {
        return "Company";
    }

    @Override
    public String customToString() {
        return organizationForm + " " +
                fullName + " " +
                shortName + ", " +
                ceo + ".";
    }
}
