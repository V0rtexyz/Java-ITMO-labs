package org.example.Commands;

/**
 * Класс AddCommand с переопределенным методом execute, имплементирует интерфейс Command
 * @author Lopatin Ivan
 * @version 1.1
 */
public class UpdateCommand implements Command {
    /**
     * Переопределенный метод execute, который обновляет поля элемента, полученного по id
     * @param args
     */
    @Override
    public void execute(String[] args){
        if (args.length == 2) {
            try {
                Integer num = Integer.parseInt(args[1]);
                CollectionManager.update(num);
            }
            catch (Exception e){
                System.out.println("id must be Integer");
            }
        }
        else{System.out.println("Wrong formant (right: Integer)");}

    }
}