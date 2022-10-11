import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class JUnitTests {

    // Can probably only call 1 time here because it's a Singleton (instead of calling inside each test)
    BoardRenderer renderer = new BoardRenderer();

    //
    // Room Tests
    //
    @Test
    void createRoom() {
        int randomY = (int)(Math.random() * 4) + 1;
        int randomX = (int)(Math.random() * 3);
        int randomZ = (int)(Math.random() * 3);
        Room testRoom = new Room(randomY, randomX, randomZ, randomY + "-" + randomX + "-" + randomZ, renderer);

        assertEquals(randomY + "-" + randomX + "-" + randomZ, testRoom.id);
    }

    @Test
    void verifyFacility() {
        String allRoomIDs[] = {
                "0-1-1",
                "1-0-0", "1-0-1", "1-0-2",
                "1-1-0",  "1-1-1", "1-1-2",
                "1-2-0",  "1-2-1", "1-2-2",
                "2-0-0", "2-0-1", "2-0-2",
                "2-1-0",  "2-1-1", "2-1-2",
                "2-2-0",  "2-2-1", "2-2-2",
                "3-0-0", "3-0-1", "3-0-2",
                "3-1-0",  "3-1-1", "3-1-2",
                "3-2-0",  "3-2-1", "3-2-2",
                "4-0-0", "4-0-1", "4-0-2",
                "4-1-0",  "4-1-1", "4-1-2",
                "4-2-0",  "4-2-1", "4-2-2"
        };
        List<String> createdRooms = new ArrayList<>();
        for (Room room: renderer.rooms) {
            createdRooms.add(room.id);
        }

        assertArrayEquals(allRoomIDs, createdRooms.toArray());
    }

    //
    // Treasure Tests
    //
    @Test
    void verifyLoot() {
        int swordsFound = 0;
        int gemsFound = 0;
        int armorFound = 0;
        int portalsFound = 0;
        int trapsFound = 0;
        int potionsFound = 0;
        for (Room room: renderer.rooms) {
            for (Treasure treasure : room.treasures) {
                if (treasure.getClass().getName().equals("Sword")) {
                    assertNotEquals(0, room.y);
                    swordsFound++;
                } else if (treasure.getClass().getName().equals("Gem")) {
                    assertNotEquals(0, room.y);
                    gemsFound++;
                } else if (treasure.getClass().getName().equals("Armor")) {
                    assertNotEquals(0, room.y);
                    armorFound++;
                } else if (treasure.getClass().getName().equals("Portal")) {
                    assertNotEquals(0, room.y);
                    portalsFound++;
                } else if (treasure.getClass().getName().equals("Trap")) {
                    assertNotEquals(0, room.y);
                    trapsFound++;
                } else if (treasure.getClass().getName().equals("Potion")) {
                    assertNotEquals(0, room.y);
                    potionsFound++;
                }
            }
        }
        // assert that 4 of each treasure class were placed
        assertEquals(4, swordsFound);
        assertEquals(4, gemsFound);
        assertEquals(4, armorFound);
        assertEquals(4, portalsFound);
        assertEquals(4, trapsFound);
        assertEquals(4, potionsFound);

    }

    //
    // Adventurer Tests
    //
    @Test
    void spawnBrawler() {
        Adventurer brawler = new Brawler("B", new ExpertStrategy(), new CarelessStrategy(), renderer.getRoomByID("0-1-1"));
        // Check class, type, strategies, and spawn room
        assertEquals("Brawler", brawler.getClass().getName());
        assertEquals("B", brawler.type);
        assertEquals("Expert", brawler.combatStrategy.type);
        assertEquals("Careless", brawler.searchStrategy.type);
        assertEquals("0-1-1", brawler.currentRoom.id);
    }

    @Test
    void spawnRunner() {
        Adventurer runner = new Runner("R", new UntrainedStrategy(), new QuickStrategy(), renderer.getRoomByID("0-1-1"));
        // Check class, type, strategies, and spawn room
        assertEquals("Runner", runner.getClass().getName());
        assertEquals("R", runner.type);
        assertEquals("Untrained", runner.combatStrategy.type);
        assertEquals("Quick", runner.searchStrategy.type);
        assertEquals("0-1-1", runner.currentRoom.id);
    }

    @Test
    void spawnSneaker() {
        Adventurer sneaker = new Sneaker("S", new StealthStrategy(), new QuickStrategy(), renderer.getRoomByID("0-1-1"));
        // Check class, type, strategies, and spawn room
        assertEquals("Sneaker", sneaker.getClass().getName());
        assertEquals("S", sneaker.type);
        assertEquals("Stealth", sneaker.combatStrategy.type);
        assertEquals("Quick", sneaker.searchStrategy.type);
        assertEquals("0-1-1", sneaker.currentRoom.id);
    }

    @Test
    void spawnThief() {
        Adventurer thief = new Thief("T", new TrainedStrategy(), new CarefulStrategy(), renderer.getRoomByID("0-1-1"));
        // Check class, type, strategies, and spawn room
        assertEquals("Thief", thief.getClass().getName());
        assertEquals("T", thief.type);
        assertEquals("Trained", thief.combatStrategy.type);
        assertEquals("Careful", thief.searchStrategy.type);
        assertEquals("0-1-1", thief.currentRoom.id);
    }


    //
    // Creature Tests
    //
    @Test
    void checkForAllCreatures() {
        int blinkersFound = 0;
        int orbitersFound = 0;
        int seekersFound = 0;
        for (Creature creature: renderer.creatures) {
            if (creature.getClass().getName().equals("Blinker")) {
                blinkersFound++;
            } else if (creature.getClass().getName().equals("Orbiter")) {
                orbitersFound++;
            } else if (creature.getClass().getName().equals("Seeker")) {
                seekersFound++;
            }
        }
        // assert that 4 of each class have spawned
        assertEquals(4, blinkersFound);
        assertEquals(4, orbitersFound);
        assertEquals(4, seekersFound);
    }

    @Test
    void spawnOrbiter() {
        Creature orbiter = new Orbiter("O", renderer.newOrbiterRoom());
        // Check type and spawn room
        assertEquals("O", orbiter.type);
        assertNotEquals("1-1", orbiter.currentRoom.x + "-" + orbiter.currentRoom.z);
    }

    @Test
    void spawnSeeker() {
        Creature seeker = new Seeker("S", renderer.newSeekerRoom());
        // Check type and spawn room
        assertEquals("S", seeker.type);
        assertNotEquals("0-1-1", seeker.currentRoom.id);
    }

    @Test
    void spawnBlinker() {
        Creature blinker = new Blinker("B", renderer.newBlinkerRoom());
        // Check type and spawn room
        assertEquals("B", blinker.type);
        assertEquals(4, blinker.currentRoom.y);
    }


}
