package com.metastring.kew.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TDU_names")
public class TDUNames extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    public Long id;

    @Column(name = "plant_id")
    private Long plantId;

    @Column(name = "basonym")
    public String basonym;

    @Column(name = "partUsed")
    public String partUsed;

    public static List<TDUNames> findByPlantId(Long id){
        return list("plant_Id", id);
    }

}
