package schedule.model;

import java.sql.Timestamp;

public class Schedule {
	private int userCode;
	private int medicineCode;
	private String startDate;
	private String endDate;
	private int dailyFrequency;

	// Constructor
	public Schedule(int userCode, int medicineCode, String startDate, String endDate,
			int dailyFrequency) {
		this.userCode = userCode;
		this.medicineCode = medicineCode;
		this.startDate = startDate;
		this.endDate = endDate;
		this.dailyFrequency = dailyFrequency;
	}

	// Getters and Setters

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

}
