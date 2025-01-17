package user.model;

import java.util.Date;

import org.mindrot.jbcrypt.BCrypt;

public class User {
	private String username;
	private String password;
	private String email;
	private String name;
	private Date birth;
	private String gender;
	private String tel;
	
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
	
	public boolean checkCryptPassword(String password) {
		boolean isChecked = false;
		try {
			isChecked = BCrypt.checkpw(password, this.password);
		} catch (Exception e) {
			System.err.println("암호화되지 않은 값이 저장되어 있습니다.");
		}
		return isChecked;
	}
	
	public boolean checkPassword(String password) {
		boolean isChecked = false;
		if(this.password.equals(password)) {
			isChecked = true;
		}
		return isChecked;
	}
}
