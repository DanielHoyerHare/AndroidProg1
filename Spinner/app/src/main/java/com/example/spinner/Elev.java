package com.example.spinner;

public class Elev {
    private String name;
    private String skill;
    private Integer alder;

    public Elev(String name, String skill, Integer alder) {
        this.name = name;
        this.skill = skill;
        this.alder = alder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Integer getAlder() {
        return alder;
    }

    public void setAlder(Integer alder) {
        this.alder = alder;
    }

    @Override
    public String toString() {
        return name;
    }
}
