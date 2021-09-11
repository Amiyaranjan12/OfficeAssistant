package com.example.officeassistant.event_page.Model;

public class Event_list {

    public String eEvent;
    public String eDetails;

    public  Event_list(){
    }

    public Event_list(String eEvent, String eDetails){
        this.eEvent=eEvent;
        this.eDetails=eDetails;

    }

    public String geteDetails() {
        return eDetails;
    }

    public void seteDetails(String eDetails) {
        this.eDetails = eDetails;
    }



    public String geteEvent() {
        return eEvent;
    }

    public void seteEvent(String eEvent) {
        this.eEvent = eEvent;
    }


}
