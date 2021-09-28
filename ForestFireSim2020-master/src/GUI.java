import processing.core.*;

public class GUI extends PApplet {
    Simulator sim;
    DisplayWindow gridDisplay;

    public void settings() {
        size(640, 550); // set the size of the screen.
    }

    public void setup() {
        // Create a simulator

        sim = new Simulator(100, 100, 0.5);

        // Create the display
        // parameters: (10,10) is upper left of display
        // (620, 530) is the width and height
        gridDisplay = new DisplayWindow(this, 10, 10, 620, 530);

        gridDisplay.setNumCols(sim.getWidth());		// NOTE:  these must match your simulator!!
        gridDisplay.setNumRows(sim.getHeight());

        // Set different grid values to different colors
        // TODO:  uncomment these lines!
        gridDisplay.setColor(Simulator.ON_FIRE, color(255, 0, 0));
        gridDisplay.setColor(Simulator.ASH, color(180, 180, 180));
        gridDisplay.setColor(Simulator.LIVING, color(0, 255, 0));
        gridDisplay.setColor(Simulator.EMPTY, color(150, 75, 0));

    }

    @Override
    public void draw() {
        background(200);

        // TODO: have your simulation run one step.
        if(sim.ended) sim = new Simulator(100, 100, 0.5);
        sim.Simulate();



        gridDisplay.drawGrid(sim.getDisplayGrid()); // display the game
    }

    public static void main(String[] args) {
        PApplet.main("GUI");
    }
}