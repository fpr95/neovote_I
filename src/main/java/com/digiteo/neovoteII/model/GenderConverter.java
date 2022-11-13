package com.digiteo.neovoteII.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<GenderValues, String> {

    @Override
    public String convertToDatabaseColumn(GenderValues genderValue) {
        if(genderValue == null){
            return "NE";
        }
        return genderValue.getCode();
    }

    @Override
    public GenderValues convertToEntityAttribute(String code) {
        if(code == "NE" || code == null){
            return GenderValues.NOT_SPECIFIED;
        }
        return Stream.of(GenderValues.values())
                .filter(s -> Objects.equals(s.getCode(), code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
