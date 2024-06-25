package com.scaler.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class User extends BaseClass{
    private String name;
    private String email;
    private String password;
    private boolean isVerified;
    @ManyToMany
    private List<Roles> roles;
}
