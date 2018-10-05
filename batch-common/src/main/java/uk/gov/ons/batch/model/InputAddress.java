package uk.gov.ons.batch.model;

import java.util.ArrayList;
import java.util.List;

class InputAddress {
    String requestSource;
}

class SFTPInputAddress extends InputAddress {
    List<Address> addresses = new ArrayList<>();
}

class HTTPInputAddress extends InputAddress {
    String addresses;
}
