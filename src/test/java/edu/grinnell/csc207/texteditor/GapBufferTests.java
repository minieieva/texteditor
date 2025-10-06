package edu.grinnell.csc207.texteditor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

public class GapBufferTests {
    @Test
    public void moveLeftRightCornerCases(){
        GapBuffer arr = new GapBuffer(5);
        arr.moveLeft();
        assertEquals(0, arr.getCursorPosition());
        arr.moveRight();
        assertEquals(0, arr.getCursorPosition());

        char ch1 = 'a';
        char ch2 = 'b';
        arr.insert(ch1);
        arr.insert(ch2);

        assertEquals(2, arr.getCursorPosition());
        arr.moveRight();
        assertEquals(2, arr.getCursorPosition());
        arr.moveLeft();
        arr.moveLeft();
        assertEquals(0, arr.getCursorPosition());
        arr.moveLeft();
        assertEquals(0, arr.getCursorPosition());
    }

    @Test
    public void moveLeftRightSimple(){
        GapBuffer st = new GapBuffer(5);
        char ch1 = 'a';
        char ch2 = 'b';
        char ch3 = 'c';
        char ch4 = 'd';
        st.insert(ch1);
        st.insert(ch2);
        st.insert(ch3);
        st.insert(ch4);

        st.moveLeft();
        st.moveLeft();

        assertEquals(2, st.getCursorPosition());

        st.moveRight();

        assertEquals(3, st.getCursorPosition());
    }

    @Test
    public void addCharAndSize(){
        GapBuffer st = new GapBuffer(5);
        assertEquals(5, st.getSize());
        char ch1 = 'i';
        st.insert(ch1);
        assertEquals("i", st.toString());
        char ch2 = 'b';
        st.insert(ch2);
        assertEquals("ib", st.toString());
        assertEquals(5, st.getSize());
    }

    @Test
    public void deleteCharAndSize(){
        GapBuffer st = new GapBuffer(5);
        char ch1 = 'a';
        char ch2 = 'b';
        char ch3 = 'c';
        st.insert(ch1);
        st.insert(ch2);
        st.insert(ch3);
        assertEquals("abc", st.toString());
        assertEquals(5, st.getSize());
        st.delete();
        assertEquals("ab", st.toString());
        assertEquals(5, st.getSize());
    }

    @Test
    public void charAtgetPos(){
        GapBuffer st = new GapBuffer(5);
        char ch1 = 'a';
        char ch2 = 'b';
        char ch3 = 'c';
        st.insert(ch1);
        st.insert(ch2);
        st.insert(ch3);
        assertEquals(3, st.getCursorPosition());
        assertEquals('a', st.getChar(0));
        assertEquals('b', st.getChar(1));
        assertEquals('c', st.getChar(2));
    }
    
    //if we just insert any elements, size always equals pos. 
    @Property
    public void sizeEqualsPos(@ForAll String input){
        GapBuffer st = new GapBuffer(5);

        for(int i = 0; i<input.length(); i++){
            st.insert(input.charAt(i));
            assertEquals(st.getCursorPosition(), st.getSize());
        }
    }
}
