package org.example.Commands;
/**
 * Класс AddCommand с переопределенным методом execute, имплементирует интерфейс Command
 * @author Lopatin Ivan
 * @version 1.1
 */
public class FlterContainsCommand implements Command{
    /**
     * Переопределенный метод execute, который
     * @param args выводит элементы, значение поля comment которых содержит заданную подстроку
     */
    @Override
    public void execute(String[] args){
        if (args.length == 2){
            CollectionManager.filter(args[1]);
        }
        else{System.out.println("Wrong formant (right: Integer)");}
    }
}

