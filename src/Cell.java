import java.util.*;

/**
 * Class for each cell within every 7x7 Room
 * Contains a type and a condition where it is revealed
 *
 * var cellType: identifies whether the cell is safe, unsafe, or a reward.
 * type cellType: int
 *
 * var isRevealed: Conditional identifying if the cell is visible or invisible
 * type isRevealed: Boolean
 *
 * array cellTypes: Array containing the types of cells. Index value corresponds to the cell type...
 *                  ... 0 = Safe; 1 = Trap; 2 = Reward;
 */

public class Cell {
    private int cellType;
    private boolean isTrick;
    private static String [] cellTypes = {"Safe", "Trap", "Reward"};
    private boolean isRevealed = false;
    private boolean hasVid;
    private String cellClue;

    // internal conversion tool from String to integer
    private int convertType(String type) {
        int newType = 0;
        for (int i = 0; i < cellTypes.length; i++) {
            if (type.equalsIgnoreCase(cellTypes[i])) {
                newType = i;
            }
        }
        return newType;
    }

    /**
     * @param type: determines the type of cell corresponding to the array
     *              type: int
     */
    public Cell(int type) {
        cellType = type;
        isRevealed = false;
        isTrick = false;
        hasVid = false; //stoof;
        cellClue = "bad";
    }

    // returns the type of cell as a string
    public String getCellType() {
        String type = cellTypes[cellType];
        return type;
    }

    /**
     * sets the type of cell as the index value based on String input
     *
     * @param type: the name of the type of cell you want this to be
     *              type: String
     */
    public void setCellType(String type) {

        //finds index of type in cellTypes
        int newType = convertType(type);
        cellType = newType;
    }

    //sets the type as the index value based on int input (overloaded method - used in Room class)
    public void setCellType(int type) {
        //finds index of type in cellTypes
        cellType = type;
    }

    //  sets the cell to being revealed/ visible
    public void revealCell() {
        isRevealed = true;
    }

    // sets the cell to being hidden
    public void hideCell(){ isRevealed = false; }

    // tells whether the cell is revealed or not
    public boolean getIsRevealed(){ return isRevealed;}

    /**
     * @return: tells whether the reward cell gives a reward (false) or is a trick (true)
     *          type: boolean
     */
    public boolean checkTrick(){
        if (cellType == 2) {
            return isTrick;
        }
        else{return false;}
    }

    /**
     *  sets the (hopefully) reward cell to being a trick without a clue
     */
    public void setTrick(){
        isTrick = true;
    }



    public String toString() {
        String display = "";
        if (hasVid){
            return " V";
        } else if (this.getCellType().equalsIgnoreCase("trap")) {
            if (isRevealed) {
                return " X"; //added a space -- may break
            } else {
                return "  ";
            }
        } else if (this.getCellType().equalsIgnoreCase("safe")) {
            return "  ";
        }
       else if(this.getCellType().equalsIgnoreCase("reward")) {
        return " ?";
        }
        return display;
    }

    public void setVid(boolean status){
        hasVid = status;
    }

    /**
     *
     * @return: returns true or false as to if Vid is in the cell.
     *         type: boolean
     */
    public boolean getHasVid(){
        return hasVid;
    }

    public void setCellClue(String clue){
//        cellClue = clue;
//        if (!(clue.equals("bad"))){
//            isTrick = false;
//        }
//        else{
//            isTrick = true;
//        }
    }

    public String getCellClue(){return cellClue;}

}