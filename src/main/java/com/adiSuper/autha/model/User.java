package com.adiSuper.autha.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;


@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "users", schema = "core", uniqueConstraints = {
        @UniqueConstraint(name = "users_pkey", columnNames = {"id"}),
        @UniqueConstraint(name = "username_unique", columnNames = {"username"}),
        @UniqueConstraint(name = "email_unique", columnNames = {"email_id"})
}, indexes = {
        @Index(name = "email_unique", unique = true, columnList = "email_id ASC"),
        @Index(name = "username_unique", unique = true, columnList = "username ASC"),
        @Index(name = "users_pkey", unique = true, columnList = "id ASC")
})
public class User implements Serializable {

    private static final long serialVersionUID = -1005314099;

    private UUID    id;
    private String  firstName;
    private String  lastName;
    private String  username;
    private String  emailId;
    private String  passwordHash;
    private Boolean enabled = false;
    private String password;
    private Boolean accLocked = false;
    private Boolean accExpired = false;
    private Boolean credExpired = false;

    public User() {}

    public User(User value) {
        this.id = value.id;
        this.firstName = value.firstName;
        this.lastName = value.lastName;
        this.username = value.username;
        this.emailId = value.emailId;
        this.passwordHash = value.passwordHash;
        this.enabled = value.enabled;
        this.accLocked = value.accLocked;
        this.accExpired = value.accExpired;
        this.credExpired = value.credExpired;
    }


    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "first_name", nullable = false, length = 100)
    @NotNull
    @Size(max = 100)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false, length = 100)
    @NotNull
    @Size(max = 100)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "username", nullable = false, length = 100)
    @NotNull
    @Size(max = 100)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "email_id", nullable = false, length = 100)
    @NotNull
    @Size(max = 100)
    public String getEmailId() {
        return this.emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Column(name = "password_hash", nullable = false, length = 200)
    @JsonIgnore
    public String getPasswordHash() {
        return this.passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Column(name = "enabled", nullable = false)
    @JsonIgnore
    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Transient
    @NotNull
    @Size(max = 100)
    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "acc_locked", nullable = false)
    @JsonIgnore
    public Boolean getAccLocked() {
        return this.accLocked;
    }

    public void setAccLocked(Boolean accLocked) {
        this.accLocked = accLocked;
    }

    @Column(name = "acc_expired", nullable = false)
    @JsonIgnore
    public Boolean getAccExpired() {
        return this.accExpired;
    }

    public void setAccExpired(Boolean accExpired) {
        this.accExpired = accExpired;
    }

    @Column(name = "cred_expired", nullable = false)
    @JsonIgnore
    public Boolean getCredExpired() {
        return this.credExpired;
    }

    public void setCredExpired(Boolean credExpired) {
        this.credExpired = credExpired;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Users (");

        sb.append(id);
        sb.append(", ").append(firstName);
        sb.append(", ").append(lastName);
        sb.append(", ").append(username);
        sb.append(", ").append(emailId);
        sb.append(", ").append(enabled);
        sb.append(", ").append(accLocked);
        sb.append(", ").append(accExpired);
        sb.append(", ").append(credExpired);
        sb.append(")");
        return sb.toString();
    }
}
