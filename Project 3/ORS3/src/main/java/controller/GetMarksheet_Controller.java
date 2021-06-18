package controller;
 
import model.MarksheetModel_Interface;
import model.ModelFactory;
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
import exception.ApplicationException;
 
/**
 * Get Marksheet functionality Controller. Performs operation for Get
 * Marksheet
 *  
 * @author SUNRAYS Technologies
 * @version 1.0
 * 
 */
@WebServlet("/controller/GetMarksheet_Controller")
public class GetMarksheet_Controller extends Base_Controller {
 
    private static Logger log = Logger.getLogger(GetMarksheet_Controller.class);
 
    @Override
    protected boolean validate(HttpServletRequest request) {
 
        log.info("GetMarksheetCTL Method validate Started");
 
        boolean pass = true;
 
        if (DataValidator.isNull(request.getParameter("rollNo"))) {
            request.setAttribute("rollNo",
                    PropertyReader.getValue("error.require", "Roll Number"));
            pass = false;
        }
 
        log.info("GetMarksheetCTL Method validate Ended");
 
        return pass;
    }
 
    @Override
    protected BaseDTO populateDTO(HttpServletRequest request) {
 
        log.info("GetMarksheetCtl Method populateDTO Started");
 
        MarksheetDTO dto = new MarksheetDTO();
 
        dto.setId(DataUtility.getLong(request.getParameter("id")));
 
        dto.setRollNo(DataUtility.getString(request.getParameter("rollNo")));
 
        dto.setName(DataUtility.getString(request.getParameter("name")));
 
        dto.setPhysics(DataUtility.getInt(request.getParameter("physics")));
 
        dto.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));
 
        dto.setMaths(DataUtility.getInt(request.getParameter("maths")));
 
        log.info("GetMarksheetCtl Method populateDTO Ended");
 
        return dto;
    }
 
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
        log.info("GetMarksheetCtl Method doGet Started");
 
        String op = DataUtility.getString(request.getParameter("operation"));
 
        // get model
        MarksheetModel_Interface model = ModelFactory.getInstance()
                .getMarksheetModel();
 
        MarksheetDTO dto = (MarksheetDTO) populateDTO(request);
 
        long id = DataUtility.getLong(request.getParameter("id"));
 
        if (OP_GO.equalsIgnoreCase(op)) {
 
            try {
                dto = model.findByRollNo(dto.getRollNo());
                if (dto != null) {
                    ServletUtility.setDto(dto, request);
                } else {
                    ServletUtility.setErrorMessage("RollNo Does Not exists",
                            request);
                }
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.setDto(dto, request);
                ServletUtility.handleException(e, request, response);
                return;
            }
 
        }
 
        ServletUtility.forward(ORSView.GET_MARKSHEET_VIEW, request, response);
 
        log.info("MarksheetCtl Method doGet Ended");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doPost(req, resp);
    }
 
    @Override
    protected String getView() {
        return ORSView.GET_MARKSHEET_VIEW;
    }
 
}