# CSC 207: Text Editor

**Author**: Mariia Minieieva

## Resources Used

+ Oracle Class String documentation: https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#String-char:A-int-int-
+ Lab List implementation: https://osera.cs.grinnell.edu/ttap/data-structures-labs/list-implementation.html
+ ...

## Changelog

_(TODO: fill me in with a log of your committed changes)_

## insert method of SimpleStringBuffer: runtime analysis
Relevant inputs: n is the size of the current buffer
Critical operations: concatenations (+) and substrings (substring(0, cursorPos-1), substring(cursorPos, sz);)
Mathematical model: 
T(n) is the time to copy all charaters.
Each of these operations copies characters proportional to the current string size n.
The total time to perform an insert (in the worst case where the cursor is near the end) is:
T(n) = 1*n + 1*n + 1*n = 3n
T(n) = 3n.
Big(O): O(n)
 
Justification:
Java strings are immutable (van't be modified in place) and the consequences of this design decision is the inefficiency of the model. Whenever the character is added to the string, insert method calls substring two times and concatination two times. So four new strings need to be created in order to insert one character.

But if we call insertion multiple times, the total runtime will be O(n^2). To build a string length n, we need to copy (0 + 1 + 2 + 3 + ... + n-1) characters (not just n-1 characters). Each new character takes longer to insert then the previous one. The sum of arithmetic series is
((n(n-1))/2) which corresponds to Big-O: O(n^2). That's why this approach is ineffective. 
