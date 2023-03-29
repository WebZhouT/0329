package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads gamelist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public GameList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGameList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses gamelist from JSON object and returns it
    private GameList parseGameList(JSONObject jsonObject) {
        GameList gl = new GameList();
        addGames(gl, jsonObject);
        return gl;
    }

    // MODIFIES: gl
    // EFFECTS: parses thingies from JSON object and adds them to gamelist
    private void addGames(GameList gl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("games");
        for (Object json : jsonArray) {
            JSONObject nextGame = (JSONObject) json;
            addThingy(gl, nextGame);
        }
    }

    // MODIFIES: gl
    // EFFECTS: parses thingy from JSON object and adds it to gamelist
    private void addThingy(GameList gl, JSONObject jsonObject) {
        String map = jsonObject.getString("map");
        int place = jsonObject.getInt("place");
        String myLegend = jsonObject.getString("myLegend");
        String weapon1 = jsonObject.getString("weapon1");
        String weapon2 = jsonObject.getString("weapon2");
        int shieldLevel = jsonObject.getInt("shieldLevel");
        int myDamage = jsonObject.getInt("myDamage");
        String squadLegend1 = jsonObject.getString("squadLegend1");
        String squadLegend2 = jsonObject.getString("squadLegend2");
        int myKill = jsonObject.getInt("myKill");
        int squadKill = jsonObject.getInt("squadKill");
        int squadDamage = jsonObject.getInt("squadDamage");
        int numberOfDeath = jsonObject.getInt("numberOfDeath");
        Game game = new Game(map, place, myLegend, weapon1, weapon2, shieldLevel, myDamage,
                squadLegend1, squadLegend2, myKill, squadKill, squadDamage, numberOfDeath);
        gl.addGame(game);
    }
}
