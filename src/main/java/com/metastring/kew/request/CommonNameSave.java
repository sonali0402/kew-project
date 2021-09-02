package com.metastring.kew.request;

import com.metastring.kew.entity.CommonNames;
import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommonNameSave {

    private Long plantId;
    private String languageCodeAndCommonName;
    private String languageCode;
    private String commonName;

    public static List<CommonNames> parse(CSVRecord csvRecord) {

        List<CommonNames> commonNamesList = new ArrayList<>();
        CommonNameSave commonNameSave = new CommonNameSave();

        commonNameSave.plantId = Long.parseLong(csvRecord.get("Plant_id"));
        String commonNameColumnValue = csvRecord.get("Languagecodeandcommonname");


        if (commonNameColumnValue != null) {
            String[] commonNameForLanguage = commonNameColumnValue.split(";");
            for (String cName : commonNameForLanguage) {
                String[] languageCName = cName.split(":", 0);

                if (languageCName != null) {
                    commonNameSave.languageCode = languageCName[0].trim();
                    String commonNameString;
                    if (languageCName.length == 1) {
                        commonNameString = "other";
                    } else {
                        commonNameString = languageCName[1].trim();
                    }
                    String[] cmnName = commonNameString.split(",", 0);
                    if (cmnName != null) {
                        for (String s : cmnName) {
                            CommonNames commonName = new CommonNames();
                            commonName.plantId = commonNameSave.plantId;
                            commonName.languageCode = commonNameSave.languageCode;
                            commonName.commonName = s;

                            commonNamesList.add(commonName);
                        }
                    }
                }

            }
        }
        return commonNamesList;

    }
}
