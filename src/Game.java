import java.util.*;
import java.util.concurrent.TimeUnit;

public class Game {
    Scanner s = new Scanner(System.in);

    Mansion manshin;
    Vid player;
    //calls all methods needed to fully start the game
    //just like in the mainGameTester class, it functions as an easy way to start the game
    public void begin(){

        setup();
        exposition();

        //need to make a conditional while loop involving whether or not player has collected all 4 clues
        playing();
    }

    //creates Mansion- arraylist of room objects for vid to move to
    // -=-=-=-=-=-=-=-=-=-=-=-=-PLEASE REMEMBER-=-=-=-=-=-=-=-=-=-=-=-=--=-=-
    // Mansion is the name of the object class, while manshin is the name of the
    //instance of the object

    public void setup(){
        //creates Mansion object
        manshin = new Mansion();
        player = new Vid(manshin);

    }

    public void exposition(){
        //this will be all the story and stuff at the beginning
        System.out.println("Hello, intrepid explorer. Welcome to LINDA AND VID!");
        System.out.println("Please type \"start\" to begin:");
        System.out.print("> ");
        String input = s.nextLine();
        if (input.equalsIgnoreCase("start")) {
            linePrint(1000);
            System.out.println("Wonderful. Our story begins...");

            try{
                Thread.sleep(3000);
            } catch (InterruptedException ie) {Thread.currentThread().interrupt();}
            System.out.println();

            System.out.println("You, Vid, arrive at the door of your childhood home.");
            System.out.println("By this time, it's more of an abandoned mansion than a nostalgic memory, but you're here on a mission:");

            try{
                Thread.sleep(6000);
            } catch (InterruptedException ie) {Thread.currentThread().interrupt();}
            System.out.println();

            System.out.println("Linda, your evil and also fraternal twin, has kidnapped your parents. Yes, you heard me correctly.");
            System.out.println("Linda lives in the floorboards, and she's not very happy that you're here to foil her plans.");

            try {
                Thread.sleep(8000);
            } catch (InterruptedException ie) {Thread.currentThread().interrupt();}
            System.out.println();

            System.out.println("However, you are determined. Your goal is to make it through the house and collect all the letters");
            System.out.println("of the password needed to crack the code! (~BUT BEWARE OF THE LINDA PIT~)");

            try {
                Thread.sleep(6000);
            } catch (InterruptedException ie) {Thread.currentThread().interrupt();}
            System.out.println();

            System.out.println("When you enter the house, you are met by a familiar hallway.");
        }

    }

    public void playing(){
        chooseRoom();
        //print out lines so they can't see traps, IMPLEMENT HERE

        //Cell type that player is currently on == safe?


        //while the player is not on a reward cell
        while (!(player.getVidMansion().getRoom(player.getRoomNum()).getCell(player.getRow(), player.getCol()).getCellType().equals("reward"))) {
            //player makes a move 
            makeMove();
            //the room is printed out with the players new location

            System.out.println(player.getVidMansion().getRoom(player.getRoomNum()).blankRoom());

            System.out.println();
            System.out.println("Vid row = " + player.getRow());
            System.out.println("Vid column = " + player.getCol());
            System.out.println();

            //a conditional if the new location is a trap cell
            //should proceed to activate the tap
            if (player.getVidMansion().getRoom(player.getRoomNum()).getCell(player.getRow(), player.getCol()).getCellType().equalsIgnoreCase("trap")) {
                System.out.println("You fell into a TRAP!");
                System.out.println("You are in the Linda Pit -- time for a game of rock, paper, scissors to determine your fate");
                RPS.RPS();
                if (RPS.getWin()) {
                    System.out.println("You have escaped... for now...");
                }

                else {
                  /*  player.loseLetter(); //might want to comment this out if just default to losing
                    if(player.getNumLetters() == 0) {
                        System.out.println("UR A LOSER! :P Vid lost all of the clues for the password and cannot escape. RIP! Linda will now continue in her world takeover");
                        System.exit(0);
                    }
                    
                   */
                }
            }

            //a conditional if the new location is a reward cell
            //should give you a real or fake letter in the word and also break the loop
            else if ((player.getVidMansion().getRoom(player.getRoomNum()).getCell(player.getRow(), player.getCol()).getCellType().equalsIgnoreCase("reward")) ){
                giveReward();
                player.getVidMansion().markIsCompleted(player.getRoomNum());
            }

            //loop iterates if it is a blank cell
        }

        printHallway();

        //FOR TESTING
//        while (true){
//            makeMove();
//            System.out.println(player.getVidMansion().getRoom(player.getRoomNum()).blankRoom());
//        }
    }


    //gives the player either a reward or a "pranked" notification
    public void giveReward(){
        if (player.getVidMansion().getRoom(player.getRoomNum()).getCell(player.getRow(), player.getCol()).checkTrick()){
            System.out.println("pranked");
        }
        else{
            System.out.println("You have gained a letter! Letter: " + player.getVidMansion().getRoom(player.getRoomNum()).getCell(player.getRow(), player.getCol()).getCellClue());
            player.addLetter("!"); //this will be letter called from cell with word array implementation later
        }
    }

    //prints the room numbers, and replaces any completed rooms' numbers with a "*"
    //called from chooseRoom()
    public void printHallway(){
        for (int i=0; i<manshin.getHallway().size(); i++){
            System.out.print(manshin.getHallway().get(i)+" ");
        }
    }


    //after hallway is printed, asks player to enter a valid room
    //choice is validated, room is printed. Player has option to continue
    //after they have memorized traps, then it will print a lot of empty lines
    //and print empty room with Vid in it, they are ready to move
    //is called from and returns to playing()
    public void chooseRoom(){
        printHallway();
        System.out.println();
        System.out.println("What room would you like to enter?");
        System.out.print("> ");
        String roomChoice = s.nextLine();
        
        while (!manshin.validateRC(roomChoice)){
            System.out.println("Invalid input, please try again.");
            printHallway();
            System.out.println();
            System.out.println("What room would you like to enter?");
            System.out.print("> ");
            roomChoice = s.nextLine();
        }
        
        System.out.println("Entered Room #" + roomChoice);
        player.setRoomNum(Integer.parseInt(roomChoice));
        Room currentRoom = manshin.getRoom(Integer.parseInt(roomChoice));
        currentRoom.getCell(6, 3).setVid(true);
        //set vid coordinates to the same location for the start
        player.setRow(6);
        player.setCol(3);


        System.out.println(currentRoom);
        System.out.println("Are you ready to continue?");
        System.out.print("> ");
        String contChoice = s.nextLine();


            while (!(contChoice.equalsIgnoreCase("yes")||contChoice.equalsIgnoreCase("Y"))) {

                if (!(contChoice.equalsIgnoreCase("no")||contChoice.equalsIgnoreCase("N"))) {
                    System.out.println("Invalid input, please try again.");
                    System.out.println(currentRoom);
                    System.out.println("Are you ready to continue?");
                    System.out.print("> ");
                    contChoice = s.nextLine();
                }

                while (contChoice.equalsIgnoreCase("no")||contChoice.equalsIgnoreCase("N")){
                    System.out.println("Okay, it will print again");
                    System.out.println(currentRoom);
                    System.out.println("Are you ready to continue?");
                    System.out.print("> ");
                    contChoice = s.nextLine();
                }
            }

            //hide all traps
            currentRoom.hideTraps();
            //if yes, they are ready, it will print inputted number of blank lines
            linePrint(1); //will be 1000 after testing
            System.out.println(currentRoom.blankRoom());
    }

    //prints x number of lines
    //called from chooseRoom()
    private void linePrint(int x){
        for (int i=0; i<x; i++){
            System.out.println();
        }
    }


    //called in a while loop from playing()
    public void makeMove() {
        System.out.println("Which direction would you like to move? (use WASD)");
        System.out.print("> ");
        String moveChoice = s.nextLine();
        //NEED TO VALIDATE



        //close bigger while

        //calls move from Vid
        boolean moveSuccess = player.move(moveChoice);
        while(!moveSuccess) {
            System.out.println("Invalid input, please try again.");
            System.out.print("> ");
            moveChoice = s.nextLine();
            moveSuccess = player.move(moveChoice);
        } //close while

    } //close method makeMove

    public void loser(){
        System.out.println("You lose!!");
    }
}



//old direction validation, just in case

//while (!(moveChoice.equalsIgnoreCase("w") || moveChoice.equalsIgnoreCase("up") || moveChoice.equalsIgnoreCase("north") ||
//        moveChoice.equalsIgnoreCase("a") || moveChoice.equalsIgnoreCase("left") || moveChoice.equalsIgnoreCase("west") ||
//        moveChoice.equalsIgnoreCase("s") || moveChoice.equalsIgnoreCase("down") || moveChoice.equalsIgnoreCase("south") ||
//        moveChoice.equalsIgnoreCase("d") || moveChoice.equalsIgnoreCase("right") || moveChoice.equalsIgnoreCase("east"))){
//
//        }