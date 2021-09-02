
package com.metastring.kew.request;


import com.metastring.kew.entity.TaxonNameList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.csv.CSVRecord;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PlantFileSave {

    private Long plantId;
    private String genus;
    private String species;
    private String author;
    private String variety;
    private Long book_id;



    public static TaxonNameList parse(CSVRecord csvRecord){
        TaxonNameList taxonNameList=new TaxonNameList();

        taxonNameList.plantId=Long.parseLong(csvRecord.get("plant_id"));
        taxonNameList.genus=csvRecord.get("genus");
        taxonNameList.species=csvRecord.get("species");
        taxonNameList.author=csvRecord.get("author");

        return taxonNameList;
    }

}
