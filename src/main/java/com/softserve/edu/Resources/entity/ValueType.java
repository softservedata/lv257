package com.softserve.edu.Resources.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Optional;

public enum ValueType {

    INTEGER("Integer", Integer.class, Types.INTEGER, "\\d+"),
    DOUBLE("Double", Double.class, Types.DECIMAL, "\\d+\\.\\d{1,3}"),
    STRING("String", String.class, Types.VARCHAR, "\\p{L}+"),
    BOOLEAN("Boolean", Boolean.class, Types.BIT, "(yes)|(no)"),
    TIMESTAMP("Timestamp", Timestamp.class, Types.TIMESTAMP, "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} (.\\d{1,8})?"),
    DATE("Date", Date.class, Types.DATE, "\\d{4}-\\d}2-\\d{2}"),
    RESOURCE_PROPERTY("resource property", ResourceProperty.class, Types.LONGVARCHAR, "\\{.{2,}\\}"); // JSON representation, i.e. {"typeID": "12"}

    public final Class<?> clazz;
    public final String typeName;
    public final int sqlType;
    public final String defaultRegex;

    ValueType(String typeName, Class<?> clazz, int sqlType, String defaultRegex) {
        this.typeName = typeName;
        this.clazz = clazz;
        this.sqlType = sqlType;
        this.defaultRegex = defaultRegex;
    }

    public static Optional<ValueType> forName(String typeName) {
        return Arrays.stream(ValueType.values())
                .filter(vt -> vt.typeName.equalsIgnoreCase(typeName))
                .findFirst();
    }

    public int getSqlType() {
        return sqlType;
    }
    
    
}
