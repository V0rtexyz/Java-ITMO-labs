package org.example.Commands;

public class Location {
    private Float x; //Поле не может быть null
    private double y;
    private Float z; //Поле не может быть null

    public void setX(Float x){
        this.x = x;
    }
    public Float getX(){
        return x;
    }



    public void setY(double y){
        this.y = y;
    }
    public double getY(){
        return y;
    }


    public void setZ(Float z){
        this.z = z;
    }
    public float getZ(){
        return z;
    }

    @Override
    public String toString(){
        return " , Location = (x:" + x + " y:" + y + " z:" + z + ") ";
    }
}
