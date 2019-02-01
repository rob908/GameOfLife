public class Model {
	int gridHeight;
	int gridWidth;
	int i = 0;
	int j = 0;
	
	Model (int height, int width) {
		String[][]gridArray = new String [height][width];
		for(i = 0; i < height; i++) {
			for (j = 0; j < width; j++) {
                gridArray [i][j] = "X";
                System.out.print("[" + gridArray[i][j]+ "]");
			}
			System.out.println();
		}
    }
    public static void main(String[] args) {
        Model gridMaker = new Model(10,10);
    }
} 