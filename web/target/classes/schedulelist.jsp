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
<%@ page import="java.util.ArrayList" %>
<%@ page import="sun.security.krb5.internal.Ticket" %>

<%

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
        <th>Schedule ID</th>
        <th>Start Time</th>
        <th>Hourly Rate</th>

    </tr>
    <%  for (TicketMachine.Schedule schedule : listofschedules) {
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
    <input type="hidden" name="action" value="createTicketMachine">
    <input type="submit" value="Back To Ticket Machine List">
</form>
</body>
</html>
