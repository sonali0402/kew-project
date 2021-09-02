package com.metastring.kew.controller;


import com.metastring.kew.entity.TaxonNameList;
import com.metastring.kew.repository.PlantFileRepository;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/plantsfile")
public class PlantFileController {

    @Inject
    PlantFileRepository plantFileRepository;


    @POST
    @Path("/savecsv")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response insertTaxonNameListsWithCSVFile(@MultipartForm MultipartFormDataInput form) throws IOException {

        Response data = plantFileRepository.taxonNameListSaveUsingCSV(form);

        return data;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlantDetails(@PathParam("id") Long id){
        TaxonNameList taxonNameList =plantFileRepository.findById(id);
        return Response.ok(taxonNameList).build();
    }


}
