package com.nhnacademy.daily.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProjectType {
    PUBLIC, PRIVATE;

    @JsonCreator
    public static ProjectType fromString(String str){
        for (ProjectType projectType : ProjectType.values()) {
            if (projectType.name().equalsIgnoreCase(str)) {
                return projectType;
            }
        }
        //default
        return PUBLIC;
    }

    @JsonValue
    public String toJson(){
        return this.name().toLowerCase();
    }
}
