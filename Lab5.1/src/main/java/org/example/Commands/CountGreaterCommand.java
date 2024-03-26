package org.example.Commands;

/**
 * Класс AddCommand с переопределенным методом execute, имплементирует интерфейс Command
 * @author Lopatin Ivan
 * @version 1.1
 */
public class CountGreaterCommand implements Command{
    /**
     * Переопределенный метод execute, который считает количество элементов с ценой большей, чем указанная пользователем
     * @param args
     */
    @Override
    public void execute(String[] args){
        if (args.length == 2) {
            try {
                float num = Float.parseFloat(args[1]);
                CollectionManager.countGreater(num);
            }
            catch (Exception e){
                System.out.println("Price must be Integer");
            }
        }
        else{System.out.println("Wrong formant (right: Integer)");}

    }
}