package org.example.Commands;

/**
 * Класс AddCommand с переопределенным методом execute, имплементирует интерфейс Command
 * @author Lopatin Ivan
 * @version 1.1
 */
public class ReorderCommand implements Command {
    /**
     * Переопределенный метод execute, отсортировывает коллекцию в обратном порядке
     * @param args
     */
    @Override
    public void execute(String[] args){
        System.out.println("Collection was reordered");
        CollectionManager.reorder();
    }
}
