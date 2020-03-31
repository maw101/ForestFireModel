package forestfiremodel.cli;

import forestfiremodel.forestfire.ForestFire;
import forestfiremodel.forestfire.ForestFireSimulation;

public class ForestFireCLI implements ForestFireSimulation {

   private ForestFire simulation;

   public ForestFireCLI() {
      simulation = new ForestFire();
   }

   public ForestFireCLI(int gridSize) {
      simulation = new ForestFire(gridSize);
   }

   /**
    * Render.
    */
   public void render() {
      for (int row = 0; row < simulation.getGridSize(); row++) {
         for (int col = 0; col < simulation.getGridSize(); col++) {
            System.out.print(simulation.getPositionValue(row, col));
         }
         System.out.println(); // new row
      }
      System.out.println();
   }

   public void runSimulation() {
      runSimulation(100);
   }

   public void runSimulation(int maxRuns) {
      int count = 1;
      simulation.populate();
      render();
      while (count < maxRuns) {
         System.out.println("Run #" + count + ": ");
         simulation.evolveOnce();
         count++;
         render();
      }
   }

}