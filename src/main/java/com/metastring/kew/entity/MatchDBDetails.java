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
@Table(name = "match_db_details")
public class MatchDBDetails extends PanacheEntityBase {
    @Id
    @Column(name = "match_id")
    public Long matchId;
    @Column(name = "match_db_name")
    public String matchDbName;
    @Column(name = "match_db_tablename")
    public String matchDbTablename;


    public String getMatchDbNameByPlantId(Long l){
        TaxonNameList tnl=new TaxonNameList();
        Long matchId= tnl.getMatchIdByPlantId(l);
        if(matchId == null)
            return null;
        MatchDBDetails matchDBDetails = MatchDBDetails.findById(matchId);
        if(matchDBDetails == null)
            return null;

        return matchDBDetails.getMatchDbName();
    }

}
