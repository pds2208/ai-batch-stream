package uk.gov.ons.batch.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class AddressRequestMessage implements Serializable {

    String message;
    String requestSource;

    public enum SOURCE {
        SFTP_SOURCE,
        HTTP_SOURCE
    }

    SOURCE source;

    abstract String convert() throws IllegalStateException;

    AddressRequestMessage(String requestSource, String message) {
        this.message = message;
        this.requestSource = requestSource;
    }

    public SOURCE getSource() {
        return source;
    }

    public void setSource(SOURCE source) {
        this.source = source;
    }

    public String getRequestSource() {
        return requestSource;
    }

    public void setRequestSource(String requestSource) {
        this.requestSource = requestSource;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}
