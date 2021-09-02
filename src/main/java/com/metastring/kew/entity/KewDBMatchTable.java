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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "kew_db_match_table")
public class KewDBMatchTable extends PanacheEntityBase {
    @Id
    @Column(name = "match_id")
    public Long matchId;
    @Column(name = "kew_medicinal_names_id")
    public String kewMedicinalNamesId;
    @Column(name = "kew_concept_id")
    public Long kewConceptId;
    @Column(name = "kew_match_status_id")
    public Long kewMatchStatusId;
    @Column(name = "kew_concept_ipni_id")
    public Long kewConceptIpniId;
}
