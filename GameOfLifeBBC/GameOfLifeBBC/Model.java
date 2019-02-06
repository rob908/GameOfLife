import java.util.Random;
import java.util.Scanner;

/* The java code runs a simple game of life programme which prints to the console. Living cells are represented as '1' and dead cells as '0'. 
 * The logic shown could be applied to a GUI for a better visual representation of the game of life.
*/

public class Model {
	private int i = 0;
	private int j = 0;
	private int columns;
	private int rows;
	private int [][] gridArray;
	
	
	//method to create the initial two dimensional array (world) with a constructor for the rows and columns of the grid
	public Model(int c, int r) {
		columns = c;
		rows = r;
		gridArray = new int [columns][rows];
	}
	
	//randomly sets cells in the grid as '1's and '0's.
	public void random(double c) { 
		Random r = new Random();
		for(i = 0; i < columns; i++) {
			for (j = 0; j < rows; j++) {
				double randomCell = r.nextDouble();
				
				if (randomCell < c) {
					gridArray [i][j] = 1;
				} else {
					gridArray [i][j] = 0;
				}
			}
		}
	}
	
	//writes the grid output to the console. this method also formats the world by adding each cell in the grid to a string, adding a space
	//between the cells, and separates each cell with a |.
	public void writeToConsole() {
		
		for(int i=0;i<rows;i++) {
			String temp = "";
			for(int j=0;j<columns;j++) {
				temp += "|" + gridArray[i][j] + "|";
			}	
			System.out.println(temp);
		}
		System.out.println(); //separates each iteration with a line
	}
	
	//method to check the state of the neighbours of each cell. counter contains the values of living neighbours for each cell. this result is then
	//returned and used in the later printLife method.
	public int countNeighbours(int r, int c) {
		int counter = 0;
		int x;
		int y;
		for(int i=r-1;i<=r+1;i++) {
			x = i;
			if (x < 0) {
				x += rows;
			}
			if (x >= rows) {
				x -= rows;
			}
			for(int j=c-1;j<=c+1;j++) {
				y = j;
				if (y < 0) {
					y += columns;
				}
				if (y >= columns) {
					y -= columns;
				}
				if (x!=r | y!=c) {
					counter += gridArray[x][y];
				}
			}
		}
		return counter;
	}
	
	//method that uses the results from the countNeighbours method to determine where changes are going to made within the world.
	public int printLife() {
		int[][] temp = new int[rows][columns];//creates temporary array to store results on
		for(int i=0;i<rows;i++) {
			for(int j=0;j<columns;j++) {
				int n = countNeighbours(i,j);	
				int alive = gridArray[i][j];
				if(alive==1) {
					if(n==2 | n==3) { //if a living cell has 2 or 3 living neighbours, it stays alive
						temp[i][j] = 1;	
					}else {
						temp[i][j] = 0;	//if a living cell has 1 or 0 living neighbours, it dies.
					}
				} else {
					temp[i][j] = 0;
					if(n==3) {
						temp[i][j] = 1; //if a dead cell has 3 living neighbours, it comes alive 
					}
				}
			}	
		}
		int changes = 0;
		for(int i=0;i<rows;i++) {
			for(int j=0;j<columns;j++) {
				if(gridArray[i][j]!=temp[i][j]) {
					changes++;
				}
				gridArray[i][j] = temp[i][j]; //implements the new world state stored in temporary to the original world (gridArray).
			}
		}
		return changes; //counts the numbers of changes made in the world. while this number is greater than 1, the process (game of life) will repeat and until 
		//no more changes happen.
	}	
	
	//asks user to input grid size and uses the results to create the game of life world
	public static int [] gridSize() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter the grid size using two numbers with a space in between e.g. 100 100:");
		int row = sc.nextInt();
		int column = sc.nextInt();
		int [] gridSize = {row, column};
		sc.close();
		return gridSize;
		
		
	}
	public static void main(String [] args) {
		int [] gridSize = gridSize();
		int r = gridSize [0];
		int c = gridSize [1];
		Model x = new Model(r,c);
		int noOfChange = 1; //initial value set to 1 so that the game can begin.
		x.random(0.6); /*determines how many values are changed to living in the initial state of the world. the closer the value is to 1, the greater
						*the occurrence of living cells.*/
		while(noOfChange > 0) { //loops while changes happen within the world so that each iteration is printed to the console
			x.writeToConsole();
			noOfChange = x.printLife();
		}
	}
}
