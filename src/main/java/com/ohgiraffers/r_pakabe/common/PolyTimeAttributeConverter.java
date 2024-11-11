package com.ohgiraffers.r_pakabe.common;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Converter(autoApply = true)
public class PolyTimeAttributeConverter implements AttributeConverter<PolyTime, LocalDateTime> {

    @Override
    public LocalDateTime convertToDatabaseColumn(PolyTime attribute) {
        if (attribute == null) {
            return null;
        }
        return LocalDateTime.parse(attribute.getRaw(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS"));
    }

    @Override
    public PolyTime convertToEntityAttribute(LocalDateTime dbData) {
        if (dbData == null) {
            return null;
        }
        return new PolyTime(dbData);
    }
}