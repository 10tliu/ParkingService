package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import tristan.web.WebObjectFactory;
import tristan.model.ServiceFactory;
import tristan.model.ServiceFacade;
import tristan.model.TicketMachine;

public final class ListTicketMachines_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");


    ServiceFacade serviceFacade = (ServiceFacade) session.getAttribute("serviceFacade");

    // If the user session has no bankApi, create a new one
    if (serviceFacade == null) {
        ServiceFactory serviceFactory = WebObjectFactory.getServiceFactory();
        serviceFacade = serviceFactory.getServiceFacade();
        session.setAttribute("ServiceFacade", serviceFacade);
    }

    // get request values
    String action = (String) request.getParameter("action");
    String ticketMachineIdReq = (String) request.getParameter("ticketMachineId");
    String ticketMachineField_AReq = (String) request.getParameter("location");
    String ticketMachineField_BReq = (String) request.getParameter("stayType");
    String ticketMachineField_CReq = (String) request.getParameter("field_C");

    String errorMessage = "";
    if ("deleteTicketMachine".equals(action)) {
        try {
            Integer ticketMachineId = Integer.parseInt(ticketMachineIdReq);
            serviceFacade.deleteTicketMachine(ticketMachineId);
        } catch (Exception e) {
            errorMessage = "problem deleting TicketMachine " + e.getMessage();
        }
    } else if ("modifyTicketMachine".equals(action)) {
        try {
            Integer ticketMachineId = Integer.parseInt(ticketMachineIdReq);
            TicketMachine ticketMachineTemplate = new TicketMachine();
            ticketMachineTemplate.setMachineId(ticketMachineId);
            ticketMachineTemplate.setLocation(ticketMachineField_AReq);
            ticketMachineTemplate.setStayType(ticketMachineField_BReq);
            //ticketMachineTemplate.setField_C(ticketMachineField_CReq);
            TicketMachine ticketMachine = serviceFacade.updateTicketMachine(ticketMachineTemplate);
            if (ticketMachine == null) {
                errorMessage = "problem modifying TicketMachine. could not find ticketMachineId " + ticketMachineId;
            }
        } catch (Exception e) {
            errorMessage = "problem modifying TicketMachine " + e.getMessage();
        }
    } else if ("createTicketMachine".equals(action)) {
        try {
            TicketMachine ticketMachineTemplate = new TicketMachine();
            ticketMachineTemplate.setLocation(ticketMachineField_AReq);
            ticketMachineTemplate.setStayType(ticketMachineField_BReq);
            //ticketMachineTemplate.setField_C(ticketMachineField_CReq);
            TicketMachine ticketMachine = serviceFacade.createTicketMachine(ticketMachineTemplate);
            if (ticketMachine == null) {
                errorMessage = "problem creating TicketMachine. Service returned null ";
            }
        } catch (Exception e) {
            errorMessage = "problem creating  TicketMachine " + e.getMessage();
        }
    } 

    List<TicketMachine> ticketMachineList = serviceFacade.retrieveAllEntities();


      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\r\n");
      out.write("        <title>TicketMachine List</title>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <!-- print error message if there is one -->\r\n");
      out.write("        <div style=\"color:red;\">");
      out.print(errorMessage);
      out.write("</div>\r\n");
      out.write("        <h1>Ticket Machine List</h1>\r\n");
      out.write("        <table>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <th>Machine ID</th>\r\n");
      out.write("                <th>Location</th>\r\n");
      out.write("                <th>Schedule ID</th>\r\n");
      out.write("                <th>Modify Or Delete</th>\r\n");
      out.write("            </tr>\r\n");
      out.write("            ");
  for (TicketMachine ticketMachine : ticketMachineList) {
            
      out.write("\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td>");
      out.print(ticketMachine.getMachineId());
      out.write("</td>\r\n");
      out.write("                <td>");
      out.print(ticketMachine.getLocation());
      out.write("</td>\r\n");
      out.write("                <td>");
      out.print(ticketMachine.getStayType());
      out.write("</td>\r\n");
      out.write("                ");
      out.write("\r\n");
      out.write("                <td>\r\n");
      out.write("                    <form action=\"schedulelist.jsp\">\r\n");
      out.write("                        <input type=\"hidden\" name=\"action\" value=\"schedulelist\">\r\n");
      out.write("                        <input type=\"hidden\" name=\"TicketMachineId\" value=\"");
      out.print(ticketMachine.getMachineId());
      out.write("\">\r\n");
      out.write("                        <input type=\"submit\" value=\"View Schedule For This Machine\">\r\n");
      out.write("                    </form>\r\n");
      out.write("                    <form action=\"ListTicketMachines.jsp\">\r\n");
      out.write("                        <input type=\"hidden\" name=\"action\" value=\"deleteTicketMachine\">\r\n");
      out.write("                        <input type=\"hidden\" name=\"ticketMachineId\" value=\"");
      out.print(ticketMachine.getMachineId());
      out.write("\">\r\n");
      out.write("                        <input type=\"submit\" value=\"Delete Ticket Machine\">\r\n");
      out.write("                    </form>\r\n");
      out.write("                    <form action=\"parkingclient.jsp\">\r\n");
      out.write("                        <input type=\"hidden\" name=\"action\" value=\"parkingclient\">\r\n");
      out.write("                        <input type=\"hidden\" name=\"TicketMachineId\" value=\"");
      out.print(ticketMachine.getMachineId());
      out.write("\">\r\n");
      out.write("                        <input type=\"submit\" value=\"Purchase Ticket\">\r\n");
      out.write("                    </form>\r\n");
      out.write("                </td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            ");
 }
      out.write("\r\n");
      out.write("\r\n");
      out.write("        </table> \r\n");
      out.write("        <BR>\r\n");
      out.write("        <form action=\"AddOrModifyTicketMachine.jsp\">\r\n");
      out.write("            <input type=\"hidden\" name=\"action\" value=\"createTicketMachine\">\r\n");
      out.write("            <input type=\"submit\" value=\"Create Ticket Machine\">\r\n");
      out.write("        </form>\r\n");
      out.write("        <form action=\"index.html\">\r\n");
      out.write("            <input type=\"hidden\" name=\"\" value=\"\">\r\n");
      out.write("            <input type=\"submit\" value=\"Back To Index\">\r\n");
      out.write("        </form>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
