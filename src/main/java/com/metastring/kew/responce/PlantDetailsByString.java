package com.metastring.kew.responce;

import com.metastring.kew.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlantDetailsByString {
    private Long plantId;
    private List<String> tradeNameList;
    private String taxonStatus;
    private String scientificName;
    private String matchDbName;
    private List<String> commonNameList;

    public List<Long> getListOfPlantIds(@PathParam("s") String s) {
        List<Long> plantIds = new ArrayList<>();
        TaxonNameList t = new TaxonNameList();
        CommonNames c = new CommonNames();
        APINames a = new APINames();
        TradeDetails td = new TradeDetails();

        plantIds.addAll(t.listOfPlantIdsWhereCanonicalNameLike(s));
        plantIds.addAll(t.listOfPlantIdsWhereScientificNameLike(s));
        plantIds.addAll(c.listOfPlantIdWhereCommonNameLike(s));
        plantIds.addAll(a.listOfPlantIdsWhereDrugNameLike(s));
        plantIds.addAll(td.listOfPlantIdsWhereTradeNameLike(s));

        return plantIds;
    }

    public List<PlantDetailsByString> getPlantDetailsByString(String s) {
        TradeDetails td = new TradeDetails();
        TaxonNameList tnl = new TaxonNameList();
        CommonNames cns = new CommonNames();
        MatchDBDetails mdd = new MatchDBDetails();

        List<PlantDetailsByString> plantDetailsByStringList = new ArrayList<>();

        List<Long> plantIdsList = getListOfPlantIds(s);
        Set<Long> plantIdsSet = new HashSet<>();
        for (Long l : plantIdsList) {
            plantIdsSet.add(l);
        }


        for (Long l : plantIdsSet) {
            PlantDetailsByString p = new PlantDetailsByString();
            p.setPlantId(l);
            p.setTradeNameList(td.listOfTradeNameByPlantId(l));
            p.setTaxonStatus(tnl.getTaxonStatusByPlantId(l));
            p.setScientificName(tnl.getScintificNameByPlantId(l));
            p.setCommonNameList(cns.listOfCommonNamesByPlantId(l));
            String matchDBName = mdd.getMatchDbNameByPlantId(l);
            if(matchDBName != null)
                p.setMatchDbName(matchDBName);

           plantDetailsByStringList.add(p);
        }

        return plantDetailsByStringList;
    }


}
