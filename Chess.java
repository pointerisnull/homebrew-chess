/***************************************************
* TITLE:       HOMEBREW CHESS                      *
*            FINAL PROJECT CS201                   *
* COURSE:      CS201                               *
* DATE:        12/5/2023                           *
*                                                  *
* DESCRIPTION: The main function for chess         *
*                                                  *
* CREDIT: I WROTE EVERYTHING FROM SCRATCH, NO CODE *
*  HAS BEEN COPIED FROM ANYWHERE                   *
***************************************************/

public class Chess {
  /**********************************************************
  * METHOD: checkRookLegallity()                            *
  * DESCRIPTION: Checks Rook logic (for rooks and queens)   *
  * PARAMETERS: Board board,                                *
  *      int fcol, int frow, int tocol, int torow           *
  * RETURN VALUE: BOOLEAN                                   *
  **********************************************************/
  public static boolean checkRookLegallity(Board board, int fcol, int frow, int tocol, int torow) {
    Piece attacker = board.getPiece(fcol, frow);
    Piece victim = board.getPiece(tocol, torow);
    char type = attacker.getType();
    char victimType = victim.getType();
    int attackColor = attacker.getColor();
    int victimColor = victim.getColor();
    //horizontal
    if(frow == torow) {
      //check if pieces are in the way
      if(tocol > fcol) {
        for(int colCheck = fcol+1; colCheck < tocol; colCheck++) {
          if(board.getPiece(colCheck, frow).getType() != 'e') return false;
        }
      } else if(tocol < fcol) {
        for(int colCheck = tocol+1; colCheck < fcol-1; colCheck++) {
          if(board.getPiece(colCheck, frow).getType() != 'e') return false;
        } 
      }
      if(victimType != 'e' && victimColor == attackColor) return false;
      return true;
    }
    //vertical
    if(fcol == tocol) {
      if(frow < torow) {
        for(int rowCheck = frow+1; rowCheck < torow; rowCheck++) {
          if(board.getPiece(fcol, rowCheck).getType() != 'e') return false;
        }
      } else if(torow < frow) {
        for(int rowCheck = torow+1; rowCheck < frow; rowCheck++) {
          if(board.getPiece(fcol, rowCheck).getType() != 'e') return false;
        }
      }

      if(victimType != 'e' && victimColor == attackColor) return false;
      return true;
    }
    return false;
  }
  /**********************************************************
  * METHOD: checkBishopLegallity()                          *
  * DESCRIPTION: Checks Bishop logic(for bishops and queens)*
  * PARAMETERS: Board board,                                *
  *      int fcol, int frow, int tocol, int torow           *
  * RETURN VALUE: BOOLEAN                                   *
  **********************************************************/
 
  public static boolean checkBishopLegallity(Board board, int fcol, int frow, int tocol, int torow) {
    Piece attacker = board.getPiece(fcol, frow);
    Piece victim = board.getPiece(tocol, torow);
    char type = attacker.getType();
    char victimType = victim.getType();
    int attackColor = attacker.getColor();
    int victimColor = victim.getColor();
    //north east
    if(tocol > fcol && torow < frow) {
      int cdist = tocol - fcol;
      int rdist = frow - torow;
      if(cdist == rdist) {
        if(attackColor == victimColor) return false;

      } else return false;

      return true;
    }
    //north west
    if(fcol > tocol && torow < frow) {
      int cdist = fcol - tocol;
      int rdist = frow - torow;
      if(cdist == rdist) {
        if(attackColor == victimColor) return false;

      } else return false;
      return true;
    }   
    //south east
    if(tocol > fcol && torow > frow) {
      int cdist = tocol - fcol;
      int rdist = torow - frow;
      if(cdist == rdist) {
        if(attackColor == victimColor) return false;

      } else return false;
 
      return true;
    }

    //south west
    if(fcol > tocol && torow > frow) {
      int cdist = fcol - tocol;
      int rdist = torow - frow;
      if(cdist == rdist) {
        if(attackColor == victimColor) return false;

      } else return false;
     
      return true;
    }   

    return false;
  }
  /**********************************************************
  * METHOD: isLegalMove()                                   *
  * DESCRIPTION: Checks Piece                               *
  * PARAMETERS: Board board,                                *
  *      int fcol, int frow, int tocol, int torow           *
  * RETURN VALUE: BOOLEAN                                   *
  **********************************************************/
 
  public static boolean isLegalMove(Board board, int fcol, int frow, int tocol, int torow) {
    Piece attacker = board.getPiece(fcol, frow);
    Piece victim = board.getPiece(tocol, torow);
    char type = attacker.getType();
    char victimType = victim.getType();
    int attackColor = attacker.getColor();
    int victimColor = victim.getColor();

    switch(type) {
      case 'p': 
        //first move, pawn can move foward 2 spaces (optional) 
        if(attacker.isFirstMove()) {
          //black
          if(attackColor == 0) {
            if((torow-frow == 1 || torow - frow == 2) && tocol == fcol && victimType == 'e')
              return true;
          }else if(attackColor == 1) {
            if((frow-torow == 1 || frow - torow == 2) &&  tocol == fcol && victimType == 'e')
              return true;
          }
        }
        //normal case
        if(attackColor == 0) {
          if((torow-frow == 1) && tocol == fcol && victimType == 'e')
            return true;
        }else if(attackColor == 1) {
          if((frow-torow == 1) &&  tocol == fcol && victimType == 'e')
            return true;
        }
        //attack case
        if(attackColor == 0) {
          if((torow-frow == 1) && (tocol+1 == fcol || tocol-1 == fcol) && victimType != 'e' && victimColor != attackColor)
            return true;
        }else if(attackColor == 1) {
          if((frow-torow == 1) &&  (tocol+1 == fcol || tocol-1 == fcol) && victimType != 'e' && victimColor != attackColor)
            return true;
        }
        break;
      case 'r':
        if(checkRookLegallity(board, fcol, frow, tocol, torow)) return true; 
        break;
      case 'b':
        if(checkBishopLegallity(board, fcol, frow, tocol, torow)) return true; 
        break;
      case 'q':
        if(checkBishopLegallity(board, fcol, frow, tocol, torow)) return true;
        else if(checkRookLegallity(board, fcol, frow, tocol, torow)) return true; 
        break;
      case 'k':
        if(attackColor != victimColor) {
          //vertical
          if(tocol == fcol) 
            if(Math.abs(torow-frow) == 1) return true;
          //horizontal
          if(torow == frow) 
            if(Math.abs(tocol-fcol) == 1) return true;
          //diagonal
          if(Math.abs(tocol-fcol)==1 && Math.abs(frow-torow)==1) return true;
        }
        break;
      case 'n':
        if(attackColor != victimColor) {
          if((torow > 0 && torow <= 8) && (tocol > 0 && tocol <= 8)) {
            //north east
            if((frow - torow) == 2 && (tocol - fcol) == 1) return true;
            if((frow - torow) == 1 && (tocol - fcol) == 2) return true;
            //south east
            if((torow - frow) == 2 && (tocol - fcol) == 1) return true;
            if((torow - frow) == 1 && (tocol - fcol) == 2) return true;
            //north west
            if((frow - torow) == 2 && (fcol - tocol) == 1) return true;
            if((frow - torow) == 1 && (fcol - tocol) == 2) return true;
            //south west
            if((torow - frow) == 2 && (fcol - tocol) == 1) return true;
            if((torow - frow) == 1 && (fcol - tocol) == 2) return true;
          }
        }
        break;
      default:

        break;
    }
    return false;
  }
  /**********************************************************
  * METHOD: Main()                                          *
  * DESCRIPTION: Plays Chess                                *
  * RETURN VALUE: void                                      *
  **********************************************************/
 
  public static void main(String[] args) {
    int done = 0;
    Board board = new Board();
    board.setup();
    Display display = new Display();
    int donelol = 0;
    while(done == 0) {
      if(display.getFirstIndex() != 0 && display.getSecondIndex() != 0) {
        int frow = display.getFirstIndex()/8+1;
        int fcol = display.getFirstIndex()%8;
        int torow = display.getSecondIndex()/8+1;
        int tocol = display.getSecondIndex()%8;
        /*fixex weird bug where the left col was shifted up*/
        if(tocol == 0) {
          torow -=1 ;
          tocol = 8; 
        }
        if(fcol == 0) {
          frow -= 1;
          fcol = 8;
        }

        System.out.println("FromCol: " + fcol + " ToCol: " + tocol);
        System.out.println("FromRow: " + frow + " ToRow: " + torow);
        if(isLegalMove(board, fcol, frow, tocol, torow)) {
          board.movePiece(fcol, frow, tocol, torow);
          board.getPiece(tocol, torow).hasMoved();
        }
        display.resetSelected();
      }
      display.updateBoard(board);
    }
  } 
}
