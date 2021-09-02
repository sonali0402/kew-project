package com.metastring.kew.repository;

import com.metastring.kew.entity.TaxonNameList;
import com.metastring.kew.entity.TaxonSynonyms;
import com.metastring.kew.request.PlantFileSave;
import com.metastring.kew.request.TaxonSynonymsSave;
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
public class TaxonSynonymsRepository implements PanacheRepository<TaxonSynonyms> {
    public Response taxonSynonymsSaveUsingCSV(@MultipartForm MultipartFormDataInput form) throws IOException {

        List<TaxonSynonyms> taxonSynonymsDataRows = new ArrayList<>();
        Map<String, List<InputPart>> data = form.getFormDataMap();
        for (Map.Entry<String, List<InputPart>> e : data.entrySet()) {
            for (InputPart inputPart : e.getValue()) {
                InputStream inputStream = inputPart.getBody(InputStream.class, null);
                Reader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                CSVParser csvParser = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(fileReader);
                Iterable<CSVRecord> csvRecords = csvParser.getRecords();

                for (CSVRecord csvRecord : csvRecords) {
                    TaxonSynonyms taxonSynonyms = TaxonSynonymsSave.parse(csvRecord);
                    taxonSynonymsDataRows.add(taxonSynonyms);
                }

            }
        }

        for (TaxonSynonyms t : taxonSynonymsDataRows) {
            persist(t);
        }
        return Response.ok().entity(taxonSynonymsDataRows).build();
    }

    @Produces(MediaType.APPLICATION_JSON)
    public List<TaxonSynonyms> getTaxonSynonymsById(Long plantId) {
        List<TaxonSynonyms> taxonSynonymsList = TaxonSynonyms.findByPlantId(plantId);

        return taxonSynonymsList;
    }
}
