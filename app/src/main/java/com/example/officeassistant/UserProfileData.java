package com.example.officeassistant;

public class UserProfileData {
       public String userId;
       public String userJdate;
       public String userfullName;
       public  String userDesignation;
       public String userMobile;
       public String userBasicpay;
       public String userAddress;

       public UserProfileData(){


       }


    public UserProfileData(String userId, String userJdate,String userfullName,String userDesignation,String userMobile,String userBasicpay, String userAddress){

        this.userId=userId;
        this.userJdate=userJdate;
        this.userfullName=userfullName;
        this.userDesignation=userDesignation;
        this.userMobile=userMobile;
        this.userBasicpay=userBasicpay;
        this.userAddress=userAddress;

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
}
