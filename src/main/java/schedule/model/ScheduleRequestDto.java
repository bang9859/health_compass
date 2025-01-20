package schedule.model;

public class ScheduleRequestDto {

	private Integer userCode;
	private Integer medicineCode;
	private String startDate;
	private String endDate;
	private Integer dailyFrequency;
	
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
