package org.example.Commands;

/**
 * Класс AddCommand с переопределенным методом execute, имплементирует интерфейс Command
 * @author Lopatin Ivan
 * @version 1.1
 */
public class RemoveCommand implements Command {
    /**
     * Переопределенный метод execute, который удаляет элемент из коллекции по его d
     * @param args
     */
    @Override
    public void execute(String[] args){
        try {
            if (args.length == 2) {
                Integer num = Integer.parseInt(args[1]);
                CollectionManager.remove(num);
            } else {System.out.println("Wrong formant (right: Integer)");}
        }
        catch (Exception e){
                System.out.println("id must be Integer");
        }
    }
}
