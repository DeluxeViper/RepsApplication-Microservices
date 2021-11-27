/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.frontend.business;

import com.mycompany.frontend.helper.Exercise;
import com.mycompany.frontend.helper.ExercisesXML;
import com.mycompany.frontend.helper.User;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Set;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author student
 */
public class BusinessUtil {

    public static User isAuthenticated(String username, String password, String token) throws IOException {
        Client userServiceClient = ClientBuilder.newClient();
        WebTarget userWebTarget = userServiceClient.target("http://localhost:8080/UserService/webresources/user");

        InputStream is = userWebTarget.path(username).request(MediaType.APPLICATION_XML).get(InputStream.class);
        String xml = IOUtils.toString(is, "utf-8");
        User user = userxmlToObject(xml);

        System.out.println("isAuthenticated User: " + user.toString());

        if (token != null && user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
            System.out.println("VALID USER");
            return user;
        } else {
            System.out.println("INVALID USER");
            return null;
        }
    }

    public static User getCurrentUser(int userId) throws IOException {
        Client userServiceClient = ClientBuilder.newClient();
        WebTarget userWebTarget = userServiceClient.target("http://localhost:8080/UserService/webresources/user");

        InputStream is = userWebTarget.path(userId + "").request(MediaType.APPLICATION_XML).get(InputStream.class);
        String xml = IOUtils.toString(is, "utf-8");
        User user = userxmlToObject(xml);

        return user;
    }

    public static Set<Exercise> getExercises(int userId) throws IOException {
        Client exerciseServiceClient = ClientBuilder.newClient();
        WebTarget exerciseWebTarget = exerciseServiceClient.target("http://localhost:8080/ExerciseService/webresources/exercises");

        InputStream is = exerciseWebTarget.path("" + userId).request(MediaType.APPLICATION_XML).get(InputStream.class);
        String xml = IOUtils.toString(is, "utf-8");

        ExercisesXML exercisesXML = exercisesxmlToObjects(xml);

        System.out.println("Exercises received: " + exercisesXML.getExercises());
        return exercisesXML.getExercises();
    }

    public static Exercise addExercise(Exercise exercise, int user_id) {
        Client exerciseServiceClient = ClientBuilder.newClient();
        WebTarget exerciseWebTarget = exerciseServiceClient.target("http://localhost:8080/ExerciseService/webresources/exercise");

//        Form form = new Form();
        MultivaluedMap<String, String> map = new MultivaluedHashMap<>();
        map.add("name", exercise.getName());
        map.add("description", exercise.getDescription());
        map.add("startTime", exercise.getStartDate().toString());
        map.add("endTime", exercise.getEndDate().toString());
        map.add("user_id", user_id + "");
        Response exerciseId = exerciseWebTarget.request().post(Entity.form(map));
        System.out.println("Response: " + exerciseId);

//        exercise.setExercise_id(exerciseId.getEntity());
        return exercise;
    }

    private static User userxmlToObject(String xml) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(User.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            User user = (User) jaxbUnmarshaller.unmarshal(new StringReader(xml));

            return user;
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static ExercisesXML exercisesxmlToObjects(String xml) {
        JAXBContext jaxbContext;

        try {
            jaxbContext = JAXBContext.newInstance(ExercisesXML.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            ExercisesXML exercisesXML = (ExercisesXML) jaxbUnmarshaller.unmarshal(new StringReader(xml));

            return exercisesXML;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}
