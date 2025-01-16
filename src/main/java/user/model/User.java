package user.model;

import java.util.Date;

public class User {
	String username;
	String password;
	String email;
	String name;
	Date birth;
	String gender;
	String tel;
	
	public User(String username, String password, String email, String name, Date birth, String gender, String tel) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.birth = birth;
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
