package com.kuroshan.MONEYTRACKER.request;

import com.kuroshan.MONEYTRACKER.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter // getter here is must
@Builder
public class Registration {
    @NotBlank(message = "name should not be blank")
    private String name;
    @NotBlank(message = "mail should not be blank")
    private String email;
    @NotBlank(message = "password should not be blank")
    private String password;

    public User toUser() {
        return User.builder().email(this.email).name(this.name).password(this.password).build();
    }
}
