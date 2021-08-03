package com.sda.spring.projekt1.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserNameFormDTO {
    @NotNull
    @Length(min = 3, message = "Imię musi mieć przynajmniej 3 znaki")
    @Pattern(regexp = "[A-Za-zążźćńółęĄŻŹĆŃŁÓĘ]+")
    private String name;
}