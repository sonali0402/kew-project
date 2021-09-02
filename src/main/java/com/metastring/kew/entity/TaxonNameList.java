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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "taxon_name_list")
public class TaxonNameList extends PanacheEntityBase {

    @Id
    @Column(name = "plant_id")
    public Long plantId;
    @Column(name = "genus")
    public String genus;
    @Column(name = "species")
    public String species;
    @Column(name = "author")
    public String author;
    @Column(name = "infra_species")
    public String infraSpecies;
    @Column(name = "infra_species_rank")
    public String infraSpeciesRank;
    @Column(name = "scientific_name")
    public String scientificName;
    @Column(name = "canonical_name")
    public String canonicalName;
    @Column(name = "match_id")
    public Long matchId;
    @Column(name = "taxon_status")
    public String taxonStatus;
    @Column(name = "ref_id")
    public Long refId;


    public List<TaxonNameList> listWhereLike(String s){
        return list("scientific_name like ?1",  "%"+s+"%");
    }

    public List<Long> listOfPlantIdsWhereScientificNameLike(String s){
        List<Long> l=new ArrayList<>();
        List<TaxonNameList> taxonNameListList=listWhereLike(s);
        for (TaxonNameList nameList:taxonNameListList ) {

            l.add(nameList.getPlantId());
        }
        return l;
    }

    public List<TaxonNameList> listWhereCanonicalNameLike(String s){
        return list("canonical_name like ?1",  "%"+s+"%");
    }

    public List<Long> listOfPlantIdsWhereCanonicalNameLike(String s){
        List<Long> l=new ArrayList<>();
        List<TaxonNameList> taxonNameListList=listWhereCanonicalNameLike(s);
        for (TaxonNameList nameList:taxonNameListList ) {

            l.add(nameList.getPlantId());
        }
        return l;
    }

    public String getTaxonStatusByPlantId(Long l){
        String taxonStatus;
        TaxonNameList t=TaxonNameList.findById(l);
        taxonStatus=t.getTaxonStatus();
        return taxonStatus;
    }

    public String getScintificNameByPlantId(Long l){
        String scientificName;
        TaxonNameList t=TaxonNameList.findById(l);
        scientificName=t.getScientificName();
        return scientificName;
    }

    public Long getMatchIdByPlantId(Long l){
        Long matchId;
        TaxonNameList t=TaxonNameList.findById(l);
        matchId=t.getMatchId();
        return matchId;
    }

}
