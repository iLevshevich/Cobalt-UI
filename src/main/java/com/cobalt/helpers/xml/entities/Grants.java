package com.cobalt.helpers.xml.entities;

import lombok.Getter;
import lombok.NonNull;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType(propOrder = {"grant"}, name = "grants")
public class Grants {
    @Getter
    private List<Grant> grant;

    @XmlElement(name = "grant")
    public void setGrant(@NonNull final List<Grant> grant) {
        this.grant = grant;
    }
}
