/***************************************************
* COURSE:      CS201                               *
* DATE:        12/5/2023                           *
*                                                  *
* DESCRIPTION: Class that defines an array list of *
*  Pieces. Board class is used to manipulate piece *
*  positions as well as display the current state  *
*  of the board.                                   *
***************************************************/
import java.util.ArrayList;
import java.util.Scanner;

public class Board {
    private ArrayList<Piece> board;

    public Board() {
        this.board = new ArrayList<Piece>();
        for(int i = 0; i < 65; i++) {
          Piece empty = new Piece();
          this.board.add(empty);
        }
    }
    /**********************************************************
    * METHOD: convertPos()                                    *
    * DESCRIPTION: converts col and row to array coords       *
    * PARAMETERS: row, col                                    *
    * RETURN VALUE: VOID                                      *
    **********************************************************/
 
    public int convertPos(int c, int r) {
      return (8*r) - (8-c);
    }
     /**********************************************************
     * METHOD: Add()                                           *
     * DESCRIPTION: Add a piece to the array list              *
     * PARAMETERS: Piece, row, col                             *
     * RETURN VALUE: VOID                                      *
     **********************************************************/
    public void add(Piece p, int c, int r) {
        this.board.set(convertPos(c, r), p);
    }
    /**********************************************************
    * METHOD: setup()                                         *
    * DESCRIPTION: Adds all the default peices to the board   *
    * PARAMETERS: VOID                                        *
    * RETURN VALUE: VOID                                      *
    **********************************************************/
    public void setup() {
      String setup = "0r0n0b0q0k0b0n0r0p0p0p0p0p0p0p0p1p1p1p1p1p1p1p1p1r1n1b1q1k1b1n1r";
      int step = 0;
      for(int r = 1; r <= 8; r++) {
        if(r == 1 || r == 2 || r == 7 || r == 8) {
          for(int c = 1; c <= 8; c++) {
            int color = (int)setup.charAt(step)-48;
            char type = setup.charAt(step+1);
            Piece p = new Piece(type, color);
            add(p, c, r);
            step += 2;
          }
        }
      }
    }
    /**********************************************************
    * METHOD: getPiece()                                      *
    * DESCRIPTION: Returns the piece on a position            *
    * PARAMETERS: row, col                                    *
    * RETURN VALUE: Piece                                     *
    **********************************************************/
    public Piece getPiece(int c, int r) {
      Piece p = this.board.get(convertPos(c, r));
      return p;
    }
   /**********************************************************
   * METHOD: promotePawn()                                   *
   * DESCRIPTION: promotes Pawn to piece of user's choice    *
   * PARAMETERS: Piece, newType                              *
   * RETURN VALUE: VOID                                      *
   **********************************************************/
    public void promotePawn(Piece pawn) {
      if(pawn.getType() == 'p') {
        Scanner scanner = new Scanner(System.in);
        System.out.print("What would you like to promote your pawn to? =>");
        char in = scanner.next().charAt(0);
        pawn.setType(in, 111);
      }
    }
   /**********************************************************
   * METHOD: movePiece()                                     *
   * DESCRIPTION: moves a piece to given coordinates. Returns*
   *  the captured piece (if any)                            *
   * PARAMETERS: Piece, col, row, destination                *
   * RETURN VALUE: Captured Piece                            *
   **********************************************************/
    public Piece movePiece(int fCol, int fRow, int toCol, int toRow) {
      Piece p = getPiece(fCol, fRow);
      Piece captured = new Piece();
      if(p.getType() != 'e') {
        if(p.getType() == 'p' && Character.getNumericValue(p.getColor()) == 1 && toRow == 8) promotePawn(p);
        captured = this.board.set(convertPos(toCol, toRow), p);
        Piece e = new Piece();
        add(e, fCol, fRow); 
      }
      return captured;
    }
   /**********************************************************
   * METHOD: print()                                         *
   * DESCRIPTION: Draws the current chess board              *
   * PARAMETERS: VOID                                        *
   * RETURN VALUE: VOID                                      *
   **********************************************************/
    public void print() { 
     /* draw mock board */
      for(int r = 8; r >= 1; r--) {
        for(int c = 1; c <= 8; c++) {
          System.out.print(getPiece(c,r).toString() + " ");
        }
        System.out.println();
      }
    }
}
