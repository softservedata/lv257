package edu.softserve.entity;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Optional;



public enum ValueType {
    INTEGER("integer", Integer.class, "\\d+", Types.INTEGER),
    DOUBLE("double", Double.class, "\\d+\\.\\d{3}", Types.DECIMAL),
    STRING("string", String.class, "\\p{L}+", Types.VARCHAR),
    BOOLEAN("boolean", Boolean.class, "(yes)|(no)", Types.BIT),
    TIMESTAMP("timestamp", Timestamp.class, "", Types.TIMESTAMP),
    RESOURCE_PROPERTY("resource property", ResourceProperty.class, "\\{.{2,}\\}", Types.LONGVARCHAR); // JSON representation, i.e. {"typeID": "12"}

    public final Class<?> clazz;
    public final String typeName;
    public final String defaultRegex;
    public final int sqlType;
    

    ValueType(String typeName, Class<?> clazz, String defaultRegex, int sqlType) {
        this.typeName = typeName;
        this.clazz = clazz;
        this.defaultRegex = defaultRegex;
        this.sqlType = sqlType;
    }

    public static Optional<ValueType> forName(String typeName) {
        return Arrays.stream(ValueType.values())
                .filter(vt -> vt.typeName.equalsIgnoreCase(typeName))
                .findFirst();
    }

    public String getTypeName() {
        return typeName;
    }

    public int getSqlType() {
        return sqlType;
    }
    
    
    
}
