package com.example.officeassistant.Admin.Update_profile;

import androidx.annotation.NonNull;

import com.google.firebase.database.ValueEventListener;

public class Admin_userProfileEdit_Model {
    public String userId;
    public String userJdate;
    public String userfullName;
    public  String userDesignation;
    public String userMobile;
    public String userBasicpay;
    public String userAddress;
    public String userName;
    public String userMail;
    public String userAcode;
    public int admin;

   public Admin_userProfileEdit_Model(){}

    public Admin_userProfileEdit_Model(String uId, String uJdate,String ufullName,String uDesignation,String uMobile,String uBasicpay, String uAddress,String uName,String uMail, String uAcode,int uadmin){
        this.userId=uId;
        this.userJdate=uJdate;
        this.userfullName=ufullName;
        this.userDesignation=uDesignation;
        this.userMobile=uMobile;
        this.userBasicpay=uBasicpay;
        this.userAddress=uAddress;
        this.userName=uName;
        this.userMail=uMail;
        this.userAcode=uAcode;
        this.admin=uadmin;

    }

    public String getUserAcode() {
        return userAcode;
    }

    public void setUserAcode(String userAcode) {
        this.userAcode = userAcode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserJdate() {
        return userJdate;
    }

    public void setUserJdate(String userJdate) {
        this.userJdate = userJdate;
    }

    public String getUserfullName() {
        return userfullName;
    }

    public void setUserfullName(String userfullName) {
        this.userfullName = userfullName;
    }

    public String getUserDesignation() {
        return userDesignation;
    }

    public void setUserDesignation(String userDesignation) {
        this.userDesignation = userDesignation;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserBasicpay() {
        return userBasicpay;
    }

    public void setUserBasicpay(String userBasicpay) {
        this.userBasicpay = userBasicpay;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }
}
