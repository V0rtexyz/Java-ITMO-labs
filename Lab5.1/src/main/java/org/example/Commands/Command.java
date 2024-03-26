package org.example.Commands;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Интерфейс для команд
 */
public interface Command {
    void execute(String[] args) throws IOException;
}
