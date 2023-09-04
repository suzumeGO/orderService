package com.example.orderService.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class Payment implements Serializable {
    @CreditCardNumber(message = "Неверный номер карты")
    private String ccNumber;
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
            message="Требуемый формат MM/YY")
    private String ccExpiration;
    @NotBlank(message = "Введите имя владельца")
    private String ccHolder;
    @Digits(integer = 3, fraction = 0, message = "Неверный CVV")
    private String ccCVV;

}

