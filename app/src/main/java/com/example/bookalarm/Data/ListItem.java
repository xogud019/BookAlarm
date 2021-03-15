package com.example.bookalarm.Data;

import java.io.Serializable;

public class ListItem implements Serializable {
    private String title ="";
    private String description ="";
    private String pubDate;
    private String priceStandard ="";
    private String priceSales ="";
    private String discountRate ="";
    private String saleStatus ="";
    private String mileage ="";
    private String mileageRate ="";
    private String coverSmallUrl ="";
    private String coverLargeUrl ="";
    private String publisher = "";
    private String rank ="";
    private String author ="";
    private String translator ="";
    private String customerReviewRank = "";
    public ListItem(){}

    public ListItem(String title, String pubDate, String priceStandard, String priceSales, String author,String coverSmallUrl){
        this.title = title;
        this.pubDate = pubDate;
        this.priceStandard = priceStandard;
        this.priceSales = priceSales;
        this.coverSmallUrl = coverSmallUrl;
        this.author = author;
    }

    public ListItem(String title, String description, String pubDate, String priceStandard, String priceSales,
                    String discountRate, String saleStatus, String mileage, String mileageRate, String coverSmallUrl,
                    String coverLargeUrl, String publisher, String rank, String author, String customerReviewRank, String translator) {
        this.title = title;
        this.description = description;
        this.pubDate = pubDate;
        this.priceStandard = priceStandard;
        this.priceSales = priceSales;
        this.discountRate = discountRate;
        this.saleStatus = saleStatus;
        this.mileage = mileage;
        this.mileageRate = mileageRate;
        this.coverSmallUrl = coverSmallUrl;
        this.coverLargeUrl = coverLargeUrl;
        this.publisher = publisher;
        this.rank = rank;
        this.author = author;
        this.translator = translator;
        this.customerReviewRank = customerReviewRank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getPriceStandard() {
        return priceStandard;
    }

    public void setPriceStandard(String priceStandard) {
        this.priceStandard = priceStandard;
    }

    public String getPriceSales() {
        return priceSales;
    }

    public void setPriceSales(String priceSales) {
        this.priceSales = priceSales;
    }

    public String getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(String discountRate) {
        this.discountRate = discountRate;
    }

    public String getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(String saleStatus) {
        this.saleStatus = saleStatus;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getMileageRate() {
        return mileageRate;
    }

    public void setMileageRate(String mileageRate) {
        this.mileageRate = mileageRate;
    }

    public String getCoverSmallUrl() {
        return coverSmallUrl;
    }

    public void setCoverSmallUrl(String coverSmallUrl) {
        this.coverSmallUrl = coverSmallUrl;
    }

    public String getCoverLargeUrl() {
        return coverLargeUrl;
    }

    public void setCoverLargeUrl(String coverLargeUrl) {
        this.coverLargeUrl = coverLargeUrl;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCustomerReviewRank() { return customerReviewRank; }

    public void setCustomerReviewRank(String customerReviewRank) { this.customerReviewRank = customerReviewRank; }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }
}
