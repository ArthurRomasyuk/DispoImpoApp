package com.example.artur.dispoimpoapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Shop {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Shop")
    @Expose
    private String shop;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("password2")
    @Expose
    private String password2;
    @SerializedName("Phone")
    @Expose
    private String phone;
    @SerializedName("classified")
    @Expose
    private String classified;
    @SerializedName("Succursale")
    @Expose
    private String succursale;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("GSM_Number")
    @Expose
    private String gSMNumber;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("adresse")
    @Expose
    private String adresse;
    @SerializedName("zip")
    @Expose
    private String zip;
    @SerializedName("town")
    @Expose
    private String town;
    @SerializedName("vat")
    @Expose
    private String vat;

    /**
     * No args constructor for use in serialization
     *
     */
    public Shop() {
    }

    /**
     *
     * @param zip
     * @param shop
     * @param phone
     * @param adresse
     * @param vat
     * @param classified
     * @param succursale
     * @param type
     * @param password
     * @param id
     * @param password2
     * @param email
     * @param description
     * @param company
     * @param name
     * @param town
     * @param gSMNumber
     */
    public Shop(String id, String shop, String name, Object description, String password, String password2, String phone, String classified, String succursale, String email, String type, String gSMNumber, String company, String adresse, String zip, String town, String vat) {
        super();
        this.id = id;
        this.shop = shop;
        this.name = name;
        this.description = description;
        this.password = password;
        this.password2 = password2;
        this.phone = phone;
        this.classified = classified;
        this.succursale = succursale;
        this.email = email;
        this.type = type;
        this.gSMNumber = gSMNumber;
        this.company = company;
        this.adresse = adresse;
        this.zip = zip;
        this.town = town;
        this.vat = vat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getClassified() {
        return classified;
    }

    public void setClassified(String classified) {
        this.classified = classified;
    }

    public String getSuccursale() {
        return succursale;
    }

    public void setSuccursale(String succursale) {
        this.succursale = succursale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGSMNumber() {
        return gSMNumber;
    }

    public void setGSMNumber(String gSMNumber) {
        this.gSMNumber = gSMNumber;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id='" + id + '\'' +
                ", shop='" + shop + '\'' +
                ", name='" + name + '\'' +
                ", description=" + description +
                ", password='" + password + '\'' +
                ", password2='" + password2 + '\'' +
                ", phone='" + phone + '\'' +
                ", classified='" + classified + '\'' +
                ", succursale='" + succursale + '\'' +
                ", email='" + email + '\'' +
                ", type='" + type + '\'' +
                ", gSMNumber='" + gSMNumber + '\'' +
                ", company='" + company + '\'' +
                ", adresse='" + adresse + '\'' +
                ", zip='" + zip + '\'' +
                ", town='" + town + '\'' +
                ", vat='" + vat + '\'' +
                '}';
    }
}