package com.cobalt.helpers.xml.entities;

import lombok.Getter;
import lombok.NonNull;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType(propOrder = {"user"}, name = "users")
@XmlRootElement
public class Users {
    @Getter
    private List<User> user;

    @XmlElement(name = "user")
    public void setUser(@NonNull final List<User> user) {
        this.user = user;
    }
}
