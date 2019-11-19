package io.turntabl;

public class CustomerTO {
    private String companyName;
    private String contactName;
    private String city;
    private String country;

    public CustomerTO(String companyName, String contactName, String city, String country) {
        this.companyName = companyName;
        this.contactName = contactName;
        this.city = city;
        this.country = country;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public String getCity(){return this.city;}

    public String getCountry(){return this.country;}
}
