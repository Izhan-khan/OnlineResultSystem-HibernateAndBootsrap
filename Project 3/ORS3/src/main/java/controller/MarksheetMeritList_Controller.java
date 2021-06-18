package controller;
 
import model.MarksheetModel_Interface;
import model.ModelFactory;
import utility.DataUtility;
import utility.PropertyReader;
import utility.ServletUtility;

import java.io.IOException;
import java.util.List;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.log4j.Logger;

import dto.BaseDTO;
import dto.MarksheetDTO;
import exception.ApplicationException;
 
/**
 * Marksheet Merit List functionality Controller. Performs operation for
 * Marksheet Merit List
 *  
 * @author SUNRAYS Technologies
 * @version 1.0
 * 
 */
@WebServlet("/controller/MarksheetMeritList_Controller")
public class MarksheetMeritList_Controller extends Base_Controller {
 
    public static final String OP_BACK = "Back";
 
    private static Logger log = Logger.getLogger(MarksheetMeritList_Controller.class);
 
    @Override
    protected BaseDTO populateDTO(HttpServletRequest request) {
        MarksheetDTO dto = new MarksheetDTO();
 
        return dto;
    }
 
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
        log.info("MarksheetMeritListCtl doGet Start");
 
        List list = null;
 
        int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
 
        pageNo = (pageNo == 0) ? 1 : pageNo;
 
        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
                .getValue("page.size")) : pageSize;
 
        MarksheetDTO dto = (MarksheetDTO) populateDTO(request);
 
        String op = DataUtility.getString(request.getParameter("operation"));
 
        MarksheetModel_Interface model = ModelFactory.getInstance()
                .getMarksheetModel();
 
        try {
 
            if (OP_BACK.equalsIgnoreCase(op)) {
                ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
                return;
            }
 
            list = model.getMeritList(pageNo, pageSize);
            ServletUtility.setList(list, request);
            if (list == null || list.size() == 0) {
                ServletUtility.setErrorMessage("No record found ", request);
            }
            ServletUtility.setList(list, request);
 
            ServletUtility.setPageNo(pageNo, request);
            ServletUtility.setPageSize(pageSize, request);
            ServletUtility.forward(ORSView.MARKSHEET_MERIT_LIST_VIEW, request,
                    response);
 
        } catch (ApplicationException e) {
            log.error(e);
            ServletUtility.handleException(e, request, response);
            return;
        }
 
        log.info("MarksheetMeritListCtl doGet End");
    }
 
    @Override
    protected String getView() {
        return ORSView.MARKSHEET_MERIT_LIST_VIEW;
    }
 
}
