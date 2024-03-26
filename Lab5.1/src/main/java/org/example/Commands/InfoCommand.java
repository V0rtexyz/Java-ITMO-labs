package org.example.Commands;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Класс AddCommand с переопределенным методом execute, имплементирует интерфейс Command
 * @author Lopatin Ivan
 * @version 1.1
 */
public class InfoCommand implements Command {
    /**
     * Переопределенный метод execute, который Выводит информацию о коллекции
     *
     * @param args
     */
    @Override
    public void execute(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\lopat\\IdeaProjects\\Lab5.1\\src\\main\\java\\org\\example\\Commands\\CollectionData.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Дата инициализации коллекции: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Тип коллекции: LinkedList");
        System.out.println("Тип данных, находящийся в коллекции: Ticket");
        System.out.println("Размер коллекции: " + CollectionManager.ticketList.size());
        System.out.println();

    }
}
