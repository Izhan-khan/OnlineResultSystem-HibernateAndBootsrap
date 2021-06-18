package controller;

/**
 * Contains ORS and Controller URI
 *
 * @author SUNRAYS Technologies
 * @version 1.0
 * 
 */
public interface ORSView {

	public String APP_CONTEXT = "/ORS3";

	public String PAGE_FOLDER = "/web-pages";

	public String IMAGE_FOLDER = "../images";

	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	public String ERROR_VIEW = PAGE_FOLDER + "/Error.jsp";

	public String MARKSHEET_VIEW = PAGE_FOLDER + "/Marksheet.jsp";
	public String MARKSHEET_LIST_VIEW = PAGE_FOLDER + "/MarksheetList.jsp";
	public String GET_MARKSHEET_VIEW = PAGE_FOLDER + "/GetMarksheet.jsp";
	public String USER_VIEW = PAGE_FOLDER + "/User.jsp";
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserList.jsp";
	public String COLLEGE_VIEW = PAGE_FOLDER + "/College.jsp";
	public String COLLEGE_LIST_VIEW = PAGE_FOLDER + "/CollegeList.jsp";
	public String STUDENT_VIEW = PAGE_FOLDER + "/Student.jsp";
	public String STUDENT_LIST_VIEW = PAGE_FOLDER + "/StudentList.jsp";
	public String ROLE_VIEW = PAGE_FOLDER + "/Role.jsp";
	public String ROLE_LIST_VIEW = PAGE_FOLDER + "/RoleList.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistration.jsp";
	public String LOGIN_VIEW = PAGE_FOLDER + "/Login.jsp";
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePassword.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfile.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPassword.jsp";
	public String MARKSHEET_MERIT_LIST_VIEW = PAGE_FOLDER + "/MarksheetMeritList.jsp";
	public String COURSE_VIEW = PAGE_FOLDER + "/Course.jsp";
	public String COURSE_LIST_VIEW = PAGE_FOLDER + "/CourseList.jsp";
	public String FACULTY_VIEW = PAGE_FOLDER + "/Faculty.jsp";
	public String FACULTY_LIST_VIEW = PAGE_FOLDER + "/FacultyList.jsp";
	public String SUBJECT_VIEW = PAGE_FOLDER + "/Subject.jsp";
	public String SUBJECT_LIST_VIEW = PAGE_FOLDER + "/SubjectList.jsp";
	public String TIMETABLE_VIEW = PAGE_FOLDER + "/TimeTable.jsp";
	public String TIMETABLE_LIST_VIEW = PAGE_FOLDER + "/TimeTableList.jsp";

	public String ERROR_CTL = "ORS3/web-pages/Error.jsp";

	public String Background_Image = IMAGE_FOLDER + "Bg.jpg";

	public String STYLESHEET = APP_CONTEXT + "/web-pages/StyleSheet.css";

	public String JASPER_CTL = APP_CONTEXT + "/controller/Jasper_Controller";
	public String MARKSHEET_CTL = APP_CONTEXT + "/controller/Marksheet_Controller";
	public String MARKSHEET_LIST_CTL = APP_CONTEXT + "/controller/MarksheetList_Controller";
	public String USER_CTL = APP_CONTEXT + "/controller/User_Controller";
	public String USER_LIST_CTL = APP_CONTEXT + "/controller/UserList_Controller";
	public String COLLEGE_CTL = APP_CONTEXT + "/controller/College_Controller";
	public String COLLEGE_LIST_CTL = APP_CONTEXT + "/controller/CollegeList_Controller";
	public String STUDENT_CTL = APP_CONTEXT + "/controller/Student_Controller";
	public String STUDENT_LIST_CTL = APP_CONTEXT + "/controller/StudentList_Controller";
	public String ROLE_CTL = APP_CONTEXT + "/controller/Role_Controller";
	public String ROLE_LIST_CTL = APP_CONTEXT + "/controller/RoleList_Controller";
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistration_Controller";
	public String LOGIN_CTL = APP_CONTEXT + "/Login_Controller";
	public String WELCOME_CTL = APP_CONTEXT + "/Welcome_Controller";
	public String LOGOUT_CTL = APP_CONTEXT + "/Login_Controller";
	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/controller/GetMarksheet_Controller";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/controller/ChangePassword_Controller";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/controller/MyProfile_Controller";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPassword_Controller";
	public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT + "/controller/MarksheetMeritList_Controller";
	public String COURSE_CTL = APP_CONTEXT + "/controller/Course_Controller";
	public String COURSE_LIST_CTL = APP_CONTEXT + "/controller/CourseList_Controller";
	public String FACULTY_CTL = APP_CONTEXT + "/controller/Faculty_Controller";
	public String FACULTY_LIST_CTL = APP_CONTEXT + "/controller/FacultyList_Controller";
	public String SUBJECT_CTL = APP_CONTEXT + "/controller/Subject_Controller";
	public String SUBJECT_LIST_CTL = APP_CONTEXT + "/controller/SubjectList_Controller";
	public String TIMETABLE_CTL = APP_CONTEXT + "/controller/TimeTable_Controller";
	public String TIMETABLE_LIST_CTL = APP_CONTEXT + "/controller/TimeTableList_Controller";

}
