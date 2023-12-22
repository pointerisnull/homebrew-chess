/*****************************************************
* COURSE:      CS201                                 *
* DATE:        12/5/2023                             *
*                                                    *
* DESCRIPTION: Class that defines the graphic portion*
* of the program, and handles user interaction.      *
*****************************************************/


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Display extends JFrame implements ActionListener{
  JPanel pane;
  JButton[] squares;
  int firstSelect;
  int secondSelect;
  int flag;
  Color lastColor;
  /**********************************************************
  * METHOD: Display()                                       *
  * DESCRIPTION: Creates a Display Class                    *
  * PARAMETERS: VOID                                        *
  * RETURN VALUE: VOID                                      *
  **********************************************************/
 
  public Display() {
    this.firstSelect = -1; 
    this.secondSelect = -1; 
    this.flag = 1; 
    ImageIcon image = new ImageIcon("res/board.png");
    Image tempImage = image.getImage();
    Image temp = tempImage.getScaledInstance(512, 512,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
    image = new ImageIcon(temp);
    
    JLabel label = new JLabel();
    squares = new JButton[64]; 
  
    label.setIcon(image);

    JFrame frame = new JFrame("Homebrew Chess");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setSize(512, 512);
    frame.setLayout(null);
 
    pane = new JPanel();
    pane.setBounds(6, 6, 500, 500);
    frame.add(pane);
    Color backgroundColor = new Color(188, 143, 143);
    Color whiteColor = new Color(255, 228, 181);
    Color blackColor = new Color(101, 67, 33);
    lastColor = whiteColor;
    frame.getContentPane().setBackground(backgroundColor);
    pane.setLayout(new GridLayout(8, 8));
    pane.setBackground(backgroundColor);
    pane.setBorder(BorderFactory.createEtchedBorder(new Color(55,55, 55), new Color(20, 20, 20)));
    int colorSwitch = 0;

    for(int i = 0; i < 64; i++) {
      if(i % 8 == 0) colorSwitch = (colorSwitch == 1) ? 0 : 1;
      squares[i] = new JButton("");
      if(colorSwitch == 1) {
        squares[i].setBackground(whiteColor);
        colorSwitch = 0;
      } else {
        squares[i].setBackground(blackColor);
        colorSwitch = 1;
      }
      squares[i].addActionListener(this);
      squares[i].setBorder(BorderFactory.createEtchedBorder(new Color(55,55, 55), new Color(20, 20, 20)));
      pane.add(squares[i]);
    }
    frame.setVisible(true);

  }
  /**********************************************************
  * METHOD: actionPerformed()                               *
  * DESCRIPTION: listens for mouse button presses           *
  * PARAMETERS: ActionEvent                                 *
  * RETURN VALUE: VOID                                      *
  **********************************************************/
 
  @Override
  public void actionPerformed(ActionEvent e) {
    for(int i = 0; i < 64; i++) {
      if(e.getSource() == this.squares[i]) {
        /* square is already selected, deselect */
        if(this.firstSelect == i) {
          squares[this.firstSelect].setBackground(this.lastColor);
          this.firstSelect = -1;
          this.secondSelect = -1;
        /* first select */
        } else if(this.firstSelect == -1) {
            this.lastColor = squares[i].getBackground();
            this.firstSelect = i;
            this.squares[i].setBackground(new Color(0, 200, 200));
        /* second select */
        } else if(this.secondSelect == -1){
            this.secondSelect = i;
            this.squares[this.firstSelect].setBackground(this.lastColor);
        }
      }
    }
  }
  /**********************************************************
  * METHOD: get____Index()                                  *
  * DESCRIPTION: returns the selected postion index         *
  * PARAMETERS: VOID                                        *
  * RETURN VALUE: VOID                                      *
  **********************************************************/
 
  public int getFirstIndex() {
    return this.firstSelect+1;
  }

  public int getSecondIndex() {
    return this.secondSelect+1;
  }
  /**********************************************************
  * METHOD: resetSelected()                                 *
  * DESCRIPTION: resets the selected indexes to nothing     *
  * PARAMETERS: VOID                                        *
  * RETURN VALUE: VOID                                      *
  **********************************************************/
 
  public void resetSelected() {
    this.firstSelect = -1;
    this.secondSelect = -1;
  }
  /**********************************************************
  * METHOD: updateBoard()                                   *
  * DESCRIPTION: updates the board position's/colors        *
  * PARAMETERS: Board                                       *
  * RETURN VALUE: VOID                                      *
  **********************************************************/
 
  public void updateBoard(Board board) {
    for(int r = 1; r <= 8; r++) {
      for(int c = 1; c <= 8; c++) {
        if(board.getPiece(c,r).getType() != 'e') {
          char type = board.getPiece(c, r).getType();
          int color = board.getPiece(c, r).getColor();
          ImageIcon icon;
          if(type == 'p') {
            if(color == 1)
              icon = new ImageIcon("res/wp.png");
            else
              icon = new ImageIcon("res/bp.png");
          }
          else if(type == 'r') {
            if(color == 1)
              icon = new ImageIcon("res/wr.png");
            else
              icon = new ImageIcon("res/br.png");
          }
          else if(type == 'n') {
            if(color == 1)
              icon = new ImageIcon("res/wn.png");
            else
              icon = new ImageIcon("res/bn.png");
          }
          else if(type == 'b') {
            if(color == 1)
              icon = new ImageIcon("res/wb.png");
            else
              icon = new ImageIcon("res/bb.png");
          }
          else if(type == 'q') {
            if(color == 1)
              icon = new ImageIcon("res/wq.png");
            else
              icon = new ImageIcon("res/bq.png");
          }
          else if(type == 'k') {
            if(color == 1)
              icon = new ImageIcon("res/wk.png");
            else
              icon = new ImageIcon("res/bk.png");
          } else icon = new ImageIcon("res/bp.png");
          Image img = icon.getImage();
          Image newimg = img.getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH);
          icon = new ImageIcon(newimg);  
          this.squares[board.convertPos(c,r)-1].setIcon(icon);
        } else {
          this.squares[board.convertPos(c,r)-1].setIcon(null);
        }
      }
    }
  }
}
