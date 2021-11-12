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

import com.google.gson.Gson;
import dtos.API_TestDTO.*;
import utils.EMF_Creator;
import utils.HttpUtils;

import java.util.ArrayList;
import java.util.List;

@Path("info")
public class DemoResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get() {
        return "{\"msg\": \"Hello anonymous\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("remote")
    public String getFiveAPI() {
        Gson gson = new Gson();
        try {
            ChuckDTO chuckDTO = gson.fromJson(HttpUtils.fetchData("https://api.chucknorris.io/jokes/random"), ChuckDTO.class);

            DadDTO dadDTO = gson.fromJson(HttpUtils.fetchData("https://icanhazdadjoke.com"), DadDTO.class);

            xkcdDTO xkcdDTO = gson.fromJson(HttpUtils.fetchData("https://xkcd.com/info.0.json"), xkcdDTO.class);

            List<Object> result = new ArrayList<>();
            result.add(chuckDTO);
            result.add(dadDTO);
            result.add(xkcdDTO);

            return gson.toJson(result);
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