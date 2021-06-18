package controller;
 
import model.CourseModel_Interface;
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
import dto.CourseDTO;
import exception.ApplicationException;
 
/**
 * Course List functionality Controller. Performs operation for list, search
 * and delete operations of Course
 *  
 * @author SUNRAYS Technologies
 * @version 1.0
 * 
 */
 
@WebServlet("/controller/CourseList_Controller")
public class CourseList_Controller extends Base_Controller {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(CourseList_Controller.class);
 
    @Override
    protected BaseDTO populateDTO(HttpServletRequest request) {
        CourseDTO dto = new CourseDTO();
 
        dto.setName(DataUtility.getString(request.getParameter("name")));
        dto.setDuration(DataUtility.getLong(request.getParameter("duration")));
        dto.setDescription(DataUtility.getString(request.getParameter("description")));
        
        
        return dto;
    }
 
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        log.info("CourseListCtl.doGet Start");
 
        List<Object> list = null;
 
        int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
 
        pageNo = (pageNo == 0) ? 1 : pageNo;
 
        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
                .getValue("page.size")) : pageSize;
 
        CourseDTO dto = (CourseDTO) populateDTO(request);
 
        String op = DataUtility.getString(request.getParameter("operation"));
 
        CourseModel_Interface model = ModelFactory.getInstance().getCourseModel();
 
        try {
 
            if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op)
                    || "Previous".equalsIgnoreCase(op)) {
 
                if (OP_SEARCH.equalsIgnoreCase(op)) {
                    pageNo = 1;
                } else if (OP_NEXT.equalsIgnoreCase(op)) {
                    pageNo++;
                } else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
                    pageNo--;
                }
 
            }else if (OP_NEW.equalsIgnoreCase(op)) {
                ServletUtility.redirect(ORSView.COURSE_CTL, request,
                        response);
                return;
            } else if (OP_DELETE.equalsIgnoreCase(op)) {
                pageNo = 1;
                String[] ids = request.getParameterValues("ids");
                System.out.println("ids "+ids);
                if (ids != null && ids.length != 0) {
                	CourseDTO deletedDto = new CourseDTO();
                    for (String id : ids) {
                        deletedDto.setId(DataUtility.getLong(id));
                        model.delete(deletedDto);
                        ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
                    }
                } else {
                    ServletUtility.setErrorMessage("Select at least one record", request);
                }
            }
            System.out.println(dto.getDuration());
            list = model.search(dto, pageNo, pageSize);
            ServletUtility.setList(list, request);
            if (list == null || list.size() == 0) {
                ServletUtility.setErrorMessage("No record found ", request);
            }
            ServletUtility.setList(list, request);
            ServletUtility.setDto(dto, request);
            ServletUtility.setPageNo(pageNo, request);
            ServletUtility.setPageSize(pageSize, request);
            ServletUtility.forward(ORSView.COURSE_LIST_VIEW, request, response);
 
        } catch (ApplicationException e) {
            log.error(e);
            ServletUtility.handleException(e, request, response);
            return;
        }
        log.info("CourseListCtl doGet End");
    }
 
    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doGet(req, resp);
	}
    
    
    @Override
    protected String getView() {
        return ORSView.COURSE_LIST_VIEW;
    }
}