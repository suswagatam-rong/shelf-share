package org.kai.shelfshare.auth;
/*
 * @created 25/06/2024 - 09:37
 * @project Shelf Share
 * @author  Suswagatam Rong
 */

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {

	@NotEmpty(message = "First name is mandatory")
	@NotBlank(message = "First name is mandatory")
	private String firstname;

	@NotEmpty(message = "Last name is mandatory")
	@NotBlank(message = "Last name is mandatory")
	private String lastname;

	@Email(message = "Email is not formatted")
	@NotEmpty(message = "Email is mandatory")
	@NotBlank(message = "Email is mandatory")
	private String email;

	@NotEmpty(message = "Password is mandatory")
	@NotBlank(message = "Password is mandatory")
	@Size(min = 8, message = "Password should be 8 characters long minimum")
	private String password;
}