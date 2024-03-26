package org.example.Commands;

import static org.example.Commands.CollectionManager.ticketList;

/**
 * Класс AddCommand с переопределенным методом execute, имплементирует интерфейс Command
 * @author Lopatin Ivan
 * @version 1.1
 */
public class RemoveFirstCommand implements Command {
    /**
     * Переопределенный метод execute, удаляет первый элемент из коллекции
     * @param args
     */
    @Override
    public void execute(String[] args){
        if (ticketList.size() == 0){
            System.out.println("Empty collection");
        }
        else {
            CollectionManager.removeFirst();
            System.out.println("The first element has been deleted");
        }
    }
}
