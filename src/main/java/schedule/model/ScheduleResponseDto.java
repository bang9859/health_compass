package schedule.model;



public class ScheduleResponseDto {
	private Integer userCode;
	private Integer medicineCode;
	private String startDate;
	private String endDate;
	private Integer dailyFrequency;
	
	
	public ScheduleResponseDto(ScheduleRequestDto scheduleDto) {
		this.userCode = scheduleDto.getUserCode();
		this.medicineCode = scheduleDto.getMedicineCode();
		this.startDate = scheduleDto.getStartDate();
		this.endDate = scheduleDto.getEndDate();
		this.dailyFrequency = scheduleDto.getDailyFrequency();
	
	}
}
