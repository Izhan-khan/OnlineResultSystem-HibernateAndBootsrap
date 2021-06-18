package controller;
 
import model.ModelFactory;
import model.RoleModel_Interface;
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
 
import org.apache.log4j.Logger;

import dto.BaseDTO;
import dto.MarksheetDTO;
import dto.RoleDTO;
import exception.ApplicationException;
import exception.DuplicateRecordException;
 
/**
 * Role functionality Controller. Performs operation for add, update and
 * get Role
 *  
 * @author SUNRAYS Technologies
 * @version 1.0
 * 
 */
 @WebServlet("/controller/Role_Controller")
public class Role_Controller extends Base_Controller {
 
    private static final long serialVersionUID = 1L;
 
    private static Logger log = Logger.getLogger(Role_Controller.class);
 
    @Override
    protected boolean validate(HttpServletRequest request) {
 
        log.info("RoleCtl Method validate Started");
 
        boolean pass = true;
 
        if (DataValidator.isNull(request.getParameter("name"))) {
        	ServletUtility.setErrorMessage(PropertyReader.getValue("error.require", "Name"), request);
            pass = false;
        }
 
        if (DataValidator.isNull(request.getParameter("description"))) {
        	ServletUtility.setErrorMessage(PropertyReader.getValue("error.require", "Description"), request);
            pass = false;
        }
 
        log.info("RoleCtl Method validate Ended");
 
        return pass;
    }
 
    @Override
    protected BaseDTO populateDTO(HttpServletRequest request) {
 
        log.info("RoleCtl Method populateDTO Started");
 
        RoleDTO dto = new RoleDTO();
 
        dto.setId(DataUtility.getLong(request.getParameter("id")));
 
        dto.setName(DataUtility.getString(request.getParameter("name")));
 
        dto.setDescription(DataUtility.getString(request.getParameter("description")));
 
        populateGenericDTO(dto, request);
        
        log.info("RoleCtl Method populateDTO Ended");
 
        return dto;
    }
 
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        log.info("RoleCtl Method doGet Started");
 
        System.out.println("In do get");
 
        String op = DataUtility.getString(request.getParameter("operation"));
 
      System.out.println("operation"+op);
        
        // get model
        RoleModel_Interface model = ModelFactory.getInstance().getRoleModel();
 
        long id = DataUtility.getLong(request.getParameter("id"));
 
        if (OP_SAVE.equalsIgnoreCase(op)) {
 
            RoleDTO dto = (RoleDTO) populateDTO(request);
 
            try {
                if (id > 0) {
                    model.update(dto);
                } else {
                    long pk = model.add(dto);
                    dto.setId(pk);
                }
 
                ServletUtility.setDto(dto, request);
                ServletUtility.setSuccessMessage("Data is successfully saved",
                        request);
 
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            } catch (DuplicateRecordException e) {
                ServletUtility.setDto(dto, request);
                ServletUtility.setErrorMessage("Role already exists", request);
            }
 
        } else if (OP_DELETE.equalsIgnoreCase(op)) {
 
            RoleDTO dto = (RoleDTO) populateDTO(request);
            try {
                model.delete(dto);
                ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request,
                        response);
                return;
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }
 
        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
 
            ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request, response);
            return;
 
        } else { // View page
 
            if (id > 0 || op != null) {
                RoleDTO dto;
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
 
        ServletUtility.forward(ORSView.ROLE_VIEW, request, response);
 
        log.info("RoleCtl Method doGet Ended");
    }
 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    		doGet(req, resp);
    }
    
    @Override
    protected String getView() {
        return ORSView.ROLE_VIEW;
    }
 
}