package org.example.Commands;


import java.time.LocalDate;
import java.time.LocalDateTime;

// Формат Тикета [Name, coordinates X, coordinates Y, price, discount/maybe NULL,
// comment, ticket type/maybe NULL, person Birthday, person Height,
// person passportID, person location x, person location y/maybe NULL, person location z]
public class Ticket implements Comparable {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float price; //Значение поля должно быть больше 0
    private Double discount; //Поле может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 100
    private String comment; //Строка не может быть пустой, Поле не может быть null
    private TicketType type; //Поле может быть null
    private Person person; //Поле не может быть null



    public void updateID(){
        this.id = CollectionManager.genID();
    }

    public void setID(Integer id){
        this.id = id;
    }

    public Integer getID(){return id;}

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }


    public void setCoordinates(Double x, Integer y){
        Coordinates coordinatesXY = new Coordinates();
        coordinatesXY.setX(x);
        coordinatesXY.setY(y);
        this.coordinates = coordinatesXY;

    }
    public Coordinates getCoordinates(){
        return coordinates;
    }



    public void setCreationDate(){
        this.creationDate = LocalDateTime.now();
    }
    public java.time.LocalDateTime getCreationDate(){
        return creationDate;
    }

    public void setCreationDatefile(java.time.LocalDateTime t){
        this.creationDate = t;
    }

    public void setPrice(float price){
        this.price = price;
    }
    public float getPrice(){
        return price;
    }



    public void setDiscount(Double discount){
        this.discount = discount;
    }
    public Double getDiscount(){
        return discount;
    }



    public void setComment(String comment){
        this.comment = comment;
    }
    public String getComment(){
        return comment;
    }



    public void setType(TicketType type){
        this.type = type;
    }
    public TicketType getType(){
        return type;
    }



    public void setPerson(Person person){
        this.person = person;
    }
    public Person getPerson(){
        return person;
    }

    @Override
    public String toString(){
        return "id = " + id + " , name = " + name + coordinates.toString() + " , creation date = "
                + creationDate + " , price = " + price + " , discount = " + discount + " , comment = " + comment +
                " , type = " + type + person.toString() + "\n";
    }
    @Override
    public int compareTo(Object o) {
        var obj = (Ticket) o;
        return this.id-obj.getID();
    }

    public String parseCoordinate(){
        return "";
    }
}
