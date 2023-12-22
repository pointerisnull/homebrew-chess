/*****************************************************
* COURSE:      CS201                                 *
* DATE:        12/5/2023                             *
*                                                    *
* DESCRIPTION: Class that defines a basic chess piece*
*'symbol' Can be a pawn, knight, bishop, rook, King, *
* or queen, and 'e' for empty space                  *
*               p, n, b, r, k, q                     *
* Example: symbol = p (pawn)                         *
* Color: 0 - back                                    *
*        1 - white                                   *
*****************************************************/
public class Piece {
    private char symbol;
    private int color;
    private int isFirstMove;
    private int securityCode = 111;
    /**********************************************************
    * METHOD: Piece()                                         *
    * DESCRIPTION: Constructor for a chess piece              *
    * PARAMETERS: symbol, color                               *
    * RETURN VALUE: VOID                                      *
    **********************************************************/

    public Piece(char symbol, int color) {
        this.symbol = symbol;
        this.color = color;
        this.isFirstMove = 1;
    }

    public Piece() {
      this.symbol = 'e';
      this.color = -1;
    }
    /*********************************************************
    * METHOD: getType()                                      *
    * DESCRIPTION: return the piece symbol                   *
    * PARAMETERS: VOID                                       *
    * RETURN VALUE: CHAR                                     *
    **********************************************************/
    public char getType() {
        return this.symbol;
    }
    /**********************************************************
     * METHOD: getColor()                                     *
     * DESCRIPTION: returns the piece's color                 *
     * PARAMETERS: VOID                                       *
     * RETURN VALUE: INT                                      *
     **********************************************************/
    public int getColor() {
        return this.color;
    }
    /*********************************************************
    * METHOD: isFirstMove()                                  *
    * DESCRIPTION: is it the piece's first move?             *
    * PARAMETERS: VOID                                       *
    * RETURN VALUE: BOOL                                     *
    *********************************************************/
    
    public boolean isFirstMove() {
      if(isFirstMove == 1) return true;
      else return false;
    }
    /***********************************************************
     * METHOD: setType()                                       *
     * DESCRIPTION: Set's the piece's symbol, if it needs to be*
     *  changed                                                *
     * PARAMETERS: CHAR symbol                                 *
     * RETURN VALUE: VOID                                      *
     **********************************************************/
    public void setType(char t, int code) {
        if(code == this.securityCode)
            this.symbol = t;
    }
    /**********************************************************
    * METHOD: setColor()                                      *
    * DESCRIPTION: sets the piece's color                     *
    * PARAMETERS: COLOR                                       *
    * RETURN VALUE: VOID                                      *
    **********************************************************/
    public void setColor(int c, int code) {
        if(code == this.securityCode) {
            this.color = c;
        }
    }
    /**********************************************************
    * METHOD: firstMove()                                     *
    * DESCRIPTION: called on the piece's first move           *
    * PARAMETERS: VOID                                        *
    * RETURN VALUE: VOID                                      *
    **********************************************************/
    public void hasMoved() {
      this.isFirstMove = 0;
    }
    /**********************************************************
    * METHOD: toString()                                      *
    * DESCRIPTION: Print the object as the value of the card  *
    * PARAMETERS: NULL                                        *
    * RETURN VALUE: String                                    *
    **********************************************************/
    public String toString() {
        char color = (char)this.color;
        char symbol = (char)this.symbol;
        if(this.color < 0) color = '_'; 
        if(this.symbol == 'e') symbol = '_'; 
        return "" + color + symbol;
    }
    /**********************************************************
    * METHOD: printSummary()                                  *
    * DESCRIPTION: Print the object's attribs formatted       *
    * PARAMETERS: NULL                                        *
    * RETURN VALUE: NULL                                      *
    **********************************************************/
    public void printSummary() {
        System.out.printf("Piece: %c\n", this.symbol);
    }
    /**********************************************************
    * METHOD: convertName()                                   *
    * DESCRIPTION: Converts a piece 'char' name to a          *
    *   printable string.                                     *
    * PARAMETERS: CHAR                                        *
    * RETURN VALUE: STRING                                    *
    **********************************************************/
    public String convertName(char in) {
        String name;
        switch (in) {
          case 'k': name = "King";
          case 'q': name = "Queen";
          case 'p': name = "Pawn";
          case 'n': name = "Knight";
          case 'r': name = "Rook";
          case 'b': name = "Bishop";
          case '1': name = "White";
          case '0': name = "Black";
          default: name = "NULL";
        }
        return name;
    }
}
