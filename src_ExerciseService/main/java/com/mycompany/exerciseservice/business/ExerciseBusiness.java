/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.exerciseservice.business;

import com.mycompany.exerciseservice.helper.Exercise;
import com.mycompany.exerciseservice.helper.ExercisesXML;
import com.mycompany.exerciseservice.persistence.Exercise_CRUD;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author student
 */
public class ExerciseBusiness {
    
    public ExercisesXML getAllExercisesForUser(int userId){
        ExercisesXML exercisesXML = new ExercisesXML();
        exercisesXML.setExercises(Exercise_CRUD.getExercisesForUser(userId));
        return exercisesXML;
    }
    
    public int saveExercise(Exercise exercise, int user_id){
        int exerciseId = Exercise_CRUD.saveExercise(exercise, user_id);
        
        return exerciseId;
    }
    
//    public Exercise getExercise(int exerciseId){
//        
//    }
    
//    public Exercise saveExercise(Exercise exercise){
//        
//    }
}
