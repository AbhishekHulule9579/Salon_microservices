package com.infinite.payload.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private  Long id;
    private String fullName;
    private String email;
}
