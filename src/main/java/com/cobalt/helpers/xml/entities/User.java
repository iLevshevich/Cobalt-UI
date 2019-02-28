package com.cobalt.helpers.xml.entities;

import lombok.Getter;
import lombok.NonNull;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"name", "password", "role", "grants"}, name = "user")
public class User {
    @Getter
    private String name;

    @Getter
    private String password;

    @Getter
    private String role;

    @Getter
    private Grants grants;

    @XmlAttribute(name = "name", required = true)
    public void setName(@NonNull final String name) {
        this.name = name;
    }

    @XmlAttribute(name = "password", required = true)
    public void setPassword(@NonNull final String password) {
        this.password = password;
    }

    @XmlAttribute(name = "role", required = true)
    public void setRole(@NonNull final String role) {
        this.role = role;
    }

    @XmlElement(name = "grants")
    public void setGrants(@NonNull final Grants grants) {
        this.grants = grants;
    }
}
