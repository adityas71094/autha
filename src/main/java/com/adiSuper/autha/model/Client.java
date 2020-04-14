package com.adiSuper.autha.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;



@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(name = "client", schema = "core", uniqueConstraints = {
        @UniqueConstraint(name = "client_pkey", columnNames = {"id"}),
        @UniqueConstraint(name = "key_unique", columnNames = {"key"})
}, indexes = {
        @Index(name = "client_pkey", unique = true, columnList = "id ASC"),
        @Index(name = "key_unique", unique = true, columnList = "key ASC")
})
public class Client implements Serializable {

    private static final long serialVersionUID = 625794804;

    private Integer id;
    private Integer userId;
    private String  name;
    private String  key;

    public Client() {}

    public Client(Client value) {
        this.id = value.id;
        this.userId = value.userId;
        this.name = value.name;
        this.key = value.key;
    }

    public Client(
            Integer id,
            Integer userId,
            String  name,
            String  key
    ) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.key = key;
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

    @Column(name = "user_id", nullable = false, precision = 32)
    @NotNull
    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "name", nullable = false, length = 100)
    @NotNull
    @Size(max = 100)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "key", nullable = false, length = 100)
    @NotNull
    @Size(max = 100)
    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Client (");

        sb.append(id);
        sb.append(", ").append(userId);
        sb.append(", ").append(name);
        sb.append(", ").append(key);

        sb.append(")");
        return sb.toString();
    }
}
