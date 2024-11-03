package com.ruediger.cybersecure.security_controls;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "nist_controls")
public class NistControl {

    @Id
    @Column(name = "control_id", nullable = false, unique = true)
    private String controlId;

    @Column(name = "control_name", nullable = false)
    private String controlName;

    @Column(name = "control_description", nullable = false)
    private String controlDescription;

    @Column(name = "discussion", nullable = false)
    private String discussion;

    // Constructors, Getters, and Setters

    public NistControl() {
    }

    public NistControl(String controlId, String controlName, String controlDescription, String discussion) {
        this.controlId = controlId;
        this.controlName = controlName;
        this.controlDescription = controlDescription;
        this.discussion = discussion;
    }

    public String getControlId() {
        return controlId;
    }

    public void setControlId(String controlId) {
        this.controlId = controlId;
    }

    public String getControlName() {
        return controlName;
    }

    public void setControlName(String controlName) {
        this.controlName = controlName;
    }

    public String getControlDescription() {
        return controlDescription;
    }

    public void setControlDescription(String controlDescription) {
        this.controlDescription = controlDescription;
    }

    public String getDiscussion() {
        return discussion;
    }

    public void setDiscussion(String discussion) {
        this.discussion = discussion;
    }
}