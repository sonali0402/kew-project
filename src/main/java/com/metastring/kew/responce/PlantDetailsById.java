package com.metastring.kew.responce;

import com.metastring.kew.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlantDetailsById {


    private TaxonNameList taxonNameList;
    private List<TaxonSynonyms> taxonSynonymsList;
    private List<TradeDetails> tradeDetailsList;
    private List<CommonNames> commonNamesList;
    private List<APINames> apiNamesList;
    private List<TDUNames> tduNamesList;


    public static PlantDetailsById findByPlantId(Long id) {
        PlantDetailsById plantDetailsById = new PlantDetailsById();
        plantDetailsById.setTaxonNameList(TaxonNameList.findById(id));
        plantDetailsById.setTaxonSynonymsList(TaxonSynonyms.findByPlantId(id));
        plantDetailsById.setTradeDetailsList(TradeDetails.findByPlantId(id));
        plantDetailsById.setCommonNamesList(CommonNames.findByPlantId(id));
        plantDetailsById.setApiNamesList(APINames.getListOfAPINamesBasedOnPlantId(id));
        plantDetailsById.setTduNamesList(TDUNames.findByPlantId(id));
        return plantDetailsById;
    }
}
