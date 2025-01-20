/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author Nicola
 */
public class UserData {

    private String firstname;
    private String lastname;
    private String adress;
    private String session;
    private String ipAdress;

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public UserData() {
    }

    public UserData(String firstname, String lastname, String adress) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.adress = adress;

    }

    @Override
    public String toString() {
        return "UserData{" + "firstname=" + firstname + ", lastname=" + lastname + ", adress=" + adress + '}';
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

}
