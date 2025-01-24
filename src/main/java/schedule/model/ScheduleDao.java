package schedule.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import user.model.UserDao;
import util.DBManager;

public class ScheduleDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private ScheduleDao() {

	}

	private static ScheduleDao instance = new ScheduleDao();

	public static ScheduleDao getInstance() {
		return instance;
	}

	// 1. 일정 추가 메서드
	public void addSchedule(ScheduleRequestDto scheduleDto) {
		conn = DBManager.getConnection();

		String sql = "INSERT INTO schedule (user_code, medicine_code, start_date, end_date, daily_frequency) VALUES (?, ?, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, scheduleDto.getUserCode());
			pstmt.setInt(2, scheduleDto.getMedicineCode());
			pstmt.setString(3, scheduleDto.getStartDate());
			pstmt.setString(4, scheduleDto.getEndDate());
			pstmt.setInt(5, scheduleDto.getDailyFrequency());

			pstmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 일정 조회 및 출력 메서드
	public List<Schedule> findSchedulesByUsername(String username) {
	    List<Schedule> schedules = new ArrayList<>();
	    conn = DBManager.getConnection();
	    
	    UserDao userDao = UserDao.getInstance();
	    int userCode = userDao.findUserCodeByUsername(username);

	    String sql = "SELECT * FROM schedule WHERE userCode=?";
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, userCode); 
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            int medicineCode = rs.getInt("medicineCode");
	            String startDate = rs.getString("startDate");
	            String endDate = rs.getString("endDate");
	            int dailyFrequency = rs.getInt("dailyFrequency");

	            Schedule schedule = new Schedule(userCode, medicineCode, startDate, endDate, dailyFrequency);
	            schedules.add(schedule);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return schedules;
	}

}
