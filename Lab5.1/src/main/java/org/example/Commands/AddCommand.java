package org.example.Commands;

import org.example.Commands.Ticket;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Objects;
import java.util.Scanner;

import static org.example.Commands.CollectionManager.ticketList;
import static org.example.Commands.Parsers.doubleParser;
import static org.example.Commands.Parsers.floatParser;


/**
 * Класс AddCommand с переопределенным методом execute, имплементирует интерфейс Command
 * @author Lopatin Ivan
 * @version 1.1
 */
public class AddCommand implements Command {
    /**
     * Переопределенный метод execute, который добавляет элемент в коллекцию
     * @param args
     */
    @Override
    public void execute(String[] args) {

            Ticket ticket = new Ticket();

            ticket.updateID();


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

    private Double doubleParer(String str) {
        str = str.replace(",",".");
        return Double.parseDouble(str);
    }
}
