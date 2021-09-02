package com.metastring.kew.controller;

import com.metastring.kew.responce.PlantDetailsById;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/plantdetails")
public class GetAllPlantDetailsByPlantId {



    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlantDetails(@PathParam("id") Long id){
        PlantDetailsById plantDetailsById = PlantDetailsById.findByPlantId(id);

        return Response.ok().entity(plantDetailsById).build();
    }
}
