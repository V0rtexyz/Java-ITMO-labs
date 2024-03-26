package org.example.Commands;


import java.time.LocalDate;

public class Person {
    public java.time.LocalDate birthday; //Поле не может быть null
    private Integer height; //Поле не может быть null, Значение поля должно быть больше 0
    private String passportID; //Поле не может быть null
    private Location locationXYZ; //Поле может быть null


    public void setBirthday(Integer year, Integer month, Integer day){
        this.birthday = java.time.LocalDate.of(year, month, day);
    }

    public void setBirthdayfile(java.time.LocalDate t){
        this.birthday = t;
    }

    public java.time.LocalDate getBirthday(){
        return birthday;
    }



    public void setHeight(Integer height){
        this.height = height;
    }
    public Integer getHeight(){
        return height;
    }



    public void setPassportID(String passportID){
        this.passportID = passportID;
    }
    public String getPassportID(){
        return passportID;
    }


    public void setLocation(Float x, double y, Float z){
        Location locationXYZ = new Location();
        locationXYZ.setX(x);
        locationXYZ.setY(y);
        locationXYZ.setZ(z);
        this.locationXYZ = locationXYZ;
    }

    public void setLocationfile(Location l){

        this.locationXYZ = l;
    }

    public Location getLocation(){
        return locationXYZ;
    }

    @Override
    public String toString(){
        return " , birthday = " + birthday + " , Height = " + height + " , PassportID = " + passportID + locationXYZ.toString();
    }
}




