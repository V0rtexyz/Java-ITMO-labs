package org.example.Commands;

import org.example.Commands.Command;
import org.example.Commands.FileManager;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Класс AddCommand с переопределенным методом execute, имплементирует интерфейс Command
 * @author Lopatin Ivan
 * @version 1.1
 */
public class SaveCommand implements Command {
    /**
     * Переопределенный метод execute, который сохраняет коллекцию в файл
     * @param args
     */
    @Override
    public void execute(String[] args) throws IOException {
        FileManager.outCollection();
    }
}
