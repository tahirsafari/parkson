package com.parkson.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDto {
    @NotBlank
    @Size(min = 4, max = 40)
    private String username;
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
    

    public UserDto() {}
	public UserDto(@NotBlank @Size(min = 4, max = 40) String username,
			@NotBlank @Size(min = 6, max = 20) String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
