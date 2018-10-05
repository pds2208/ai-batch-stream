package uk.gov.ons.batch.model;

import com.google.gson.Gson;

public class HTTPRequestMessage extends AddressRequestMessage {

    public HTTPRequestMessage(String callback, String message) {
        super(callback, message);
        source = SOURCE.HTTP_SOURCE;
    }

    @Override
    String convert() throws IllegalStateException {
        HTTPInputAddress ad = new HTTPInputAddress();
        Gson gson = new Gson();
        //Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            ad.requestSource = requestSource;
            ad.addresses = super.getMessage();
            return gson.toJson(ad);
        } catch (Exception e) {
            throw new IllegalStateException("CSV file is not in CSV format or the format is incorrect");
        }
    }

}
