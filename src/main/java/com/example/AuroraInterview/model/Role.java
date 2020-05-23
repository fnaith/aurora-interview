package com.example.AuroraInterview.model;

import javax.persistence.*;

@Entity
public class Role {

    public static final String USER = "USER";
    public static final String MANAGER = "MANAGER";

    @Id
    @SequenceGenerator(name="ROLE_SEQUENCE", sequenceName="ROLE_SEQUENCE_ID", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="ROLE_SEQUENCE")
    private Long id;
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
