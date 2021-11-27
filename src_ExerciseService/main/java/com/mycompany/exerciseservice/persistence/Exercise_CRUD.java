/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.exerciseservice.persistence;

import com.mycompany.exerciseservice.helper.Exercise;
import com.mycompany.exerciseservice.helper.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author student
 */
public class Exercise_CRUD {
    private static Connection getCon() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Exercise_REPS?autoReconnect=true&useSSL=false&serverTimezone=UTC", "root", "student");
            System.out.println("Connection established");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
    
    public static int saveExercise(Exercise exercise, int userid) {
        try {
            Connection con = getCon();
            String insert = "insert into Exercise_REPS.exercise (user_id, name, description, startdate, enddate) VALUES (?, ?, ?, ?, ?)";
            System.out.println("INSERTING Exercise");
            PreparedStatement ps = con.prepareStatement(insert);
            ps.setInt(1, userid);
            ps.setString(2, exercise.getName());
            ps.setString(3, exercise.getDescription());
            ps.setTimestamp(4, Timestamp.valueOf(exercise.getStartDate()));
            ps.setTimestamp(5, Timestamp.valueOf(exercise.getEndDate()));
            
            int rowsAffected = ps.executeUpdate();

            System.out.println("rowsAffected: "+ rowsAffected);
            if (rowsAffected == 0){
                throw new SQLException("Creating exercise failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = ps.getGeneratedKeys()){
                if (generatedKeys.next()){
                    exercise.setExercise_id((int) generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating exercise failed, no ID obtained.");
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return exercise.getExercise_id();
    }
    
        public static Set<Exercise> getExercisesForUser(int userId) {
        Set<Exercise> exercises = new HashSet<>();
        try {
            Connection con = getCon();
            String select = "SELECT * FROM Exercise_REPS.exercise where user_id LIKE \"" + userId + "\";\n";

            PreparedStatement ps = con.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int exerciseId = rs.getInt("exercise_id");
                String exerciseName = rs.getString("name");
                String exerciseDescription = rs.getString("description");

                LocalDateTime startDateTime = rs.getTimestamp("startdate").toLocalDateTime();
                LocalDateTime endDateTime = rs.getTimestamp("enddate").toLocalDateTime();

                int exerciseTypeId = rs.getInt("exercise_type_id");

                Exercise exercise = new Exercise(exerciseId, exerciseName, exerciseDescription, startDateTime, endDateTime);
//                System.out.println("Exercise being added:" + exercise);
                exercises.add(exercise);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return exercises;
    }
}
