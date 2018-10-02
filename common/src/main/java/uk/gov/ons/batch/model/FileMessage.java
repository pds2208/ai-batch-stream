package uk.gov.ons.batch.model;

import com.google.gson.Gson;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FileMessage implements Serializable {

    private String message;
    private String callback;

    public FileMessage(String callback, String message) {
        this.message = message;
        this.callback = callback;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getasJSON() throws IllegalStateException {
        return convert();
    }

    private String convert() throws IllegalStateException {
        InputAddress ad = new InputAddress();
        ad.fileName = callback;
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

    class InputAddress {
        String fileName;
        List<Address> addresses = new ArrayList<>();
    }

}
