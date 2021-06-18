package controller;
 
import model.ModelFactory;
import model.RoleModel_Interface;
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
import dto.RoleDTO;
import dto.UserDTO;
import exception.ApplicationException;
import exception.RecordNotFoundException;
 
/**
 * Login functionality Controller. Performs operation for Login
 *  
 *  
 * @author SUNRAYS Technologies
 * @version 1.0
 * 
 */

@WebServlet(name = "Login_Controller" ,urlPatterns = {"/Login_Controller"})
public class Login_Controller extends Base_Controller {
 
    private static final long serialVersionUID = 1L;
    public static final String OP_REGISTER = "Register";
    public static final String OP_SIGN_IN = "SignIn";
    public static final String OP_SIGN_UP = "SignUp";
    public static final String OP_LOG_OUT = "logout";
    private static Logger log = Logger.getLogger(Login_Controller.class);
 
    @Override
    protected boolean validate(HttpServletRequest request) {
 
        log.info("LoginCtl Method validate Started");
 
        boolean pass = true;
 
        String op = request.getParameter("operation");
        if (OP_SIGN_UP.equals(op) || OP_LOG_OUT.equals(op)) {
            return pass;
        }
 
        String login = request.getParameter("login");
 
        if (DataValidator.isNull(login)) {
            request.setAttribute("login",
                    PropertyReader.getValue("error.require", "Login Id"));
            pass = false;
        } else if (!DataValidator.isEmail(login)) {
            request.setAttribute("login",
                    PropertyReader.getValue("error.invalid", "Login Id"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("password"))) {
            request.setAttribute("password",
                    PropertyReader.getValue("error.require", "Password"));
            pass = false;
        }
 
        log.info("LoginCtl Method validate Ended");
 
        return pass;
    }
 
    @Override
    protected BaseDTO populateDTO(HttpServletRequest request) {
 
        log.info("LoginCtl Method populateDTO Started");
 
        UserDTO dto = new UserDTO();
 
        dto.setId(DataUtility.getLong(request.getParameter("id")));
        dto.setLogin(DataUtility.getString(request.getParameter("login")));
        dto.setPassword(DataUtility.getString(request.getParameter("password")));
 
        log.info("LoginCtl Method populateDTO Ended");
 
        return dto;
    }
 
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        log.info(" Method doGet Started");
 
        String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        UserModel_Interface model = ModelFactory.getInstance().getUserModel();
        RoleModel_Interface role = ModelFactory.getInstance().getRoleModel();
 
        long id = DataUtility.getLong(request.getParameter("id"));
 
        if (OP_SIGN_IN.equalsIgnoreCase(op)) {
 
            UserDTO dto = (UserDTO) populateDTO(request);
 
            try {
            	dto = model.authenticate(dto.getLogin(), dto.getPassword());
 
            	System.out.println("DTO "+dto);
            	
                if (dto != null) {
                    session.setAttribute("user", dto);
                    long rollId = dto.getRoleId();
 
                    RoleDTO roleDTO = role.findByPK(rollId);
                    
                    if (roleDTO != null) {
                        session.setAttribute("role", roleDTO);
                    }
                    
                    System.out.println(dto.getName());
                    
                    String URI =(String) session.getAttribute("URI");
                    
                    System.out.println("URI" +URI);
                    
                    if(URI != null && !URI.equalsIgnoreCase("null")) {
                    	response.sendRedirect(URI);
                    	return;
                    }else {
                    
                    ServletUtility.forward(ORSView.WELCOME_VIEW, request,
                            response);
                    return;
                    }
                } else {
                    dto = (UserDTO) populateDTO(request);
                    ServletUtility.setDto(dto, request);
                }
 
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            } catch (RecordNotFoundException e) {
            	log.error(e);
                ServletUtility.setErrorMessage(
                        "Invalid LoginId And Password", request);	
            }
 
        } else if (OP_LOG_OUT.equals(op)) {
 
            session = request.getSession();
 
            session.invalidate();
            
            request.setAttribute("logoutMessage", "Logout Successfull");
 
            ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);
 
            return;
 
        } else if (OP_SIGN_UP.equalsIgnoreCase(op)) {
 
            ServletUtility.redirect(ORSView.USER_REGISTRATION_CTL, request,
                    response);
            return;
 
        }
 
        else { // View page
 
            if (id > 0 || op != null) {
                UserDTO userDTO;
                try {
                    userDTO = model.findByPK(id);
                    ServletUtility.setDto(userDTO, request);
                } catch (ApplicationException e) {
                    log.error(e);
                    ServletUtility.handleException(e, request, response);
                    return;
                }
            }
        }
        ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);
 
        log.info("LoginCtl Method doGet Ended");
    
    
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	doPost(req, resp);
    }
 
    @Override
    protected String getView() {
        return ORSView.LOGIN_VIEW;
    }
 
}