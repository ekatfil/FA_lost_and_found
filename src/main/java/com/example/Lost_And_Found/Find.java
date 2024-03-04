package com.example.Lost_And_Found;

import jakarta.persistence.*;

import java.sql.Date;


@Entity
public class Find {
    private Long findId;

    @Temporal(TemporalType.DATE)
    private java.sql.Date findDate;

    private String findCategory;
    private String findSubcategory;
    private String findPlace;
    private String findDescription;
    private String findContacts;


    protected Find() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getFindId() {
        return findId;
    }

    public void setFindId(Long findId) {
        this.findId = findId;
    }

    public Date getFindDate() {
        return findDate;
    }

    public void setFindDate(Date findDate) {
        this.findDate = findDate;
    }

    public String getFindCategory() {
        return findCategory;
    }

    public void setFindCategory(String findCategory) {
        this.findCategory = findCategory;
    }

    public String getFindSubcategory() {
        return findSubcategory;
    }

    public void setFindSubcategory(String findSubcategory) {
        this.findSubcategory = findSubcategory;
    }

    public String getFindPlace() {
        return findPlace;
    }

    public void setFindPlace(String findPlace) {
        this.findPlace = findPlace;
    }

    public String getFindDescription() {
        return findDescription;
    }

    public void setFindDescription(String findDescription) {
        this.findDescription = findDescription;
    }

    public String getFindContacts() {
        return findContacts;
    }

    public void setFindContacts(String findContacts) {
        this.findContacts = findContacts;
    }




    @Override
    public String toString() {
        return "Find [find_id=" + findId + ", find_date=" + findDate +", find_category=" + findCategory + ", find_subcategory=" + findSubcategory + ", find_place=" + findPlace + ", find_description=" + findDescription + ", find_contacts=" + findContacts + "]";
    }
}
