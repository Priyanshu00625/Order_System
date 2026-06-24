package com.net.OrderSystem.entity;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "user")
@Data
public class User {
    @Id
    private ObjectId id;
    @NotBlank
    @NotNull(message = "UserName can,t be null")
    @Indexed(unique = true)
    @Pattern(
            regexp = ".*[a-zA-Z].*",
            message = "Username must contain at least one alphabet"
    )
    private String userName;
    @NotNull
    @NotEmpty
    @NotBlank
    private String password;
    private String email;
    private Order orders;
    private String role;
    private LocalDateTime time;
}
