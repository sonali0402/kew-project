package com.metastring.kew.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jooq.meta.jaxb.Strategy;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "taxon_synonyms")
public class TaxonSynonyms extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "synonym_id")
    public Long synonymId;

    @Column(name = "accepted_name_plant_id")
    public Long acceptedNamePlantId;

    @Column(name = "synonym_plant_id")
    public Long synonymPlantId;


    public static List<TaxonSynonyms> findByPlantId(Long id){
        return list("accepted_name_plant_id", id);
    }

}
