package com.metastring.kew.request;

import com.metastring.kew.entity.TaxonSynonyms;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.csv.CSVRecord;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaxonSynonymsSave {

    private Long acceptedNamePlantId;
    private Long synonymPlantId;

    public static TaxonSynonyms parse(CSVRecord csvRecord){
        TaxonSynonyms taxonSynonyms=new TaxonSynonyms();
        taxonSynonyms.synonymPlantId=Long.parseLong(csvRecord.get("plant_id"));
        taxonSynonyms.acceptedNamePlantId=Long.parseLong(csvRecord.get("accepted_plant_id"));

        return taxonSynonyms;
    }
}
