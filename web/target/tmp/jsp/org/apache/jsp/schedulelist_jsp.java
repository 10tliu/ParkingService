package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import tristan.web.WebObjectFactory;
import tristan.model.ServiceFactory;
import tristan.model.ServiceFacade;
import tristan.model.TicketMachine;
import java.util.ArrayList;
import sun.security.krb5.internal.Ticket;

public final class schedulelist_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("\r\n");


    ServiceFacade serviceFacade = (ServiceFacade) session.getAttribute("serviceFacade");
    List<TicketMachine.Schedule> listofschedules= new ArrayList<TicketMachine.Schedule>();

    // If the user session has no bankApi, create a new one
    if (serviceFacade == null) {
        ServiceFactory serviceFactory = WebObjectFactory.getServiceFactory();
        serviceFacade = serviceFactory.getServiceFacade();
        session.setAttribute("ServiceFacade", serviceFacade);
    }

    // get request values
    String action = (String) request.getParameter("action");
    String ticketMachineIdReq = (String) request.getParameter("TicketMachineId");
    String ticketMachineField_AReq = (String) request.getParameter("location");
    String ticketMachineField_BReq = (String) request.getParameter("stayType");
    String ticketMachineField_CReq = (String) request.getParameter("field_C");

    String errorMessage = "";
    if (action.equals("schedulelist")) {
       try{

           List<TicketMachine> ticketMachineswithschedules = serviceFacade.retrieveAllEntities();
           //listofschedules = ticketMachineswithschedules.get(0).getSchedule();
           for(int i=0;i<ticketMachineswithschedules.size();i++)
           {
               String machineid =ticketMachineswithschedules.get(i).getMachineId().toString();

               if (machineid.equals(ticketMachineIdReq))
               {
                   listofschedules = ticketMachineswithschedules.get(i).getSchedule();
               }
               break;
           }

       }catch (Exception ex)
       {
          // errorMessage = "problem creating  TicketMachine " + e.getMessage();
       }
   }


    List<TicketMachine> ticketMachineList = serviceFacade.retrieveAllEntities();
    listofschedules = ticketMachineList.get(0).getSchedule();


      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\r\n");
      out.write("    <title>TicketMachine List</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<!-- print error message if there is one -->\r\n");
      out.write("<div style=\"color:red;\">");
      out.print(errorMessage);
      out.write("</div>\r\n");
      out.write("<h1>Schedule List</h1>\r\n");
      out.write("\r\n");
      out.write("<table>\r\n");
      out.write("    <tr>\r\n");
      out.write("        <th>Schedule ID</th>\r\n");
      out.write("        <th>Start Time</th>\r\n");
      out.write("        <th>Hourly Rate</th>\r\n");
      out.write("\r\n");
      out.write("    </tr>\r\n");
      out.write("    ");
  for (TicketMachine.Schedule schedule : listofschedules) {
    
      out.write("\r\n");
      out.write("    <tr>\r\n");
      out.write("        <td>");
      out.print(schedule.getScheduleID());
      out.write("</td>\r\n");
      out.write("        <td>");
      out.print(schedule.getStartTime());
      out.write("</td>\r\n");
      out.write("        <td>");
      out.print(schedule.getHourlyRate());
      out.write("</td>\r\n");
      out.write("        ");
      out.write("\r\n");
      out.write("    </tr>\r\n");
      out.write("    ");
 }
      out.write("\r\n");
      out.write("\r\n");
      out.write("</table>\r\n");
      out.write("<BR>\r\n");
      out.write("\r\n");
      out.write("<form action=\"ListTicketMachines.jsp\">\r\n");
      out.write("    <input type=\"hidden\" name=\"action\" value=\"createTicketMachine\">\r\n");
      out.write("    <input type=\"submit\" value=\"Back To Ticket Machine List\">\r\n");
      out.write("</form>\r\n");
      out.write("</body>\r\n");
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
