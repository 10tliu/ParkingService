<%-- 
    Document   : AddOrModifyTicketMachine
    Created on : Nov 30, 2018, 11:17:38 PM
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

    TicketMachine ticketMachine = null;
    Integer ticketMachineId = null;

    if ("modifyTicketMachine".equals(action)) {
        try {
            ticketMachineId = Integer.parseInt(ticketMachineIdReq);
            ticketMachine = serviceFacade.retrieveTicketMachine(ticketMachineId);
        } catch (Exception e) {
            errorMessage = "problem finding ticketMachine " + e.getMessage();
        }
    } else if ("createTicketMachine".equals(action)) {
        try {
            ticketMachine = new TicketMachine();
        } catch (Exception e) {
            errorMessage = "problem finding ticketMachine " + e.getMessage();
        }
    } else {
        errorMessage = "cannot recognise action: " + action;
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <title>Edit Ticket Machine</title>
    </head>
    <body>
        <% if ("createTicketMachine".equals(action)) {
        %>
        <h1>Add New Ticket Machine</h1>
        <% } else {%>
        <h1>Modify Ticket Machine <%=ticketMachineId%></h1>
        <% }%>
        <form action="ListTicketMachines.jsp">
            <table>
                <tr>
                    <th>Field</th>
                    <th>Current Value</th>
                    <th>New Value</th>
                </tr>
                <tr>
                    <td>Ticket Machine Id</td>
                    <td><%=ticketMachine.getMachineId()%></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Location</td>
                    <td><%=ticketMachine.getLocation()%></td>
                    <td><input type="text" name="location" value ="<%=ticketMachine.getLocation()%>"></td>
                </tr>
                <tr>
                    <td>Schedule ID</td>
                    <td><%=ticketMachine.getStayType()%></td>
                    <td><input type="text" name="stayType" value="null"></td>
                </tr>
                <tr>
                    <%--<td>field_C</td>
                    <td><%=ticketMachine.getField_C()%></td>
                    <td><input type="text" name="field_C" value ="<%=ticketMachine.getField_C()%>"></td>--%>
                </tr>
            </table> 
            <BR>
            <% if ("createTicketMachine".equals(action)) {
            %>
            <input type="hidden" name="action" value="createTicketMachine">
            <input type="hidden" name="ticketMachineId" value="<%=ticketMachineId%>">
            <input type="submit" value="Create New Ticket Machine">
            <% } else if ("modifyTicketMachine".equals(action)) {
            %>
            <input type="hidden" name="action" value="modifyTicketMachine">
            <input type="hidden" name="ticketMachineId" value="<%=ticketMachineId%>">
            <input type="submit" value="Modify Ticket Machine">
            <% }%>
        </form>
        <form action="ListTicketMachines.jsp">
            <input type="submit" value="Cancel and Return">
        </form>
    </body>
</html>
