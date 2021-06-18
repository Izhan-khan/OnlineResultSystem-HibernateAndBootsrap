package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dto.BaseDTO;
import dto.MarksheetDTO;
import dto.StudentDTO;
import exception.ApplicationException;
import exception.DatabaseException;
import exception.DuplicateRecordException;
import exception.RecordNotFoundException;
import model.MarksheetModel_Interface;
import model.ModelFactory;
import model.StudentModel_Interface;
import utility.DataUtility;
import utility.DataValidator;
import utility.PropertyReader;
import utility.ServletUtility;

/**
 * Marksheet functionality Controller. Performs operation for add, update,
 * delete and get Marksheet
 *
 * @author SunilOS
 * @version 1.0
 * 
 */
@WebServlet("/controller/Marksheet_Controller")
public class Marksheet_Controller extends Base_Controller {

	private static Logger log = Logger.getLogger(Marksheet_Controller.class);

	@Override
	protected void preload(HttpServletRequest request) {
		StudentModel_Interface model = ModelFactory.getInstance().getStudentModel();
		try {
			List l = model.list();
			request.setAttribute("studentList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}

	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("MarksheetCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("rollNo"))) {
			ServletUtility.setErrorMessage(PropertyReader.getValue("error.require", "Roll Number"),request);
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("physics"))) {
			ServletUtility.setErrorMessage(PropertyReader.getValue("error.require", "Physics marks"), request);
			pass = false;
		}
		if (DataValidator.isNotNull(request.getParameter("physics"))
				&& !DataValidator.isInteger(request.getParameter("physics"))) {
			ServletUtility.setErrorMessage(PropertyReader.getValue("error.integer", "Physics marks"), request);
			pass = false;
		}
		if (DataUtility.getInt(request.getParameter("physics")) > 100) {
			ServletUtility.setErrorMessage("Physics marks can not be greater than 100", request);
			pass = false;
		}
		if (DataUtility.getInt(request.getParameter("physics")) < 0) {
			ServletUtility.setErrorMessage("Physics marks can not be negative", request);
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("chemistry"))) {
			ServletUtility.setErrorMessage(PropertyReader.getValue("error.require", "Chemistry marks"), request);
			pass = false;
		}
		if (DataValidator.isNotNull(request.getParameter("chemistry"))
				&& !DataValidator.isInteger(request.getParameter("chemistry"))) {
			ServletUtility.setErrorMessage(PropertyReader.getValue("error.integer", "Chemistry marks"), request);
			pass = false;
		}
		if (DataUtility.getInt(request.getParameter("chemistry")) > 100) {
			ServletUtility.setErrorMessage("Chemistry marks can not be greater than 100", request);
			pass = false;
		}
		if (DataUtility.getInt(request.getParameter("chemistry")) < 0) {
			ServletUtility.setErrorMessage("Chemistry marks can not be negative", request);
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("maths"))) {
			ServletUtility.setErrorMessage(PropertyReader.getValue("error.require", "Maths marks"), request);
			pass = false;
		}
		if (DataValidator.isNotNull(request.getParameter("maths"))
				&& !DataValidator.isInteger(request.getParameter("maths"))) {
			ServletUtility.setErrorMessage(PropertyReader.getValue("error.integer", "Maths marks"), request);
			pass = false;
		}
		if (DataUtility.getInt(request.getParameter("maths")) > 100) {
			ServletUtility.setErrorMessage("Maths marks can not be greater than 100", request);
			pass = false;
		}
		if (DataUtility.getInt(request.getParameter("maths")) < 0) {
			ServletUtility.setErrorMessage("Maths marks can not be negative", request);
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("studentId"))) {
			request.setAttribute("studentId", PropertyReader.getValue("error.require", "Student Name"));
			pass = false;
		} else if (request.getParameter("studentId").equalsIgnoreCase("0")) {
			ServletUtility.setErrorMessage(PropertyReader.getValue("error.select", "Student"), request);
			pass = false;
		}

		log.debug("MarksheetCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("MarksheetCtl Method populatebean Started");

		MarksheetDTO bean = new MarksheetDTO();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setRollNo(DataUtility.getString(request.getParameter("rollNo")));

		/*
		 * Setting Student Name by its Primary Key
		 */

		bean.setStudentId(DataUtility.getInt(request.getParameter("studentId")));

		StudentDTO studentbean = null;
		StudentModel_Interface studentModel = ModelFactory.getInstance().getStudentModel();

		try {
			studentbean = studentModel.findByPK(DataUtility.getInt(request.getParameter("studentId")));
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(bean.getStudentId() != 0){
			bean.setName(studentbean.getName());
	
			bean.setPhysics(DataUtility.getInt(request.getParameter("physics")));
	
			bean.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));
	
			bean.setMaths(DataUtility.getInt(request.getParameter("maths")));
	
			bean.setStudentId(DataUtility.getLong(request.getParameter("studentId")));
		}
		
		populateGenericDTO(bean, request);

		log.debug("MarksheetCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("MarksheetCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		MarksheetModel_Interface model = ModelFactory.getInstance().getMarksheetModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0) {
			MarksheetDTO bean;
			try {
				bean = model.findByPK(id);
				ServletUtility.setDto(bean, request);
				System.out.println(bean.getName());
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("MarksheetCtl Method doGet Ended");
	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("MarksheetCtl Method doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		MarksheetModel_Interface model = ModelFactory.getInstance().getMarksheetModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			MarksheetDTO bean = (MarksheetDTO) populateDTO(request);
			System.out.println(id);
			if (id > 0) {
				try {
					model.update(bean);
					ServletUtility.setSuccessMessage("Data is successfully saved", request);
				} catch (ApplicationException e) {
					log.error(e);
				} catch (DuplicateRecordException e) {
					log.error(e);
				}
			} else {
				try {
					long pk = model.add(bean);
						bean.setId(pk);
						ServletUtility.setSuccessMessage("Data is successfully saved", request);
				} catch (ApplicationException e) {
					log.error(e);
					ServletUtility.setErrorMessage("ApplicationException", request);
				} catch (DuplicateRecordException e) {
					log.error(e);
					ServletUtility.setErrorMessage("Roll Number already exists", request);
				}

			}
			ServletUtility.setDto(bean, request);

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			MarksheetDTO bean = (MarksheetDTO) populateDTO(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("MarksheetCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		return ORSView.MARKSHEET_VIEW;
	}

}
