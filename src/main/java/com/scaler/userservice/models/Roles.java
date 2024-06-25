package com.scaler.userservice.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Roles extends BaseClass{
    private  String role;
}
