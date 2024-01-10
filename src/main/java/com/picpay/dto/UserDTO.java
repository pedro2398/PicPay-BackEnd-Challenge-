package com.picpay.dto;

import com.picpay.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String email, String password, String document, BigDecimal balance, UserType userType) {
}
