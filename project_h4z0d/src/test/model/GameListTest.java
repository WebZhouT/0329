package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class GameListTest {
    private GameList gameList;
    private GameList gameList1;
    private GameList gameListEmpty;
    private Game game1;
    private Game game2;
    private Game game3;

    @BeforeEach
    void runBefore() {
        gameList = new GameList();
        gameList1 = new GameList();
        gameListEmpty = new GameList();
        game1 = new Game ("World's Edge", 20, "Octane", "Flatline", null,
                1, 0, "Ash", "Horizon", 0, 0,
                0, 1,1);
        game2 = new Game("Broken Moon", 9, "Wraith", "R-301","Nemesis",
                3,1233,"Bloodhound","Lifeline",3,5,
                3189,1,2);
        game3 = new Game("Broken Moon", 3, "Wraith", "R-301","Flatline",
                3,1233,"Bloodhound","Lifeline",3,5,
                3189,1,2);
        gameList.addGame(game1);
        gameList.addGame(game2);
        gameList1.addGame(game1);
        gameList1.addGame(game2);
        gameList1.addGame(game3);
    }

    @Test
    void testConstructor() {
        assertTrue(gameListEmpty.isEmpty());
        assertFalse(gameList.isEmpty());
        assertEquals(2, gameList.length());
        assertEquals(0, gameListEmpty.length());
    }

    @Test
    void testDeleteGameSuccess() {
        gameList.deleteGame(1);
        assertEquals(1, gameList.length());
    }

    @Test
    void testDeleteGameFail(){
        gameList.deleteGame(89);
        assertEquals(2, gameList.length());
    }

    @Test
    void testDeleteAllGame(){
        gameList.deleteGame(1);
        gameList.deleteGame(2);
        assertTrue(gameList.isEmpty());
        assertEquals(0, gameList.length());
    }

    @Test
    void testAccessGameRecordSuccess() {
        assertTrue(gameList.accessGameRecord(1));
        assertTrue(gameList.accessGameRecord(2));
    }

    @Test
    void testAccessGameRecordFail() {
        assertFalse(gameList.accessGameRecord(0));
        assertFalse(gameList.accessGameRecord(888));
    }

    @Test
    void testAccessLegendRecordSuccess() {
        LinkedList<Game> legendAcc = gameList.accessLegendRecord("Octane");
        assertEquals(1, legendAcc.size());
    }

    @Test
    void testAccessLegendRecordFail() {
        LinkedList<Game> legendAcc = gameList.accessLegendRecord("Mirage");
        assertTrue(legendAcc.size() == 0);
    }

    @Test
    void testAccessWeaponRecord() {
        LinkedList<Game> weaponAcc = gameList.accessWeaponRecord("R-301");
        assertEquals(1, weaponAcc.size());
    }

    @Test
    void testAccessWeaponMultipleRecords() {
        LinkedList<Game> weaponAcc = gameList1.accessWeaponRecord("R-301");
        assertEquals(2, weaponAcc.size());
    }

    @Test
    void testAccessMapRecord() {
        LinkedList<Game> mapAcc = gameList1.accessMapRecord("Broken Moon");
        assertEquals(2, mapAcc.size());
    }

    @Test
    void testAccessTopRecord() {
        LinkedList<Game> placeAcc = gameList1.accessTopRecord(8);
        assertEquals(1, placeAcc.size());
    }

    @Test
    void testAccessAll() {
        assertEquals("[[Id: 1, Map: World's Edge, Place: 20, My Legend: Octane, Weapon 1: Flatline, "
                + "Weapon 2: null, Shield Level: 1, My Damage: 0, Squad Legend 1: Ash, Squad Legend 2: Horizon, "
                + "My Kill: 0, Squad Kill: 0, Squad Damage: 0, My Number of Death: 1], [Id: 2, Map: Broken Moon, "
                + "Place: 9, My Legend: Wraith, Weapon 1: R-301, Weapon 2: Nemesis, Shield Level: 3, My Damage: 1233, "
                + "Squad Legend 1: Bloodhound, Squad Legend 2: Lifeline, My Kill: 3, Squad Kill: 5, "
                + "Squad Damage: 3189, My Number of Death: 1]]", gameList.accessAll());
    }







}
