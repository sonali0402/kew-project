package com.metastring.kew.controller;

import com.metastring.kew.entity.CommonNames;
import com.metastring.kew.repository.CommonNamesRepository;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/commonnamesfile")
public class CommonNamesController {

    @Inject
    CommonNamesRepository commonNamesRepository;


    @POST
    @Path("/savecsv")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response insertCommonNamesWithCSVFile(@MultipartForm MultipartFormDataInput form) throws IOException {

        Response data = commonNamesRepository.commonNamesSaveUsingCSV(form);

        return data;
    }


    @GET
    @Path("{s}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getPlantIds(@PathParam("s") String name) throws Exception {

        CommonNames c=new CommonNames();
        List<CommonNames> commonNamesList= c.listWhereLike(name);


        return Response.ok(commonNamesList).build();
    }

}
