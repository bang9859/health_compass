package schedule.model;

public class ScheduleRequestDto {

	private Integer userCode;
	private Integer medicineCode;
	private String medicineName;
	private Integer dailyFrequency;
	private String startDate;
	private String endDate;
	private String depositMethod;
	
	public ScheduleRequestDto(Integer userCode, Integer medicineCode,String medicineName, String depositMethod, String startDate, String endDate,
			Integer dailyFrequency ) {
		this.userCode = userCode;
		this.medicineCode = medicineCode;
		this.medicineName = medicineName;
		this.depositMethod = depositMethod;
		this.startDate = startDate;
		this.endDate = endDate;
		this.dailyFrequency = dailyFrequency;
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
	
	
	
	
}
