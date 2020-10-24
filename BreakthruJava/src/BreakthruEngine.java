import java.util.Arrays;

import javax.swing.JFrame;

public class BreakthruEngine {
	static String chessBoard[][]={
             {"-","-","-","-","-","-","-","-","-","-","-",},
             {"-","-","-","r","r","r","r","r","-","-","-",},
             {"-","-","-","-","-","-","-","-","-","-","-",},
             {"-","r","-","-","R","R","R","-","-","r","-",},
             {"-","r","-","R","-","-","-","R","-","r","-",},
             {"-","r","-","R","-","K","-","R","-","r","-",},
             {"-","r","-","R","-","-","-","R","-","r","-",},
             {"-","r","-","-","R","R","R","-","-","r","-",},
             {"-","-","-","-","-","-","-","-","-","-","-",},
             {"-","-","-","r","r","r","r","r","-","-","-",},
             {"-","-","-","-","-","-","-","-","-","-","-"}};
//	static String chessBoard[][]={
//            {"-","-","-","-","-","-","-","-","-","-","-",},
//            {"-","-","-","-","-","-","-","-","-","-","-",},
//            {"-","-","-","-","-","-","-","-","-","-","-",},
//            {"-","-","-","-","-","-","-","-","-","-","-",},
//            {"-","-","-","-","-","-","p","-","-","-","-",},
//            {"-","-","-","-","-","K","-","-","-","-","-",},
//            {"-","-","-","-","-","-","-","-","-","-","-",},
//            {"-","-","-","-","-","-","-","-","-","-","-",},
//            {"-","-","-","-","-","-","-","-","-","-","-",},
//            {"p","p","-","-","-","-","-","-","-","-","-",},
//            {"R","p","-","-","-","-","-","-","-","-","-"}};	
	static int KingPositionC ;
	static int globalDepth=4; 
	static int secondMove =0;
	public static void main(String[] args) {
		while (!"K".equals(chessBoard[KingPositionC/11][KingPositionC%11])) {KingPositionC++;}
		
		JFrame f=new JFrame("Breakthru!");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UserInterface ui=new UserInterface();
        f.add(ui);
        f.setSize(500, 500);
        f.setVisible(true);
        f.repaint();
		makeMove(alphaBeta(globalDepth, 100000, -100000, "", 0));
		makeMove("555a-");
		undoMove("555a-");
		for (int i=0;i<11;i++) {
            System.out.println(Arrays.toString(chessBoard[i]));
        }
		System.out.println(possibleMoves());
	}
	public static String alphaBeta(int depth,int beta, int alpha,String move, int player) {
		 //return in the form of 1234b##########
       String list=possibleMoves();
       if (depth==0 || list.length()==0) {return move+(rating.rating(list.length(),depth)*(player*2-1));}
       //sort later
       player=1-player;//either 1 or 0
       for (int i=0;i<list.length();i+=5) {
           makeMove(list.substring(i,i+5));
           flipBoard();
           String returnString=alphaBeta(depth-1, beta, alpha, list.substring(i,i+5), player);
           int value=Integer.valueOf(returnString.substring(5));
           flipBoard();
           undoMove(list.substring(i,i+5));
           if (player==0) {
               if (value<=beta) {beta=value; if (depth==globalDepth) {move=returnString.substring(0,5);}}
           } else {
               if (value>alpha) {alpha=value; if (depth==globalDepth) {move=returnString.substring(0,5);}}
           }
           if (alpha>=beta) {
               if (player==0) {return move+beta;} else {return move+alpha;}
           }
       }
       if (player==0) {return move+beta;} else {return move+alpha;}
	}
	public static void flipBoard() {
		String temp;
        for (int i=0;i<32;i++) {
            int r=i/11, c=i%11;
            if (Character.isUpperCase(chessBoard[r][c].charAt(0))) {
                temp=chessBoard[r][c].toLowerCase();
            } else {
                temp=chessBoard[r][c].toUpperCase();
            }
            if (Character.isUpperCase(chessBoard[10-r][10-c].charAt(0))) {
                chessBoard[r][c]=chessBoard[10-r][10-c].toLowerCase();
            } else {
                chessBoard[r][c]=chessBoard[10-r][10-c].toUpperCase();
            }
            chessBoard[10-r][10-c]=temp;
        }
        
       
	}
	public static void makeMove(String move) {
		chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]=chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))];
		chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))]="-";
		if ("K".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))])) {
            KingPositionC=8*Character.getNumericValue(move.charAt(2))+Character.getNumericValue(move.charAt(3));
        }
		
//		if (move.charAt(4)!='K') {
//			secondMove +=1;
//		}else {secondMove=2;}
//		if (secondMove ==2) {
//			makeMove(move);
//			secondMove = 0;
//		}
	}	
	public static void undoMove(String move) {

		chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))]=chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))];
		chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]=String.valueOf(move.charAt(4));
		if ("K".equals(chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))])) {
            KingPositionC=8*Character.getNumericValue(move.charAt(0))+Character.getNumericValue(move.charAt(1));
        }
//		if (secondMove==1 && move.charAt(4)!='K') {
//			secondMove -=1;
//		} else if (secondMove == 0) {
//			secondMove = 1;}
//			
			
			
			
		 
	}
	public static int rating() {
		return 0;
	}
	public static String  possibleMoves() {
		String 	list = "";
		for (int i=0; i<121;i++) {
			switch (chessBoard[i/11][i%11]) {
				
				case "R": list+= possibleR(i);
					break;
		        case "K": list+= possibleK(i);
		            break;
		        
			}
		}	
		return list; //(x1,y1,x2,y2,capturePiece)
	
	}
	public static String possibleR(int i) {
		String list = "",OldPiece;
		int r=i/11,c=i%11;
		int temp=1;
		for (int j =-1; j<=1; j+=2) {
			try {
				while("-".equals(chessBoard[r][c+temp*j]))
                {
                    OldPiece=chessBoard[r][c+temp*j];
                    chessBoard[r][c]="-";
                    chessBoard[r][c+temp*j]="R";
                    if (kingSafe()) {
                        //list=list+r+c+r+(c+temp*j)+OldPiece;
                        int p=c+temp*j;
                        list=list+Integer.toHexString(r)+Integer.toHexString(c)+Integer.toHexString(r)+Integer.toHexString(p)+OldPiece;
                        
                    }
                    chessBoard[r][c]="R";
                    chessBoard[r][c+temp*j]=OldPiece;
                    temp++;
				}
			} catch (Exception e) {}
			temp =1;
			try {
                while("-".equals(chessBoard[r+temp*j][c]))
                {
                    OldPiece=chessBoard[r+temp*j][c];
                    chessBoard[r][c]="-";
                    chessBoard[r+temp*j][c]="R";
                    if (kingSafe()) {
                        //list=list+r+c+(r+temp*j)+c+OldPiece;
                        int q= r+temp*j;
                        list=list+Integer.toHexString(r)+Integer.toHexString(c)+Integer.toHexString(q)+Integer.toHexString(c)+OldPiece;
                    }
                    chessBoard[r][c]="R";
                    chessBoard[r+temp*j][c]=OldPiece;
                    temp++;
                }
			} catch (Exception e) {}
                temp=1;
			for (int k=-1; k<=1 ; k+=2) {
				try {
					if (Character.isLowerCase(chessBoard[r+j][c+k].charAt(0)) ) {
						OldPiece = chessBoard[r+j][c+k];
						chessBoard[r][c]="-";
						chessBoard[r+j][c+k]="R";
						if (kingSafe()) {
							//list = list+r+c+(r+j)+(c+k)+OldPiece;
							int x= r+j,  y=c+k;
							list = list +Integer.toHexString(r)+Integer.toHexString(c)+Integer.toHexString(x)+Integer.toHexString(y)+OldPiece;
							
						}
						chessBoard[r][c] = "R";
						chessBoard[r+j][c+k] = OldPiece;
					}
				} catch (Exception e) {}
			}
		}
		return list;
	}
    
	public static String possibleK(int i) {
		String list = "",OldPiece;
		int r=i/11,c=i%11;
		int temp=1;
		for (int j =-1; j<=1; j+=2) {
			try {
				while("-".equals(chessBoard[r][c+temp*j]))
                {
                    OldPiece=chessBoard[r][c+temp*j];
                    chessBoard[r][c]="-";
                    chessBoard[r][c+temp*j]="K";
                    if (kingSafe()) {
                        //list=list+r+c+r+(c+temp*j)+OldPiece;
                        int p= c+temp*j;
                        list = list+Integer.toHexString(r)+Integer.toHexString(c)+Integer.toHexString(r)+Integer.toHexString(p)+OldPiece;
                        
                    }
                    chessBoard[r][c]="K";
                    chessBoard[r][c+temp*j]=OldPiece;
                    temp++;
				}
			} catch (Exception e) {}
			temp =1;
			try {
                while("-".equals(chessBoard[r+temp*j][c]))
                {
                    OldPiece=chessBoard[r+temp*j][c];
                    chessBoard[r][c]="-";
                    chessBoard[r+temp*j][c]="K";
                    if (kingSafe()) {
                        //list=list+r+c+(r+temp*j)+c+OldPiece;
                        int q=r+temp*j;
                        list= list+Integer.toHexString(r)+Integer.toHexString(c)+Integer.toHexString(q)+Integer.toHexString(c)+OldPiece;
                    }
                    chessBoard[r][c]="K";
                    chessBoard[r+temp*j][c]=OldPiece;
                    temp++;
                }
			} catch (Exception e) {}
                temp=1;
			for (int k=-1; k<=1 ; k+=2) {
				try {
					if (Character.isLowerCase(chessBoard[r+j][c+k].charAt(0)) ) {
						OldPiece = chessBoard[r+j][c+k];
						chessBoard[r][c]="-";
						chessBoard[r+j][c+k]="K";
						if (kingSafe()) {
							//list = list+r+c+(r+j)+(c+k)+OldPiece;
							int x= r+j, y=c+k;
							list = list+Integer.toHexString(r)+Integer.toHexString(c)+Integer.toHexString(x)+Integer.toHexString(y)+OldPiece;
									
						}
						chessBoard[r][c] = "K";
						chessBoard[r+j][c+k] = OldPiece;
					}
				} catch (Exception e) {}
			}
		}
		return list;
	}
	public static boolean kingSafe() {
		
		
		//escort attack
		
		for (int i=-1; i<=1; i+=2) {
            for (int j=-1; j<=1; j+=2) {
                if (i!=0 || j!=0) {
                    try {
                        if ("r".equals(chessBoard[KingPositionC/11+i][KingPositionC%11+j])) {
                            return false;
                        }
                    } catch (Exception e) {}
                }
            }
        }
		return true;
	}
}
