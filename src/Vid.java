import java.util.ArrayList;
import java.util.Scanner;

public class Vid {

    private int roomNum;
    private int numLetters;
    private ArrayList<String> letters; //the letters that vid has collected
    private int[] positionInRoom = new int[2]; //position = index in grid array
    //positionInRoom is an (x, y) coordinate system representing the place in the grid array (row, col)
    private Mansion vidMansion;

    public Vid(Mansion m){
        //room -1 because they haven't chosen which room to go to yet
        roomNum = -1;
        numLetters = 0;
        letters = new ArrayList<String>();

        //instead of setting it to 2, set it to random position that is not a trap 
        positionInRoom[0] = 2;
        positionInRoom[1] = 2;
        vidMansion = m;
    }

    public int getRoomNum(){
        return roomNum;
    }

    public Mansion getVidMansion(){return vidMansion;}

    public int getNumLetters(){
        return numLetters;
    }

    public int getRow(){
        return positionInRoom[0];
    }

    //format of positionInRoom: [row, column]
    public int getCol(){
        return positionInRoom[1];
    }

    public void setRow(int row) { positionInRoom[0] = row; }

    public void setCol(int col) { positionInRoom[1] = col; }

    public String getPositionInRoom(){
        return "[ " + positionInRoom[0] + ", " + positionInRoom[1] + "]";
    }

    public void setRoomNum(int num) {
        roomNum = num;
    }

    public void addLetter(String newLetter) {
        letters.add(newLetter);
        numLetters++;
    }

    public void loseLetter() {
        letters.remove(numLetters-1); //removing letter from last index
        numLetters--;
    }


    /**
     *
     * @param direction: a string input of the direction the player wishes to go
     * @return: returns true if move is valid, otherwise returns false
     */
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ALSO PLEASE I NEED YOU TO MAKE THIS METHOD DO ALL OF THE MOVING AND RESETTING~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //it needs to change vid's position array, reset the cell he was in, make safe any trap cells he's already been on, and print the room again
    public boolean move(String direction){
        if(direction.equalsIgnoreCase("w")|| direction.equalsIgnoreCase("up") || direction.equalsIgnoreCase("north") ){ //up
            if(!checkBounds(positionInRoom[0]-1, vidMansion.getRoom(roomNum) )){
                System.out.println("should be original pos: " + positionInRoom[0] + " " + positionInRoom[1]);
                System.out.println("Move out of bounds. Try again:");
                //System.out.println("fail check: " + positionInRoom[1]);
                return false; //return false if move is unsuccessful (out of bounds)
            }
            
            //System.out.println("pass check: " + positionInRoom[1]);

             //remove vid from his first cell
            vidMansion.getRoom(roomNum).getCell(positionInRoom[0], positionInRoom[1]).setVid(false);
            //reveal cell that vid was in
            vidMansion.getRoom(roomNum).getCell(positionInRoom[0], positionInRoom[1]).revealCell();
            //move upward one
            positionInRoom[0]--;
            vidMansion.getRoom(roomNum).getCell(positionInRoom[0], positionInRoom[1]).setVid(true);
            //vidMansion.getRoom(roomNum).getCell(positionInRoom[0], positionInRoom[1]).hideCell();

            return true; //return true if move is a success
        }

        else if(direction.equalsIgnoreCase("s")|| direction.equalsIgnoreCase("down") || direction.equalsIgnoreCase("south")){ //down
            if(!checkBounds(positionInRoom[0]+1, vidMansion.getRoom(roomNum) )){
                
                System.out.println("Move out of bounds. Try again:");
                return false;
            }
            
            vidMansion.getRoom(roomNum).getCell(positionInRoom[0], positionInRoom[1]).setVid(false);
            vidMansion.getRoom(roomNum).getCell(positionInRoom[0], positionInRoom[1]).revealCell();
            positionInRoom[0]++;
            vidMansion.getRoom(roomNum).getCell(positionInRoom[0], positionInRoom[1]).setVid(true);
            //vidMansion.getRoom(roomNum).getCell(positionInRoom[0], positionInRoom[1]).deactivateCell();

            return true;
        }

        else if(direction.equalsIgnoreCase("a")|| direction.equalsIgnoreCase("left") || direction.equalsIgnoreCase("west")){ //left
            if(!checkBounds(positionInRoom[1]-1, vidMansion.getRoom(roomNum) )){
                
                System.out.println("Move out of bounds. Try again:");
                return false;
            }
           
            vidMansion.getRoom(roomNum).getCell(positionInRoom[0], positionInRoom[1]).setVid(false);
            vidMansion.getRoom(roomNum).getCell(positionInRoom[0], positionInRoom[1]).revealCell();
            positionInRoom[1]--;
            vidMansion.getRoom(roomNum).getCell(positionInRoom[0], positionInRoom[1]).setVid(true);
            //vidMansion.getRoom(roomNum).getCell(positionInRoom[0], positionInRoom[1]).deactivateCell();

            return true;
        }

        else if(direction.equalsIgnoreCase("d")|| direction.equalsIgnoreCase("right") || direction.equalsIgnoreCase("east")){ //right
            if(!checkBounds(positionInRoom[1]+1, vidMansion.getRoom(roomNum) )){
                
                System.out.println("Move out of bounds. Try again:");
                return false; //return false if move is unsuccessful (out of bounds)
            }
           
            vidMansion.getRoom(roomNum).getCell(positionInRoom[0], positionInRoom[1]).setVid(false);
            vidMansion.getRoom(roomNum).getCell(positionInRoom[0], positionInRoom[1]).revealCell();
            positionInRoom[1]++;
            vidMansion.getRoom(roomNum).getCell(positionInRoom[0], positionInRoom[1]).setVid(true);
            //vidMansion.getRoom(roomNum).getCell(positionInRoom[0], positionInRoom[1]).deactivateCell();

            return true; //return true if move is a success
        }
        System.out.println("Invalid letter. Use WASD. Try again:");
        return false; //if they input wrong letter
    }

    // checks if coordinates are within room bounds
    public boolean checkBounds(int coord, Room room){
        if(coord < room.getSize() && coord > -1){
            return true;
        }
        return false;
    }

}