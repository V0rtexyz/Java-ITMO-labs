package org.example.Commands;


import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static org.example.Commands.CollectionManager.ticketList;

/**
 * Класс AddCommand с переопределенным методом execute, имплементирует интерфейс Command
 * @author Lopatin Ivan
 * @version 1.1
 */
public class ClearCommand implements Command{
    /**
     * Переопределенный метод execute, который очищает коллекцию
     * @param args
     */
    @Override
    public void execute(String[] args){
        if (ticketList.size() == 0){
            System.out.println("Empty Collection");
        }
        else{
            ticketList.clear();
            System.out.println("Collection was cleared");
        }
    }
}
