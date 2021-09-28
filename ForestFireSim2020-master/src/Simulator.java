import java.sql.SQLOutput;
import java.util.ArrayList;

public class Simulator {

    int frameCounter = 0;
    public Tree[][] trees;

    public static int ON_FIRE = 1;
    public static int EMPTY = -1;
    public static int ASH = 2;
    public static int LIVING = 0;

    public static int turnAshTime = 10;

    public boolean ended = false;
    public int LIVING_TREES = 0;

    /***
     * Create a new simulator.  The simulator creates a new Forest of size (r, c).
     *
     * @param r
     * @param c
     */
    public Simulator(int r, int c, double density) {
        trees = new Tree[r][c];

        int totalTrees = (int) (r*c*density);
        LIVING_TREES = totalTrees;

        ArrayList<int[]> availableSpots = new ArrayList<int[]>();

        for(int a = 0; a < r; a++){
            for(int b = 0; b < c; b++){

                int[] arr = {a,b};
                trees[a][b] = new Tree(-1);

                availableSpots.add(arr);
            }
        }

        for(int a = 0; a < totalTrees; a++){
            int random = (int) ( Math.random() * availableSpots.size() );
            int[] tree = availableSpots.get( random );

            trees[tree[0]][tree[1]] = new Tree(0);
            if(a == totalTrees / 2){
                trees[tree[0]][tree[1]].type = 1;
            }
            availableSpots.remove(random);

        }


    }

    public void Simulate(){
        frameCounter++;

        Tree[][] clone = new Tree[trees.length][trees[0].length];

        if(ended) System.out.println("Ended: " + LIVING_TREES);

        for(int a = 0; a < trees.length; a++){
            for(int b = 0; b < trees[0].length; b++){
                clone[a][b]= new Tree( trees[a][b].type );
                clone[a][b].burnTime = trees[a][b].burnTime;
                clone[a][b].ashTime = trees[a][b].ashTime;
            }
        }

        int living = 0;
        boolean dead = true;

        for(int a = 0; a < trees.length; a++){
            for(int b = 0; b < trees[0].length; b++){

                Tree tree = clone[a][b];



                if(tree.type == 1){
                    dead = false;
                    trees[a][b].burnTime++;

                    if(trees[a][b].burnTime > 30) trees[a][b].type = 2;
                    double r = 1;
                    for(double c = a - r; c <= a + r; c++){
                        for(double d = b - r; d <= b + r; d++){
                            if(realSquare((int) c, (int) d)) trees[(int)c][(int)d].type = 1;
                        }
                    }

                }
                if(tree.type == 2){
                    dead = false;
                    trees[a][b].ashTime++;

                }
                if(tree.type == 0){
                    living++;
                }

                if(tree.ashTime > trees[a][b].turnAshTime){

                    trees[a][b] = new Tree(0);
                    if(Math.random() > 0.1)trees[a][b] = new Tree(-1);
                }
            }
        }

        LIVING_TREES = living;
        ended = dead;

    }


    public boolean realSquare(int r, int c){
        return r < trees.length && c < trees[0].length && r > -1 && c > -1 && trees[r][c].type == 0;
    }



    // TODO: add methods outlines in assignment sheet
    // * way to get statistical information about the current state of the simulation
    // * way to run simulation for one step
    // * way to do a "full run" of running until fires are burned out
    // * way to (re-)initialize your forest with particular tree density
    //

    public int getWidth() {
        return trees.length;   // TODO: change this once you make a grid variable
    }

    public int getHeight() {
        return trees[0].length;   // TODO: change this once you make a grid variable
    }

    public Tree[][] getDisplayGrid() {
        return trees;
    }
}

class Tree{
    public int type = -1;
    public int burnTime = 0;
    public int ashTime = 0;
    public int greenery = (int)(Math.random() * 5) ;
    public int turnAshTime = (int)(Math.random() * 2);


    public Tree(int type){
        this.type = type;
    }
}