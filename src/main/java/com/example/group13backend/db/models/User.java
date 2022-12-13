package com.example.group13backend.db.models;

import com.example.group13backend.views.PublicView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity(name = "Users")
@Table(name = "Users")
public class User {
    @Id
    @JsonView(PublicView.class)
    private Long id;

    @JsonView(PublicView.class)
    private boolean admin;

    @JsonView(PublicView.class)
    private String firstName;

    @JsonView(PublicView.class)
    private String lastName;

    @JsonView(PublicView.class)
    private String email;

    private String password;

    @JsonView(PublicView.class)
    private LocalDate dob;

    //address

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dob = dob;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }
}
