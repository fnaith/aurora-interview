package com.example.AuroraInterview.model;

import javax.persistence.*;

@Entity
@Table(name = "MENU")
public class Menu {

    @Column(name = "id")
    @Id
    Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "content")
    String content;

    @Column(name = "created_by")
    String createdBy;

    @Column(name = "created_at")
    Long createdAt;

    @Column(name = "updated_by")
    String updatedBy;

    @Column(name = "updated_at")
    Long updatedAt;

    @Column(name = "deleted")
    Boolean deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
