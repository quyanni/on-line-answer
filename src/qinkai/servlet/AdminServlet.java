package qinkai.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import qinkai.entity.*;
import qinkai.service.AdminService;
import qinkai.service.StudentService;
import qinkai.util.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	// 上传文件存储目录
	private static final String UPLOAD_DIRECTORY = "upload";

	// 上传配置
	private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
	private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method.equals("login")) {
			login(request, response);
		} else if (method.equals("logOut")) {
			logOut(request, response);
		} else if (method.equals("showCourseList")) {
			showCourseList(request, response);
		} else if (method.equals("updateCourseUI")) {
			updateCourseUI(request, response);
		} else if (method.equals("updateCourse")) {
			updateCourse(request, response);
		} else if (method.equals("delCourse")) {
			delCourse(request, response);
		} else if (method.equals("addCourse")) {
			addCourse(request, response);
		} else if (method.equals("showTeacherList")) {
			showTeacherList(request, response);
		} else if (method.equals("updateTeacherUI")) {
			updateTeacherUI(request, response);
		} else if (method.equals("updateTeacher")) {
			updateTeacher(request, response);
		} else if (method.equals("addTeacher")) {
			addTeacher(request, response);
		} else if (method.equals("delTeacher")) {
			delTeacher(request, response);
		} else if (method.equals("showTeacherCourseList")) {
			showTeacherCourseList(request, response);
		} else if (method.equals("showCollegeList")) {
			showCollegeList(request, response);
		} else if (method.equals("updateCollege")) {
			updateCollege(request, response);
		} else if (method.equals("addCollege")) {
			addCollege(request, response);
		} else if (method.equals("delCollege")) {
			delCollege(request, response);
		} else if (method.equals("showMsgList")) {
			showMsgList(request, response);
		} else if (method.equals("updateMsgUI")) {
			updateMsgUI(request, response);
		} else if (method.equals("updateMsg")) {
			updateMsg(request, response);
		} else if (method.equals("delMsg")) {
			delMsg(request, response);
		} else if (method.equals("showAnswerList")) {
			showAnswerList(request, response);
		} else if (method.equals("updateAnswer")) {
			updateAnswer(request, response);
		} else if (method.equals("delAnswer")) {
			delAnswer(request, response);
		} else if (method.equals("checkPassword")) {
			checkPassword(request, response);
		} else if (method.equals("changePassword")) {
			changePassword(request, response);
		}else if(method.equals("showStudentList")){
            showStudentList(request,response);
		}else if(method.equals("importStudent")){
			importStudent(request, response);
			showStudentList(request, response);
		}else if(method.equals("delStudent")){
           delStudent(request, response);
		}else if(method.equals("updateStudentUI")){
			updateStudentUI(request, response);
		}else if(method.equals("updateStudent")){
			updateStudent(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}



	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			String password= request.getParameter("password");
			AdminService adminService = new AdminService();
			Admin admin = adminService.login(username, password);
			if (admin != null) {
				request.getSession().setAttribute("admin", admin);
				response.sendRedirect("admin/home.jsp");
				return;
			} else {
				request.setAttribute("msg", "用户名或密码错误");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
	}
	
	protected void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = ((Admin)request.getSession().getAttribute("admin")).getUsername();
			String password= request.getParameter("newPassword");
			AdminService adminService = new AdminService();
			boolean isSuccess = adminService.changePassword(username, password);
			if (isSuccess) {
				request.setAttribute("msg", "修改成功！");
				request.getRequestDispatcher("admin/changePassword.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void showStudentList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AdminService adminService = new AdminService();
			List<Student> list = adminService.showStudentList();
			request.setAttribute("list", list);
			request.getRequestDispatcher("admin/studentList.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void checkPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String username = ((Admin)request.getSession().getAttribute("admin")).getUsername();
			String password = request.getParameter("password");
			AdminService adminService = new AdminService();
			Admin admin = adminService.login(username, password);
			if (admin == null) {
				response.getWriter().println(0);
			} else {
				response.getWriter().println(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void showCourseList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AdminService adminService = new AdminService();
			String collegeName = request.getParameter("collegeName");
			String teacherName = request.getParameter("teacherName");
			List<Course> list = adminService.showCourseList(collegeName, teacherName);
			request.setAttribute("list", list);
			request.getRequestDispatcher("admin/courseList.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void updateCourseUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int cid = Integer.parseInt(request.getParameter("cid"));
			AdminService adminService = new AdminService();
			Course course = adminService.findCourse(cid);
			request.setAttribute("course", course);
			request.getRequestDispatcher("admin/updateCourse.jsp").forward(request, response);;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void updateCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int cid = Integer.parseInt(request.getParameter("cid"));
			String cname = request.getParameter("cname");
			String tuname = request.getParameter("tuname");
			int coid = Integer.parseInt(request.getParameter("coid"));
			String introduction = request.getParameter("introduction");
			AdminService adminService = new AdminService();
			adminService.updateCourse(cid, cname, tuname, coid, introduction);
			showCourseList(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void delCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int cid = Integer.parseInt(request.getParameter("cid"));
			AdminService adminService = new AdminService();
			adminService.delCourse(cid);
			showCourseList(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void addCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int cid = Integer.parseInt(request.getParameter("cid"));
			String cname = request.getParameter("cname");
			String tuname = request.getParameter("tuname");
			int coid = Integer.parseInt(request.getParameter("coid"));
			String introduction = request.getParameter("introduction");
			AdminService adminService = new AdminService();
			adminService.addCourse(cid, cname, introduction, tuname, coid);
			showCourseList(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void showTeacherList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AdminService adminService = new AdminService();
			List<Teacher> list = adminService.showTeacherList();
			request.setAttribute("list", list);
			request.getRequestDispatcher("admin/teacherList.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void updateTeacherUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			AdminService adminService = new AdminService();
			Teacher teacher = adminService.findTeacher(username);
			request.setAttribute("teacher", teacher);
			request.getRequestDispatcher("admin/updateTeacher.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void updateStudentUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			AdminService adminService = new AdminService();
			Student student = adminService.findStudent(username);
			request.setAttribute("student", student);
			request.getRequestDispatcher("admin/updateStudent.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void updateTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			String realname = request.getParameter("realname");
			String position = request.getParameter("position");
			String introduction = request.getParameter("introduction");
			int coid = Integer.parseInt(request.getParameter("coid"));
			AdminService adminService = new AdminService();
			adminService.updateTeacher(username, realname, position, introduction, coid);
			showTeacherList(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	protected void updateStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			String realname = request.getParameter("realname");
			String password = request.getParameter("password");
			AdminService adminService = new AdminService();
			adminService.updateStudent(username, realname, password);
			showStudentList(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void addTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			String realname = request.getParameter("realname");
			String position = request.getParameter("position");
			String introduction = request.getParameter("introduction");
			int coid = Integer.parseInt(request.getParameter("coid"));
			AdminService adminService = new AdminService();
			adminService.addTeacher(username, realname, position, introduction, coid);
			showTeacherList(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void addStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			String realname = request.getParameter("realname");
			String password = request.getParameter("password");
			AdminService adminService = new AdminService();
			adminService.addStudent(username,password,realname);
			showStudentList(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	protected void importStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 检测是否为多媒体上传
		if (!ServletFileUpload.isMultipartContent(request)) {
			// 如果不是则停止
			PrintWriter writer = response.getWriter();
			writer.println("Error: 表单必须包含 enctype=multipart/form-data");
			writer.flush();
			return;
		}

		// 配置上传参数
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// 设置临时存储目录
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// 设置最大文件上传值
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// 设置最大请求值 (包含文件和表单数据)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// 中文处理
		upload.setHeaderEncoding("UTF-8");

		// 构造临时路径来存储上传的文件
		// 这个路径相对当前应用的目录
		String uploadPath = request.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;


		// 如果目录不存在则创建
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		try {
			// 解析请求的内容提取文件数据
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(request);

			if (formItems != null && formItems.size() > 0) {
				// 迭代表单数据
				for (FileItem item : formItems) {
					// 处理不在表单中的字段
					if (!item.isFormField()) {
						String fileName = new File(item.getName()).getName();
						String filePath = uploadPath + File.separator + fileName;
						File storeFile = new File(filePath);
						// 在控制台输出文件的上传路径
						System.out.println(filePath);
						// 保存文件到硬盘
						item.write(storeFile);
						 importDataToDb(filePath);
					}
				}
			}
		} catch (Exception ex) {
			PrintWriter writer = response.getWriter();
			writer.println("Error:"+ex.getMessage());
			writer.flush();
		}

	}


	private int importDataToDb(String filePath){
		try {
			XSSFWorkbook wookbook = new XSSFWorkbook(new FileInputStream(filePath));
			XSSFSheet sheet = wookbook.getSheet("Sheet1");
			int rows = sheet.getPhysicalNumberOfRows();
			int cnt = 0;
			for (int i = 1; i < rows; i++) {
				XSSFRow row = sheet.getRow(i);
				if (row != null) {
					int cells = row.getPhysicalNumberOfCells();
					String value = "";
					for (int j = 0; j < cells; j++) {
						XSSFCell cell = row.getCell(j);
						if (cell != null) {
							switch (cell.getCellType()) {
								case Cell.CELL_TYPE_FORMULA: break;
								case HSSFCell.CELL_TYPE_NUMERIC:
									value += String.valueOf(cell.getNumericCellValue()) + ",";break;
								case HSSFCell.CELL_TYPE_STRING: value += cell.getStringCellValue() + ",";break;
								default: value += "";break;
							}
						}
					}
					String[] val = value.split(",");
					Connection conn = JDBCUtil.getConnection();
					String sql = "insert into student values(?, ?, ?)";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, val[0]);
					ps.setString(2, val[1]);
					ps.setString(3, val[2]);
					cnt += ps.executeUpdate();
					JDBCUtil.release(conn, ps);

				}
			}
			return cnt;
		}catch (Exception e){
			e.getStackTrace();
		}
	return 0;
}

	protected void delStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			AdminService adminService = new AdminService();
			adminService.delStudent(username);
			showStudentList(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void delTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			AdminService adminService = new AdminService();
			adminService.delTeacher(username);
			showTeacherList(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void showTeacherCourseList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			AdminService adminService = new AdminService();
			List<Course> list = adminService.showTeacherCourseList(username);
			request.setAttribute("list", list);
			request.getRequestDispatcher("admin/courseList.jsp").forward(request, response);;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void showCollegeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AdminService adminService = new AdminService();
			List<College> list = adminService.showCollegeList();
			request.setAttribute("list", list);
			request.getRequestDispatcher("admin/collegeList.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void updateCollege(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int coid = Integer.parseInt(request.getParameter("coid"));
			String coname = request.getParameter("coname");
			AdminService adminService = new AdminService();
			adminService.updateCollege(coid, coname);
			showCollegeList(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void addCollege(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int coid = Integer.parseInt(request.getParameter("coid"));
			String coname = request.getParameter("coname");
			AdminService adminService = new AdminService();
			adminService.addCollege(coid, coname);
			showCollegeList(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void delCollege(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int coid = Integer.parseInt(request.getParameter("coid"));
			AdminService adminService = new AdminService();
			adminService.delCollege(coid);
			showCollegeList(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void showMsgList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String courseName = request.getParameter("courseName");
			String teacherName = request.getParameter("teacherName");
			String keywords = request.getParameter("keywords");
			AdminService adminService = new AdminService();
			List<Message> list = adminService.showMsgList(courseName, teacherName, keywords);
			request.setAttribute("list", list);
			request.getRequestDispatcher("admin/msgList.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void updateMsgUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int mid = Integer.parseInt(request.getParameter("mid"));
			StudentService studentService = new StudentService();
			Message message = studentService.findMessageById(mid);
			request.setAttribute("message", message);
			request.getRequestDispatcher("admin/updateMsg.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void updateMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int mid = Integer.parseInt(request.getParameter("mid"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			AdminService adminService = new AdminService();
			boolean isSuccess = adminService.updateMsg(mid, title, content);
			if (isSuccess) {
				showMsgList(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void delMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int mid = Integer.parseInt(request.getParameter("mid"));
			AdminService adminService = new AdminService();
			boolean isSuccess = adminService.delMsg(mid);
			if (isSuccess) {
				showMsgList(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void showAnswerList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AdminService adminService = new AdminService();
			List<Answer> list = adminService.showAnswerList();
			request.setAttribute("list", list);
			request.getRequestDispatcher("admin/answerList.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void updateAnswer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int aid = Integer.parseInt(request.getParameter("aid"));
			String content = request.getParameter("content");
			AdminService adminService = new AdminService();
			adminService.updateAnswer(aid, content);
			showAnswerList(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void delAnswer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int aid = Integer.parseInt(request.getParameter("aid"));
			AdminService adminService = new AdminService();
			adminService.delAnswer(aid);
			showAnswerList(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
