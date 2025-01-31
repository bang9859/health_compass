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

		String sql = "INSERT INTO schedule (user_code, medicine_code,medicine_name,deposit_method, start_date, end_date, daily_frequency)"
				+ "VALUES (?,?, ?, ?, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, scheduleDto.getUserCode());
			pstmt.setInt(2, scheduleDto.getMedicineCode());			
			pstmt.setString(3, scheduleDto.getMedicineName());
			pstmt.setString(4, scheduleDto.getDepositMethod());
			pstmt.setString(5, scheduleDto.getStartDate());
			pstmt.setString(6, scheduleDto.getEndDate());
			pstmt.setInt(7, scheduleDto.getDailyFrequency());

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
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    String sql = "SELECT s.* FROM schedule s JOIN users u ON s.user_code = u.user_code WHERE u.username = ?";

	    try {
	        conn = DBManager.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, username); // username 파라미터 설정
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	        	int scheduleCode = rs.getInt(1);
	            int userCode = rs.getInt(2);
	            int medicineCode = rs.getInt(3);	            
	            String medicineName = rs.getString(4);
	            String depositMethod = rs.getString(5);
	            String startDate = rs.getString(6);
	            String endDate = rs.getString(7);
	            int dailyFrequency = rs.getInt(8);

	            Schedule schedule = new Schedule(scheduleCode,userCode, medicineCode,medicineName,depositMethod, startDate, endDate, dailyFrequency);
	            schedules.add(schedule);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    System.out.println(schedules);
	    return schedules;
	}
	
	public Schedule findScheduleByScheduleCode(int scheduleCode) {
		Schedule schedule = null;
		
		conn = DBManager.getConnection();

		String sql = "SELECT * FROM schedule WHERE schedule_code=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, scheduleCode);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
	            int userCode = rs.getInt(2);
	            int medicineCode = rs.getInt(3);	            
	            String medicineName = rs.getString(4);
	            String depositMethod = rs.getString(5);
	            String startDate = rs.getString(6);
	            String endDate = rs.getString(7);
	            int dailyFrequency = rs.getInt(8);

	            schedule = new Schedule(scheduleCode,userCode, medicineCode,medicineName,depositMethod, startDate, endDate, dailyFrequency);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return schedule;
		
		
		
	}

}
