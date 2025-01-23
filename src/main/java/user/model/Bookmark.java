package user.model;

public class Bookmark {
	String hospitalId;
	String hospitalName;
	String address;
	
	public Bookmark(String hospitalId, String hospitalName, String address) {
		this.hospitalId = hospitalId;
		this.hospitalName = hospitalName;
		this.address = address;
	}
	
	public String getHospitalId() {
		return hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public String getAddress() {
		return address;
	}
}
