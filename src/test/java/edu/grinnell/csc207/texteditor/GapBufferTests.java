package edu.grinnell.csc207.texteditor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;

public class GapBufferTests {
    @Test
    public void moveLeftRightCornerCases(){
        GapBuffer arr = new GapBuffer(2);
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

        //expansion
        char ch3 = 'c';
        arr.insert(ch3);
        arr.moveRight();
        arr.moveRight();
        arr.moveRight();
        assertEquals(3, arr.getCursorPosition());
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
        GapBuffer st = new GapBuffer(3);
        char ch1 = 'a';
        char ch2 = 'b';
        char ch3 = 'c';
        st.insert(ch1);
        st.insert(ch2);
        st.insert(ch3);
        assertEquals("abc", st.toString());
        assertEquals(3, st.getSize());
        st.delete();
        assertEquals("ab", st.toString());
        assertEquals(3, st.getSize());
    }

    @Test
    public void charAtgetPosAndExpansion(){
        GapBuffer arr = new GapBuffer(3);
        char ch1 = 'a';
        char ch2 = 'b';
        char ch3 = 'c';
        arr.insert(ch1);
        arr.insert(ch2);
        arr.insert(ch3);
        assertEquals(3, arr.getCursorPosition());
        assertEquals('a', arr.getChar(0));
        assertEquals('b', arr.getChar(1));
        assertEquals('c', arr.getChar(2));

        //expansion
        assertEquals(3, arr.getSize());
        char ch4 = 'd';
        arr.insert(ch4);
        assertEquals(6, arr.getSize());
        arr.delete();
        assertEquals(6, arr.getSize());
    }

    @Test
    public void expansionThreeTimes(){
        GapBuffer arr = new GapBuffer(1);
        char ch1 = 'a';
        char ch2 = 'b';
        char ch3 = 'c';
        arr.insert(ch1);
        arr.insert(ch2);
        assertEquals(2, arr.getSize());
        arr.insert(ch3);
        arr.insert(ch1);
        assertEquals(4, arr.getSize());
        arr.insert(ch2);
        assertEquals(8, arr.getSize());
    }
    
    //if we delete any number of elements (smaller then array length), the size stays the same
    @Property
    public void sizeEqualsPos(@ForAll @IntRange(min = 0, max = 100) int toDelete, 
                            @ForAll @IntRange(min = 50, max = 100) int sz){
        GapBuffer arr = new GapBuffer(sz);

        for(int i = 0; i < toDelete; i++){
            arr.delete();
            assertEquals(sz, arr.getSize());
        }
    }
}
