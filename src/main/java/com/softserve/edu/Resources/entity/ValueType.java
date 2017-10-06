package com.softserve.edu.Resources.entity;

import java.lang.reflect.InvocationTargetException;
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
    DATE("Date", Date.class, Types.TIMESTAMP, "\\d{4}-\\d}2-\\d{2}"),
    TIMESTAMP("Timestamp", Timestamp.class, Types.TIMESTAMP, "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} (.\\d{1,8})?");
//    RESOURCE_PROPERTY("Resource property", ResourceProperty.class, Types.VARCHAR, "\\{.{2,}\\}"); // JSON representation, i.e. {"typeID": "12"}

    public final Class<?> clazz;
    public final String typeName;
    public final int sqlType;
    public final String defaultPattern;

    ValueType(String typeName, Class<?> clazz, int sqlType, String defaultPattern) {
        this.typeName = typeName;
        this.clazz = clazz;
        this.sqlType = sqlType;
        this.defaultPattern = defaultPattern;
    }

    public static Optional<ValueType> forName(String typeName) {
        return Arrays.stream(ValueType.values())
                .filter(vt -> vt.typeName.equalsIgnoreCase(typeName))
                .findFirst();
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public String getTypeName() {
        return typeName;
    }

    public int getSqlType() {
        return sqlType;
    }

    public String getDefaultPattern() {
        return defaultPattern;
    }

    public static int compare(ValueType valueType, String value1, String value2) {
        switch (valueType) {
            case INTEGER    : return compare(value1, value2, Integer.class);
            case DOUBLE     : return compare(value1, value2, Double.class);
            case DATE       : return compare(value1, value2, java.util.Date.class);
            case TIMESTAMP  : return compare(value1, value2, java.util.Date.class);
            case STRING     : return compare(value1, value2, String.class);
            case BOOLEAN    : return compare(value1, value2, Boolean.class);
        }
        return value1.compareTo(value2);
    }

    private static <T extends Comparable<T>> int compare(String value1, String value2, Class<T> clazz) {
        try {
            T val1 = getValueOf(value1, clazz);
            T val2 = getValueOf(value2, clazz);
            return val1.compareTo(val2);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return value1.compareTo(value2);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> T getValueOf(String value1, Class<T> clazz)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return (T) clazz.getMethod("valueOf", String.class).invoke(null, value1);
    }
}
