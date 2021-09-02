package com.metastring.kew.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "api_taxon_mapping")
public class APITaxonMapping extends PanacheEntityBase {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(name = "api_id")
    private Long apiId;
    @Column(name = "plant_id")
    private Long plantd;

    public static List<APITaxonMapping> findAPIListByPlantId(Long id){
        return list("plant_id", id);
    }

    public static List<Long> listOfAPIIds(Long id){
        List<Long> apiIds=new ArrayList<>();
       List<APITaxonMapping> l= APITaxonMapping.findAPIListByPlantId(id);

        for ( APITaxonMapping a:l) {
            Long apiId=a.getApiId();
           apiIds.add(apiId);
        }
       return apiIds;
    }


    public static List<APITaxonMapping> findAPIListByAPIId(Long id){
        return list("api_id", id);
    }


    public static List<Long> listOfPlantIds(Long id){
        List<Long> plantIds=new ArrayList<>();
        List<APITaxonMapping> l= APITaxonMapping.findAPIListByAPIId(id);

        for ( APITaxonMapping a:l) {
            Long plantId=a.getApiId();
            plantIds.add(plantId);
        }
        return plantIds;
    }

}
