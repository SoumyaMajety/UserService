package com.scaler.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;
@Data
@Entity
public class Token extends BaseClass{
    private String token;
    private Date expiryDate;
    private boolean isValid;
    @ManyToOne
    private User user;

}
