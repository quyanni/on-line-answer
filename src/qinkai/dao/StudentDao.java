package qinkai.dao;

import qinkai.entity.College;
import qinkai.entity.Student;
import qinkai.entity.Teacher;
import qinkai.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

	public Student login(String username, String password) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from student where username=? and password=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		Student student = null;
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			String realname = rs.getString("realname");
			student = new Student(username, password, realname);
		}
		JDBCUtil.release(conn, ps, rs);
		return student;
	}

	public boolean addStudent(String username, String password, String realname) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "insert into student values(?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		ps.setString(3, realname);
		int cnt = ps.executeUpdate();
		JDBCUtil.release(conn, ps);
		return cnt > 0;
	}

	public boolean changePassword(String username, String password) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update student set password=? where username=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, password);
		ps.setString(2, username);
		int cnt = ps.executeUpdate();
		JDBCUtil.release(conn, ps);
		return cnt > 0;
	}

	public boolean checkUsername(String username) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from student where username=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		boolean flag = false;
		if (rs.next()) {
			flag = true;
		}
		JDBCUtil.release(conn, ps, rs);
		return flag;
		
	}

	public List<Student> findAllStudents() throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select a.*,IFNULL(b.num,0) as questionnum from  student a \n" +
				"left join  (\n" +
				"select count(*) num,suname from message\n" +
				"GROUP BY suname) b on a.username = b.suname";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Student> list = new ArrayList<>();
		while (rs.next()) {
			String username = rs.getString("username");
			String password = rs.getString("password");
			String realname = rs.getString("realname");
			String questionnum = rs.getString("questionnum");
			Student teacher = new Student(username, password, realname,questionnum);
			list.add(teacher);
		}
		JDBCUtil.release(conn, ps, rs);
		return list;
	}


	public boolean delStudent(String username) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "delete from student where username=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		int cnt = ps.executeUpdate();
		JDBCUtil.release(conn, ps);
		return cnt > 0;
	}

	public Student findStudent(String username) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from student  where username=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		Student student = null;
		if (rs.next()) {
			String password = rs.getString("password");
			String realname = rs.getString("realname");
			student = new Student(username, password, realname);
		}
		JDBCUtil.release(conn, ps, rs);
		return student;
	}

	public boolean updateStudent(String username, String realname, String password) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update student set realname=?, password=? where username=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, realname);
		ps.setString(2, password);
		ps.setString(3, username);
		int cnt = ps.executeUpdate();
		JDBCUtil.release(conn, ps);
		return cnt > 0;
	}

}
