package org.lms.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SystemUserRequestDto {

    @NotBlank(message = "First name is require")
    @Size(max = 100 , message = "First name must not exceed 100 characters")
    private String firstName;

    @NotBlank(message = "Last name is require")
    @Size(max = 100 , message = "Last name must not exceed 100 characters")
    private String lastName;

    @NotBlank(message = "Email is require")
    @Email(message = "Email format is not valid")
    private String email;

    @NotBlank(message = "Password is require")
    @Size(min = 6 , message = "Password must be at least 6 characters")
    private String password;

    private String contact;
}
