package schedule.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class ScheduleDao {

	// 1. 일정 추가 메서드
	public boolean addSchedule(int userCode, int medicineCode, String startDate, String endDate, int dailyFrequency) {
		String sql = "INSERT INTO schedule (user_code, medicine_code, start_date, end_date, daily_frequency) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = DBManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, userCode);
			pstmt.setInt(2, medicineCode);
			pstmt.setString(3, startDate);
			pstmt.setString(4, endDate);
			pstmt.setInt(5, dailyFrequency);

			int result = pstmt.executeUpdate();
			return result > 0; // 성공 시 true 반환
		} catch (Exception e) {
			e.printStackTrace();
			return false; // 실패 시 false 반환
		}
	}

	// 2. 일정 조회 메서드
	public List<Schedule> getUserSchedules(int userCode) {
		String sql = "SELECT s.schedule_code, s.user_code, s.medicine_code, s.start_date, s.end_date, "
				+ "s.daily_frequency, s.reg_date, s.mod_date, u.username, m.medicine_name " + "FROM schedule s "
				+ "JOIN users u ON s.user_code = u.user_code "
				+ "JOIN medicines m ON s.medicine_code = m.medicine_code " + "WHERE s.user_code = ?";

		List<Schedule> schedules = new ArrayList<>();
		try (Connection conn = DBManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, userCode);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Schedule schedule = new Schedule(
						rs.getInt("schedule_code"), 
						rs.getInt("user_code"),
						rs.getInt("medicine_code"), 
						rs.getString("start_date"), 
						rs.getString("end_date"),
						rs.getInt("daily_frequency"),
						rs.getTimestamp("reg_date"), 
						rs.getTimestamp("mod_date"),
						rs.getString("username"), 
						rs.getString("medicine_name"));
					schedules.add(schedule);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return schedules;
	}
}
