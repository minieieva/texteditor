package edu.grinnell.csc207.texteditor;

/**
 * A naive implementation of a text buffer using a <code>String</code>.
 */
public class SimpleStringBuffer {
    private int sz;
    private int cursorPos;
    private String buffer;

    public SimpleStringBuffer(){
        this.sz = 0; 
        this.cursorPos = 0;
        buffer = new String(); 
    }

    /**
     * Inserts character ch into the buffer at the cursor's current position, advancing the 
     * cursor one position forward.
     * @param ch a character to insert 
     */
    public void insert(char ch) {
        buffer = buffer.substring(0, cursorPos) + ch + buffer.substring(cursorPos, sz);
        sz++;
        cursorPos += 1;
    }

    /**
     *  Deletes the character at the cursor's current position, moving the cursor one position 
     * backwards. Does nothing if there are no characters in the buffer.
     */
    public void delete() {
        if(sz==0){}
        else{
            buffer = buffer.substring(0, cursorPos-1) + buffer.substring(cursorPos, sz);
            cursorPos -= 1;
            sz--; 
        }
    }

    /**
     * Returns the current position of the cursor.
     * @return the current position of the cursor.
     */
    public int getCursorPosition() {
        return cursorPos;
    }

    /**
     * Moves the cursor one position left. The cursor stays put if it is already at the 
     * beginning of the buffer.
     */
    public void moveLeft() {
        if(cursorPos == 0){}
        else{
            cursorPos--;
        }
    }

    /**
     * Moves the cursor one position forwards. The cursor stays put if it is already at the 
     * end of the buffer.
     */
    public void moveRight() {
        if(cursorPos == sz){}
        else{
            cursorPos++;
        }
    }

    /**
     * Returns the size of the buffer.
     * @return the size of the buffer.
     */
    public int getSize() {
        return sz;
    }

    /**
     * Returns the ith character of the buffer, zero-indexed. Throws an 
     * IndexOutOfBoundsException if i is an invalid index into the buffer.
     * @param i the index of the character that will be returned.
     * @throw IndexOutOfBoundsException if i is invalid index. 
     * @return the character at index i.
     */
    public char getChar(int i) {
        if(i < 0 || i > sz-1){
            throw new IndexOutOfBoundsException();
        }
        else{
            char charAtI = buffer.charAt(i);
            return charAtI;
        }
    }

    /**
     * Returns the contents of the buffer as a String.
     * @return field buffer: the contents of the buffer as a String.
     */
    @Override
    public String toString() {
        return buffer;
    }
}
