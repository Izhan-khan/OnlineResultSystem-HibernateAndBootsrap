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
 * Marksheet List functionality Controller. Performs operation for list, search
 * and delete operations of Marksheet
 *  
 * @author SUNRAYS Technologies
 * @version 1.0
 * 
 */
@WebServlet("/controller/MarksheetList_Controller")
public class MarksheetList_Controller extends Base_Controller {
 
    private static Logger log = Logger.getLogger(MarksheetList_Controller.class);
 
    @Override
    protected BaseDTO populateDTO(HttpServletRequest request) {
        MarksheetDTO dto = new MarksheetDTO();
 
        dto.setRollNo(DataUtility.getString(request.getParameter("rollNo")));
 
        dto.setName(DataUtility.getString(request.getParameter("name")));
 
        return dto;
    }
 
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
        log.info("MarksheetListCtl doGet Start");
 
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
 
            if (OP_SEARCH.equalsIgnoreCase(op) || OP_NEXT.equalsIgnoreCase(op)
                    || OP_PREVIOUS.equalsIgnoreCase(op)) {
 
                if (OP_SEARCH.equalsIgnoreCase(op)) {
                    pageNo = 1;
                } else if (OP_NEXT.equalsIgnoreCase(op)) {
                    pageNo++;
                } else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
                    pageNo--;
                }
 
            } else if (OP_NEW.equalsIgnoreCase(op)) {
                ServletUtility.redirect(ORSView.MARKSHEET_CTL, request,
                        response);
                return;
            } else if (OP_DELETE.equalsIgnoreCase(op)) {
                pageNo = 1;
                String[] ids = request.getParameterValues("ids");
                if (ids != null && ids.length != 0) {
                    MarksheetDTO deletedDto = new MarksheetDTO();
                    for (String id : ids) {
                    	System.out.println("id "+id);
                        deletedDto.setId(DataUtility.getLong(id));
                        model.delete(deletedDto);
                        ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
                    }
                } else {
                    ServletUtility.setErrorMessage(
                            "Select at least one record", request);
                }
            }
            list = model.search(dto, pageNo, pageSize);
            ServletUtility.setList(list, request);
            if (list == null || list.size() == 0) {
                ServletUtility.setErrorMessage("No record found ", request);
            }
            ServletUtility.setList(list, request);
            ServletUtility.setDto(dto, request);
            ServletUtility.setPageNo(pageNo, request);
            ServletUtility.setPageSize(pageSize, request);
            ServletUtility.forward(ORSView.MARKSHEET_LIST_VIEW, request,
                    response);
 
        } catch (ApplicationException e) {
            log.error(e);
            ServletUtility.handleException(e, request, response);
            return;
        }
 
        log.info("MarksheetListCtl doGet End");
    }
 
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doGet(req, resp);
    }
 
    
    @Override
    protected String getView() {
        return ORSView.MARKSHEET_LIST_VIEW;
    }
 
}