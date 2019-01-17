<%-- 
    Document   : ListEntities
    Created on : Nov 30, 2018, 11:17:02 PM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="tristan.web.WebObjectFactory"%>
<%@page import="tristan.model.ServiceFactory"%>
<%@page import="tristan.model.ServiceFacade"%>
<%@page import="tristan.model.TicketMachine"%>
<%@page import="tristan.model.Schedule"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="sun.security.krb5.internal.Ticket" %>

<%

    ServiceFacade serviceFacade = (ServiceFacade) session.getAttribute("serviceFacade");
    List<Schedule> listofschedules= new ArrayList<Schedule>();

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
    //String ticketMachineField_CReq = (String) request.getParameter("field_C");

    Integer ticketMachineId = Integer.valueOf(ticketMachineIdReq);
    TicketMachine ticketMachine = serviceFacade.retrieveTicketMachine(ticketMachineId);
    if (ticketMachine!= null) listofschedules = ticketMachine.getSchedule();

    String errorMessage = "";
 /*   if (action.equals("schedulelist")) {
       try{

           List<TicketMachine> ticketMachineswithschedules = serviceFacade.retrieveAllEntities();
//           listofschedules = ticketMachineswithschedules.get(0).getSchedule();


           for(int i=0;i<ticketMachineswithschedules.size();i++)
           {
               System.out.println(ticketMachineswithschedules.size());
               Integer machineid =ticketMachineswithschedules.get(i).getMachineId();
               System.out.println(ticketMachineswithschedules.get(i).getMachineId());
               if (machineid.equals(tickeMachineId))
               {
                   listofschedules = ticketMachineswithschedules.get(i).getSchedule();
                   System.out.println(listofschedules.size());
               }
               break;
           }

       }catch (Exception ex)
       {
          // errorMessage = "problem creating  TicketMachine " + e.getMessage();
       }
   }*/


%>



<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>TicketMachine List</title>
</head>
<body>
<!-- print error message if there is one -->
<div style="color:red;"><%=errorMessage%></div>
<h1>Schedule List</h1>

<table>
    <tr>
        <th>Schedule Band</th>
        <th>Band Start Time</th>
        <th>Hourly Rate</th>

    </tr>
    <%  for (Schedule schedule : listofschedules) {
    %>
    <tr>
        <td><%=schedule.getScheduleID()%></td>
        <td><%=schedule.getStartTime()%></td>
        <td><%=schedule.getHourlyRate()%></td>
        <%--<td>
            <form action="AddOrModifyTicketMachine.jsp">
                <input type="hidden" name="action" value="modifyTicketMachine">

                <input type="submit" value="Modify Schedule" enable="false" >
            </form>
            <form action="ListTicketMachines.jsp">
                <input type="hidden" name="action" value="deleteTicketMachine">

                <input type="submit" value="Delete Schedule">
            </form>
        </td>--%>
    </tr>
    <% }%>

</table>
<BR>

<form action="ListTicketMachines.jsp">
    <input type="hidden" name="action" value="schedulelist">
    <input type="submit" value="Back To Ticket Machine List">
</form>
<form action="AddOrModifySchedule.jsp">
    <input type="hidden" name="action" value="modifySchedule">
    <input type="hidden" name="ticketMachineId" value="<%ticketMachine.getMachineId();%>">
    <input type="submit" value="Modify Schedule">
</form>
<a href="/AddOrModifySchedule.jsp">Modify Schedule</a>
</body>
</html>
