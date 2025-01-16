package user.model;

import java.util.Date;

public class UserResponseDto {
	private String username;
	private String password;
	private String email;
	private String name;
	private Date birth;
	private String gender;
	private String tel;
	
	public UserResponseDto(UserRequestDto userDto) {
		this.username = userDto.getUsername();
		this.password = userDto.getPassword();
		this.email = userDto.getEmail();
		this.name = userDto.getName();
		this.birth = userDto.getBirth();
		this.gender = userDto.getGender();
		this.tel = userDto.getTel();
	}
}
