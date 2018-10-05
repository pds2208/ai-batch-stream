package uk.gov.ons.batch.model;

public class Address {
    private int id;
    private String address;

    Address(int id, String address) {
        this.id = id;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String address) {
        this.address = address;
    }

    public String toString() {
        return this.id + this.address;
    }
}

