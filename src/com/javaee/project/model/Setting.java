package com.javaee.project.model;

import java.sql.Time;
import java.util.Timer;

public class Setting {

    private String restaurant_name;
    private int  number_of_table;
    private int number_person_table;
    private String address;
    private String email;
    private String phone_number;
    private int reservation_fee;
    private int duration_time_reservation;
    private Time opening_time;
    private Time closing_time;

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public int getNumber_of_table() {
        return number_of_table;
    }

    public void setNumber_of_table(int number_of_table) {
        this.number_of_table = number_of_table;
    }

    public int getNumber_person_table() {
        return number_person_table;
    }

    public void setNumber_person_table(int number_person_table) {
        this.number_person_table = number_person_table;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getReservation_fee() {
        return reservation_fee;
    }

    public void setReservation_fee(int reservation_fee) {
        this.reservation_fee = reservation_fee;
    }

    public int getDuration_time_reservation() {
        return duration_time_reservation;
    }

    public void setDuration_time_reservation(int duration_time_reservation) {
        this.duration_time_reservation = duration_time_reservation;
    }

    public Time getOpening_time() {
        return opening_time;
    }

    public void setOpening_time(Time opening_time) {
        this.opening_time = opening_time;
    }

    public Time getClosing_time() {
        return closing_time;
    }

    public void setClosing_time(Time closing_time) {
        this.closing_time = closing_time;
    }




}
