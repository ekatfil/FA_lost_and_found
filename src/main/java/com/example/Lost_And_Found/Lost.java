package com.example.Lost_And_Found;

import jakarta.persistence.*;

import java.sql.Date;


@Entity
public class Lost {
    private Long lostId;

    @Temporal(TemporalType.DATE)
    private java.sql.Date lostDate;

    private String lostCategory;
    private String lostSubcategory;
    private String lostPlace;
    private String lostDescription;
    private String lostContacts;


    protected Lost() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getLostId() {
        return lostId;
    }

    public void setLostId(Long lostId) {
        this.lostId = lostId;
    }

    public Date getLostDate() {
        return lostDate;
    }

    public void setLostDate(Date lostDate) {
        this.lostDate = lostDate;
    }

    public String getLostCategory() {
        return lostCategory;
    }

    public void setLostCategory(String lostCategory) {
        this.lostCategory = lostCategory;
    }

    public String getLostSubcategory() {
        return lostSubcategory;
    }

    public void setLostSubcategory(String lostSubcategory) {
        this.lostSubcategory = lostSubcategory;
    }

    public String getLostPlace() {
        return lostPlace;
    }

    public void setLostPlace(String lostPlace) {
        this.lostPlace = lostPlace;
    }

    public String getLostDescription() {
        return lostDescription;
    }

    public void setLostDescription(String lostDescription) {
        this.lostDescription = lostDescription;
    }

    public String getLostContacts() {
        return lostContacts;
    }

    public void setLostContacts(String lostContacts) {
        this.lostContacts = lostContacts;
    }



    @Override
    public String toString() {
        return "Lost [lost_id=" + lostId + ", lost_date=" + lostDate +", lost_category=" + lostCategory + ", lost_subcategory=" + lostSubcategory + ", lost_place=" + lostPlace + ", lost_description=" + lostDescription + ", lost_contacts=" + lostContacts + "]";
    }


}