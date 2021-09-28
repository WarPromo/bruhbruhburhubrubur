public class MonteCarloOutput {
    public static void main(String[] args) {
        // A non-graphical runner for doing a lot
        // of simulations and displaying the results

        // You'd run this if you wanted to run, say 1000 trials for
        // a set of initial conditions and see the results.
        for(double density = 0.05; density <= 1; density += 0.05){
            System.out.println(density + ","  + avgSim(50, 50, density,  1000));
        }



    }

    public static double avgSim(int r, int c, double density, double trials){
        double avg = 0;
        for(int a = 0; a < trials; a++){
            double result = oneSim(r, c, density);
            avg+=result;
        }
        return avg / trials;
    }

    public static double oneSim(int r, int c, double density){
        Simulator sim = new Simulator(r,c, density);

        while(sim.ended == false){
            sim.Simulate();
        }

        return sim.LIVING_TREES  / ( r*c * density);
    }
}
