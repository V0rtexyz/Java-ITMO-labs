package org.example.Commands;

/**
 * Класс HelpCommand с переопределенным методом execute, имплеминтирует интерфейс Command
 * @author Lopatin Ivan
 * @version 1.1
 */
public class HelpCommand implements Command{
    /**
     * Переопределенный метод execute, который выводит справку по командам
     * @param args
     */
    @Override
    public void execute(String[] args){
        System.out.println("""
                +++help:вывести справку по доступным командам
                +++info:вывести в стандартный поток вывода информацию о коллекции(тип, дата инициализации, количество элементов и т.д.)
                +++show:вывести в стандартный поток вывода все элементы коллекции в строковом представлении
                +++add {element}:добавить новый элемент в коллекцию
                +++update id {element}:обновить значение элемента коллекции, if которого равен заданному
                +++remove_by_id:удалить элемент из коллекции по его id
                +++clear:очистить коллекцию
                +++save:сохранить коллекцию в файл
                +++execute_script file_name:считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в которых их вводит пользователь в интерактивном режиме
                +++exit:завершить программу(без сохранения  файл)
                +++remove_first:удалить первый элемент из коллекции
                +++reorder:отсортировать коллекцию в порядке, обратному, нынешнему
                +++sort:отсортировать коллекцию в естественном порядке
                +++count_greater_than_price price:вывести количество элементов, значение поля price которых больше заданного
                +++filter_contains_comment comment:вывести элементы, значение поля comment которых содержит заданную подстроку
                +++print_descending:вывести элементы коллекции в порядке убывания
                """);
    }
}
