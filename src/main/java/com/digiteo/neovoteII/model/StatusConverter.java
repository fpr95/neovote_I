package com.digiteo.neovoteII.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<ElectionStatus, String> {

    @Override
    public String convertToDatabaseColumn(ElectionStatus electionStatus) {
        if(electionStatus == null){
            return null;
        }
        return electionStatus.getCode();
    }

    @Override
    public ElectionStatus convertToEntityAttribute(String code) {
        if(code == null){
            return null;
        }
        return Stream.of(ElectionStatus.values())
                .filter(s -> Objects.equals(s.getCode(), code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
