package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
class Database {

    private int sno, smoke, co2;
    private String floor, room,status;

    public Database(int sno,  String floor, String room,int smoke, int co2,String status) {
        this.sno = sno;
        this.smoke = smoke;
        this.co2 = co2;
        this.floor = floor;
        this.room = room;
        this.status = status;
    }

    public int getSno() {
        return sno;
    }

    public int getSmoke() {
        return smoke;
    }

    public int getCo2() {
        return co2;
    }

    public String getFloor() {
        return floor;
    }

    public String getRoom() {
        return room;
    }
    
    public String getStatus(){
        return status;
    }

}