package org.example.Commands;



import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Вызывает срабатывание наших команд
 */
public class Invoker {
    public static void main(String[] args) throws IOException, ParseException {
        Map<String, Command> commands = new HashMap<>();
        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand());
        commands.put("show", new ShowCommand());
        commands.put("add", new AddCommand());
        commands.put("update", new UpdateCommand());
        commands.put("remove_by_id", new RemoveCommand());
        commands.put("clear", new ClearCommand());
        commands.put("save", new SaveCommand());
        commands.put("execute_script", new ExecuteCommand());
        commands.put("remove_first", new RemoveFirstCommand());
        commands.put("reorder",new ReorderCommand());
        commands.put("sort", new SortCommand());
        commands.put("count_greater_than_price", new CountGreaterCommand());
        commands.put("filter_contains_comment", new FlterContainsCommand());
        commands.put("print_decsending", new PrintDescendingCommand());

        System.out.println("Select \"help\" for command list");
        //Проверка ввода, user frendly
        Scanner sc = new Scanner(System.in);
        FileManager.inCollection();
        while(sc.hasNext()) {
                try {
                    String line = sc.nextLine();
                    if (line.equalsIgnoreCase("exit")) {
                        break;
                    }
                    String[] tokens = line.split(" ");
                    Command command = commands.get(tokens[0]);
                    command.execute(tokens);
                } catch (Exception e) {
                    System.out.println("Wrong format");
                }
            }
        }

    }

