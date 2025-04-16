package com.nhnacademy.daily.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberCreateCommand {
    private String id;
    private String name;
    private Integer age;
    @JsonProperty("class")
    private ClassType clazz = ClassType.B;

    public MemberCreateCommand() {
    }

    public MemberCreateCommand(String id, String name, Integer age, ClassType clazz) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.clazz = clazz;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setClazz(ClassType clazz) {
        this.clazz = clazz;
    }

    public void setId(String id) {
        this.id = id;
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
