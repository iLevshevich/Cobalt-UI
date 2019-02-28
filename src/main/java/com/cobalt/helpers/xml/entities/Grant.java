package com.cobalt.helpers.xml.entities;

import lombok.Getter;
import lombok.NonNull;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"operation", "available"}, name = "grant")
public class Grant {
    @Getter
    private String operation;

    @Getter
    private String available;

    @XmlAttribute(name = "operation", required = true)
    public void setOperation(@NonNull final String operation) {
        this.operation = operation;
    }

    @XmlAttribute(name = "available", required = true)
    public void setAvailable(@NonNull final String available) {
        this.available = available;
    }
}
