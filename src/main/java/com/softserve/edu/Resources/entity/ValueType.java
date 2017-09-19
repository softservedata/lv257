package com.softserve.edu.Resources.entity;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Optional;

public enum ValueType {

    INTEGER("integer", Integer.class, "\\d+"),
    DOUBLE("double", Double.class, "\\d+\\.\\d{3}"),
    STRING("string", String.class, "\\p{L}+"),
    BOOLEAN("boolean", Boolean.class, "(yes)|(no)"),
    TIMESTAMP("timestamp", Timestamp.class, ""),
    RESOURCE_PROPERTY("resource property", ResourceProperty.class, "\\{.{2,}\\}"); // JSON representation, i.e. {"typeID": "12"}

    public final Class<?> clazz;
    public final String typeName;
    public final String defaultRegex;

    ValueType(String typeName, Class<?> clazz, String defaultRegex) {
        this.typeName = typeName;
        this.clazz = clazz;
        this.defaultRegex = defaultRegex;
    }

    public static Optional<ValueType> forName(String typeName) {
        return Arrays.stream(ValueType.values())
                .filter(vt -> vt.typeName.equalsIgnoreCase(typeName))
                .findFirst();
    }
}
