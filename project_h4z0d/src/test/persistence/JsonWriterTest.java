package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            GameList gl = new GameList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyGameList() {
        try {
            GameList gl = new GameList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyGameList.json");
            writer.open();
            writer.write(gl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyGameList.json");
            gl = reader.read();
            assertEquals(0, gl.length());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralGameList() {
        try {
            GameList gl = new GameList();
            gl.addGame(new Game("World's Edge", 20, "Octane", "Flatline", "R99",
                    1, 0, "Ash", "Horizon", 0, 0,
                    0, 1));
            gl.addGame(new Game("Broken Moon", 9, "Wraith", "R-301","Nemesis",
                    3,1233,"Bloodhound","Lifeline",3,5,
                    3189,1));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralGameList.json");
            writer.open();
            writer.write(gl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralGameList.json");
            gl = reader.read();
            List<Game> games = gl.getGames();
            assertEquals(2, games.size());
            checkGame("World's Edge", 20, "Octane", "Flatline", "R99",
                    1, 0, "Ash", "Horizon", 0, 0,
                    0, 1, games.get(0));
            checkGame("Broken Moon", 9, "Wraith", "R-301","Nemesis",
                    3,1233,"Bloodhound","Lifeline",3,5,
                    3189,1, games.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
