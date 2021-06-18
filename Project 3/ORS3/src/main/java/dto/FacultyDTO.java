package dto;

import java.util.Date;
/**
 * Faculty DTO classes
 *
 * @author SUNRAYS Technologies
 * @version 1.0
 * 
 *
 */

public class FacultyDTO extends BaseDTO{

	
	private static final long serialVersionUID = 1L;
	/**
     * Name of Faculty
     */
	private String name;
	/**
     * Faculty Qualification
     */
	private String qualification;
	/**
     * Faculty Emai;
     */
	private String email;
	/**
     * Faculty Mobile Number
     */
	private String mobileNo;
	/**
     * Faculty Gender
     */
	private String gender;
	/**
     * Faculty DOB
     */
	private Date dob;
	/**
     * Faculty's College Id
     */
	private long collegeId;
	/**
     * Faculty's Course Id
     */
	private long courseId;
	/**
     * Faculty's Subject Id
     */
	private long subjectId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getKey() {
		return id+"";
	}
	public String getValue() {
		return name;
	}
}
