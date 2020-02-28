package com.ehtesham.hospitalmanagement.domain;

public enum Type {
    DOCTOR ("Doctor"),
    PATIENT ("Patient");

    private final String name;

    Type(String name) {
        this.name = name;
    }
}
