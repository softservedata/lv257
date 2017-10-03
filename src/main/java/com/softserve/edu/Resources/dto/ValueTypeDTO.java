package com.softserve.edu.Resources.dto;

import com.softserve.edu.Resources.entity.ValueType;

public class ValueTypeDTO {
    public final String title;
    public final ValueType valueType;
    public final String defaultPattern;

    public ValueTypeDTO(ValueType valueType) {
        this.valueType = valueType;
        title = valueType.typeName;
        defaultPattern = valueType.getDefaultPattern();
    }
}
