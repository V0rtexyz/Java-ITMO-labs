package org.example.Commands;

/**
 * Класс AddCommand с переопределенным методом execute, имплементирует интерфейс Command
 * @author Lopatin Ivan
 * @version 1.1
 */
public class PrintDescendingCommand implements Command {
    /**
     * Переопределенный метод execute, который выводит элементы команды в обратном порядке
     * @param args
     */
    @Override
    public void execute(String[] args){
        CollectionManager.printDescending();
    }
}
