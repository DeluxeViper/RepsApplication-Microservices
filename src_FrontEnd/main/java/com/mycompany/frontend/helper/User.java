/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.frontend.helper;
import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author student
 */
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    
    private int userid;
    private String firstName;
    private String lastName;
    private String phone;
    private String username;
    private String password;
   
    @XmlTransient
    private Set<Exercise> exercises;

    public User(){
        
    }
    
    public User(int userid, String firstName, String lastName, String phone, String username, String password) {
        this.userid = userid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.username = username;
        this.password = password;
        exercises = new HashSet<>();
    }

    public int getUserid(){
        return userid;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void addExercise(Exercise exercise){
        exercises.add(exercise);
    }
    
    public Set<Exercise> getExercises(){
        return exercises;
    }
    
    public void addAllExercises(Set<Exercise> exercises){
        this.exercises.addAll(exercises);
    }
    
    public void setExercises(Set<Exercise> exercises){
        this.exercises = new HashSet<>(exercises);
    }

    @Override
    public String toString() {
        return "User{" + "userid=" + userid + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone + ", username=" + username + ", password=" + password + ", exercises=" + exercises + '}';
    }
}

