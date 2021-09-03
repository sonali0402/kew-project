package com.metastring.kew.controller;

import com.metastring.kew.entity.APINames;
import com.metastring.kew.entity.CommonNames;
import com.metastring.kew.entity.TaxonNameList;
import com.metastring.kew.entity.TradeDetails;
import com.metastring.kew.responce.PlantDetailsById;
import com.metastring.kew.responce.PlantDetailsByString;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/plantinfo")
public class GetPlantDetailsByString {

    @GET
    @Path("{s}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getListOfPlantIds(@PathParam("s") String s){
//        List<Long> plantIds=new ArrayList<>();
//        TaxonNameList t=new TaxonNameList();
//        CommonNames c=new CommonNames();
//        APINames a=new APINames();
//        TradeDetails td=new TradeDetails();
//
//        plantIds.addAll(t.listOfPlantIdsWhereCanonicalNameLike(s));
//        plantIds.addAll(t.listOfPlantIdsWhereScientificNameLike(s));
//        plantIds.addAll(c.listOfPlantIdWhereCommonNameLike(s));
//        plantIds.addAll(a.listOfPlantIdsWhereDrugNameLike(s));
//        plantIds.addAll(td.listOfPlantIdsWhereTradeNameLike(s));
//        return Response.ok().entity(plantIds).build();
//

        PlantDetailsByString plantDetailsByString=new PlantDetailsByString();
        List<PlantDetailsByString> plantDetailsByStringList= plantDetailsByString.getPlantDetailsByString(s);

        return Response.ok().entity(plantDetailsByStringList).build();

    }

}
