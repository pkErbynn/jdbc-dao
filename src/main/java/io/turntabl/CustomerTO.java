package io.turntabl;

public class CustomerTO {
    private String companyName;
    private String contactName;
    private String city;
    private String country;

    CustomerTO(){}

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
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
