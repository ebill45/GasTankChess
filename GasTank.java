// GasTank Game
// By: Bill Yee
// CSc 180: Project 2
// 3/23/2015

import java.awt.List;
import java.lang.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GasTank {
	
	int[][][] board; 
	char[][][] printboard;
	//piece[][] board;
	int	maxdepth = 2;
	//ArrayList<String> humanlist;
	//ArrayList<String> cpulist;
	
	public GasTank() {
		
		
		board = new int[9][8][2];
		printboard = new char[9][8][2];
		String move;
		initBoard();
		Scanner kb = new Scanner(System.in);
		System.out.println("Will you go 1st or 2nd (enter 1 or 2): "); 
		int num = kb.nextInt();
		
		if(num == 1){
			while(true) {
				ArrayList<String> humanlist = new ArrayList<String>();
				//ArrayList<String> cpulist = new ArrayList<String>(); 
				
				printBoard();
				//moveGenerator(0, cpulist);
				moveGenerator(1, humanlist);
				//System.out.print("CPU");
				//movePrinter(cpulist);
				//System.out.print("Human");
				//movePrinter(humanlist);
				move = getMove();
				makeMove(move);
				miniMax();	
			}
		}
		else {
			while(true) {
				ArrayList<String> humanlist = new ArrayList<String>();
				//ArrayList<String> cpulist = new ArrayList<String>(); 
				printBoard();
				miniMax();	
				printBoard();
				//moveGenerator(0, cpulist);
				moveGenerator(1, humanlist);
				//System.out.print("CPU");
				//movePrinter(cpulist);
				//System.out.print("Human");
				//movePrinter(humanlist);
				move = getMove();
				makeMove(move);
			}
		}
		
	}
	
	
	private void movePrinter(ArrayList<String> list) {
		
		System.out.println("\nMoves are: ");
		for(int x = 0; x < list.size(); x++){ 
			System.out.print(list.get(x) + ", ");
			if((x+1)%16 == 0)
				System.out.println();
		}
	}


	public void initBoard() {
		for(int x = 0; x <= 8; x++) {
			for (int y = 0; y < 8; y++){
				board[x][y][0] = 0;
				board[x][y][1] = 0;
			}
		}
				
		board[3][7][0] = 1;
		board[4][7][0] = 3;
		board[5][7][0] = 4;
		board[6][7][0] = 1;
		board[4][6][0] = 2;			
		board[6][6][0] = 2;
		
		board[3][1][0] = -1;
		board[4][1][0] = -3;
		board[5][1][0] = -4;
		board[6][1][0] = -1;
		board[4][2][0] = -2;
		board[6][2][0] = -2;
		
		board[3][7][1] = 3;
		board[4][7][1] = 3;
		board[5][7][1] = 3;
		board[6][7][1] = 3;
		board[4][6][1] = 3;			
		board[6][6][1] = 3;
		
		board[3][1][1] = 3;
		board[4][1][1] = 3;
		board[5][1][1] = 3;
		board[6][1][1] = 3;
		board[4][2][1] = 3;
		board[6][2][1] = 3;
		
		//testing 
		//board[2][2][0] = -1;
		//board[2][2][1] = 3;
		//printboard[2][2][0] = 'q';
		//printboard[2][2][1] = ' ';
		//testing 
		
		printboard[1][0][0] = 'A';
		printboard[2][0][0] = 'B';
		printboard[3][0][0] = 'C';
		printboard[4][0][0] = 'D';
		printboard[5][0][0] = 'E';
		printboard[6][0][0] = 'F';
		printboard[7][0][0] = 'G';
		printboard[8][0][0] = 'H';
		
		printboard[1][0][1] = ' ';
		printboard[2][0][1] = ' ';
		printboard[3][0][1] = ' ';
		printboard[4][0][1] = ' ';
		printboard[5][0][1] = ' ';
		printboard[6][0][1] = ' ';
		printboard[7][0][1] = ' ';
		printboard[8][0][1] = ' ';
		
		printboard[0][1][0] = '1';
		printboard[0][2][0] = '2';
		printboard[0][3][0] = '3';
		printboard[0][4][0] = '4';
		printboard[0][5][0] = '5';
		printboard[0][6][0] = '6';
		printboard[0][7][0] = '7';
		
		printboard[0][1][1] = ' ';
		printboard[0][2][1] = ' ';
		printboard[0][3][1] = ' ';
		printboard[0][4][1] = ' ';
		printboard[0][5][1] = ' ';
		printboard[0][6][1] = ' ';
		printboard[0][7][1] = ' ';
		printboard[0][0][0] = ' ';
		printboard[0][0][1] = ' ';
	}
	
	public void printBoard() {
		translateBoard();
		for(int y = 7; y >= 0; y--) {
			System.out.println();
			for(int x = 0; x <= 8; x++) {
				System.out.print(printboard[x][y][0]);
				System.out.print(printboard[x][y][1] + " ");
			}
		}
	}
	
	public void translateBoard() {
		for(int x = 1; x <=8; x++){
			for(int y = 1; y <=7; y++){
				if(board[x][y][0] == 0){
					printboard[x][y][0] = '-';
					printboard[x][y][1] = '-';
				}
				else{
					if(board[x][y][0] == -1)
						printboard[x][y][0] = 'b';
					if(board[x][y][0] == 1)
						printboard[x][y][0] = 'B';
					if(board[x][y][0] == -2)
						printboard[x][y][0] = 'n';
					if(board[x][y][0] == 2)
						printboard[x][y][0] = 'N';
					if(board[x][y][0] == -3)
						printboard[x][y][0] = 'q';
					if(board[x][y][0] == 3)
						printboard[x][y][0] = 'Q';
					if(board[x][y][0] == -4)
						printboard[x][y][0] = 'k';
					if(board[x][y][0] == 4)
						printboard[x][y][0] = 'K';
					
					printboard[x][y][1] = Character.forDigit(board[x][y][1], 8);
				}
			}
		}
	}
	
	
	
	String getMove() {
		String move, tempmove;
		boolean goodmove = false;
		
		do{
		tempmove = getInput();
		move = moveValidator(tempmove);
		
		if(move.length() > 2)
			goodmove = true;
		
		} while(goodmove == false);
		
		return move;
	}
	
	void moveGenerator(int turn, ArrayList<String> list) {
		// turn 1 = human, turn 0 = cpu
		
		if (turn == 0)
		{
			//list = cpulist;
		
			for(int x = 1; x<= 8; x++){
				for( int y = 1; y < 8; y++) {
					int piecetype = board[x][y][0];		
					
					if( piecetype != 0){
						switch (piecetype) {
							case 1:			// Bishops
								bishopGen(x,y, list);
								break; 
							case 2: 		// kNights
								knightGen(x,y, list);
								break; 
							case 3: 		// Queens
								queenGen(x,y, list);
								break;
							case 4: 		// Kings
								kingGen(x,y, list);
								break;
							default: 
								break; 
						}
					}
				}
			}
		}
		else {
			//list = humanlist;
			
			for(int x = 1; x<= 8; x++){
				for( int y = 1; y < 8; y++) {
					int piecetype = board[x][y][0];		
					
					if( piecetype != 0){
						switch (piecetype) {
							case -1:			// Bishops
								bishopGen(x,y, list);
								break; 
							case -2: 		// kNights
								knightGen(x,y, list);
								break; 
							case -3: 		// Queens
								queenGen(x,y, list);
								break;
							case -4: 		// Kings
								kingGen(x,y, list);
								break;
							default: 
								break; 
						}
					}
				}
			}
		}
		
	}
	private void kingGen(int x, int y, ArrayList<String> list) {

		String shortlist = Integer.toString(x) + Integer.toString(y);
		
		if(board[x][y][1] == 0)			// if no gas, just get out
			return;
		
		for(int i = -1; i <= 1; i++){
			for(int j = -1; j <= 1; j++){
				if(!((i== 0) && (j== 0))){								// no movement, no need to go in
					if(boundCheck(x, y, i, j) == true){
						if(board[x + i][y + j][0] == 0)					// if blank space
							list.add(shortlist + Integer.toString(x +i) + Integer.toString(y + j)); 
						if(board[x + i][y + j][0] != 0)	{				// if there's a piece
							if( (board[x][y][0] < 0) && (board[x+i][y+j][0] != -4) )		// don't eat king on own side
								list.add(shortlist + Integer.toString(x +i) + Integer.toString(y + j) + printboard[x + i][y + j][0] + Integer.toString(board[x + i][y + j][1]) + Integer.toString(board[x][y][1])); 
							if( (board[x][y][0] > 0) && (board[x+i][y+j][0] != 4) )			// don't eat king on own side
								list.add(shortlist + Integer.toString(x +i) + Integer.toString(y + j) + printboard[x + i][y + j][0] + Integer.toString(board[x + i][y + j][1]) + Integer.toString(board[x][y][1])); 
						}
					}
				}
			}
		}
	}

	private void queenGen(int x, int y, ArrayList<String> list) {
		knightGen(x, y, list);
		bishopGen(x, y, list);
	}


	private void knightGen(int x, int y, ArrayList<String> list) {
		
		if(board[x][y][1] == 0)			// if no gas, just get out
			return;
		
		String shortlist = Integer.toString(x) + Integer.toString(y);
		
		for(int i = -1; i <= 1; i+= 2){
			for(int j = -1; j <= 1; j+= 2){
				if(boundCheck(x, y, i, j*2) != false) {
					if(board[x + i][y + j*2][0] == 0)		// if blank space
						list.add(shortlist + Integer.toString(x + i) + Integer.toString(y + j*2));
					if(board[x + i][y + j*2][0] != 0){	
						if(((board[x][y][0] < 0) && ( board[x+i][y+j*2][0] != -4)))		// negative can't eat own king
							list.add(shortlist + Integer.toString(x + i) + Integer.toString(y + j*2) + printboard[x + i][y + j*2][0]);
						if ((board[x][y][0] > 0) && ( board[x+i][y+j*2][0] != 4))		// positive can't eat own king
							list.add(shortlist + Integer.toString(x + i) + Integer.toString(y + j*2) + printboard[x + i][y + j*2][0] + Integer.toString(board[x + i][y + j*2][1]) + Integer.toString(board[x][y][1]));
					}
				}
			}
		}
		for(int i = -1; i <= 1; i+= 2){
			for(int j = -1; j <= 1; j+= 2){
				if(boundCheck(x, y, i*2, j) != false){
					if(board[x + i*2][y + j][0] == 0)		// if blank space
						list.add(shortlist + Integer.toString(x + i*2) + Integer.toString(y + j));
					if(board[x + i*2][y + j][0] != 0){		// if piece
						if( ((board[x][y][0] < 0) && ( board[x+i*2][y+j][0] != -4)) || ((board[x][y][0] > 0) && ( board[x+i*2][y+j][0] != 4)))
							list.add(shortlist + Integer.toString(x + i*2) + Integer.toString(y + j) + printboard[x + i*2][y + j][0] + Integer.toString(board[x + i*2][y + j][1]) + Integer.toString(board[x][y][1]));
					}
				}
			}
		}
			
	}


	private void bishopGen(int x, int y, ArrayList<String> list) {
	
		if(board[x][y][1] == 0)			// if no gas, just get out
			return;
		
		String shortlist = Integer.toString(x) + Integer.toString(y);
		
		int magnitude = 1; 
		for(int i = -1; i <= 1; i+= 2){
			for(int j = -1; j <= 1; j+= 2){
				while (true){
					if(boundCheck(x, y, magnitude*i, magnitude*j) == false){
						magnitude = 1; 
						break;
					}
					
					if(board[x + magnitude*i][y + magnitude*j][0] == 0) {		// if blank space
						list.add(shortlist + Integer.toString(x + magnitude*i) + Integer.toString(y + magnitude*j));
						magnitude++;
						if(boundCheck(x, y, magnitude*i, magnitude*j) == false){
							magnitude = 1;
							break;
						}
					}
				
					if(board[x + magnitude*i][y + magnitude*j][0] != 0) {		// if there's a piece
						if( (board[x][y][0] < 0) && (board[x + magnitude*i][y + magnitude * j][0] != -4) ) 	{
							list.add(shortlist + Integer.toString(x + magnitude*i) + Integer.toString(y + magnitude*j) + printboard[x + magnitude*i][y + magnitude*j][0] + Integer.toString(board[x + magnitude*i][y + magnitude*j][1]) + Integer.toString(board[x][y][1]));
							//System.out.println("Testing: " +shortlist + Integer.toString(x + magnitude*i) + Integer.toString(y + magnitude*j) + printboard[x + magnitude*i][y + magnitude*j][0] + Integer.toString(board[x + magnitude*i][y + magnitude*j][1]) + Integer.toString(board[x][y][1]));
						}
						if( (board[x][y][0] > 0) && (board[x + magnitude*i][y + magnitude * j][0] != 4) ) {
							list.add(shortlist + Integer.toString(x + magnitude*i) + Integer.toString(y + magnitude*j) + printboard[x + magnitude*i][y + magnitude*j][0] + Integer.toString(board[x + magnitude*i][y + magnitude*j][1]) + Integer.toString(board[x][y][1]));
							//System.out.println("Testing: " +shortlist + Integer.toString(x + magnitude*i) + Integer.toString(y + magnitude*j) + printboard[x + magnitude*i][y + magnitude*j][0] + Integer.toString(board[x + magnitude*i][y + magnitude*j][1]) + Integer.toString(board[x][y][1]));
						}
						magnitude = 1;
						break; 
					}
				}	
			}
		}
	}

	
	boolean boundCheck(int x, int y, int i, int j) {
		boolean inbound = true;
	
		if((x + i < 1) || (x + i > 8))
			inbound = false;
		if((y + j < 1) || (y + j > 7))
			inbound = false; 
		
		return inbound; 
	}

	
	String moveValidator(String move) {

		int fromX = Character.getNumericValue(move.charAt(0) - 48);
		int fromY = Character.getNumericValue(move.charAt(1));
		int toX = Character.getNumericValue(move.charAt(2) - 48);
		int toY = Character.getNumericValue(move.charAt(3));
		
		ArrayList<String> humanlist = new ArrayList<String>();
		ArrayList<String> cpulist = new ArrayList<String>(); 
		moveGenerator(1, humanlist);
		moveGenerator(0, cpulist);
		
		String compare;
		
		String movenums = Integer.toString(fromX) + Integer.toString(fromY) + Integer.toString(toX) + Integer.toString(toY);
		
		int piecetype = board[fromX][fromY][0];		
		if (piecetype > 0){			// cpu
			for(int x = 0; x < cpulist.size(); x++) {
				compare = cpulist.get(x).substring(0, 4);
				//System.out.println("Compare string is " + compare + " movenums is " + movenums);
				if(movenums.equals(compare)){
					System.out.println("Move accepted");
					return cpulist.get(x);
				}
			}
		}
		else						// human
		{
			for(int x = 0; x < humanlist.size(); x++) {
				compare = humanlist.get(x).substring(0, 4);
				//System.out.println("Compare string is " + compare + " movenums is " + movenums);
				if(movenums.equals(compare)){
					System.out.println("Move accepted");
					return humanlist.get(x); 
				}
			}
		}
		
		return " ";
	}
	
	
	String getInput() {
		boolean validflag = false;
		String s;
		Scanner in = new Scanner(System.in);		
		do {
			System.out.println("\nEnter move in the format like a3d6: ");
			s = in.nextLine();
			if (s.length() == 4){
				if((s.charAt(0) >= 'a' && s.charAt(0) <= 'h') && (s.charAt(1) >= '1' && s.charAt(1) <= '8') 
				&& (s.charAt(2) >= 'a' && s.charAt(2) <= 'h') && (s.charAt(3) >= '1' && s.charAt(3) <= '8'))
					validflag = true;	
			}
			if(!validflag)
				System.out.println("Invalid input");
		} while (!validflag);
		
		System.out.println("Valid input");
		return s;
	}
	
	void makeMove(String move) {
		int fromX = Character.getNumericValue(move.charAt(0));		//  (0) - 48
		int fromY = Character.getNumericValue(move.charAt(1));
		int toX = Character.getNumericValue(move.charAt(2));		//  (2) - 48
		int toY = Character.getNumericValue(move.charAt(3));
		
		if( board[toX][toY][0] == 0){
			board[toX][toY][0] = board[fromX][fromY][0];
			board[toX][toY][1] = board[fromX][fromY][1]-1;
		}
		else {
			board[toX][toY][0] = board[fromX][fromY][0];
			printboard[toX][toY][0] = pieceTranslator(board[fromX][fromY][0]);
			//System.out.print("\nEating! piece is now " + board[fromX][fromY][0]);
			board[toX][toY][1] = 3;
		}
		
		board[fromX][fromY][0] = 0;
		board[fromX][fromY][1] = 0;
		
		translateBoard();		// could i wait til later for this
	}
	
	void undoMove(String move) {		// where do i store gas for undo'd piece
		int fromX = Character.getNumericValue(move.charAt(0));		// (0) - 48
		int fromY = Character.getNumericValue(move.charAt(1));
		int toX = Character.getNumericValue(move.charAt(2));		// (2) - 48
		int toY = Character.getNumericValue(move.charAt(3));
		
		board[fromX][fromY][0] = board[toX][toY][0];  
		
		if( move.length() > 5) {
			//System.out.println("move is " + move + " move length is " + move.length());
			printboard[toX][toY][0] = move.charAt(4);
			board[toX][toY][0] = pieceTranslator(move.charAt(4));
			board[toX][toY][1] = Character.getNumericValue(move.charAt(5));
			board[fromX][fromY][1] = Character.getNumericValue(move.charAt(6));
		}
		else {
			printboard[toX][toY][0] = '-';
			board[fromX][fromY][1] = board[toX][toY][1]+1;
			board[toX][toY][0] = 0;
			board[toX][toY][1] = 0;

		}
		
		translateBoard();		// could i wait til later for this
	}
	

	
	void miniMax() {
		int alpha  = -10000, beta = 10000;
		int best = -20000, depth = 1, score = 0;
		String bestmove = "1111";
		
		ArrayList<String> maxlist = new ArrayList<String>();
		moveGenerator(0, maxlist);
		
		for (int x = 0; x < maxlist.size(); x++) {			// 
			makeMove(maxlist.get(x));
			//printBoard();													// this might be needed
			//System.out.println("Minimax move is " + maxlist.get(x));		// this might be needed
			score = min(depth +1, maxlist.get(x), beta, alpha);
			if(score > best) {
				best = score;
				bestmove = maxlist.get(x);
				//System.out.println("\nBest move is " + bestmove);
			}
			undoMove(maxlist.get(x));
			//printBoard();
			//System.out.println("Undo Minimax move " + maxlist.get(x));
		}
		String printmove = moveConverter(bestmove);
		System.out.println("My move is: " + printmove);
		makeMove(bestmove);
		checkWinner(score);
	}
	
	
	int min(int depth, String move, int beta, int alpha) {
		int best = 20000, score;
		String bestmove = null;
		ArrayList<String> minlist = new ArrayList<String>();
		moveGenerator(1, minlist);
		
		if(checkMate(move) != 0)		// game over
			return checkMate(move);
		if(depth == maxdepth)
			return evaluate();
		
		for (int x = 0; x < minlist.size(); x++) {			// 
			makeMove(minlist.get(x));
			//printBoard();
			//System.out.println("minmove is " + minlist.get(x));
			score = max(depth +1, minlist.get(x), beta, alpha);
			if(score < beta) {
				beta = score;
				bestmove = minlist.get(x);
			}
			undoMove(minlist.get(x));
			
			if(beta <= alpha) {
				score = alpha;
				return score;
			}
			//printBoard();
			//System.out.println("Undid minmove " + minlist.get(x));
		}
		return best;
	}
	
	


	int max(int depth, String move, int beta, int alpha) {
		int best = -20000, score;
		String bestmove = null;
		ArrayList<String> maxlist = new ArrayList<String>();
		moveGenerator(0, maxlist);
		
		if(checkMate(move)!= 0)		// game over
			return checkMate(move);
		if(depth == maxdepth)
			return evaluate();
		
		for (int x = 0; x < maxlist.size(); x++) {			// 
			makeMove(maxlist.get(x));
			//printBoard();
			//System.out.println("Maxmove is " + maxlist.get(x));
			score = min(depth +1, maxlist.get(x), beta, alpha);
			if(score > alpha) {
				alpha = score;
				//bestmove = maxlist.get(x);
			}
			undoMove(maxlist.get(x));
			if( beta <= alpha) {
				score = beta;
				return score;
			}
			//printBoard();
			//System.out.println("Undid Maxmove " + maxlist.get(x));
		}
		return best;
	}
	
	int evaluate() {
		
		int score = 0;
		
		for(int x = 1; x<= 8; x++){
			for( int y = 1; y < 8; y++) {
				int piecetype = board[x][y][0];		
				
				if( piecetype != 0){
					switch (piecetype) {
						case 1:			// Bishops
							if(board[x][y][1] > 0)
								score += 450 - (board[x][y][1] * 60);	
							else
								score -= 10;
							break; 
						case 2: 		// kNights
							if(board[x][y][1] > 0)
								score += 400 - (board[x][y][1] * 50);	
							else 
								score -= 10;
							break; 
						case 3: 		// Queens
							if(board[x][y][1] > 0)
								score += 750 - (board[x][y][1] * 125);	
							else
								score -= 10;
							break;
						case 4: 		// Kings
							score += 4000 - (board[x][y][1] * 10);	
							break;
						case -1:			// Bishops
							if(board[x][y][1] > 0)
								score -= 450 - (board[x][y][1] * 60);	
							else
								score+= 10;
							break; 
						case -2: 		// kNights
							if(board[x][y][1] > 0)
								score -= 400 - (board[x][y][1] * 50);	
							else
								score+= 10;
							break; 
						case -3: 		// Queens
							if(board[x][y][1] > 0)
								score -= 750 - (board[x][y][1] * 125);	
							else
								score+= 10;
							break;
						case -4: 		// Kings
							score -= 4000 - (board[x][y][1] * 10);	
							break;
						default: 
							//System.out.println("Invalid piece");
							break; 
					}
				}
			}
		}
		
		return score;
	}
	
	
	int checkMate(String move) {		// Not quite same meaning in traditional chess sense, checks to see if game's over.
		
		int winscore = 0;
		
		if(move.length() > 5){
			if(move.charAt(5) == 'K')
				winscore = 10000;
			if(move.charAt(5) == 'k')
				winscore = -10000;
		}		
		return winscore;
	}
	
	String moveConverter(String move) {
		//String outmove = "Computer moves ";
		String outmove = "Computer moves " + (char)(move.charAt(0)+48) + move.charAt(1) + (char)(move.charAt(2)+48) + move.charAt(3);
		String theirmove = "(" + (char)(move.charAt(0)+48) + numConverter(move.charAt(1)) + (char)(move.charAt(2)+48) + numConverter(move.charAt(3)) + ")";
		
		return outmove + theirmove;
	}
	
	char numConverter(char in){
		char out = 1;
		switch (in) {
		case '1':
			out = '7';
			break;
		case '2': 
			out = '6';
			break;
		case '3':
			out = '5';
			break;
		case '4':
			out = '4';
			break;
		case '5':
			out = '3';
			break;
		case '6':
			out = '2';
			break;
		case '7':
			out = '1';
			break;
		}
		return out;
	}
	int pieceTranslator(char in) {
		switch (in) {
			case 'b': 
				return -1;
			case 'B': 
				return 1;
			case 'n':
				return -2;
			case 'N':
				return 2;
			case 'q': 
				return -3;
			case 'Q':
				return 3;
			case 'k':
				return -4;
			case 'K':
				return 4;
		}
		return 0;
	}
	
	char pieceTranslator(int in) {
		switch (in) {
			case -1: 
				return 'b';
			case 1: 
				return 'B';
			case -2:
				return 'n';
			case 2:
				return 'N';
			case -3: 
				return 'q';
			case 3:
				return 'Q';
			case -4:
				return 'k';
			case 4:
				return 'K';
		}
		return '-';
	}
	
	
	private int checkWinner(int score) {
		if(score == 10000){
			System.out.println("You win");
			try{System.in.read();}
			catch(Exception e){}
			System.exit(0);
		}
		if(score == -1000){
			System.out.println("I win!");
			try{System.in.read();}
			catch(Exception e){}
			System.exit(0);
		}
		
		return 0;
	}
	
}

