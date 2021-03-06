package forestfiremodel.forestfire;

import java.util.Random;

/**
 * The type Forest fire.
 * <p>
 * Simulates a Forest Fire by implementing the Drossel and Schwabl definition of the forest-fire model.
 */
public class ForestFire {

    private char grid[][];
    private int gridSize;
    private static final double PROB_TREE = 0.5;
    private static final double PROB_TREE_NO_NEIGHBOURS_BURNING_IGNITES = 0.15;
    private static Random rand = new Random();
    private static final int[][] MOORE_NEIGHBOURS = {
        {-1, -1},   {-1, 0},    {-1, 1},
        {0, -1},                {0, 1},
        {1, -1},    {1, 0},     {1, 1}};

    /**
     * The constant BURNING_CELL to represent a burning cell
     */
    public static final char BURNING_CELL = 'B';
    /**
     * The constant TREE_CELL to represent a tree cell
     */
    public static final char TREE_CELL = 'T';
    /**
     * The constant BLANK_CELL to represent a blank/empty cell
     */
    public static final char BLANK_CELL = ' ';

    /**
     * Instantiates a new Forest fire.
     */
    public ForestFire() {
        gridSize = 10;
        grid = new char[gridSize][gridSize];
    }

    /**
     * Instantiates a new Forest fire.
     *
     * @param gridSize the grid size
     */
    public ForestFire(int gridSize) {
        this.gridSize = gridSize;
        grid = new char[gridSize][gridSize];
    }

    /**
     * Populate.
     */
    public void populate() {
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                if (rand.nextDouble() < 0.5)
                    grid[row][col] = TREE_CELL;
                else
                    grid[row][col] = BLANK_CELL;
            }
        }
    }

    /**
     * Evolves the simulation once.
     * <p>
     * Each cell can be in three distinct states: empty, burning and tree. It evolves according to the following
     * rules:
     * A burning cell turns into an empty cell
     * A tree will burn if at least one neighbour is burning
     * A tree ignites with a given probability even if no neighbour is burning
     * An empty space fills with a tree with a given probability
     */
    public void evolveOnce() {
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                switch(grid[row][col]) {
                    case BURNING_CELL:  // burning cell, turns into empty cell
                        grid[row][col] = BLANK_CELL;
                        break;
                    case BLANK_CELL:    // blank cell, fills with tree with probability p
                        if (rand.nextDouble() < PROB_TREE)
                            grid[row][col] = TREE_CELL;
                        break;
                    case TREE_CELL:
                        if (countBurningNeighbours(col, row) > 0) // if one or more neighbours burning, ignites
                            grid[row][col] = BURNING_CELL;
                        else if (rand.nextDouble() < PROB_TREE_NO_NEIGHBOURS_BURNING_IGNITES)
                            // if no neighbours burning, ignites with the given probability
                            grid[row][col] = BURNING_CELL;
                        break;
                    default:
                        throw new IllegalStateException("Cell at " + row + " " + col + " has an invalid type.");
                }
            }
        }
    }
    
    /**
     * Gets grid size.
     *
     * @return the grid size
     */
    public int getGridSize() {
        return gridSize;
    }

    /**
     * Gets the value at a given position.
     *
     * @param row the row
     * @param col the column
     * @return the position value
     */
    public char getPositionValue(int row, int col) {
        return grid[row][col];
    }

    private int countBurningNeighbours(int x, int y) {
        int count = 0;
        for (int[] offset : MOORE_NEIGHBOURS)
            if (isCellBurning(x + offset[0], y + offset[1]))
                count++;
        return count;
    }

    private boolean isCellBurning(int x, int y) {
        return x >= 0 && y >= 0 && x < gridSize && y < gridSize && (grid[y][x] == BURNING_CELL);
    }

}