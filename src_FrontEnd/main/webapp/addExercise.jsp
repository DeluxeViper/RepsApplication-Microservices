<%-- 
    Document   : addExercise
    Created on : Mar 29, 2021, 1:30:37 AM
    Author     : student
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
        <link rel='stylesheet' id='flatpickr-style-css'  href='https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css?ver=5.2.4' type='text/css' media='all' />
        <script type='text/javascript' src='https://cdn.jsdelivr.net/npm/flatpickr?ver=5.2.4'></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $('input.date').flatpickr({
                    altInput: true,
                    altFormat: "F j, Y H:i",
                    enableTime: true,
                    dateFormat: "Y-m-d H:i",
                });
            });
        </script>
        <title>Add Exercise Page</title>
    </head>
    <body>
        <h1>REPS</h1>
    <center>        
        <h2>Add your Exercise below!</h2>
        <form action="AddExercise" method="post">
            Name: <input type = "text" name = "exerciseName"/>
            <br />
            Description: <textarea name = "exerciseDescription" cols="40" rows="5"></textarea>
            <br />
            Pick a start date: <input type="text" name="startDateTime" class="date"/>
            <br />
            Pick an end date: <input type="text" name="endDateTime" class="date"/>
            <br />
            <button type="submit" name="Add Exercise">Add Exercise</button>
        </form>
    </center>
</body>
</html>