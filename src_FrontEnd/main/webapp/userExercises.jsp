<%-- 
    Document   : userExercises
    Created on : Mar 27, 2021, 12:27:47 AM
    Author     : student
--%>

<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="com.mycompany.frontend.helper.Exercise"%>
<%@page import="com.mycompany.frontend.helper.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Exercises Page</title>
    </head>
    
    <%
        User user = (User) request.getAttribute("user");
        Set<Exercise> exercises = (HashSet) user.getExercises();
    %>
    
    <body>
        Hey <%=user.getUsername()%>
        <center>
        <h3>Here are your upcoming exercises</h3>
        <form name="userExercisesForm" action="addExercise.jsp" method="get">
            <table border="2" align="center" cellpadding="5" cellspacing="5">
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Start Date & Time</th>
                    <th>End Date & Time</th>
                </tr>
                <% for (Exercise exercise : exercises) {%>
                <tr>
                    <td>
                        <%=exercise.getName()%>
                    </td>
                    <td>
                        <%=exercise.getDescription()%>
                    </td>
                    <td>
                        <%=exercise.getStartDate()%>
                    </td>
                    <td>
                        <%=exercise.getEndDate()%>
                    </td>
                </tr>
                <% } %>
            </table>
            <% if (exercises.isEmpty()) { %>
                </br>
                <tr><td>Looks like you don't have any exercises coming up. Try adding some below!</td></tr>
                </br> </br>
            <% }%>
            <button type ="submit" name="addExercise" value="addExerciseValue">
                Add Exercise
            </button>           
        </form>
    </center>
    </body>
</html>
