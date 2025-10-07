package edu.grinnell.csc207.texteditor;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

/**
 * The driver for the TextEditor Application.
 */
public class TextEditor {

    /**
     * Renders the entire GapBuffer to the given screen, calling screen.refresh() to update the display. 
     * @throws IOException 
     */
    public static void drawBuffer(GapBuffer buf, Screen screen) throws IOException{

        int row = 0;
        int col = 0;
        String str = buf.toString();
        
        for(int i = 0; i< str.length(); i++){
            char ch = str.charAt(i);
            if(ch == '\n'){
                row++;
                col = 0;
            }
            else{
            screen.setCharacter(row, col, new TextCharacter(ch));
            col++;
            }

        }

        screen.setCursorPosition(new TerminalPosition(buf.getCursorPosition(), row));
        screen.refresh();
    }

    /**
     * The main entry point for the TextEditor application.
     * @param args command-line arguments.
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Compiled!");
        if (args.length != 1) {
            System.err.println("Usage: java TextEditor <filename>");
            System.exit(1);
        }

        // TODO: fill me in with a text editor TUI!
        String path = args[0];
        System.out.format("Loading %s...\n", path);

        Screen screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();
        GapBuffer buf = new GapBuffer(2);
        buf.insert('H');
        buf.insert('i');
        drawBuffer(buf, screen);
        
        System.out.println("Press something to stop the screen");
        screen.readInput();
        screen.stopScreen();
    }
}
