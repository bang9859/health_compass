package schedule.model;

public class ScheduleRequestDto {

	private Integer userCode;
	private Integer medicineCode;
	private String startDate;
	private String endDate;
	private Integer dailyFrequency;
	private String medicineName;
	private String depositMethod;
	
	public ScheduleRequestDto(Integer userCode, Integer medicineCode, String startDate, String endDate,
			Integer dailyFrequency, String medicineName, String depositMethod) {
		this.userCode = userCode;
		this.medicineCode = medicineCode;
		this.startDate = startDate;
		this.endDate = endDate;
		this.dailyFrequency = dailyFrequency;
		this.medicineName = medicineName;
		this.depositMethod = depositMethod;
	}
	public ScheduleRequestDto(int userCode, int medicineCode, String startDate, String endDate, int dailyFrequency) {
		this.userCode = userCode;
		this.medicineCode = medicineCode;
		this.startDate = startDate;
		this.endDate = endDate;
		this.dailyFrequency = dailyFrequency;
	}
	public Integer getUserCode() {
		return userCode;
	}
	public Integer getMedicineCode() {
		return medicineCode;
	}
	public String getStartDate() {
		return startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public Integer getDailyFrequency() {
		return dailyFrequency;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public String getDepositMethod() {
		return depositMethod;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	
	public void setDepositMethod(String depositMethod) {
		this.depositMethod = depositMethod;
	}
	
	
	
}
