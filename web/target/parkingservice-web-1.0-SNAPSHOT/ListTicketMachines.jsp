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

<%

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
        <h1>Ticket Machine List</h1>
        <table>
            <tr>
                <th>Machine ID</th>
                <th>Location</th>
                <th>Stay Type</th>
                <th>Modify Or Delete</th>
            </tr>
            <%  for (TicketMachine ticketMachine : ticketMachineList) {
            %>
            <tr>
                <td><%=ticketMachine.getMachineId()%></td>
                <td><%=ticketMachine.getLocation()%></td>
                <td><%=ticketMachine.getStayType()%></td>
                <%--<td><%=ticketMachine.getField_C()%></td>--%>
                <td>
                    <form action="AddOrModifyTicketMachine.jsp">
                        <input type="hidden" name="action" value="modifyTicketMachine">
                        <input type="hidden" name="TicketMachineId" value="<%=ticketMachine.getMachineId()%>">
                        <input type="submit" value="Modify Ticket Machine">
                    </form>
                    <form action="ListTicketMachines.jsp">
                        <input type="hidden" name="action" value="deleteTicketMachine">
                        <input type="hidden" name="ticketMachineId" value="<%=ticketMachine.getMachineId()%>">
                        <input type="submit" value="Delete Ticket Machine">
                    </form>
                </td>
            </tr>
            <% }%>

        </table> 
        <BR>
        <form action="AddOrModifyTicketMachine.jsp">
            <input type="hidden" name="action" value="createTicketMachine">
            <input type="submit" value="Create Ticket Machine">
        </form>
    </body>
</html>
