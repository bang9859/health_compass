package schedule.model;



public class Schedule {
	
	private int scheduleCode;
	private int userCode;
	private int medicineCode;
	private String medicineName;
	private String depositMethod;
	private String startDate;
	private String endDate;
	private int dailyFrequency;

	// Constructor
	public Schedule(int scheduleCode, int userCode, int medicineCode,String medicineName,String depositMethod, String startDate, String endDate,
			int dailyFrequency) {
		this.scheduleCode = scheduleCode;
		this.userCode = userCode;
		this.medicineCode = medicineCode;
		this.medicineName = medicineName;
		this.depositMethod = depositMethod;
		this.startDate = startDate;
		this.endDate = endDate;
		this.dailyFrequency = dailyFrequency;
	}

	// Getters and Setters
	public int getScheduleCode() {
		return scheduleCode;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getDepositMethod() {
		return depositMethod;
	}

	public void setDepositMethod(String depositMethod) {
		this.depositMethod = depositMethod;
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

}
