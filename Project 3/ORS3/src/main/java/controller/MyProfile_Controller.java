package controller;

import model.ModelFactory;
import model.UserModel_Interface;
import utility.DataUtility;
import utility.DataValidator;
import utility.PropertyReader;
import utility.ServletUtility;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import dto.BaseDTO;
import dto.UserDTO;
import exception.ApplicationException;
import exception.DuplicateRecordException;

/**
 * My Profile functionality Controller. Performs operation for update your
 * Profile
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * 
 */
@WebServlet("/controller/MyProfile_Controller")
public class MyProfile_Controller extends Base_Controller {

	public static final String OP_CHANGE_MY_PASSWORD = "ChangePassword";

	private static Logger log = Logger.getLogger(MyProfile_Controller.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.info("MyProfileCtl Method validate Started");

		boolean pass = true;

		String op = DataUtility.getString(request.getParameter("operation"));

		if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op) || op == null) {

			return pass;
		}

		if (DataValidator.isNull(request.getParameter("firsName"))) {
			System.out.println("firstName" + request.getParameter("firstName"));
			request.setAttribute("firstName", PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.require", "MobileNo"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date Of Birth"));
			pass = false;
		}

		log.info("MyProfileCtl Method validate Ended");

		return pass;

	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		log.info("MyProfileCtl Method populateDTO Started");

		UserDTO dto = new UserDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setLogin(DataUtility.getString(request.getParameter("login")));

		dto.setName(DataUtility.getString(request.getParameter("firstName"))+" "+DataUtility.getString(request.getParameter("lastName")));

		dto.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));

		dto.setGender(DataUtility.getString(request.getParameter("gender")));

		dto.setDob(DataUtility.getDate(request.getParameter("dob")));

		populateGenericDTO(dto, request);
		
		return dto;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		log.info("MyprofileCtl Method doGet Started");

		UserDTO userdto = (UserDTO) session.getAttribute("user");
		long id = userdto.getId();
		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		UserModel_Interface model = ModelFactory.getInstance().getUserModel();

		if (OP_SAVE.equalsIgnoreCase(op)) {
			UserDTO dto = (UserDTO) populateDTO(request);
			try {
				if (id > 0) {
					userdto.setName(dto.getName());
					userdto.setGender(dto.getGender());
					userdto.setMobileNo(dto.getMobileNo());
					userdto.setDob(dto.getDob());
					model.update(userdto);

				}
				ServletUtility.setDto(dto, request);
				ServletUtility.setSuccessMessage("Profile has been updated successfully ", request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Login id already exists", request);
			}
		} else if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.CHANGE_PASSWORD_CTL, request, response);
			return;

		} else { // View page
			if (id > 0 || op != null) {
				System.out.println("in id > 0  condition");
				UserDTO dto;
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

		ServletUtility.forward(ORSView.MY_PROFILE_VIEW, request, response);

		log.info("MyProfileCtl Method doGet Ended");
	}

	@Override
	protected String getView() {
		return ORSView.MY_PROFILE_VIEW;
	}

}