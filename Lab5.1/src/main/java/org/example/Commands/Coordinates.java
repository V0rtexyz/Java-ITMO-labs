package org.example.Commands;
/// Класс закончен
public class Coordinates {
    private Double x; //Максимальное значение поля: 70, Поле не может быть null
    private Integer y; //Поле не может быть null

    public void setX(Double x){
        this.x = x;
    }
    public Double getX(){
        return x;
    }



    public void setY(Integer y){
        this.y = y;
    }
    public Integer getY(){
        return y;
    }

    @Override
    public String toString(){
        return " , coordinates = (x:" + x + " y:" + y + ") ";
    }
}