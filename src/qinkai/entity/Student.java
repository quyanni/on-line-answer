package qinkai.entity;

public class Student {
	private String username;
	private String password;
	private String realname;
	private String questionname;//问题数
	public Student() {
		
	}

	//同名构造方法
	public Student(String username, String password, String realname,String questionname) {
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.questionname = questionname;
	}


	public Student(String username, String password, String realname) {
		this.username = username;
		this.password = password;
		this.realname = realname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	@Override
	public String toString() {
		return "Student [username=" + username + ", password=" + password + ", realname=" + realname + ",questionnum="+questionname+"]";
	}

	public String getQuestionname() {
		return questionname;
	}

	public void setQuestionname(String questionname) {
		this.questionname = questionname;
	}
}
