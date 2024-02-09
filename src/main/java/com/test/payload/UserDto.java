package com.test.payload;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDto {
         Long id;
         String userName;
         String password;
}
