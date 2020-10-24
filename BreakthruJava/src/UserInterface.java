import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
public class UserInterface extends JPanel implements MouseListener, MouseMotionListener{
	static int mouseX, mouseY,newmouseX,newmouseY;
	
	static int squareSize=32;
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.yellow);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        /*for (int i=0 ; i<100;i+=2) {
        	g.setColor(new Color(255,200,100));
            g.fillRect((i)%10*squareSize, ((i)/10)*squareSize, squareSize, squareSize);
            g.setColor(new Color(150,50,30));
            g.fillRect(((i+1)%10)*squareSize, ((i+1)/10)*squareSize, squareSize, squareSize);
            
        }*/
        for (int i=0 ; i < 11 ; i++) {
        	for (int j=0; j < 11; j++) {
        		if (((i+j)%2)==0) {
                	g.setColor(new Color(255,200,100));
        		} else {
                	g.setColor(new Color(150,50,30));
        		}
                g.fillRect((i)*squareSize, (j)*squareSize, squareSize, squareSize);
        	}
        }
//        g.setColor(Color.BLUE);
//        g.fillRect(x-20, y-20, 40, 40);
//        g.setColor(new Color(190,81,215));
//        g.fillRect(40, 20, 80, 50);
        //g.drawImage(chessPiecesImage, x, y, x+64, y+64, 0, 0, 64, 64, this);
        Image chessPiecesImage;
        chessPiecesImage=new ImageIcon("C:\\Users\\DELL\\git\\BreakthruJava\\BreakthruJava\\src\\ChessPieces.png").getImage();
        for (int i=0;i<121;i++) {
        	int j=-1,k=-1;
        	switch (BreakthruEngine.chessBoard[i/11][i%11]) {
        	case "P": j=5; k=0;
        	break;
        	case "p": j=5; k=1;
        	break;
        	case "R": j=2; k=0;
        	break;
        	case "r": j=2; k=1;
        	break;
        	case "N": j=4; k=0;
        	break;
        	case "n": j=4; k=1;
        	break;
        	case "B": j=3; k=0;
        	break;
        	case "b": j=3; k=1;
        	break;
        	case "Q": j=1; k=0;
        	break;
        	case "q": j=1; k=1;
        	break;
        	case "K": j=0; k=0;
        	break;
        	case "a": j=0; k=1;
        	break;
        	}

        	if (j!=-1 && k!=-1) {
        		g.drawImage(chessPiecesImage, (i%11)*squareSize, (i/11)*squareSize, (i%11+1)*squareSize, (i/11+1)*squareSize, j*64, k*64, (j+1)*64, (k+1)*64, this);
        	}
        }

    }
    public void mouseMoved(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
    	if (e.getX()<11*squareSize && e.getY()<11*squareSize) {
    		//inside the board
    		mouseX=e.getX();
    		mouseY= e.getY();
    		repaint();
    	}
        }
    public void mouseReleased(MouseEvent e) {
        if (e.getX()<11*squareSize &&e.getY()<11*squareSize) {
            //if inside the board
            newmouseX=e.getX();
            newmouseY=e.getY();
            if (e.getButton()==MouseEvent.BUTTON1) {
                String dragMove;
                if (newmouseY/squareSize==0 && mouseY/squareSize==1 && "P".equals(BreakthruEngine.chessBoard[mouseY/squareSize][mouseX/squareSize])) {
                    //pawn promotion
                    dragMove=""+mouseX/squareSize+newmouseX/squareSize+BreakthruEngine.chessBoard[newmouseY/squareSize][newmouseX/squareSize]+"QP";
                } else {
                    //regular move
                    dragMove=""+mouseY/squareSize+mouseX/squareSize+newmouseY/squareSize+newmouseX/squareSize+BreakthruEngine.chessBoard[newmouseY/squareSize][newmouseX/squareSize];
                }
                String userPosibilities=BreakthruEngine.possibleMoves();
                if (userPosibilities.replaceAll(dragMove, "").length()<userPosibilities.length()) {
                    //if valid move
                    BreakthruEngine.makeMove(dragMove);
                    BreakthruEngine.flipBoard();
                    BreakthruEngine.makeMove(BreakthruEngine.alphaBeta(BreakthruEngine.globalDepth, 100000, -100000, "", 0));
                    BreakthruEngine.flipBoard();
                    repaint();
                }
            }
            repaint();
        }
    }
    
    public void mouseClicked(MouseEvent e) {
       
    }
    public void mouseDragged(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
