import java.util.Arrays;

public class BreakthruEngine {
//	static String chessBoard[][]={
//             {"-","-","-","-","-","-","-","-","-","-","-",},
//             {"-","-","-","r","r","r","r","r","-","-","-",},
//             {"-","-","-","-","-","-","-","-","-","-","-",},
//             {"-","r","-","-","R","R","R","-","-","r","-",},
//             {"-","r","-","R","-","-","-","R","-","r","-",},
//             {"-","r","-","R","-","K","-","R","-","r","-",},
//             {"-","r","-","R","-","-","-","R","-","r","-",},
//             {"-","r","-","-","R","R","R","-","-","r","-",},
//             {"-","-","-","-","-","-","-","-","-","-","-",},
//             {"-","-","-","r","r","r","r","r","-","-","-",},
//             {"-","-","-","-","-","-","-","-","-","-","-"}};
	static String chessBoard[][]={
            {"-","-","-","-","-","-","-","-","-","-","-",},
            {"-","-","-","-","-","-","-","-","-","-","-",},
            {"-","-","-","-","-","-","-","-","-","-","-",},
            {"-","-","-","-","-","-","-","-","-","-","-",},
            {"-","-","-","-","-","-","p","-","-","-","-",},
            {"-","-","-","-","-","K","-","-","-","-","-",},
            {"-","-","-","-","-","-","-","-","-","-","-",},
            {"-","-","-","-","-","-","-","-","-","-","-",},
            {"-","-","-","-","-","-","-","-","-","-","-",},
            {"p","p","-","-","-","-","-","-","-","-","-",},
            {"R","p","-","-","-","-","-","-","-","-","-"}};	
	static int KingPositionC ;
	static int secondMove =0;
	public static void main(String[] args) {
		while (!"K".equals(chessBoard[KingPositionC/11][KingPositionC%11])) {KingPositionC++;}
		makeMove("555a");
		makeMove("a091p");
		for (int i=0;i<11;i++) {
            System.out.println(Arrays.toString(chessBoard[i]));
        }
		System.out.println(possibleMoves());
	}
	
	public static void makeMove(String move) {
		chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]=chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))];
		chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))]="-";
//		if (move.charAt(4)!='K') {
//			secondMove +=1;
//		}
//		if (secondMove ==2) {
//			secondMove = 0;
//		}
	}	
	public static void undoMove(String move) {

		chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))]=chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))];
		chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]=String.valueOf(move.charAt(4));
		chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))]=chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))];
		chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]=String.valueOf(move.charAt(4));
//		if (move.charAt(4)!='K') {
//			secondMove = 0;
//		} else if (secondMove == 0) {
//			secondMove = 1;
//			
//			
//		} 
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
                        list=list+Integer.toHexString(r)+Integer.toHexString(c)+Integer.toHexString(r)+Integer.toHexString(c+temp*j)+OldPiece;
                        
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
                        list=list+Integer.toHexString(r)+Integer.toHexString(c)+Integer.toHexString(r+temp*j)+Integer.toHexString(c)+OldPiece;
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
							list = list +Integer.toHexString(r)+Integer.toHexString(c)+Integer.toHexString(r+j)+Integer.toHexString(c+k)+OldPiece;
							
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
                        list = list+Integer.toHexString(r)+Integer.toHexString(c)+Integer.toHexString(r)+Integer.toHexString(Math.abs(c*temp*j))+OldPiece;
                        
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
                        list= list+Integer.toHexString(r)+Integer.toHexString(c)+Integer.toHexString(r+temp*j)+Integer.toHexString(c)+OldPiece;
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
							list = list+Integer.toHexString(r)+Integer.toHexString(c)+Integer.toHexString(r+j)+Integer.toHexString(c+k)+OldPiece;
									
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
