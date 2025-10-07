package edu.grinnell.csc207.texteditor;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

/**
 * The driver for the TextEditor Application.
 */
public class TextEditor {

    /**
     * Renders the entire GapBuffer to the given screen, calling screen.refresh() to update the display.
     * @param buf GapBuffer whose contents will be on teh screen.
     * @param screen for rendering.
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


        String path = args[0];
        System.out.format("Loading %s...\n", path);
        

        Screen screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();
        GapBuffer buf = new GapBuffer(50);

        Path pathText = Paths.get(path);
        if (Files.exists(pathText) && Files.isRegularFile(pathText)) {
            String text = Files.readString(pathText);

            for (int i = 0; i < text.length(); i++) {
                char ch = text.charAt(i);
                buf.insert(ch);
            }
            System.out.println("File path read correctly!");
        } 
        else {
            System.out.println("Incorrect file path!");
        }

        boolean isRunning = true;
        while (isRunning) {
            KeyStroke stroke = screen.readInput();
            KeyType stroke_type = stroke.getKeyType();

            if(stroke_type == KeyType.Escape){
                isRunning = false;
            }
            else{
                if(stroke_type == KeyType.Character){
                    buf.insert(stroke.getCharacter());
                }
                else if(stroke_type == KeyType.ArrowLeft){
                    buf.moveLeft();
                }
                else if(stroke_type == KeyType.ArrowRight){
                    buf.moveRight();
                }
                else if(stroke_type == KeyType.Backspace){
                    buf.delete();
                }
                drawBuffer(buf, screen);
            }
        }

        Files.writeString(pathText, buf.toString());

        screen.stopScreen();

        System.out.println("File saved!");
    }
}
