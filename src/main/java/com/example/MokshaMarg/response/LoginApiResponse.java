package com.example.MokshaMarg.response;

public class LoginApiResponse {

	public LoginApiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Long userId;

	private String name;

	private String email;

	private String roles;

	public LoginApiResponse(boolean status, String message, Object data, Long userId, String name, String email,
			String roles, String token) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.roles = roles;
		this.token = token;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roleName) {
		this.roles = roleName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	private String token;

}
