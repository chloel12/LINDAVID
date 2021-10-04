import java.util.*;

public class Mansion {

    private ArrayList<String> hallway;
    private ArrayList<Room> rooms;
    private String [] clues = {"DUCK", "SHIN", "GOAT", "DEAN", "MEAN", "MODE", "FEET", "TOES", "SHOE", "TRUE", "DEAD", "BEES", "POOP", "FREE", "SWAG", "HOME", "BOAT", "NSFW", "RSVP", "ROOM", "SHIP", "DEEZ", "STUN", "RUNG", "CRAB", "DOWN", "COOL", "PEEP", "CARD", "FART", "LARD", "TINA", "CARP", "COKE", "YOKE", "BACK", "PACK", "MATH", "CODE", "YEET", "YOLK", "HAIR", "NAIL", "ALEX", "RING", "NOSE", "WYAT", "FALE", "LITL", "SMES", "CHAI", "RICE", "TOES", "YASS", "BUTT", "EYES", "DEEP", "BEEP", "LEAP", "MARS", "DATA", "PANT", "COKE", "SOCK", "FISH", "BOOT", "DLIN", "DVID", "NINA", "LINA", "LEAH", "DAVE", "MINA", "LENA", "SAMN", "POKE", "WOKE", "LAME", "GOOD", "MEEP", "BOOK", "DAYS", "PRAY", "ROCK", "MARK", "ANDY", "WHEE", "1234", "EHHH", "jOhN", "WIND", "OOGA", "BOOG", "BOOP", "LOOT", "LOOM", "DOGE", "COIN", "MOON", "HIGH", "TALL", "FLEE", "MATH", "CHEM", "ROOT", "MOOT", "HACK", "LOOP", "SNOW", "MASK", "BARF", "WOOF", "MEOW", "MOOO", "QUAK", "OINK", "YAWN", "FALL", "TEXT", "HOLE", "SMOL", "TOIL", "APPS", "COOL", "COIL", "COOP", "FOIL", "BOIL", "OYLE",  "CORN", "MAMA", "OHIO", "LAND", "BURN", "DADA", "FIRE", "SHOO", "FOOD", "CELLS", "", "LUNG", "BLUE", "CHAR", "CARS", "NOPE", "POPE", "MEAT", "MEET", "ZOOM", "MICE", "SOUP", "LAPS", "CUPS", "BALL", "COOK", "CHEF", "HIKE"};
    private int clueNum;
    private String clueWord;
    private String clue1;
    private String clue2;
    private String clue3;
    private String clue4;

    public Mansion(){
        hallway = new ArrayList<String>();
        rooms = new ArrayList<Room>();
        createHallRooms();
        clueNum = (int) (Math.random()*clues.length)+1;
        clueWord = clues[clueNum];
        clue1 = clueWord.substring(0,1);
        clue2 = clueWord.substring(1, 2);
        clue3 = clueWord.substring(2, 3);
        clue4 = clueWord.substring(3, 4);
    }

    private void createHallRooms(){

        for (int i=1; i<=8; i++){
            hallway.add(i+"");
            rooms.add(new Room(7));
        }

        //This adds and creates the rooms that you can select, 1-8 at indexes 0-7, respectively.
        boolean cluesAssigned = false;

        //the 4 rooms that will have clues in their reward cells
        int clueRoom1 = (int) (Math.random()*8);
        int clueRoom2 = (int) (Math.random()*8);
        while (clueRoom1 == clueRoom2){
            clueRoom2 = (int) (Math.random()*8);
        }
        int clueRoom3 = (int) (Math.random()*8);
        while (clueRoom3 == clueRoom1 || clueRoom3 == clueRoom2){
            clueRoom3 = (int) (Math.random()*8);
        }
        int clueRoom4 = (int) (Math.random()*8);
        while (clueRoom4 == clueRoom1 || clueRoom4 == clueRoom2 || clueRoom4 == clueRoom3){
            clueRoom4 = (int) (Math.random()*8);
        }


        rooms.get(clueRoom1).setRoomClue(clue1);
        rooms.get(clueRoom2).setRoomClue(clue2);
        rooms.get(clueRoom3).setRoomClue(clue3);
        rooms.get(clueRoom4).setRoomClue(clue4);


        //generates indexes that will be divided into room difficulties after this
        int [] difficultyNums = {0, 1, 2, 3, 4, 5, 6, 7};
        for (int currentIndex=0; currentIndex < difficultyNums.length; currentIndex++){
            int newIndex = (int) (Math.random()*8);
            int currentNum = difficultyNums[currentIndex];
            int swapNum = difficultyNums[newIndex];
            difficultyNums[currentIndex] = swapNum;
            difficultyNums[newIndex] = currentNum;
        }

        //FOR TESTING
        /*for (int i=0; i<difficultyNums.length; i++){
            System.out.print(difficultyNums[i]+" ");
        }
        System.out.println();*/

        //we have 4 ints, clue1, clue2, clue3, and clue4, all of which
        // are a room number that will have a clue in it. The other rooms will
        // have false clues

        //creates numbers of rooms in hallway ArrayList and blank 7x7 rooms in rooms ArrayList
//        for (int i=1; i<=8; i++){
//            hallway.add(i+"");
//            rooms.add(new Room(7));
//        }

        //uses generated difficulties to create traps for each room
        for (int i=0; i<difficultyNums.length; i++) {
            if (i <= 2) {
                rooms.get(difficultyNums[i]).createTraps(0);
            } else if (i <= 5) {
                rooms.get(difficultyNums[i]).createTraps(1);
            } else{
                rooms.get(difficultyNums[i]).createTraps(2);
            }
        }

        // generates reward cells for each room
        for (Room room : rooms){
            room.createReward();
        }

        //FOR TESTING
        //markIsCompleted(2);
    }

    //Room object accessor
    public Room getRoom(int roomNum){
        return rooms.get(roomNum-1);
    }

    //hallway ArrayList accessor
    public ArrayList<String> getHallway(){return hallway;}

    //this makes sure the Room that Vid is trying to enter is between 1-8 and not completed
    public boolean validateRC(String rc){
        int roomNum;
        try{
                roomNum = Integer.parseInt(rc);
            if (roomNum >= 1 && roomNum <= 8 && !(rooms.get(roomNum-1).getIsCompleted())){
                return true;
            }
        }
        catch(Exception e){
            return false;
        }
        return false;
    }

    //this method has been commented out in this class, it was used for testing
    //when a Room is completed, it becomes marked on the String ArrayList
    public void markIsCompleted(int roomNum){
        rooms.get(roomNum-1).changeIsCompleted();
        hallway.set(roomNum-1, "*");
    }

}