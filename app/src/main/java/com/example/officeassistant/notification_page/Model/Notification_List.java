 package com.example.officeassistant.notification_page.Model;

public class Notification_List {

    public String employNotification;

    public Notification_List(){

    }

    public Notification_List(String employNotification) {
        this.employNotification=employNotification;
    }


    public String getEmployNotification() {
        return employNotification;
    }

    public void setEmployNotification(String employNotification) {
        this.employNotification = employNotification;
    }
}
