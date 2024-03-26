package org.example.Commands;

/**
 * Класс AddCommand с переопределенным методом execute, имплементирует интерфейс Command
 * @author Lopatin Ivan
 * @version 1.1
 */
public class ShowCommand implements Command {
    /**
     * Переопределенный метод execute, который выводит элементы коллекции
     * @param args
     */
    @Override
    public void execute(String[] args){
        CollectionManager.show();
    }
}
