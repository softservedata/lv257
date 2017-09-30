package com.softserve.edu.Resources.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "system_privilege")
public class SystemPrivilege extends GenericPrivilege {

    @Column(name = "Accessible_Field")
    private boolean accessible;

    public SystemPrivilege() {
    }

    public boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SystemPrivilege)) return false;
        if (!super.equals(o)) return false;

        SystemPrivilege that = (SystemPrivilege) o;

        return accessible == that.accessible;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (accessible ? 1 : 0);
        return result;
    }


}
