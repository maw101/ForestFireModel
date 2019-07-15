public class ForestFire {

    private char grid[][];
    private int gridSize;

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

    }

}