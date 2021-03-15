package com.example.bookalarm.DB;

public class BOOK {
    private int ID;
    private String TITLE;
    private String PUB;
    private String AUT;
    private String PUBDATE;
    private String COVERSMALLURL;
    private String PRICE;
    private String SALEPRICE;

    public BOOK(){}

    public BOOK(String TITLE, String PUB, String AUT, String PUBDATE, String COVERSMALLURL, String PRICE, String SALEPRICE) {
        this.TITLE = TITLE;
        this.PUB = PUB;
        this.AUT = AUT;
        this.PUBDATE = PUBDATE;
        this.COVERSMALLURL = COVERSMALLURL;
        this.PRICE = PRICE;
        this.SALEPRICE = SALEPRICE;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getPUB() {
        return PUB;
    }

    public void setPUB(String PUB) {
        this.PUB = PUB;
    }

    public String getAUT() {
        return AUT;
    }

    public void setAUT(String AUT) {
        this.AUT = AUT;
    }

    public String getPUBDATE() {
        return PUBDATE;
    }

    public void setPUBDATE(String PUBDATE) {
        this.PUBDATE = PUBDATE;
    }

    public String getCOVERSMALLURL() {
        return COVERSMALLURL;
    }

    public void setCOVERSMALLURL(String COVERSMALLURL) {
        this.COVERSMALLURL = COVERSMALLURL;
    }

    public String getPRICE() {
        return PRICE;
    }

    public void setPRICE(String PRICE) {
        this.PRICE = PRICE;
    }

    public String getSALEPRICE() {
        return SALEPRICE;
    }

    public void setSALEPRICE(String SALEPRICE) {
        this.SALEPRICE = SALEPRICE;
    }
}
