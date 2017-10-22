package com.softserve.edu.Resources.entity;

import com.softserve.edu.Resources.Constants;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Optional;

/**
 * Instances of @ResourceProperty describe particular property
 * intrinsic to a particular kind of GenericResource. GenericResource
 * can have a collection (a set in general) of different properties.
 */
@Entity
@Table(
        name = "RESOURCE_PROPERTIES",
        uniqueConstraints = @UniqueConstraint(name = "unq_title_units",columnNames = {"Title", "Units"}))
public class ResourceProperty implements Comparable<ResourceProperty>{

    transient static long idgen = 0;

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    @Column(name="Column_Name", unique = true, nullable = false)
    @NotEmpty
    private String columnName;

    @Column(name="Title", nullable = false)
    @NotEmpty
    private String title;

    @Column(name = "Units")
    private String units;

    @Column(name = "Units_short")
    private String unitsShort;
    
    @Column(name = "Hint")
    private String hint;

    @Column(name = "Regex", nullable = false)
    private String pattern = ".+";

    @Enumerated(EnumType.STRING)
    @Column(name = "Value_Type")
    private ValueType valueType = ValueType.STRING;

    @Column(name = "Multivalued")
    private boolean multivalued = false;

    public ResourceProperty() {
    }

    public ResourceProperty(String title) {
        this.columnName = title;
        this.title = title;
        this.id = idgen++;
    }

    public Class<?> valueClassFor(String classDescription) {
        final Optional<ValueType> valueType = ValueType.forName(classDescription);
        if (!valueType.isPresent()) {
            throw new IllegalArgumentException("Inappropriate type requested");
        }
        return valueType.map(vt -> vt.clazz).get();
    }

    public long getId() {
        return id;
    }

    public ResourceProperty setId(long id) {
        this.id = id;
        return this;
    }

    public String getUnitsShort() {
        return unitsShort;
    }

    public void setUnitsShort(String unitsShort) {
        this.unitsShort = unitsShort;
    }

    public String getColumnName() {
        return columnName;
    }

    public ResourceProperty setColumnName(String columnName) {
        this.columnName = columnName;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ResourceProperty setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUnits() {
        return units;
    }

    public ResourceProperty setUnits(String units) {
        this.units = units;
        return this;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getPattern() {
        return pattern;
    }

    public ResourceProperty setPattern(String pattern) {
        this.pattern = pattern;
        return this;
    }

    public ResourceProperty setId(Long id) {
        this.id = id;
        return this;
    }

    public ValueType getValueType() {
        return valueType;
    }

    public ResourceProperty setValueType(ValueType valueType) {
        this.valueType = valueType;
        return this;
    }

    public boolean isMultivalued() {
        return multivalued;
    }

    public ResourceProperty setMultivalued(boolean multivalued) {
        this.multivalued = multivalued;
        return this;
    }

    @Override
    public String toString() {
        return units == null ? columnName : String.join(", ", columnName, units);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceProperty that = (ResourceProperty) o;

        if (!columnName.equals(that.columnName)) return false;
        return units != null ? units.equals(that.units) : that.units == null;
    }

    @Override
    public int hashCode() {
        int result = columnName.hashCode();
        result = 31 * result + (units != null ? units.hashCode() : 0);
        return result;
    }

    public String getDescription() {
        return units == null || units.isEmpty()
                       ? title
                       : String.join(", ", title, units);
    }

    @Override
    public int compareTo(ResourceProperty o) {

        return this.getColumnName().compareToIgnoreCase(o.getColumnName());
        
        
    }
}
