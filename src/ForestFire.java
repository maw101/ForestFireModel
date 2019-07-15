public class ForestFire {

    private char grid[][];
    private int gridSize;
    private static final int[][] MOORE_NEIGHBOURS = {
        {-1, -1},   {-1, 0},    {-1, 1},
        {0, -1},                {0, 1},
        {1, -1},    {1, 0},     {1, 1}};


    public ForestFire() {
        gridSize = 15;
        grid = new char[gridSize][gridSize];
    }

    public ForestFire(int gridSize) {
        this.gridSize = gridSize;
        grid = new char[gridSize][gridSize];
    }

    public void runSimulation() {
        populate();
        while (true) {

        }
    }

    public void populate() {
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                if (Math.random() <= 0.5)
                    grid[row][col] = 'T';
                else
                    grid[row][col] = ' ';
            }
        }
    }

    public void increment() {
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                
            }
        }
    }

    private int countBurningNeighbours(int x, int y) {
        int count = 0;
        for (int[] offset : MOORE_NEIGHBOURS)
            if (isWithinGrid(x + offset[0], y + offset[1]))
               count++;
        return count;
    }

    private boolean isWithinGrid(int x, int y) {
        return x >= 0 && y >= 0 && x < gridSize && y < gridSize;
    }



}