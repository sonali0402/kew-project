package com.metastring.kew.repository;

import com.metastring.kew.entity.CommonNames;
import com.metastring.kew.entity.TaxonNameList;
import com.metastring.kew.request.CommonNameSave;
import com.metastring.kew.request.PlantFileSave;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class CommonNamesRepository implements PanacheRepository<CommonNames> {
    public Response commonNamesSaveUsingCSV(@MultipartForm MultipartFormDataInput form) throws IOException {

        List<CommonNames> commonNamesListDataRows = new ArrayList<>();
        List<CommonNames> commonNamesListDataEachRow = new ArrayList<>();
        Map<String, List<InputPart>> data = form.getFormDataMap();
        for (Map.Entry<String, List<InputPart>> e : data.entrySet()) {
            for (InputPart inputPart : e.getValue()) {
                InputStream inputStream = inputPart.getBody(InputStream.class, null);
                Reader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                CSVParser csvParser = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(fileReader);
                Iterable<CSVRecord> csvRecords = csvParser.getRecords();

                for (CSVRecord csvRecord : csvRecords) {
                    commonNamesListDataEachRow = CommonNameSave.parse(csvRecord);
                    commonNamesListDataRows.addAll(commonNamesListDataEachRow);
                }

            }
        }

        for (CommonNames c : commonNamesListDataRows) {
            persist(c);
        }
        return Response.ok().entity(commonNamesListDataRows).build();
    }


    public List<CommonNames> getCommonNamesLisByPlantId(Long plantId) {
        List<CommonNames> commonNamesList = CommonNames.findByPlantId(plantId);
        return commonNamesList;
    }


}
