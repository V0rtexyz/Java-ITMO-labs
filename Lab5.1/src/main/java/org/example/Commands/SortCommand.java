package org.example.Commands;

/**
 * Класс AddCommand с переопределенным методом execute, имплементирует интерфейс Command
 * @author Lopatin Ivan
 * @version 1.1
 */
public class SortCommand implements Command {
    /**
     * Переопределенный метод execute, который сортирует коллекцию по умолчанию
     * @param args
     */
    @Override
    public void execute(String[] args) {
        CollectionManager.sorting();
        System.out.println("Collection was sorted");
    }
}
