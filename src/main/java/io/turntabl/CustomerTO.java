package io.turntabl;

public class CustomerTO {
    private String companyName;
    private String contactName;
    private String contactTitle;
    private String city;
    private String country;
    private String phone;

    CustomerTO(){}

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public String getCity(){return this.city;}

    public String getCountry(){return this.country;}

    public String getPhone() {
        return phone;
    }
}
