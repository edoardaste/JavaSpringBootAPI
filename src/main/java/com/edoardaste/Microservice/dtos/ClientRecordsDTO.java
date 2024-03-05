package com.edoardaste.Microservice.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ClientRecordsDTO (@NotBlank String nameClient, @NotNull String addressClient){
}
