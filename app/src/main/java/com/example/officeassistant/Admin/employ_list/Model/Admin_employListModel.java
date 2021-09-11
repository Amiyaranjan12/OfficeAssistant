package com.example.officeassistant.Admin.employ_list.Model;

public class Admin_employListModel {

    public String userfullName;
    public String userAcode;
    public String userName;
    public  String userMail;
    public String cid;
    public  String userDesignation;
    public String notific;
    public String image;




    public Admin_employListModel(){
    }

    public Admin_employListModel(String userfullName, String userAcode, String userName,String userMail,String cid,String userDesignation,String notific
                                  ,String image){
        this.userfullName=userfullName;
        this.userAcode=userAcode;
        this.userName=userName;
        this.userMail=userMail;
        this.cid=cid;
        this.userDesignation=userDesignation;
        this.notific=notific;
        this.image=image;



    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNotific() {
        return notific;
    }

    public void setNotific(String notific) {
        this.notific = notific;
    }

    public String getUserDesignation() {
        return userDesignation;
    }

    public void setUserDesignation(String userDesignation) {
        this.userDesignation = userDesignation;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public String getUserAcode() {
        return userAcode;
    }

    public void setUserAcode(String userAcode) {
        this.userAcode = userAcode;
    }

    public String getUserfullName() {
        return userfullName;
    }

    public void setUserfullName(String userfullName) {
        this.userfullName = userfullName;
    }
}
