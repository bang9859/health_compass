package user.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserRequestDto {
	private String username;
	private String password;
	private String email;
	private String name;
	private Date birth;
	private String gender;
	private String tel;
	
	public UserRequestDto(String username, String password, String email, String name, Date birth, String gender, String tel) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.birth = birth;
		this.gender = gender;
		this.tel = tel;
	}

	public UserRequestDto(String username, String password, String email, String name, String birth, String gender, String tel) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = formatter.parse(birth);
			this.birth = date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.gender = gender;
		this.tel = tel;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public Date getBirth() {
		return birth;
	}

	public String getGender() {
		return gender;
	}

	public String getTel() {
		return tel;
	}
}
