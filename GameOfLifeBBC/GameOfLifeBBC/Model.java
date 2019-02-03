import java.util.Random;

public class Model {
	int i = 0;
	int j = 0;
	int columns;
	int rows;
	int [][] gridArray;
	
	
	public Model(int c, int r) {
		columns = c;
		rows = r;
		gridArray = new int [columns][rows];
	}
	
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
	
	public void writeToConsole() {
		
		for(int i=0;i<rows;i++) {
			String temp = "";
			for(int j=0;j<columns;j++) {
				temp += " | " + gridArray[i][j];
			}	
			System.out.println(temp);
		}	
	}
	
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
	
	public int[][] getGridArray() {
		return gridArray;
	}
	
	public int printLife() {
		int[][] temp = new int[rows][columns];
		// Loop through the cells
		for(int i=0;i<rows;i++) {
			for(int j=0;j<columns;j++) {
				int n = countNeighbours(i,j);	
				int alive = gridArray[i][j];
				if(alive==1) {
					if(n==2 | n==3) {
						temp[i][j] = 1;	
					}else {
						temp[i][j] = 0;	
					}
				} else {
					temp[i][j] = 0;
					if(n==3) {
						temp[i][j] = 1;
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
				gridArray[i][j] = temp[i][j];
			}
		}
		return changes;
	}	
	
	public static void main(String [] args) {
		Model x = new Model(20,20);
		int noOfChange = 1;
		x.random(0.6);
	while(noOfChange > 0) {
		x.writeToConsole();
		noOfChange = x.printLife();
	}
	}
}
