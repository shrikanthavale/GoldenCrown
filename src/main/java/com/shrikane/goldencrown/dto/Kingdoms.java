package com.shrikane.goldencrown.dto;

public enum Kingdoms {
    SPACE("Gorilla"),
    LAND("Panda"),
    WATER("Octopus"),
    ICE("Mammoth"),
    AIR("Owl"),
    FIRE("Dragon");

    Kingdoms(final String animal) {
        this.animal = animal;
    }

    private String animal;

    public String getAnimal() {
        return animal;
    }
}
