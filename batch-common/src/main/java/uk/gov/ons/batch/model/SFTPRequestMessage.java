package uk.gov.ons.batch.model;

import com.google.gson.Gson;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.List;

public class SFTPRequestMessage extends AddressRequestMessage {

    public SFTPRequestMessage(String fileName, String message) {
        super(fileName, message);
        source = SOURCE.SFTP_SOURCE;
    }

    public String getasJSON() throws IllegalStateException {
        return convert();
    }

    String convert() throws IllegalStateException {
        SFTPInputAddress ad = new SFTPInputAddress();
        ad.requestSource = requestSource;
        Gson gson = new Gson();
        //Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String[] HEADERS = {"ID", "ADDRESS"};
        try {
            final CSVFormat format = CSVFormat.DEFAULT
                    .withHeader(HEADERS)
                    .withFirstRecordAsHeader();
            final Iterable<CSVRecord> records = CSVParser.parse(message, format);

            for (CSVRecord record : records) {
                final String n = record.get("ID").trim();
                final String addr = record.get("ADDRESS").trim();
                ad.addresses.add(new Address(Integer.valueOf(n), addr));
            }
            return gson.toJson(ad);
        } catch (Exception e) {
            throw new IllegalStateException("CSV file is not in CSV format or the format is incorrect");
        }
    }

}
