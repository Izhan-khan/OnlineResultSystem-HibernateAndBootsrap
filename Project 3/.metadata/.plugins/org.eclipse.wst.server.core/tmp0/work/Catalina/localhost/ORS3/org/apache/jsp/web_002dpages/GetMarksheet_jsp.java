/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.29
 * Generated at: 2021-05-16 16:50:09 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.web_002dpages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import controller.GetMarksheet_Controller;
import controller.ChangePassword_Controller;
import controller.MyProfile_Controller;
import controller.UserRegistration_Controller;
import utility.HTMLUtility;
import java.util.HashMap;
import utility.ServletUtility;
import utility.DataUtility;
import controller.Login_Controller;
import controller.ORSView;

public final class GetMarksheet_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_classes.add("utility.HTMLUtility");
    _jspx_imports_classes.add("controller.GetMarksheet_Controller");
    _jspx_imports_classes.add("controller.MyProfile_Controller");
    _jspx_imports_classes.add("utility.ServletUtility");
    _jspx_imports_classes.add("controller.ChangePassword_Controller");
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
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
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
      out.write("<title></title>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div class=\"container\" id=\"container\">\r\n");
      out.write("\t\t<div id=\"header\">\r\n");
      out.write("\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "Header.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t");

			if (ServletUtility.getErrorMessage(request) != "") {
		
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t<div class=\"alert alert-danger alert-dismissible fade show text-center\" role=\"alert\">\r\n");
      out.write("\t\t\t");
      out.print(ServletUtility.getErrorMessage(request));
      out.write("\r\n");
      out.write("\t\t\t<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t");

			}
			if (ServletUtility.getSuccessMessage(request) != "") {
		
      out.write("\r\n");
      out.write("\t\t<div class=\"alert alert-success alert-dismissible fade show text-center\" role=\"alert\">\r\n");
      out.write("\t\t\t");
      out.print(ServletUtility.getSuccessMessage(request));
      out.write("\r\n");
      out.write("\t\t\t<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t");

			}
		
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t<div class=\"d-flex flex-column justify-content-around\" id=\"App_Body\" style=\"margin-bottom: 15vh;\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t<div id=\"login_backgound\">\r\n");
      out.write("\t\t\t\t<div class=\"form-block text-white \">\r\n");
      out.write("\t\t\t\t\t<div class=\"text-center my-4 \">\r\n");
      out.write("\t\t\t\t\t\t<h3>Get Marksheet</h3>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<form action=\"");
      out.print(ORSView.GET_MARKSHEET_CTL);
      out.write("\" method=\"post\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<div class=\"form-group first text-white mb-0\">\r\n");
      out.write("\t\t\t\t\t\t\t<label for=\"username\">Enter Roll Number</label>\r\n");
      out.write("\t\t\t\t\t\t\t<input name=\"rollNo\" type=\"text\" class=\"form-control\" placeholder=\"Enter Roll Number\" id=\"rollNo\" value=\"");
      out.print(DataUtility.getStringData(dto.getRollNo()));
      out.write("\" required>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t\t<font style=\"color: red;\">");
      out.print(ServletUtility.getMessage("rollNo", request));
      out.write("</font>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<div class=\"d-grid gap-2 col-8 mx-auto mt-3 mb-5 text-nowrap\">\r\n");
      out.write("\t\t\t\t\t\t\t<button type=\"submit\" class=\"btn btn-outline-primary btn-block  \" name=\"operation\" value=\"");
      out.print(GetMarksheet_Controller.OP_GO);
      out.write("\">Get Marksheet</button>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t</form>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t");

				if (dto != null && dto.getRollNo() != null && dto.getPhysics() != null && dto.getMaths() != null
						&& dto.getChemistry() != null) {
			
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t<div class=\"alert alert-light\" role=\"alert\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<h3>\r\n");
      out.write("\t\t\t\t\tRoll Number :\r\n");
      out.write("\t\t\t\t\t");
      out.print(dto.getRollNo());
      out.write("</h3>\r\n");
      out.write("\t\t\t\t<h3>\r\n");
      out.write("\t\t\t\t\tPhysics :\r\n");
      out.write("\t\t\t\t\t");
      out.print(dto.getPhysics());
      out.write("</h3>\r\n");
      out.write("\t\t\t\t<h3>\r\n");
      out.write("\t\t\t\t\tChemistry :\r\n");
      out.write("\t\t\t\t\t");
      out.print(dto.getChemistry());
      out.write("</h3>\r\n");
      out.write("\t\t\t\t<h3>\r\n");
      out.write("\t\t\t\t\tMaths :\r\n");
      out.write("\t\t\t\t\t");
      out.print(dto.getMaths());
      out.write("</h3>\r\n");
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t");

				}
			
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t<div id=\"footer\">\r\n");
      out.write("\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "Footer.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t<!-- Hidden Fields -->\r\n");
      out.write("\r\n");
      out.write("\t<input type=\"hidden\" name=\"id\" value=\"");
      out.print(dto.getId());
      out.write("\">\r\n");
      out.write("\t<input type=\"hidden\" name=\"createdBy\" value=\"");
      out.print(dto.getCreatedBy());
      out.write("\">\r\n");
      out.write("\t<input type=\"hidden\" name=\"modifiedBy\" value=\"");
      out.print(dto.getModifiedBy());
      out.write("\">\r\n");
      out.write("\t<input type=\"hidden\" name=\"createdDatetime\" value=\"");
      out.print(dto.getCreatedDatetime());
      out.write("\">\r\n");
      out.write("\t<input type=\"hidden\" name=\"modifiedDatetime\" value=\"");
      out.print(dto.getModifiedDatetime());
      out.write("\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
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
