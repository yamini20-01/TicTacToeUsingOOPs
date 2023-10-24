import java.util.Random;
import java.util.Scanner;

class TicTacToe{
	static char[][] board;
	public TicTacToe() {
		board=new char[3][3];
		initBoard();
	}
	void initBoard() {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				board[i][j]=' ';
			}
		}
	}
	static void dispBoard() {
		System.out.println("-------------");
		for(int i=0;i<board.length;i++) {
			System.out.print("| ");
			for(int j=0;j<board[i].length;j++) {
				System.out.print(board[i][j]+" | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}

	static void placeMark(int row,int col,char mark) {
		if(row>=0 && row<=2 && col>=0 && col<=2) {
		board[row][col]=mark;
		}
		else {
		System.out.println("Invalid Position");	
		}
	}
	static boolean checkColWin() 
	{
		for(int j=0;j<=2;j++) {
		if(board[0][j]!=' ' && board[0][j]==board[1][j] && board[1][j]==board[2][j]) 
		{
			return true;
		}
		}
		return false;
	}	
	static boolean checkRowWin() 
	{
		for(int i=0;i<=2;i++) {
		if(board[i][0]!=' ' &&  board[i][0]==board[i][1] && board[i][1]==board[i][2]) 
		{
			return true;
		}
		}
		return false;
	}	
	static boolean checkDiagnolWin()
	{
		for(int i=0;i<=2;i++) {
		if(board[0][0]!=' ' && board[0][0]==board[1][1] && board[1][1]==board[2][2] ||
				board[0][2]!=' '&&	board[0][2]==board[1][1] && board[1][1]==board[2][0]) 
		{
			return true;
		}
		}
		return false;
	}	
	static boolean checkDraw() {
		for(int i=0;i<=2;i++) {
			for(int j=0;j<=2;j++) {
				if(board[i][j]==' ') {
					return false;
				}
			}
		}
		return true;
	}
}

abstract class Player{
	String name;
	char mark;
	abstract void makeMove();
	boolean isValidMove(int row,int col) {
		if(row>=0 && row<=2 && col>=0 && col<=2)
		{
			if(TicTacToe.board[row][col]==' ') {
				return true;
			}
		}
		System.out.println("Invalid Position");
		return false;
	}
}

class HumanPlayer extends Player{
	
	HumanPlayer(String name,char mark){
		this.name=name;
		this.mark=mark;
	}
	void makeMove() {
		Scanner scan=new Scanner(System.in);
		int row,col;
		do {
		System.out.println("Enter row and column");
		row=scan.nextInt();
		col=scan.nextInt();
		}while(!isValidMove(row,col));
		TicTacToe.placeMark(row, col, mark);
	}
	
}
class AIPlayer extends Player{
	
	AIPlayer(String name,char mark){
		this.name=name;
		this.mark=mark;
	}
	void makeMove() {
		Scanner scan=new Scanner(System.in);
		int row,col;
		do {
			Random r=new Random();
			row=r.nextInt(3);
			col=r.nextInt(3);
		}while(!isValidMove(row,col));
		TicTacToe.placeMark(row, col, mark);
	}
}
public class LaunchGame {
	public static void main(String[] args) {
		TicTacToe t=new TicTacToe();
		
		HumanPlayer p1=new HumanPlayer("Bob",'X');
		AIPlayer p2=new AIPlayer("AI",'O');
		
		Player cp;
		cp=p1;
		
		while(true) {
			System.out.println(cp.name + " turn");
			cp.makeMove();
			TicTacToe.dispBoard();
			if(TicTacToe.checkColWin() || TicTacToe.checkRowWin() || TicTacToe.checkDiagnolWin()) {
				System.out.println(cp.name+ " has won");
				break;
			}
			else if(TicTacToe.checkDraw()) {
				System.out.println("game is draw");
				break;
			}
			else {
				if(cp==p1) {
					cp=p2;
				}
				else {
					cp=p1;
				}
				}
			}
	
	/*	HumanPlayer p1=new HumanPlayer("Bob",'X');
		HumanPlayer p2=new HumanPlayer("Alice",'O');
		
		HumanPlayer cp;
		cp=p1;
		
		while(true) {
			System.out.println(cp.name + " turn");
			cp.makeMove();
			TicTacToe.dispBoard();
			if(TicTacToe.checkColWin() || TicTacToe.checkRowWin() || TicTacToe.checkDiagnolWin()) {
				System.out.println(cp.name+ " has won");
				break;
			}
			else {
				if(cp==p1) {
					cp=p2;
				}
				else {
					cp=p1;
				}
			}
		}
*/
/*		t.dispBoard();
		t.placeMark(0, 2, 'X');	
		t.placeMark(1, 1, 'X');
		t.placeMark(2, 0, 'X');	
		t.dispBoard();
		System.out.println(t.checkRowWin());
		System.out.println(t.checkColWin());
		System.out.println(t.checkDiagnolWin());
*/
	}
}
