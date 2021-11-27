/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.exerciseservice.endpoint;

import com.mycompany.exerciseservice.business.ExerciseBusiness;
import com.mycompany.exerciseservice.helper.ExercisesXML;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * REST Web Service
 *
 * @author student
 */
@Path("exercises/{user_id}")
public class ExercisesResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ExerciseResource
     */
    public ExercisesResource() {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.exerciseservice.endpoint.ExercisesResource
     * @param userId
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml(@PathParam("user_id") int userId) {
        ExerciseBusiness exerciseBus = new ExerciseBusiness();
        ExercisesXML exercisesXML = exerciseBus.getAllExercisesForUser(userId);
        
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(ExercisesXML.class);
            
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(exercisesXML, sw);
            
            return sw.toString();
        } catch (JAXBException ex){
            Logger.getLogger(ExercisesResource.class.getName()).log(Level.SEVERE, null, ex);
            return "Error occurred!";
        }
    }

    /**
     * PUT method for updating or creating an instance of ExercisesResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
