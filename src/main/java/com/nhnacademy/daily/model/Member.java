package com.nhnacademy.daily.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class Member {
    private String id;
    private String name;
    private Integer age;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonProperty("class")
    private ClassType clazz;

    public Member() {
    }

    public Member(String id, String name, Integer age, ClassType clazz) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.clazz = clazz;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public ClassType getClazz() {
        return clazz;
    }
}
