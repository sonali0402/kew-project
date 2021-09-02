package com.metastring.kew.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trade_details")
public class TradeDetails extends PanacheEntityBase {

    @Id
    @Column(name = "traded_id")
    public Long tradedId;

    @Column(name = "plant_id")
    private Long plantId;

    @Column(name = "trade_name")
    public String tradeName;

    @Column(name = "part_used")
    public String partUsed;

    @Column(name = "endemic")
    public String endemic;

    @Column(name = "ret")
    public String ret;

    @Column(name = "high_volume_trade")
    public String highVolumeTrade;

    public static List<TradeDetails> findByPlantId(Long id){
        return list("plant_id", id);
    }

    public List<TradeDetails> listWhereLike(String s){
        return list("trade_name like ?1",  "%"+s+"%");
    }

    public List<Long> listOfPlantIdsWhereTradeNameLike(String s){
        List<Long> l=new ArrayList<>();
        List<TradeDetails> tradeDetailsList=listWhereLike(s);
        for (TradeDetails t:tradeDetailsList ) {

            l.add(t.getPlantId());
        }
        return l;
    }

    public List<String> listOfTradeNameByPlantId(Long id){
        List<String> tradeNameList=new ArrayList<>();

        List<TradeDetails> tradeDetailsList=findByPlantId(id);
        for (TradeDetails t:tradeDetailsList) {
            if(t.getTradeName()!=null) {
                tradeNameList.add(t.getTradeName());
            }
        }
        return tradeNameList;
    }


}
