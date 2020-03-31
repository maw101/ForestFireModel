package forestfiremodel;

import forestfiremodel.cli.ForestFireCLI;
import forestfiremodel.forestfire.ForestFireSimulation;

/**
 * The type forestfiremodel.Application.
 */
public class Application {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ForestFireSimulation fireSimulation = new ForestFireCLI();
        fireSimulation.runSimulation();
    }

}