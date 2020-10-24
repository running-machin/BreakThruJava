
public class rating {
//	static int kingMidBoard[][]={
//	        {-30,-40,-40,-50,-50,-40,-40,-30},
//	        {-30,-40,-40,-50,-50,-40,-40,-30},
//	        {-30,-40,-40,-50,-50,-40,-40,-30},
//	        {-30,-40,-40,-50,-50,-40,-40,-30},
//	        {-20,-30,-30,-40,-40,-30,-30,-20},
//	        {-10,-20,-20,-20,-20,-20,-20,-10},
//	        { 20, 20,  0,  0,  0,  0, 20, 20},
//	        { 20, 30, 10,  0,  0, 10, 30, 20}};
//}
	public static int rating() {
		int counter=0;
		counter+=rateAttack();
		counter+=rateMaterial();
		counter+=ratemoveability();
		counter+=ratepositional();
		
		return 0;
	}
	public static int rateAttack() {
		int counter=0;
		int tempPostionC=BreakthruEngine.KingPositionC;
		for(int i=0;i<121;i++) {
			switch (BreakthruEngine.chessBoard[i/11][i%11]) {
			case "P":{BreakthruEngine.KingPositionC=i;if (!BreakthruEngine.kingSafe()) {counter-=64;}};	
			break;
			case "R":{BreakthruEngine.KingPositionC=i;if (!BreakthruEngine.kingSafe()) {counter-=500;}};
			break;
			case "B":{BreakthruEngine.KingPositionC=i;if (!BreakthruEngine.kingSafe()) {counter-=300;}};
			break;
			case "K":{BreakthruEngine.KingPositionC=i;if (!BreakthruEngine.kingSafe()) {counter-=300;}};
			break;
			case "Q":{BreakthruEngine.KingPositionC=i;if (!BreakthruEngine.kingSafe()) {counter-=900;}};
			break;
			}
		}
		BreakthruEngine.KingPositionC=tempPostionC;
		if (!BreakthruEngine.kingSafe()) {counter-=200;}
		return counter/2;
	}
	public static int rateMaterial() {
		int counter=0;
	
		for(int i=0;i<121;i++) {
			switch (BreakthruEngine.chessBoard[i/11][i%11]) {
			case "R":counter+=500;
			break;
			
			}
		}
		return counter;
	
	}
	public static int ratemoveability() {
		return 0;
	}
	public static int ratepositional() {
		return 0;
	}
	
	
}


