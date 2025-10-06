package edu.grinnell.csc207.texteditor;

/**
 * A gap buffer-based implementation of a text buffer.
 */
public class GapBuffer {

    private int gapStartIndex; 
    private int afterCursorIndex;
    private char[] buffer; 

    public GapBuffer(int size){
        gapStartIndex = 0;
        afterCursorIndex = size;
        buffer = new char[size];
    }

    /**
     * Inserts character ch into the buffer at the cursor's current position, advancing the 
     * cursor one position forward.
     * @param ch a character to insert 
     */
    public void insert(char ch) {
        if(gapStartIndex < afterCursorIndex){
            buffer[gapStartIndex] = ch;
            gapStartIndex++;
        }
        else{
            //expanding
        }
    }

    /**
     * Deletes the character at the cursor's current position, moving the cursor one position 
     * backwards. Does nothing if there are no characters in the buffer.
     */        
    public void delete() {
        if(gapStartIndex> 0){
        gapStartIndex--;
        }
    }

    /**
     * Returns the current position of the cursor.
     * @return the starting index of the gap.
     */        
    public int getCursorPosition() {
        return gapStartIndex;
    }

    /**
     * Moves the cursor one position left. The cursor stays put if it is already at the 
     * beginning of the buffer.
     */
    public void moveLeft() {
        if(gapStartIndex > 0){
            gapStartIndex--;
            afterCursorIndex--;
            buffer[afterCursorIndex] = buffer[gapStartIndex];
        }
    }

    /**
     * Moves the cursor one position forwards. The cursor stays put if it is already at the 
     * end of the buffer.
     */   
    public void moveRight() {
        if(afterCursorIndex < buffer.length){
            buffer[gapStartIndex] = buffer[afterCursorIndex];
            gapStartIndex++;
            afterCursorIndex++;
        }
    }

    /**
     * Returns the size of the buffer.
     * @return the size of the buffer.
     */    
    public int getSize() {
        return buffer.length;
    }

    /**
     * Returns the ith character of the buffer, zero-indexed. Throws an 
     * IndexOutOfBoundsException if i is an invalid index into the buffer.
     * @param i the index of the character that will be returned.
     * @throw IndexOutOfBoundsException if i is invalid index. 
     * @return the character at index i.
     */
    public char getChar(int i) {
        if(i < 0 || i > getSize()-1){
            throw new IndexOutOfBoundsException();
        }
        else{
            char charAtI = buffer[i];
            return charAtI;
        }
    }

    /**
     * Returns the contents of the buffer as a String.
     * @return field buffer: the contents of the buffer as a String.
     */    
    public String toString() {
        String strbeforeGap = new String(buffer, 0, gapStartIndex);
        String strafterGap = new String(buffer, afterCursorIndex, buffer.length - afterCursorIndex);
        return strbeforeGap + strafterGap;
    }
}
