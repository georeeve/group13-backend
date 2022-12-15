package com.example.group13backend.db.models;

import com.example.group13backend.views.PublicView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

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
    @NotBlank(message = "First name must not be blank")
    private String firstName;

    @JsonView(PublicView.class)
    @NotBlank(message = "Last name must not be blank")
    private String lastName;

    @JsonView(PublicView.class)
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Not a valid email address",
            //http://emailregex.com/
            regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    private String email;

    @NotBlank(message = "Password must not be blank")
    private String password;

    @JsonView(PublicView.class)
    private LocalDate dob;

    //TODO: add address
    @JsonView(PublicView.class)
    @NotBlank(message = "First line of address must not be blank")
    private String addressLine1;

    @JsonView(PublicView.class)
    private String addressLine2;

    @JsonView(PublicView.class)
    @NotBlank(message = "City must not be blank")
    private String city;

    @JsonView(PublicView.class)
    @NotBlank(message = "Postcode must not be blank")
    private String postCode;

    public User() {
    }

    public User(
            String firstName,
            String lastName,
            String email,
            String password,
            LocalDate dob,
            String addressLine1,
            String addressLine2,
            String city,
            String postCode
    ) {
        this.admin = false;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.postCode = postCode;
    }

    public User(
            Long id,
            boolean admin,
            String firstName,
            String lastName,
            String email,
            LocalDate dob,
            String addressLine1,
            String addressLine2,
            String city,
            String postCode
    ) {
        this.id = id;
        this.admin = admin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.postCode = postCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", admin=" + admin +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", postCode='" + postCode + '\'' +
                '}';
    }
}
