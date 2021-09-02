package com.metastring.kew.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "API_names")
public class APINames extends PanacheEntityBase {
    @Id
    @Column(name = "api_id")
    public Long apiId;
    @Column(name = "drug_name")
    public String drugName;
    @Column(name = "official_name")
    public String officialName;
    @Column(name = "scientific_name")
    public String scientificName;
    @Column(name = "partUsed")
    public String partUsed;


    public static List<APINames> getListOfAPINamesBasedOnPlantId(Long plantId){
        List<APINames> apiNamesList=new ArrayList<>();
        List<Long> apiIds=APITaxonMapping.listOfAPIIds(plantId);
        for (Long id:apiIds) {
            apiNamesList.add(APINames.findById(id));
        }
        return apiNamesList;
    }


    public List<APINames> listWhereLike(String s){
        return list("drug_name like ?1",  "%"+s+"%");
    }

    public List<Long> listOfAPIidsWhereDrugNameLike(String s){
        List<Long> l=new ArrayList<>();
        List<APINames> apiNamesList=listWhereLike(s);
        for (APINames name:apiNamesList ) {

            l.add(name.getApiId());
        }
        return l;
    }


    public List<Long> listOfPlantIdsWhereDrugNameLike(String s){
        List<Long> listOfPlantIds=new ArrayList<>();
        List<Long> listOfAPIIds=listOfAPIidsWhereDrugNameLike(s);
        APITaxonMapping apiTaxonMapping=new APITaxonMapping();

        for (Long l:listOfAPIIds) {
            listOfPlantIds.addAll(apiTaxonMapping.listOfPlantIds(l));
        }

        return listOfPlantIds;
    }

}
