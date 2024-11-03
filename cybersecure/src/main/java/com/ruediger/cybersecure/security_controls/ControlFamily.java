package com.ruediger.cybersecure.security_controls;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "control_families")
public class ControlFamily {

    @Id
    @Column(name = "family_id", nullable = false, unique = true)
    private String familyId;

    @Column(name = "name", nullable = false)
    private String name;

    // Constructors, Getters, and Setters

    public ControlFamily() {
    }

    public ControlFamily(String familyId, String name) {
        this.familyId = familyId;
        this.name = name;
    }

    public String getFamilyId() {
        return familyId;
    }

    public void setFamilyId(String familyId) {
        this.familyId = familyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


