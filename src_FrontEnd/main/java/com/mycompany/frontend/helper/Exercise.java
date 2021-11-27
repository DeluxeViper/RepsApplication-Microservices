/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.frontend.helper;

/**
 *
 * @author student
 */
import java.time.LocalDateTime;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "exercise")
@XmlAccessorType(XmlAccessType.FIELD)
public class Exercise {

    int exercise_id;

    String name;

    String description;

//    ExerciseType type;
    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    LocalDateTime startDate;

    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    LocalDateTime endDate;

    boolean completed;

    public Exercise() {

    }

    public Exercise(String name, String description, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.completed = false;
    }

    public Exercise(int exercise_id, String name, String description, LocalDateTime startDate, LocalDateTime endDate) {
        this.exercise_id = exercise_id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.completed = false;
    }

//    public ExerciseType getType() {
//        return type;
//    }
//
//    public void setType(ExerciseType type) {
//        this.type = type;
//    }
    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Exercise{" + "name=" + name + ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + ", completed=" + completed + '}';
    }
}
