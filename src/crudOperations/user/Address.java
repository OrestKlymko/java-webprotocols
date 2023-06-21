package crudOperations.user;

public class Address {
    public Address(String street, String suite, String city, String zipcode, Geo geo) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geo = geo;
    }

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

}
