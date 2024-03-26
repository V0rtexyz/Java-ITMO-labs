package org.example.Commands;

import org.example.Commands.Ticket;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.Commands.Parsers.doubleParser;

/**
 * Класс CollectionManager является микроконтроллером, который содержит методы, реализующие управление коллекцией
 * @author Lopatin Ivan
 * @version 1.1
 */
public class CollectionManager {
    /**
     * Статичное поле, реализующий хранение коллекции
     */
    static LinkedList<Ticket> ticketList = new LinkedList<>();

    /**
     * Позволяет генерировать уникальные id
     * @return Integer
     */
    public static Integer genID(){
        Integer ret = 0;
        Integer[] ID = new Integer[ticketList.size()];
        for (Ticket ticket : ticketList){
            ID[ret] = ticket.getID();
            ret++;
        }
        ret = 1;
        for (Ticket ticket : ticketList){
            int count = 0;
            for (Ticket t : ticketList){
                if (ret.equals(t.getID())){
                    ret++;
                    break;
                }
                count++;
            }
            if (count == ticketList.size()){
                break;}
        }
        return ret;
    }

    /**
     * Метод, добавляющий элементы в коллекцию
     * @param t
     */
    public static void add(Ticket t) {
        ticketList.add(t);
    }

    /**
     * Метод, выводящий коллекцию
     */
    public static void show(){
        for (Ticket ticket : ticketList) {
            System.out.println(ticket.toString());
        }
    }

    /**
     * Сортирует коллекцию в обратном порядке
     */
    public static void reorder(){
        Collections.reverse(ticketList);
    }

    /**
     * Удаляет первый элемент
     */
    public static void removeFirst(){
        System.out.println(ticketList.pollFirst());
    }

    /**
     * Сортирует коллекцию по умолчанию
     */
    public static void sorting(){
        Collections.sort(ticketList, Ticket::compareTo);
    }

    /**
     * Выводит в обратном порядке
     */
    public static void printDescending(){

        LinkedList<Ticket> temporaryCollection = new LinkedList<>();

        temporaryCollection = getCollection();

        Collections.sort(temporaryCollection, Ticket::compareTo);

        if (temporaryCollection.isEmpty()){
            System.out.println("Empty Collection");
        }
        else {
            for (int i = temporaryCollection.size() - 1; i >= 0; i--) {
                if (temporaryCollection.isEmpty()) {
                    System.out.println("Empty Collection");
                    break;
                }
                System.out.println(temporaryCollection.get(i).toString());
            }
        }
    }

    /**
     * Считает, сколько ticket больше указанной стоимости
     * @param pr
     */
    public static void countGreater(float pr){
        int count = 0;
        for (Ticket ticket : ticketList){
            float thiss = ticket.getPrice();
            if (thiss > pr){
                count++;
            }
        }
        System.out.println("The number of items in the collection, with a higher price set = " + count);
    }

    /**
     * Фильтрует tickets, чтобы они содержали указанную подстроку
     * @param str
     */
    public static void filter(String str){
        for (Ticket ticket : ticketList){
            String field = ticket.getComment();
            Pattern pattern = Pattern.compile(str);

            Matcher matcher = pattern.matcher(field);

            if (field.contains(str)){
                System.out.println(ticket);
            }
        }
    }

    /**
     * Удаляет элемент по его id
     * @param ID
     */
    public static void remove(Integer ID){
        boolean check = false;
        int count = 0;
        for (Ticket ticket : ticketList) {
            Integer num = ticket.getID();
            if (Objects.equals(num, ID)){
                check = true;
                ticketList.remove(count);
                System.out.println("Ticket was deleted");
            }
            count++;
        }
        if (!check){
            System.out.println("This ID does not exist");
        }
    }

    /**
     * Получает коллекцию
     * @return
     */
    public static LinkedList<Ticket> getCollection(){
        return ticketList;
    }

    /**
     * Обновляет поля элемента по его id
     * @param ID
     */
    public static void update(Integer ID){
        boolean check = false;
        for (Ticket ticket : ticketList) {
            Integer num = ticket.getID();
            if (Objects.equals(num, ID)) {
                check = true;

                while (true) {
                    System.out.println("Select name: ");
                    Scanner sc = new Scanner(System.in);
                    String name = sc.nextLine();
                    if (Objects.equals(name, "")) {
                        System.out.println("This fields cannot be null");
                    } else {
                        ticket.setName(name);
                        break;
                    }
                }


                ticket.setCreationDate();


                while (true) {
                    System.out.println("Select coordinates(format: x y): ");
                    try {
                        Scanner sc = new Scanner(System.in);
                        String coordinates = sc.nextLine();
                        String[] coordinatesDI = coordinates.split(" ");
                        if (Double.parseDouble(doubleParser(String.valueOf(coordinatesDI[0]))) < 70 && coordinatesDI.length == 2) {
                            ticket.setCoordinates(Double.parseDouble(doubleParser(String.valueOf(coordinatesDI[0]))), Integer.valueOf(coordinatesDI[1]));
                            break;
                        } else {
                            System.out.println("x must be less than 70");
                        }
                    } catch (Exception e){ System.out.println("This fields cannot be null and must be in format(x and y is Integer): x y");}
                }


                while (true) {
                    try {
                        System.out.println("Select price: ");
                        Scanner sc = new Scanner(System.in);
                        String price = sc.nextLine();
                        if (Float.parseFloat(Parsers.floatParser(price)) >= 0) {
                            ticket.setPrice(Float.parseFloat(Parsers.floatParser(price)));
                            break;
                        } else {
                            System.out.println("Price must be greater than 0");
                        }
                    } catch (Exception e) {System.out.println("This fields cannot be null and must be in this format: Rational Number");}
                }


                while (true) {
                    try {
                        System.out.println("Select Discount: ");
                        Scanner sc = new Scanner(System.in);
                        String discount = sc.nextLine();

                        if (Objects.equals(discount, ""))
                        {ticket.setDiscount(0.0);
                            break;}
                        else if ((Double.parseDouble(Parsers.doubleParser(discount)) > 0 && Double.parseDouble(Parsers.doubleParser(discount)) < 100)) {
                            ticket.setDiscount(Double.parseDouble(Parsers.doubleParser(discount)));
                            break;
                        }
                        else {
                            System.out.println("This fields can't be less than 0 and greater than 100");
                        }
                    } catch (Exception e) {System.out.println("This fields cannot be null and must be in this format: Rational Number");}
                }


                while (true) {
                    System.out.println("Select comment: ");
                    Scanner sc = new Scanner(System.in);
                    String comment = sc.nextLine();
                    if (!comment.isEmpty()) {
                        ticket.setComment(comment);
                        break;
                    } else {
                        System.out.println("This fields cannot be null");
                    }
                }

                while (true) {
                    try {
                        System.out.println("Select Ticket Type(VIP, BUDGETARY, CHEAP): ");
                        Scanner sc = new Scanner(System.in);
                        String ticketType = sc.nextLine();
                        if (Arrays.stream(TicketType.values()).anyMatch(e -> e.name().equals(ticketType))) {
                            ticket.setType(TicketType.valueOf(ticketType));
                            break;
                        } else {
                            System.out.println("This fields cannot be null and must be in this format");
                        }
                    } catch (Exception e) {
                        System.out.println("This fields cannot be null and must be in this format");
                    }
                }


                Person person = new Person();


                while (true) {
                    try {
                        System.out.println("Select birthday(format XX.XX.XXXX, Mounth.Day.Year): ");
                        String datePattern = "^(0[1-9]|1[0-2])\\.(0[1-9]|[12][0-9]|3[01])\\.\\d{4}$";
                        Pattern pattern = Pattern.compile(datePattern);

                        Scanner sc = new Scanner(System.in);
                        String date = sc.nextLine();
                        Matcher matcher = pattern.matcher(date);
                        if (matcher.matches()) {
                            String[] words = date.split("\\.");
                            person.setBirthday(Integer.parseInt(words[2]), Integer.parseInt(words[0]), Integer.parseInt(words[1]));
                            break;
                        } else {
                            System.out.println("This fields cannot be null and must be in this format: XX.XX.XXXX, Mounth.Day.Year");
                        }
                    } catch (Exception e) {
                        System.out.println("This fields cannot be null");
                    }
                }
                while (true) {
                    try{
                        System.out.println("Select height: ");
                        Scanner sc = new Scanner(System.in);
                        String height = sc.nextLine();
                        if (Integer.parseInt(height) < 0) {
                            System.out.println("high must be greater than 0");
                        } else {
                            person.setHeight(Integer.parseInt(height));
                            break;
                        }
                    } catch (Exception e){
                        System.out.println("This fields cannot be null and must be in this format: Integer");
                    }
                }

                while (true) {
                    System.out.println("Select passportID: ");
                    Scanner sc = new Scanner(System.in);
                    String passportID = sc.nextLine();
                    if (Objects.equals(passportID, "")) {
                        System.out.println("This fields cannot be null");
                    } else {
                        person.setPassportID(passportID);
                        break;
                    }
                }


                Location locationn = new Location();


                while (true) {
                    System.out.println("Select coordinates of Location(format: x y z): ");
                    try {
                        Scanner sc = new Scanner(System.in);
                        String xyz = sc.nextLine();
                        String[] xyzCORD = xyz.split(" ");
                        if (xyzCORD.length == 2) {
                            locationn.setX(Float.parseFloat(Parsers.floatParser(xyzCORD[0])));
                            locationn.setY(0);
                            locationn.setZ(Float.parseFloat(Parsers.floatParser(xyzCORD[2])));
                            person.setLocation(Float.parseFloat(Parsers.floatParser(xyzCORD[0])), 0, Float.parseFloat(Parsers.floatParser(xyzCORD[2])));

                            break;
                        } else if (xyzCORD.length == 3) {
                            locationn.setX(Float.parseFloat(Parsers.floatParser(xyzCORD[0])));
                            locationn.setY(Double.parseDouble(Parsers.doubleParser(xyzCORD[1])));
                            locationn.setZ(Float.parseFloat(Parsers.floatParser(xyzCORD[2])));
                            person.setLocation(Float.parseFloat(Parsers.floatParser(xyzCORD[0])), Double.parseDouble(Parsers.doubleParser(xyzCORD[1])), Float.parseFloat(Parsers.floatParser(xyzCORD[2])));
                            break;
                        } else {
                            System.out.println("y might be null, but x and z != null");
                        }
                    } catch (Exception e){ System.out.println("This fields cannot be null and must be in format(x and y is Integer): x y");}
                }


                ticket.setPerson(person);


                CollectionManager.add(ticket);
                if (ticketList.size() == 1){
                    try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("C:\\Users\\lopat\\IdeaProjects\\Lab5.1\\src\\main\\java\\org\\example\\Commands\\CollectionData.txt"), "UTF-8")) {
                        writer.write(String.valueOf(ticket.getCreationDate()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Ticket was set");

            }
        }
        if (!check){
            System.out.println("This ID does not exist");
        }
    }
}




