package forestfiremodel.gui;

import forestfiremodel.forestfire.ForestFire;
import forestfiremodel.forestfire.ForestFireSimulation;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ForestFireGUI extends Application implements ForestFireSimulation {

   private Stage stage;

   private static final int TILE_SIZE = 20;
   private final int GRID_SIZE = 100;
   private int WINDOW_SIZE;

   private Tile[][] grid;
   private ForestFire simulation;

   public ForestFireGUI() {
      grid = new Tile[GRID_SIZE][GRID_SIZE];
      simulation = new ForestFire(GRID_SIZE);
      WINDOW_SIZE = GRID_SIZE * TILE_SIZE;
   }

   @Override
   public void runSimulation() {
      runSimulation(100);
   }

   @Override
   public void runSimulation(int maxRuns) {
      // run inside thread so JavaFX application thread not being blocked all the time
      Thread thread = new Thread(() -> {
         // dedicated runnable for render
         Runnable updater = () -> render();

         simulation.populate();
         Platform.runLater(updater);

         for (int count = 0; count < maxRuns; count++) {
            try {
               Thread.sleep(1000);
            } catch (Exception e) {
               // do nothing
            }

            simulation.evolveOnce();
            System.out.println("Evolved Once");

            // call the render runnable so can be ran on the JavaFX application thread
            Platform.runLater(updater);
         }

         System.out.println("\nSimulation Completed");
      });

      thread.setDaemon(true);
      thread.start();
   }

   private void render() {
      Pane root = new Pane();
      root.setPrefSize(WINDOW_SIZE, WINDOW_SIZE);
      // change colour for each of the tiles
      for (int y = 0; y < GRID_SIZE; y++) {
         for (int x = 0; x < GRID_SIZE; x++) {
            grid[x][y].setFill(getTilesColor(x, y));

            // add the tile as a child of the pane
            root.getChildren().add(grid[x][y]);
         }
      }

      stage.setScene(new Scene(root));
   }

   private Color getTilesColor(int row, int col) {
      char value = simulation.getPositionValue(row, col);
      switch (value) {
         case ForestFire.BURNING_CELL:
            return Color.RED;
         case ForestFire.TREE_CELL:
            return Color.GREEN;
         default:
            return Color.BLACK;
      }
   }

   private class Tile extends StackPane {
      private int x, y;

      private Rectangle rect = new Rectangle(TILE_SIZE, TILE_SIZE);
      private Text text = new Text();

      public Tile(int x, int y) {
         this.x = x;
         this.y = y;

         rect.setFill(Color.BLACK);

         getChildren().addAll(rect, text);

         // set position in the rendered grid
         setTranslateX(x * TILE_SIZE);
         setTranslateY(y * TILE_SIZE);
      }

      public void setFill(Color fillColor) {
         rect.setFill(fillColor);
      }
   }

   private Parent initialiseGrid() {
      Pane root = new Pane();
      root.setPrefSize(WINDOW_SIZE, WINDOW_SIZE);

      // create each of the tiles
      for (int y = 0; y < GRID_SIZE; y++) {
         for (int x = 0; x < GRID_SIZE; x++) {
            Tile tile = new Tile(x, y);
            grid[x][y] = tile;

            // add the tile as a child of the pane
            root.getChildren().add(tile);
         }
      }

      return root;
   }

   @Override
   public void start(Stage primaryStage) {
      stage = primaryStage;
      Parent root = initialiseGrid();

      stage.setTitle("Forest Fire Model (Drossel and Schwabl Definition)");
      stage.setScene(new Scene(root));
      stage.show();

      runSimulation();
   }

   public static void main(String[] args) {
      launch(args);
   }
}