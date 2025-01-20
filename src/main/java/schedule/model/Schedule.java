package schedule.model;

import java.sql.Timestamp;

public class Schedule {
	private int scheduleCode;
	private int userCode;
	private int medicineCode;
	private String startDate;
	private String endDate;
	private int dailyFrequency;
	private Timestamp regDate;
	private Timestamp modDate;
	private String username;
	private String medicineName;

	// Constructor
	public Schedule(int scheduleCode, int userCode, int medicineCode, String startDate, String endDate,
			int dailyFrequency, Timestamp regDate, Timestamp modDate, String username, String medicineName) {
		this.scheduleCode = scheduleCode;
		this.userCode = userCode;
		this.medicineCode = medicineCode;
		this.startDate = startDate;
		this.endDate = endDate;
		this.dailyFrequency = dailyFrequency;
		this.regDate = regDate;
		this.modDate = modDate;
		this.username = username;
		this.medicineName = medicineName;
	}

	// Getters and Setters
	public int getScheduleCode() {
		return scheduleCode;
	}

	public void setScheduleCode(int scheduleCode) {
		this.scheduleCode = scheduleCode;
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	public int getMedicineCode() {
		return medicineCode;
	}

	public void setMedicineCode(int medicineCode) {
		this.medicineCode = medicineCode;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getDailyFrequency() {
		return dailyFrequency;
	}

	public void setDailyFrequency(int dailyFrequency) {
		this.dailyFrequency = dailyFrequency;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public Timestamp getModDate() {
		return modDate;
	}

	public void setModDate(Timestamp modDate) {
		this.modDate = modDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
}
