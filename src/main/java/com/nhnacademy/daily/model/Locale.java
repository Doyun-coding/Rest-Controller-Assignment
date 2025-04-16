package com.nhnacademy.daily.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Locale {
    KO, EN, JP;

    @JsonCreator
    public static Locale fromString(String str){
        for (Locale locale : Locale.values()) {
            if (locale.name().equalsIgnoreCase(str)) {
                return locale;
            }
        }
        //default
        return KO;
    }

    @JsonValue
    public String toJson(){
        return this.name().toLowerCase();
    }
}
