package controller;

import model.CourseModel_Interface;
import model.ModelFactory;
import model.SubjectModel_Interface;
import model.TimeTableModel_Interface;
import utility.DataUtility;
import utility.DataValidator;
import utility.PropertyReader;
import utility.ServletUtility;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dto.BaseDTO;
import dto.TimeTableDTO;
import exception.ApplicationException;
import exception.DuplicateRecordException;
import exception.RecordNotFoundException;

/**
 * TimeTable functionality Controller. Performs operation for add, update,
 * delete and get TimeTable
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * 
 */
@WebServlet("/controller/TimeTable_Controller")
public class TimeTable_Controller extends Base_Controller {

	private static Logger log = Logger.getLogger(TimeTable_Controller.class);

	@Override
	protected void preload(HttpServletRequest request) {
		CourseModel_Interface coursemodel = ModelFactory.getInstance().getCourseModel();
		SubjectModel_Interface subjectmodel = ModelFactory.getInstance().getSubjectModel();
		try {
			List courselist = coursemodel.list();
			List subjectlist = subjectmodel.list();
			request.setAttribute("courseList", courselist);
			request.setAttribute("subjectList", subjectlist);
		} catch (ApplicationException e) {
			log.error(e);
		}

	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.info("TimeTableCtl Method validate Started");

		boolean pass = true;

		String op = DataUtility.getString(request.getParameter("operation"));
		String email = request.getParameter("email");
		String dob = request.getParameter("dob");

		if (DataValidator.isNull(request.getParameter("examDate"))) {
			ServletUtility.setErrorMessage(  PropertyReader.getValue("error.select", "Exam Date"), request);
			pass = false;
		}
		if (request.getParameter("semester").equalsIgnoreCase("0")) {
			ServletUtility.setErrorMessage(  PropertyReader.getValue("error.select", "Semester"), request);
			pass = false;
		}

		if (request.getParameter("examTime").equalsIgnoreCase("0")) {
			ServletUtility.setErrorMessage(  PropertyReader.getValue("error.select", "Exam Time"), request);
			pass = false;
		}
		if (request.getParameter("courseId").equalsIgnoreCase("0")) {
			ServletUtility.setErrorMessage(  PropertyReader.getValue("error.select", "Course Id"), request);
			pass = false;
		}

		if (request.getParameter("subjectId").equalsIgnoreCase("0")) {
			ServletUtility.setErrorMessage(  PropertyReader.getValue("error.select", "Subject Id"), request);
			pass = false;
		}

		log.info("TimeTableCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.info("TimeTableCtl Method populateDTO Started");

		TimeTableDTO dto = new TimeTableDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setExamTime(DataUtility.getString(request.getParameter("examTime")));

		dto.setExamDate(DataUtility.getDate(request.getParameter("examDate")));

		dto.setCourseId(DataUtility.getLong(request.getParameter("courseId")));

		dto.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));

		dto.setSemester(DataUtility.getLong(request.getParameter("semester")));

		populateGenericDTO(dto, request);
		
		log.info("TimeTableCtl Method populateDTO Ended");

		return dto;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.info("TimeTableCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model

		TimeTableModel_Interface model = ModelFactory.getInstance().getTimeTableModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			TimeTableDTO dto = (TimeTableDTO) populateDTO(request);

			try {
				if (id > 0) {
					model.update(dto);
				} else {
					long pk = model.add(dto);
					dto.setId(pk);
				}

				ServletUtility.setDto(dto, request);
				ServletUtility.setSuccessMessage("Data is successfully saved", request);

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("TimeTable already exists", request);
			} catch (RecordNotFoundException e) {
				ServletUtility.setDto(dto, request);
				e.printStackTrace();
			}

		}

		else if (OP_DELETE.equalsIgnoreCase(op)) {

			TimeTableDTO dto = (TimeTableDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
				return;

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (RecordNotFoundException e) {
				ServletUtility.setDto(dto, request);
				e.printStackTrace();
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
			return;

		} else { // View page

			if (id > 0 || op != null) {
				TimeTableDTO dto;
				try {
					dto = model.findByPK(id);
					ServletUtility.setDto(dto, request);
				} catch (ApplicationException e) {
					log.error(e);
					ServletUtility.handleException(e, request, response);
					return;
				}
			}
		}

		ServletUtility.forward(ORSView.TIMETABLE_VIEW, request, response);

		log.info("TimeTableCtl Method doGet Ended");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);

	}

	@Override
	protected String getView() {
		return ORSView.TIMETABLE_VIEW;
	}

}