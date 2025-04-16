package com.nhnacademy.daily.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nhnacademy.daily.serializer.LocalDateToYyyyMMddDeserializer;
import com.nhnacademy.daily.serializer.LocalDateToYyyyMMddSerializer;

import java.time.LocalDate;

public class Project {
    private String code;

    @JsonSerialize(using = LocalDateToYyyyMMddSerializer.class)
    @JsonDeserialize(using = LocalDateToYyyyMMddDeserializer.class)
    private LocalDate localDate;

    private ProjectType type;

    public Project() {

    }

    public Project(String code, LocalDate localDate, ProjectType type) {
        this.code = code;
        this.localDate = localDate;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public ProjectType getType() {
        return type;
    }

    public void setType(ProjectType type) {
        this.type = type;
    }
}
