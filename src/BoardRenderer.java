import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class BoardRenderer {

    Boolean gameOver = false;
    Integer turnsTaken = 0;
    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<Adventurer> adventurers = new ArrayList<>();
    ArrayList<Creature> creatures = new ArrayList<>();
    Integer deadAdventurers;
    Integer deadCreatures;
    String endMessage;

    public BoardRenderer() { // initialize game state -- ABSTRACTION used to hide the bulk of game initialization within a few short functions
        createRooms();
        findAdjacentRooms();
        spawnAdventurers();
        spawnCreatures();
    }

    public void takeTurn() {

        turnsTaken++;

        // reset counters
        deadAdventurers = 0;
        deadCreatures = 0;

        // let all the adventurers take their turns
        for (Adventurer adventurer: adventurers) {
            if(adventurer.alive) {
                adventurer.takeTurn();
                gameOver = false;
            } else {
                deadAdventurers++;
            }
        }

        // let all the creatures take their turns
        for (Creature creature: creatures) {
            if(creature.alive) {
                creature.takeTurn();
                gameOver = false;
            } else {
                deadCreatures++;
            }
        }

        // DEBUG
        // System.out.println("deadAdventurers: " + deadAdventurers);
        // System.out.println("deadCreatures: " + deadCreatures);

        // check for "game over" game states
        if (deadAdventurers == 4) {
            endMessage = "All adventurers have been eliminated.";
            gameOver = true;
            return;
        } else if (deadCreatures == 12) {
            endMessage = "All creatures have been eliminated.";
            gameOver = true;
            return;
        } else {
            // count up all the adventurers treasure
            int totalTreasure = 0;
            for (Adventurer adventurer: adventurers) {
                totalTreasure += adventurer.treasuresFound;
                if(totalTreasure >= 50)  {
                    gameOver = true;
                    endMessage = "All treasure found.";
                    return;
                }
            }
        }

        System.out.println("--------------------------------------------");
    }

    public void displayGameState() {
        // print turn #
        System.out.println("RotLA Turn " + turnsTaken + ":");

        // render board
        for(Room room: rooms) {

            StringBuilder adPrint = new StringBuilder();
            for(Adventurer adventurer: room.adventurers) {
                if(adventurer.alive) {
                    adPrint.append(adventurer.type);
                }
            }
            if(adPrint.length() == 0) { adPrint = new StringBuilder("-"); }

            StringBuilder crPrint = new StringBuilder();
            for(Creature creature: room.creatures) {
                if(creature.alive) {
                    crPrint.append(creature.type);
                }
            }
            if(crPrint.length() == 0) { crPrint = new StringBuilder("-"); }

            System.out.print(room.id + ": " + adPrint +" : "+ crPrint +"\t");
            if(room.id.charAt(0) == '0' || room.id.charAt(4) == '2'){
                System.out.println("");
            }

        }

        // list adventurers stats
        for(Adventurer adventurer: adventurers) {
            System.out.println(adventurer.type + " - " + adventurer.treasuresFound + " Treasures(s) - " + adventurer.damage + " Damage");
        }
        // list creatures stats
        //countCreatures();
        for(Creature creature: creatures) {
            //System.out.println(creature.type + " - " + creature.treasuresFound + " Treasures(s) - " + creature.damage + " Damage");
        }

    }

    public Room getRoomByID(String ID) {
        Room foundRoom = rooms.get(0);
        for (Room room: rooms) {
            char y1 = ID.charAt(0);
            char y2 = (String.valueOf(room.y)).charAt(0);
            char x1 = ID.charAt(2);
            char x2 = (String.valueOf(room.x)).charAt(0);
            char z1 = ID.charAt(4);
            char z2 = (String.valueOf(room.z)).charAt(0);
            if(y1 == y2 && x1 == x2 && z1 == z2) {
                foundRoom = room;
            }
        }
        return foundRoom;
    }
    private void createRooms() {
        // ground level entry room
        rooms.add(new Room(0, 1, 1, "0-1-1", this));
        // 9 rooms per floor
        for(int y = 1; y <= 4; y++) {
            for(int x = 0; x <= 2; x++) {
                for(int z = 0; z <= 2; z++) {
                    rooms.add(new Room(y, x, z, y + "-" + x + "-" + z, this));
                }
            }
        }
    }

    private void findAdjacentRooms() {
        for (Room room: rooms) {
            room.findAdjacentRooms();
        }
    }
    private void spawnAdventurers() {
        Room groundLevelRoom = getRoomByID("0-1-1");
        // Brawler
        Adventurer brawler = new Brawler();
        brawler.currentRoom = groundLevelRoom;
        brawler.type = "B";
        adventurers.add(brawler);
        // Sneaker
        Adventurer sneaker = new Sneaker();
        sneaker.currentRoom = groundLevelRoom;
        sneaker.type = "S";
        adventurers.add(sneaker);
        // Runner
        Adventurer runner = new Runner();
        runner.currentRoom = groundLevelRoom;
        runner.type = "R";
        adventurers.add(runner);
        // Thief
        Adventurer thief = new Thief();
        thief.currentRoom = groundLevelRoom;
        thief.type = "T";
        adventurers.add(thief);
    }

    private void spawnCreatures() {
        // 4 of each type
        for (int i=0; i<4; i++) {
            spawnOrbiter();
            spawnSeeker();
            spawnBlinker();
        }
    }

    private void spawnOrbiter() {
        // Orbiter - starts in a non-center room and orbits (moves circularly clockwise or counter-clockwise) around outer rooms, doesn't move if in room with adventurer
        // each instance of Orbiter has its own IDENTITY even though it has identical attributes and methods
        Creature tempOrbiter = new Orbiter();
        int y = (int)(Math.random() * 4) + 1;
        int x = (int)(Math.random() * 3);
        int z;
        if (x == 1) {
            z = 2 * (int)(Math.random() * 2);
        } else {
            z = (int)(Math.random() * 3);
        }
        String roomID = y + "-" + x + "-" + z;
        tempOrbiter.currentRoom = getRoomByID(roomID);
        tempOrbiter.type = "O";
        creatures.add(tempOrbiter);
    }

    private void spawnSeeker() {
        // Seeker - starts in any random room on the 4 levels, move to join any *adjacent adventurer on their floor. Will not move if no adjacent adventurer or adventurer is already in their room
        // each instance of Seeker has its own IDENTITY even though it has identical attributes and methods
        Creature tempSeeker = new Seeker();
        int y = (int)(Math.random() * 4) + 1;
        int x = (int)(Math.random() * 3);
        int z = (int)(Math.random() * 3);
        String roomID = y + "-" + x + "-" + z;
        tempSeeker.currentRoom = getRoomByID(roomID);
        tempSeeker.type = "S";
        creatures.add(tempSeeker);
    }
    private void spawnBlinker() {
        // Blinker - always start on level 4 in random room, move randomly to any room in all 4 levels each turn, will not move if in room with adventurer
        // each instance of Blinker has its own IDENTITY even though it has identical attributes and methods
        Creature tempBlinker = new Blinker();
        int y = 4;
        int x = (int)(Math.random() * 3);
        int z = (int)(Math.random() * 3);
        String roomID = y + "-" + x + "-" + z;
        tempBlinker.currentRoom = getRoomByID(roomID);
        tempBlinker.type = "B";
        creatures.add(tempBlinker);
    }

}
