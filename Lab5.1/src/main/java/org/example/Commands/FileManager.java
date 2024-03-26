package org.example.Commands;

import java.io.*;
import java.nio.charset.StandardCharsets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;

import java.lang.reflect.Type;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDateTime;


import static java.lang.Character.getType;
import static org.example.Commands.CollectionManager.getCollection;
import static org.example.Commands.CollectionManager.ticketList;

public class FileManager {


    /**
     * В этом методе мы преобразовываем коллекцию LinkedList в JSONArray для последующего сохранения в файл
     */
    public static JSONArray parseHashsetToJson() {
        LinkedList<Ticket> tickets = getCollection();
        JSONArray ticketArray = new JSONArray();
        for (Ticket t : tickets) {
            ticketArray.add(parseStudyGroupToJson(t));
        }
        return ticketArray;
    }

    /**
     * В этом методе мы учебную группу преобразуем в JSONObject
     *
     * @param ticket учебная группа
     * @return JSONObject учебной группы
     */
    public static JSONObject parseStudyGroupToJson(Ticket ticket) {


        JSONObject ticketObject = new JSONObject();
        //Разборка коллекции на составляющие
        int id = ticket.getID();
        String name = ticket.getName();

        Coordinates coordinates = ticket.getCoordinates();
        double x = coordinates.getX();
        Integer y = coordinates.getY();

        String creationDate = ticket.getCreationDate().toString();
        float price = ticket.getPrice();
        Double discount = ticket.getDiscount();
        String comment = ticket.getComment();
        String type = ticket.getType().toString();

        Person person = ticket.getPerson();
        String birthday = person.getBirthday().toString();
        Integer height = person.getHeight();
        String passportID = person.getPassportID();

        Location location = person.getLocation();
        Float X = location.getX();
        double Y = location.getY();
        Float Z = location.getZ();

        ticketObject.put("id", id);
        ticketObject.put("name", name);
        ticketObject.put("creationDate", creationDate);

        //Создание объекта для координат
        JSONObject coordinatesJson = new JSONObject();
        coordinatesJson.put("x", x);
        coordinatesJson.put("y", y);
        ticketObject.put("coordinates", coordinatesJson);

        //Создание объекта для старосты
        ticketObject.put("price", price);
        ticketObject.put("discount", discount);
        ticketObject.put("comment", comment);
        ticketObject.put("type", type);

        JSONObject personJson = new JSONObject();
        personJson.put("birthday", birthday);
        personJson.put("height", height);
        personJson.put("passportID", passportID);

        JSONObject locationJson = new JSONObject();
        locationJson.put("x", X);
        locationJson.put("y", Y);
        locationJson.put("z", Z);
        personJson.put("location", locationJson);

        ticketObject.put("person", personJson);

        return ticketObject;
    }

    /**
     * Данный метод сохраняет нашу коллекцию в файл
     */
    public static void outCollection() {
        String jsonString = beatifulOutputJson();

        String path = System.getenv("json");
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(path), "UTF-8")) {
            writer.write(jsonString);
        } catch (IOException e) {
            System.out.println("Wrong try environment variable");
        }

    }

    /**
     * Данный метод делает красивый вывод
     * @return
     */
    public static String beatifulOutputJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(parseHashsetToJson());
        return jsonString;
    }

    /**
     * В данном методе мы читаем коллекцию из json файла
     * @return JSONArray
     */
    public static JSONArray readJsonFile() throws IOException, ParseException {
        JSONArray jsonArray = null;
        while (true) {
            try {
                String path = System.getenv("json");
            } catch (Exception e) {
                System.out.println("Variable not found");
                break;
            }
            try {
                String path = System.getenv("json");
                JSONArray jsonFile = (JSONArray) new JSONParser().parse(new FileReader(path));
                return jsonFile;
            } catch (Exception e) {
                System.out.println("Warning");
                break;
            }
        }
        return jsonArray;
    }




    /**
     * Данная функция возвращает HashSet из JSONObject
     */
    public static void inCollection() throws IOException, ParseException {
        JSONArray jsonFile = readJsonFile();
        if (jsonFile != null) {
            for (int i = 0; i < jsonFile.size(); i++) {
                try {
                    JSONObject object = (JSONObject) jsonFile.get(i);

                    Ticket ticket = new Ticket();

                    Long id = (Long) object.get("id");
                    String newName = (String) object.get("name");
                    JSONObject coordinates = (JSONObject) object.get("coordinates");
                    String creationDate = (String) object.get("creationDate");
                    Double newPrice = (Double) object.get("price");
                    Double newDiscount = (Double) object.get("discount");
                    String newComment = (String) object.get("comment");
                    JSONObject newPerson = (JSONObject) object.get("person");
                    String newType = (String) object.get("type");

                    int newId = id.intValue();

                    ticket.setID(newId);

                    ticket.setName(newName);

                    //Преобразовать coordinates
                    double x = (double) coordinates.get("x");
                    String y = String.valueOf(coordinates.get("y"));
                    Integer yy = Integer.parseInt(y);
                    ticket.setCoordinates(x, yy);


                    //Преобразовать creationDate в нужный тип
                    LocalDateTime parsed = LocalDateTime.parse(creationDate);
                    ticket.setCreationDatefile(parsed);


                    TicketType type = TicketType.valueOf(newType);
                    ticket.setType(type);


                    String pr = String.valueOf(newPrice);
                    ticket.setPrice(Float.parseFloat(pr));
                    ticket.setDiscount(newDiscount);
                    ticket.setComment(newComment);


                    Person person = new Person();
                    String personHeight = String.valueOf(newPerson.get("height"));
                    String personPassportID = (String) newPerson.get("passportID");

                    String birthday = (String) newPerson.get("birthday");
                    LocalDate p = LocalDate.parse(birthday);
                    person.setBirthdayfile(p);

                    JSONObject newLocation = (JSONObject) newPerson.get("location");
                    String X = String.valueOf(newLocation.get("x"));
                    double Y = (double) newLocation.get("y");
                    String Z = String.valueOf(newLocation.get("z"));


                    Location location = new Location();
                    location.setX(Float.parseFloat(X));
                    location.setY(Y);
                    location.setZ(Float.parseFloat(Z));

                    person.setLocationfile(location);

                    person.setPassportID(personPassportID);

                    person.setHeight(Integer.parseInt(personHeight));
                    person.setLocationfile(location);

                    ticket.setPerson(person);
                    FileManager.Validate(ticket);
                } catch (Exception e){
                    System.out.println("File integrity is compromised");
                }
            }
        }
    }
    public static void Validate(Ticket ticket){
        while (true){
            String name = ticket.getName();
            Coordinates coordinates =  ticket.getCoordinates();
            LocalDateTime creationDate =  ticket.getCreationDate();
            float price =  ticket.getPrice();
            Double discount =  ticket.getDiscount();
            String comment =  ticket.getComment();
            Person person =  ticket.getPerson();

            if (name == "" || comment == "" || person.getPassportID() == ""){
                break;
            }
            if (discount < 0 || discount > 100 || price < 0 || coordinates.getX() > 70 || person.getHeight() < 0){
                break;
            }
            ticketList.add(ticket);
            break;
        }
    }
}





