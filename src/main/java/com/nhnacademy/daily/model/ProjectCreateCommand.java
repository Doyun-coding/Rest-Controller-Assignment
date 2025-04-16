package com.nhnacademy.daily.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nhnacademy.daily.serializer.LocalDateToYyyyMMddSerializer;

import java.time.LocalDate;

public class ProjectCreateCommand {

    private String code;
    private ProjectType type;

    public ProjectCreateCommand() {}

    public ProjectCreateCommand(String code, ProjectType type) {
        this.code = code;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ProjectType getProjectType() {
        return type;
    }

    public void setProjectType(ProjectType type) {
        this.type = type;
    }
}
