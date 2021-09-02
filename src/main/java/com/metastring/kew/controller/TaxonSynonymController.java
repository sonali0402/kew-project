package com.metastring.kew.controller;

import com.metastring.kew.entity.CommonNames;
import com.metastring.kew.entity.TaxonNameList;
import com.metastring.kew.entity.TaxonSynonyms;
import com.metastring.kew.repository.CommonNamesRepository;
import com.metastring.kew.repository.TaxonSynonymsRepository;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Path("/synonymsfile")
public class TaxonSynonymController {
    @Inject
    TaxonSynonymsRepository taxonSynonymsRepository;

    @POST
    @Path("/savecsv")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response insertTaxonSynonymsListWithCSVFile(@MultipartForm MultipartFormDataInput form) throws IOException {

        Response data = taxonSynonymsRepository.taxonSynonymsSaveUsingCSV(form);

        return data;
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTaxonSynonyms(@PathParam("id") Long id){
        List<TaxonSynonyms> taxonSynonymsList =TaxonSynonyms.findByPlantId(id);
        return Response.ok(taxonSynonymsList).build();
    }




}
