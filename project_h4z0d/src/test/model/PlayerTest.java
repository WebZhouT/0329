package model;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player testPlayer1;
    private Player testPlayer2;
    private Player testPlayer3;
    private Player testPlayer4;

    @BeforeEach
    void runBefore() {
        testPlayer1 = new Player(null, 0, 0);
        testPlayer2 = new Player("StrangeAccount", 0, 1000);
        testPlayer3 = new Player("Newb", 299, 0);
        testPlayer4 = new Player("Proman", 5023, 23049);
    }

    @Test
    void testConstructor() {
    assertEquals(5023, testPlayer4.getTotalDeath());
    assertTrue(testPlayer1.getPlayerId() > 0);
    assertEquals(23049, testPlayer4.getTotalKillMade());
    assertEquals(0, testPlayer1.getKd());
    assertEquals(0, testPlayer2.getKd());
    assertEquals(0,testPlayer3.getKd());
    }

    @Test
    void testViewPlayer() {
        assertEquals("Id: 2, Name: StrangeAccount, Total Death: 0, Total Kill: 1000, Kd: 0.0",
                testPlayer2.viewPlayer());
    }

    @Test
    void testUpdatePlayerFirstSquadEliminated() {
        Game game1 = new Game("World's Edge", 20, "Octane", "Flatline", null,
                1, 0, "Ash", "Horizon", 0, 0,
                0, 1);
        testPlayer1.updatePlayer(game1);
        assertEquals(0, testPlayer1.getTotalKillMade());
        assertEquals(1, testPlayer1.getTotalDeath());
        assertEquals(0, testPlayer1.getKd());
    }

    @Test
    void testUpdatePlayerNormalGame(){
        Game game2 = new Game("Broken Moon", 9, "Wraith", "R-301","Nemesis",
                3,1233,"Bloodhound","Lifeline",3,5,
                3189,1);
        testPlayer1.updatePlayer(game2);
        assertEquals(3,testPlayer1.getTotalKillMade());
        assertEquals(1,testPlayer1.getTotalDeath());
        assertEquals(3,testPlayer1.getKd());
    }

    @Test
    void testUpdatePlayerChampion(){
        Game game3 = new Game("Storm Point",1,"Valkyrie","Volt","Charge Rifle",
                5,1999,"Bangalore","Loba",3,10,
                4892,0);
        testPlayer1.updatePlayer(game3);
        assertEquals(3,testPlayer1.getTotalKillMade());
        assertEquals(0,testPlayer1.getTotalDeath());
        assertEquals(0,testPlayer1.getKd());
        testPlayer2.updatePlayer(game3);
        assertEquals(0, testPlayer2.getKd());
        testPlayer3.updatePlayer(game3);
        assertEquals(3/300, testPlayer3.getKd());
        testPlayer4.updatePlayer(game3);
        assertEquals(23052/5023, testPlayer4.getKd());
    }
}