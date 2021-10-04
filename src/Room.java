import java.util.*;

public class Room{
    private int size;
    private Cell [][] grid;
    private boolean isCompleted;
    private String roomClue;

    private static Random gen = new Random();

    /**
     *
     * @param size: determines the length and width of the room
     *            type: int
     */
    public Room(int size){
        this.size=size;
        isCompleted=false;
        grid = new Cell[size][size];
        //creates a room of entirely blank/safe cells
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                grid[i][j] = new Cell(0);
            }
        }

        String roomClue = "bad";
    }

    //returns the size of the room
    public int getSize(){return size;}
    public void setRoomClue(String clue){
        roomClue = clue;
    }


// ASSUMES THAT ALL ROOMS ARE SQUARE
    public String toString() {
        //top row
        String entire = "";
        for (int i = 0; i < (grid.length)*4+2; i++){
            entire += "-";
        }
        entire += "\n";
        //numbered rows
        for (int i = 1; i <= grid.length; i++) {
            entire += "|";
            for (int j = 0; j < grid.length; j++) {
                //if else here- if vid position (i == vid.getXpos && j == vid.getYPos) print a v in the Cell,
                // else:call the normal toString for the cell
                //createVidCell();
                entire += grid[i-1][j] + "\t|";
                
            }
            
            entire += "\n";
            for (int j = 0; j < (grid.length)*4+2; j++){ //don't change these nums -- they worlk but wdk why
                entire += "-";
            }
            entire += "\n";
        }
        return entire;
    }

    //returns the room with only Vid in it as a String
    //must be printed to display
    public String blankRoom() {
        //top row just dashes
        String entire = "";
        for (int i = 0; i < (grid.length)*4+2; i++){
            entire += "-";
        }
        entire += "\n";
        //for the length of the room, make cells
        for (int i = 1; i <= grid.length; i++) {
            entire += "|";
            for (int j = 0; j < grid.length; j++) {
                if (grid[i-1][j].getHasVid() || grid[i-1][j].getCellType().equalsIgnoreCase("reward")){
                    entire += grid[i-1][j] + "\t|";
                } else if (grid[i-1][j].getIsRevealed()) {
                    entire += grid[i-1][j] + "\t|";
                } else{
                    entire +=  "\t|";
                }

            }

            entire += "\n";
            for (int j = 0; j < (grid.length)*4+2; j++){ //don't change these nums -- they worlk but wdk why
                entire += "-";
            }
            entire += "\n";
        }
        return entire;
    }

    public void changeIsCompleted(){
        isCompleted=!isCompleted;
    }


    //assuming rooms are 7x7, so no more than 3 per row
    //cannot refer to non-static (object) things from static methods

    //precondition --> difficulty is between 0 and 2 to correspond with
    // easy(0), medium(1), and hard(2)

    public void createTraps(int difficulty) {
        // number of traps per row
        int maxTraps = difficulty + 2;
        //go through each row
        for (Cell[] row : grid) {
            //keeps track of how many cells in each row are traps
            int count = 0;
            for (Cell cell : row) {
                cell.setCellType((int) (Math.random() * 2));
                if (cell.getCellType().equalsIgnoreCase("trap")) {
                    count++;
                }
                if (count == maxTraps) {
                    break;
                }
            }

        }

        for (Cell[] row : grid) {
            for (Cell cell : row) {
                cell.revealCell();
            }


        }
    }

    public void hideTraps(){
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].getCellType().equalsIgnoreCase("trap")){
                    grid[i][j].hideCell();
                }

            }
        }

    }


    /**
     *  Creates a single reward cell in a random position in the grid
     */
    public void createReward(){
        // X and Y coordinates of the cell
       int rewardRow = (int) (Math.random() * 3);
       int rewardCol = (int) (Math.random() * 7);
       //change the cell to reward type
       grid[rewardRow][rewardCol].setCellType(2);
       grid[rewardRow][rewardCol].setCellClue(roomClue);
    }

/*
    public void createVidCell(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(i == roomGame.player.getYpos() && j == roomGame.player.getXpos()){
                    grid[i][j].setCellType(3);
                }
            }
        }
    }

 */


    //will return coordinates and be used for reward cell
    // x coordinate will be in index 0
    // y coordinate will be in index 1
    public int [] getCellPos(String cellType) {
        // coordinates set to negative if a cell isnt found for error checking
        int[] pos = {-1, -1};
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // returns position of the first instance of the input cell type
                if (grid[i][j].getCellType() == cellType) {
                    pos[0] = i;
                    pos[1] = j;
                    return pos;

                }
            }
        }
        return pos; //lol temporary to allow for testing lol
    }

    /**
     *
     * @param x: vertical coordinate of the cell
     * @param y: horizontal coordinate of the cell
     * @return: returns the cell object
     *          type: Cell
     */
    public Cell getCell(int row, int col){
       return grid[row][col];
    }

    public boolean getIsCompleted() { return isCompleted; }

}