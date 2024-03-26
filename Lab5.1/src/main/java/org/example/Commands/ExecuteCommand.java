package org.example.Commands;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.Commands.CollectionManager.ticketList;
import static org.example.Commands.Parsers.doubleParser;


/**
 * Данный класс реализует команду execute_script
 * Команда execute_script исполняет скрипт из указанного файла
 * Данный класс реализует интерфейс Command
 */
public class ExecuteCommand implements Command {

    private static final int MAX_DEPTH = 10;
    /**
     * Текучая глубина рекурсии
     */
    private static int recursionDepth = 0;

    /**
     * Метод выполнения команды
     * 1. Находим нужный файл со скриптом
     * 2. Считываем строку
     * 3. Анализируем тип команды
     * 4. Выполняем команду
     */
    @Override
    public void execute(String[] args) {
        //Найти такой файл
        String fileName = "0";
        try {
            if (args.length != 2) {
                System.out.println("Wrong format");
            } else {
                fileName = args[1];
            }
        } catch (Exception e) {
            System.out.println("Select filename");
        }

        try (FileReader fr = new FileReader("C:\\Users\\lopat\\IdeaProjects\\Lab5.1\\src\\main\\java\\org\\example\\Commands\\executor.txt")) {
            recursionDepth += 1;
            Scanner scan = new Scanner(fr);
            Map<String, Command> commands = new HashMap<>();
            commands.put("help", new HelpCommand());
            commands.put("info", new InfoCommand());
            commands.put("show", new ShowCommand());
            commands.put("add", new AddCommand());
            commands.put("update", new UpdateCommand());
            commands.put("remove_by_id", new RemoveCommand());
            commands.put("clear", new ClearCommand());
            commands.put("save", new SaveCommand());
            commands.put("execute_script", new ExecuteCommand());
            commands.put("remove_first", new RemoveFirstCommand());
            commands.put("reorder", new ReorderCommand());
            commands.put("sort", new SortCommand());
            commands.put("count_greater_than_price", new CountGreaterCommand());
            commands.put("filter_contains_comment", new FlterContainsCommand());
            commands.put("print_decsending", new PrintDescendingCommand());
            Map<String, Integer> recursionList = new HashMap<>();
            while (scan.hasNext()) {
                try {
                    String line = scan.nextLine();
                    if (line.equalsIgnoreCase("exit")) {
                        if (recursionList.containsKey("exit")) {
                            Integer V = recursionList.get("exit");
                            recursionList.remove("exit");
                            recursionList.put("exit", V++);

                        } else {
                            recursionList.put("exit", 1);
                        }
                        break;
                    }


                    String[] token = line.split(" ");
                    Command command = commands.get(token[0]);
                    if (token[0].equals("add")) {
                        Ticket ticket = new Ticket();

                        ticket.updateID();


                        while (scan.hasNext()) {
                            System.out.println("Select name: ");
                            String name = scan.nextLine();
                            if (Objects.equals(name, "")) {
                                System.out.println("This fields cannot be null");
                            } else {
                                ticket.setName(name);
                                break;
                            }
                        }


                        ticket.setCreationDate();


                        while (scan.hasNext()) {
                            System.out.println("Select coordinates(format: x y): ");
                            try {

                                String coordinates = scan.nextLine();
                                String[] coordinatesDI = coordinates.split(" ");
                                if (Double.parseDouble(doubleParser(String.valueOf(coordinatesDI[0]))) < 70 && coordinatesDI.length == 2) {
                                    ticket.setCoordinates(Double.parseDouble(doubleParser(String.valueOf(coordinatesDI[0]))), Integer.valueOf(coordinatesDI[1]));
                                    break;
                                } else {
                                    System.out.println("x must be less than 70");
                                }
                            } catch (Exception e) {
                                System.out.println("This fields cannot be null and must be in format(x and y is Integer): x y");
                            }
                        }


                        while (scan.hasNext()) {
                            try {
                                System.out.println("Select price: ");
                                String price = scan.nextLine();
                                if (Float.parseFloat(Parsers.floatParser(price)) >= 0) {
                                    ticket.setPrice(Float.parseFloat(Parsers.floatParser(price)));
                                    break;
                                } else {
                                    System.out.println("Price must be greater than 0");
                                }
                            } catch (Exception e) {
                                System.out.println("This fields cannot be null and must be in this format: Rational Number");
                            }
                        }


                        while (scan.hasNext()) {
                            try {
                                System.out.println("Select Discount: ");
                                String discount = scan.nextLine();

                                if (Objects.equals(discount, "")) {
                                    ticket.setDiscount(0.0);
                                    break;
                                } else if ((Double.parseDouble(Parsers.doubleParser(discount)) > 0 && Double.parseDouble(Parsers.doubleParser(discount)) < 100)) {
                                    ticket.setDiscount(Double.parseDouble(Parsers.doubleParser(discount)));
                                    break;
                                } else {
                                    System.out.println("This fields can't be less than 0 and greater than 100");
                                }
                            } catch (Exception e) {
                                System.out.println("This fields cannot be null and must be in this format: Rational Number");
                            }
                        }


                        while (scan.hasNext()) {
                            System.out.println("Select comment: ");
                            String comment = scan.nextLine();
                            if (!comment.isEmpty()) {
                                ticket.setComment(comment);
                                break;
                            } else {
                                System.out.println("This fields cannot be null");
                            }
                        }

                        while (scan.hasNext()) {
                            try {
                                System.out.println("Select Ticket Type(VIP, BUDGETARY, CHEAP): ");

                                String ticketType = scan.nextLine();
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


                        while (scan.hasNext()) {
                            try {
                                System.out.println("Select birthday(format XX.XX.XXXX, Mounth.Day.Year): ");
                                String datePattern = "^(0[1-9]|1[0-2])\\.(0[1-9]|[12][0-9]|3[01])\\.\\d{4}$";
                                Pattern pattern = Pattern.compile(datePattern);

                                String date = scan.nextLine();
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
                        while (scan.hasNext()) {
                            try {
                                System.out.println("Select height: ");

                                String height = scan.nextLine();
                                if (Integer.parseInt(height) < 0) {
                                    System.out.println("high must be greater than 0");
                                } else {
                                    person.setHeight(Integer.parseInt(height));
                                    break;
                                }
                            } catch (Exception e) {
                                System.out.println("This fields cannot be null and must be in this format: Integer");
                            }
                        }

                        while (scan.hasNext()) {
                            System.out.println("Select passportID: ");

                            String passportID = scan.nextLine();
                            if (Objects.equals(passportID, "")) {
                                System.out.println("This fields cannot be null");
                            } else {
                                person.setPassportID(passportID);
                                break;
                            }
                        }


                        Location locationn = new Location();


                        while (scan.hasNext()) {
                            System.out.println("Select coordinates of Location(format: x y z): ");
                            try {
                                String xyz = scan.nextLine();
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
                            } catch (Exception e) {
                                System.out.println("This fields cannot be null and must be in format(x and y is Integer): x y");
                            }
                        }


                        ticket.setPerson(person);


                        CollectionManager.add(ticket);
                        if (ticketList.size() == 1) {
                            try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("C:\\Users\\lopat\\IdeaProjects\\Lab5.1\\src\\main\\java\\org\\example\\Commands\\CollectionData.txt"), "UTF-8")) {
                                writer.write(String.valueOf(ticket.getCreationDate()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("Ticket was set");
                    } else if (args[0].equals("update") && args.length == 2) {
                        boolean check = false;
                        for (Ticket ticket : ticketList) {
                            Integer num = ticket.getID();
                            if (Objects.equals(num, Integer.parseInt(args[1]))) {
                                check = true;
                                while (scan.hasNext()) {
                                    System.out.println("Select name: ");
                                    Scanner sc = new Scanner(fr);
                                    String name = sc.nextLine();
                                    if (Objects.equals(name, "")) {
                                        System.out.println("This fields cannot be null");
                                    } else {
                                        ticket.setName(name);
                                        break;
                                    }
                                }


                                ticket.setCreationDate();


                                while (scan.hasNext()) {
                                    System.out.println("Select coordinates(format: x y): ");
                                    try {
                                        Scanner sc = new Scanner(fr);
                                        String coordinates = sc.nextLine();
                                        String[] coordinatesDI = coordinates.split(" ");
                                        if (Double.parseDouble(doubleParser(String.valueOf(coordinatesDI[0]))) < 70 && coordinatesDI.length == 2) {
                                            ticket.setCoordinates(Double.parseDouble(doubleParser(String.valueOf(coordinatesDI[0]))), Integer.valueOf(coordinatesDI[1]));
                                            break;
                                        } else {
                                            System.out.println("x must be less than 70");
                                        }
                                    } catch (Exception e) {
                                        System.out.println("This fields cannot be null and must be in format(x and y is Integer): x y");
                                    }
                                }


                                while (scan.hasNext()) {
                                    try {
                                        System.out.println("Select price: ");
                                        Scanner sc = new Scanner(fr);
                                        String price = sc.nextLine();
                                        if (Float.parseFloat(Parsers.floatParser(price)) >= 0) {
                                            ticket.setPrice(Float.parseFloat(Parsers.floatParser(price)));
                                            break;
                                        } else {
                                            System.out.println("Price must be greater than 0");
                                        }
                                    } catch (Exception e) {
                                        System.out.println("This fields cannot be null and must be in this format: Rational Number");
                                    }
                                }


                                while (scan.hasNext()) {
                                    try {
                                        System.out.println("Select Discount: ");
                                        Scanner sc = new Scanner(fr);
                                        String discount = sc.nextLine();

                                        if (Objects.equals(discount, "")) {
                                            ticket.setDiscount(0.0);
                                            break;
                                        } else if ((Double.parseDouble(Parsers.doubleParser(discount)) > 0 && Double.parseDouble(Parsers.doubleParser(discount)) < 100)) {
                                            ticket.setDiscount(Double.parseDouble(Parsers.doubleParser(discount)));
                                            break;
                                        } else {
                                            System.out.println("This fields can't be less than 0 and greater than 100");
                                        }
                                    } catch (Exception e) {
                                        System.out.println("This fields cannot be null and must be in this format: Rational Number");
                                    }
                                }


                                while (scan.hasNext()) {
                                    System.out.println("Select comment: ");
                                    Scanner sc = new Scanner(fr);
                                    String comment = sc.nextLine();
                                    if (!comment.isEmpty()) {
                                        ticket.setComment(comment);
                                        break;
                                    } else {
                                        System.out.println("This fields cannot be null");
                                    }
                                }

                                while (scan.hasNext()) {
                                    try {
                                        System.out.println("Select Ticket Type(VIP, BUDGETARY, CHEAP): ");
                                        Scanner sc = new Scanner(fr);
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


                                while (scan.hasNext()) {
                                    try {
                                        System.out.println("Select birthday(format XX.XX.XXXX, Mounth.Day.Year): ");
                                        String datePattern = "^(0[1-9]|1[0-2])\\.(0[1-9]|[12][0-9]|3[01])\\.\\d{4}$";
                                        Pattern pattern = Pattern.compile(datePattern);

                                        Scanner sc = new Scanner(fr);
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
                                while (scan.hasNext()) {
                                    try {
                                        System.out.println("Select height: ");
                                        Scanner sc = new Scanner(fr);
                                        String height = sc.nextLine();
                                        if (Integer.parseInt(height) < 0) {
                                            System.out.println("high must be greater than 0");
                                        } else {
                                            person.setHeight(Integer.parseInt(height));
                                            break;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("This fields cannot be null and must be in this format: Integer");
                                    }
                                }

                                while (scan.hasNext()) {
                                    System.out.println("Select passportID: ");
                                    Scanner sc = new Scanner(fr);
                                    String passportID = sc.nextLine();
                                    if (Objects.equals(passportID, "")) {
                                        System.out.println("This fields cannot be null");
                                    } else {
                                        person.setPassportID(passportID);
                                        break;
                                    }
                                }


                                Location locationn = new Location();


                                while (scan.hasNext()) {
                                    System.out.println("Select coordinates of Location(format: x y z): ");
                                    try {
                                        Scanner sc = new Scanner(fr);
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
                                    } catch (Exception e) {
                                        System.out.println("This fields cannot be null and must be in format(x and y is Integer): x y");
                                    }
                                }
                                ticket.setPerson(person);
                            }

                            Integer V = recursionList.get(token[0]);
                            recursionList.remove(token[0]);
                            recursionList.put(token[0], ++V);
                            if (++V > MAX_DEPTH) {
                                System.out.println("Max depth ofrecursion");
                                break;
                            }
                        }
                    } else if (command == null) {
                        System.out.println("Null found");
                    } else {
                        command.execute(token);
                    }
                    Integer V = recursionList.get(token);
                    recursionList.remove(token);
                    recursionList.put(String.valueOf(token), ++V);
                    if (++V > MAX_DEPTH) {
                        System.out.println("Max depth ofrecursion");
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Warning");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
