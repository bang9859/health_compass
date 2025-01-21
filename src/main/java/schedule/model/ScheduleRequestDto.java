package schedule.model;

public class ScheduleRequestDto {

	private Integer userCode;
	private Integer medicineCode;
	private String startDate;
	private String endDate;
	private Integer dailyFrequency;
	
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
	
	
}
