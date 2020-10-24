
public class rating {
	static int kingBoard[][]= {
			{1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000},
			{1000, 700,700,700,700,700,700,700,700,700,1000},
			{1000,700,500,500,500,500,500,500,500,700,1000},
			{1000,700,500,300,300,300,300,300,500,700,1000},
			{1000,700,500,300,100,100,100,300,500,700,1000},
			{1000,700,500,300,100,0 ,0 ,100 ,300,500,1000},
			{1000,700,500,300,100,0 ,0 ,100 ,300,500,1000},
			{1000,700,500,300,100,100,100,300,500,700,1000},
			{1000,700,500,500,500,500,500,500,500,700,1000},
			{1000, 700,700,700,700,700,700,700,700,700,1000},
			{1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000}};
	static int kingEndBoard[][]={
			{0,0,0,0,0,0,0,0,0,0,0},
	        {0,20,10,10,30,30,30,10,10,20,0},
	        {0,10,20,30,30,30,30,30,20,10,30},
	        {0,10,30,30,40,40,40,30,30,10,30},
	        {0,10,30,30,40,40,40,30,30,10,30},
	        {0,10,20,30,30,30,30,30,20,10,30},
	        {0,10,10,0,0,0,0,0,10,10,0},
	        {0,30,30,0,0,0,0,0,30,30,0},
	        {0,0,0,0,0,0,0,0,0,0,0},
	        {0,0,0,0,0,0,0,0,0,0,0},
	        {0,0,0,0,0,0,0,0,0,0,0},
	        {0,0,0,0,0,0,0,0,0,0,0}};
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
	public static int rating(int list,int depth) {
		
		int counter=0, material= rateMaterial();
		counter+=rateAttack();
		counter+=rateMaterial();
		counter+=ratemoveability(list, depth,material);
		counter+=ratepositional(material);
		BreakthruEngine.flipBoard();
		counter-=rateAttack();
		counter-=rateMaterial();
		counter-=ratemoveability(list, depth,material);
		counter-=ratepositional(material);
		BreakthruEngine.flipBoard();
		//return -(counter+depth*50);
		return 0;
	}
	public static int rateAttack() {
		int counter=0;
		int tempPostionC=BreakthruEngine.KingPositionC;
		for(int i=0;i<121;i++) {
			switch (BreakthruEngine.chessBoard[i/11][i%11]) {
			
			case "R":{BreakthruEngine.KingPositionC=i;if (!BreakthruEngine.kingSafe()) {counter-=500;}};
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
	public static int ratemoveability(int listLength,int depth, int material) {
		int counter=0;
		counter+=listLength;//5 point per move
		if (listLength==0) {//Current side is in checkmate or stalemate
			if(BreakthruEngine.kingSafe()) {
				counter+=-200000*depth;
			} else {
				counter +=-150000*depth;	
			}
		}
		
		return counter;
	}
	public static int ratepositional(int material) {
		int counter=0;
        for (int i=0;i<121;i++) {
            switch (BreakthruEngine.chessBoard[i/11][i%11]) {
                case "K": counter+=kingBoard[i/11][i%11];
                    break;
//                case "R": if (material>=1750) {counter+=kingEndBoard[i/8][i%8]; counter+=BreakthruEngine.possibleK(BreakthruEngine.KingPositionC).length()*10;} else
//                {/*counter+=kingEndBoard[i/8][i%8];*/ counter+=BreakthruEngine.possibleK(BreakthruEngine.KingPositionC).length()*30;}
//                break;
            }
        }
        return counter;
	}
	
	
}


