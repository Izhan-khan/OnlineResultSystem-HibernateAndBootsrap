/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.29
 * Generated at: 2021-05-16 16:50:24 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.web_002dpages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Iterator;
import dto.MarksheetDTO;
import java.util.List;
import controller.MarksheetList_Controller;
import controller.Marksheet_Controller;
import controller.UserRegistration_Controller;
import utility.HTMLUtility;
import java.util.HashMap;
import utility.ServletUtility;
import utility.DataUtility;
import controller.Login_Controller;
import controller.ORSView;

public final class MarksheetList_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("controller.MarksheetList_Controller");
    _jspx_imports_classes.add("utility.HTMLUtility");
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("dto.MarksheetDTO");
    _jspx_imports_classes.add("java.util.Iterator");
    _jspx_imports_classes.add("utility.ServletUtility");
    _jspx_imports_classes.add("controller.Marksheet_Controller");
    _jspx_imports_classes.add("controller.UserRegistration_Controller");
    _jspx_imports_classes.add("java.util.HashMap");
    _jspx_imports_classes.add("controller.Login_Controller");
    _jspx_imports_classes.add("controller.ORSView");
    _jspx_imports_classes.add("utility.DataUtility");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!doctype html>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- StyleSheet Link -->\r\n");
      out.write("<link href=\"");
      out.print(ORSView.STYLESHEET);
      out.write("\" rel=\"stylesheet\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      dto.MarksheetDTO dto = null;
      dto = (dto.MarksheetDTO) _jspx_page_context.getAttribute("dto", javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      if (dto == null){
        dto = new dto.MarksheetDTO();
        _jspx_page_context.setAttribute("dto", dto, javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      }
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<title></title>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div class=\"container\" id=\"container\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t<div id=\"header\">\r\n");
      out.write("\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "Header.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t<div class=\"ty-auto flex-column h-90vh\" id=\"App_Body\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t<form action=\"");
      out.print(ORSView.MARKSHEET_LIST_CTL);
      out.write("\" method=\"post\" class=\"d-flex flex-column w-100 mx-3 my-5 justify-content-evenly\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<div class=\"ty-auto m-2 form-block text-white text-center w-100 flex-column\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<h1 class=\"mb-4\">Marksheet List</h1>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t");

						if (ServletUtility.getErrorMessage(request) != "") {
					
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<div class=\"alert alert-danger alert-dismissible fade show text-center\" role=\"alert\">\r\n");
      out.write("\t\t\t\t\t\t");
      out.print(ServletUtility.getErrorMessage(request));
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t");

						}
						if (ServletUtility.getSuccessMessage(request) != "") {
					
      out.write("\r\n");
      out.write("\t\t\t\t\t<div class=\"alert alert-success alert-dismissible fade show text-center\" role=\"alert\">\r\n");
      out.write("\t\t\t\t\t\t");
      out.print(ServletUtility.getSuccessMessage(request));
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t");

						}
					
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<div class=\"d-flex flex-wrap justify-content-evenly\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<div class=\"text-white \">\r\n");
      out.write("\t\t\t\t\t\t\t<label for=\"rollNo\">Roll-Number </label>\r\n");
      out.write("\t\t\t\t\t\t\t<input name=\"rollNo\" type=\"text\" class=\"form-control\" placeholder=\"Enter Roll-Number\" id=\"rollNo\" value=\"");
      out.print(DataUtility.getStringData(dto.getRollNo()));
      out.write("\">\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t\t<font style=\"color: red;\">");
      out.print(ServletUtility.getMessage("rollNo", request));
      out.write("</font>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<div class=\" text-white \">\r\n");
      out.write("\t\t\t\t\t\t\t<label for=\"name\">Student Name</label>\r\n");
      out.write("\t\t\t\t\t\t\t<input name=\"name\" type=\"text\" class=\"form-control\" placeholder=\"Enter Student Name\" id=\"name\" value=\"");
      out.print(DataUtility.getStringData(dto.getName()));
      out.write("\">\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t\t<font style=\"color: red;\">");
      out.print(ServletUtility.getMessage("name", request));
      out.write("</font>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<div class=\"\">\r\n");
      out.write("\t\t\t\t\t\t\t<button type=\"submit\" class=\"btn btn-outline-primary btn-block my-4 \" name=\"operation\" value=\"");
      out.print(Marksheet_Controller.OP_SEARCH);
      out.write("\">Search</button>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"\">\r\n");
      out.write("\t\t\t\t\t\t\t<button type=\"reset\" class=\"btn btn-outline-light btn-block my-4 \" name=\"operation\" value=\"Reset\">Reset</button>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<!-- Hidden Fields -->\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<input type=\"hidden\" name=\"id\" value=\"");
      out.print(dto.getId());
      out.write("\">\r\n");
      out.write("\t\t\t\t\t<input type=\"hidden\" name=\"createdBy\" value=\"");
      out.print(dto.getCreatedBy());
      out.write("\">\r\n");
      out.write("\t\t\t\t\t<input type=\"hidden\" name=\"modifiedBy\" value=\"");
      out.print(dto.getModifiedBy());
      out.write("\">\r\n");
      out.write("\t\t\t\t\t<input type=\"hidden\" name=\"createdDatetime\" value=\"");
      out.print(dto.getCreatedDatetime());
      out.write("\">\r\n");
      out.write("\t\t\t\t\t<input type=\"hidden\" name=\"modifiedDatetime\" value=\"");
      out.print(dto.getModifiedDatetime());
      out.write("\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t");

						int pageNo = ServletUtility.getPageNo(request);
						int pageSize = ServletUtility.getPageSize(request);

						List list = ServletUtility.getList(request);

						if (list != null && list.size() != 0) {
							Iterator it = list.iterator();
					
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<div class=\"table-responsive\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<table class=\"table table-fill  table-striped my-5\">\r\n");
      out.write("\t\t\t\t\t\t\t<thead class=\"table-dark\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<th class=\"\" scope=\"col\">Select</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<th class=\"\" scope=\"col\">Roll-Number</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<th class=\"\" scope=\"col\">Name</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<th class=\"\" scope=\"col\">Student-Id</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<th class=\"\" scope=\"col\">Physics</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<th class=\"\" scope=\"col\">Chemistry</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<th class=\"\" scope=\"col\">Maths</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<th class=\"\" scope=\"col\">Update</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t</thead>\r\n");
      out.write("\t\t\t\t\t\t\t<tbody class=\"table-light\">\r\n");
      out.write("\t\t\t\t\t\t\t\t");

									while (it.hasNext()) {
											dto = (MarksheetDTO) it.next();
								
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td align=\"center\"><input type=\"checkbox\" name=\"ids\" value=\"");
      out.print(dto.getId());
      out.write("\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td class=\"\">");
      out.print(dto.getRollNo());
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td class=\"\">");
      out.print(dto.getName());
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td class=\"\">");
      out.print(dto.getStudentId());
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td class=\"\">");
      out.print(dto.getPhysics());
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td class=\"\">");
      out.print(dto.getChemistry());
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td class=\"\">");
      out.print(dto.getMaths());
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td class=\"\"><a href=\"");
      out.print(ORSView.MARKSHEET_CTL);
      out.write("?id=");
      out.print(dto.getId());
      out.write("\">Edit</a></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t");

									}
									}
								
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t</tbody>\r\n");
      out.write("\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<table width=\"100%\">\r\n");
      out.write("\t\t\t\t\t<tr class=\"d-flex flex-wrap justify-content-around w-100\">\r\n");
      out.write("\t\t\t\t\t\t<td><input type=\"submit\" name=\"operation\" class=\"btn btn-outline-light btn-md m-2\" style=\"font-size: 17px\" value=\"");
      out.print(MarksheetList_Controller.OP_PREVIOUS);
      out.write("\"\r\n");
      out.write("\t\t\t\t\t\t\t\t");
      out.print(pageNo > 1 ? "" : "disabled");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t></td>\r\n");
      out.write("\t\t\t\t\t\t<td><input type=\"submit\" name=\"operation\" class=\"btn btn-outline-light btn-md m-2\" style=\"font-size: 17px\" value=\"");
      out.print(MarksheetList_Controller.OP_NEW);
      out.write("\"></td>\r\n");
      out.write("\t\t\t\t\t\t<td><input type=\"submit\" name=\"operation\" class=\"btn btn-outline-light btn-md m-2\" style=\"font-size: 17px\" value=\"");
      out.print(MarksheetList_Controller.OP_DELETE);
      out.write("\"></td>\r\n");
      out.write("\t\t\t\t\t\t<td><input type=\"submit\" name=\"operation\" class=\"btn btn-outline-light btn-md m-2\" style=\"font-size: 17px\" value=\"");
      out.print(MarksheetList_Controller.OP_NEXT);
      out.write("\"\r\n");
      out.write("\t\t\t\t\t\t\t\t");
      out.print((list.size() >= pageSize) ? "" : "disabled");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr></tr>\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t<div id=\"footer\">\r\n");
      out.write("\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "Footer.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
