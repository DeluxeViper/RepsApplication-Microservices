/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.userservice.business;

import com.mycompany.userservice.helper.User;
import com.mycompany.userservice.persistence.User_CRUD;

/**
 *
 * @author student
 */
public class UserBusiness {
    
    public User getUserByUsername(String query){
        User user = User_CRUD.getUser(query);
        return user;
    }
    
    public User getUserById(String query){
        User user = User_CRUD.getUserById(Integer.parseInt(query));
        return user;
    }
}
