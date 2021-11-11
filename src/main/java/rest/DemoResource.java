package rest;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import utils.EMF_Creator;
import utils.HttpUtils;

@Path("info")
public class DemoResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getFiveAPI() {
        try {
            String result = HttpUtils.fetchData("https://api.chucknorris.io/jokes/random");
            result += "\n";
            result += HttpUtils.fetchData("https://icanhazdadjoke.com");
            result += "\n";
            result += HttpUtils.fetchData("https://api.jokes.one");
            result += "\n";
            result += HttpUtils.fetchData("https://theaxolotlapi.netlify.app/");
            result += "\n";
            result += HttpUtils.fetchData("https://dog-facts-api.herokuapp.com/api/v1/resources/dogs/all");

            return result;
        } catch (Exception e) {
            return "Failed";
        }
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @RolesAllowed("user")
    public String getFromUser() {
        String thisUser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to User: " + thisUser + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin() {
        String thisUser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to (admin) User: " + thisUser + "\"}";
    }
}