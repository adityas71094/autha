package com.adiSuper.autha.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Entity
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

    private static final long serialVersionUID = 1816562323;

    private Integer id;
    private String  firstName;
    private String  lastName;
    private String  username;
    private String  emailId;
    private String  passwordHash;
    private String password;

    public User() {}

    public User(User value) {
        this.id = value.id;
        this.firstName = value.firstName;
        this.lastName = value.lastName;
        this.username = value.username;
        this.emailId = value.emailId;
        this.passwordHash = value.passwordHash;
        this.password = value.password;
    }

    public User(
            Integer id,
            String  firstName,
            String  lastName,
            String  username,
            String  emailId,
            String  passwordHash
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.emailId = emailId;
        this.passwordHash = passwordHash;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, precision = 32)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
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


    @NotNull
    @Transient
    @Size(max = 100)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Users (");

        sb.append(id);
        sb.append(", ").append(firstName);
        sb.append(", ").append(lastName);
        sb.append(", ").append(username);
        sb.append(", ").append(emailId);
        sb.append(", ").append(passwordHash);

        sb.append(")");
        return sb.toString();
    }
}