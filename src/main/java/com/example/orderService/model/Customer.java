package com.example.orderService.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class Customer implements Serializable {
    @NotBlank(message = "Имя получателя не может быть пустым")
    private String firstName;
    @NotBlank(message = "Фамилия получателя не может быть пустой")
    private String secondName;
    private String patronymic;
    @NotBlank(message = "Адрес электронной почты должен содержать символ @")
    private String email;
    @NotBlank(message = "Адрес не может быть пустым")
    private String address;
    @NotBlank(message = "Номер телефона не может быть пустым")
    private String phoneNumber;
    private PaymentType paymentType;

    public enum PaymentType {
        CASH, CREDIT_CARD
    }

}
