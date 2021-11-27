/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.exerciseservice.endpoint;

import com.mycompany.exerciseservice.business.ExerciseBusiness;
import com.mycompany.exerciseservice.helper.Exercise;
import com.mycompany.exerciseservice.helper.ExercisesXML;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * REST Web Service
 *
 * @author student
 */
@Path("/exercise")
public class ExerciseResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ExerciseResource
     */
    public ExerciseResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml(@PathParam("exercise_id") int exerciseId) {
//        ExerciseBusiness exerciseBus = new ExerciseBusiness();
//        ExercisesXML exercisesXML = exerciseBus.getAllExercisesForUser(userId);
//        
//        JAXBContext jaxbContext;
//        try {
//            jaxbContext = JAXBContext.newInstance(ExercisesXML.class);
//            
//            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//            
//            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            
//            StringWriter sw = new StringWriter();
//            jaxbMarshaller.marshal(exercisesXML, sw);
//            
//            return sw.toString();
//        } catch (JAXBException ex){
//            Logger.getLogger(ExercisesResource.class.getName()).log(Level.SEVERE, null, ex);
//            return "Error occurred!";
//        }

        return "NOT IMPLEMENTED";
    }

    /**
     * PUT method for updating or creating an instance of ExercisesResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    @POST
    public int createExercise(MultivaluedMap<String, String> map) {
        String startDateTime = map.get("startTime").get(0).replace("T", " ");
        String endDateTime = map.get("endTime").get(0).replace("T", " ");
        String user_id = map.get("user_id").get(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime startDt = LocalDateTime.parse(startDateTime, formatter);
        LocalDateTime endDt = LocalDateTime.parse(endDateTime, formatter);
        
        Exercise exercise = new Exercise(map.get("name").get(0), map.get("description").get(0), startDt, endDt);
        
        
//        if (exercise.getExercise_id() <= 0){
                //            System.out.println("Exercise already exists!");
                //            return exercise.getExercise_id();
                //        }
        ExerciseBusiness exerciseBus = new ExerciseBusiness();
        int exerciseId = exerciseBus.saveExercise(exercise, Integer.parseInt(user_id));

        return exerciseId;
    }
}
