package ui;

import model.Game;
import model.GameList;
import model.Player;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class ApexBot {
    private Player user;
    private Game gameRecord;
    private GameList gameList;
    private Scanner input;
    private Scanner input1;
    private Scanner input2;
    private Scanner input3;
    private Scanner input4;
    private Scanner input5;
    private Scanner input6;
    private Scanner input7;
    private Scanner input8;
    private Scanner input9;
    private Scanner input10;
    private Scanner input11;
    private Scanner input12;
    private Scanner input13;
    private Scanner input14;
    private Scanner input15;
    private Scanner input16;
    private Scanner input17;
    private Scanner input18;
    private Scanner input19;
    private Scanner input20;
    private Scanner input21;
    private Scanner input22;
    private static final String JSON_STORE = "./data/gamelist.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;



    // EFFECTS: runs the ApexBot application
    public ApexBot() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApexBot();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApexBot() {
        boolean keepGoing = true;
        String command = null;
        //initializePlayer();
        input = new Scanner(System.in);
        userSelection();
        initializeGameList();
        while (keepGoing) {
            displayMenu();
            command = input.next();
//            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!");
    }


    // MODIFIES: this
    // EFFECTS: initialize a list to store game record
    private void initializeGameList() {
        gameList = new GameList();
    }

    // MODIFIES: this
    // EFFECTS: initialize the user profile with three user inputs
    private void userSelection() {
        input1 = new Scanner(System.in);
        input2 = new Scanner(System.in);
        input3 = new Scanner(System.in);
        displayLoginMenu();
        String userName = input1.next();
        displayLoginMenuTotalDeath();
        int totalDeathAmount = input2.nextInt();

        if (totalDeathAmount >= 0) {
            displayLoginMenuTotalKill();
        } else {
            System.out.println("Cannot have a negative total death :(");
        }

        int totalKillAmount = input3.nextInt();
        if (totalKillAmount >= 0) {
            user = new Player(userName, totalDeathAmount, totalKillAmount);
        } else {
            System.out.println("Cannot have a negative total kill :(");
        }
    }


    // EFFECT: print out the sentence firstly when user log in and ask for name input
    private void displayLoginMenu() {
        System.out.println("\nHello!Your user name please:)");
    }

    // EFFECT: print out the sentence when user finishes input name and ask for total number of deaths
    private void displayLoginMenuTotalDeath() {
        System.out.println("Your total number of deaths in Apex Legends?");
    }

    // EFFECT: print out the sentence when user finished input number of death and ask for total number of kills
    private void displayLoginMenuTotalKill() {
        System.out.println("Your total number of kills?");
    }

    // EFFECT: display options for user to choose
    private void displayMenu() {
        System.out.println("\nWelcome back! What can I help you today?");
        System.out.println("\tv -> View my profile");
        System.out.println("\tn -> Add a new game record");
        System.out.println("\th -> View all my game records");
        System.out.println("\td -> Delete a game record");
        System.out.println("\ti -> Search a game record from id");
        System.out.println("\tl -> Search legend specific game records");
        System.out.println("\tw -> Search weapon specific game records");
        System.out.println("\tm -> Search map specific game records");
        System.out.println("\tp -> Search ideal top game records");
        System.out.println("\ts -> Save game history to file");
        System.out.println("\ta -> load game history from file");
        System.out.println("\tq -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("v")) {
            doViewProfile();
        } else if (command.equals("n")) {
            doAddGame();
        } else if (command.equals("h")) {
            doViewGame();
        } else if (command.equals("d")) {
            doDeleteGame();
        } else if (command.equals("i")) {
            doIdSearchGame();
        } else if (command.equals("l")) {
            doLegendSearchGame();
        } else if (command.equals("w")) {
            doWeaponSearchGame();
        } else {
            processCommandCont(command);
        }
    }

    private void processCommandCont(String command) {
        if (command.equals("m")) {
            doMapSearchGame();
        } else if (command.equals("p")) {
            doPlaceSearchGame();
        } else if (command.equals("s")) {
            saveGameList();
        } else if (command.equals("a")) {
            loadGameList();
        } else {
            System.out.println("Selection not valid...Please check the option letter again:)");
        }
    }

    // EFFECTS: prints the user profile (name, total number of death, total number of kill, kd)
    private void doViewProfile() {
        System.out.println(user.viewPlayer());
    }

    private String map;
    private int place;
    private String myLegend;
    private String weapon1;
    private String weapon2;

    // MODIFIES: this
    // EFFECTS: create new game record with user inputs
    private void doAddGame() {
        initializeGameInput();
        System.out.println("\nStart to create a new game record.");
        System.out.println("Type the map you played from:World's Edge,Broken Moon,Storm Point,Kings Canyon,Olympus");
        map = input4.next();
        System.out.println("Your placement in the match?");
        place = input5.nextInt();
        System.out.println("The legend you played in the match?");
        myLegend = input6.next();
        System.out.println("The first weapon you used in the match? Type in 'null' if you don't get one.");
        weapon1 = input7.next();
        System.out.println("The second weapon you used in the match? Type in 'null' if you don't get on");
        weapon2 = input8.next();
        doAddGameCont();
    }

    private int shieldLevel;
    private int myDamage;
    private String squadLegend1;
    private String squadLegend2;
    private int myKill;
    private int squadKill;
    private int squadDamage;
    private int numberOfDeath;

    // MODIFIES: this
    // EFFECTS: continue to fill in the data needed to create a game record and put the record into the list
    private void doAddGameCont() {
        System.out.println("Your final shield level?");
        shieldLevel = input9.nextInt();
        System.out.println("Your damage?");
        myDamage = input10.nextInt();
        System.out.println("The legend your first squad mate played?");
        squadLegend1 = input11.next();
        System.out.println("The legend your second squad mate played?");
        squadLegend2 = input12.next();
        System.out.println("Your number of kill in this game?");
        myKill = input13.nextInt();
        System.out.println("The number of total kill as a whole squad?");
        squadKill = input14.nextInt();
        System.out.println("The total damage dealt as a whole squad?");
        squadDamage = input15.nextInt();
        System.out.println("The number of death you got in this game?");
        numberOfDeath = input16.nextInt();
        gameRecord = new Game(map, place, myLegend, weapon1, weapon2, shieldLevel,
                myDamage, squadLegend1, squadLegend2, myKill, squadKill, squadDamage,
                numberOfDeath);
        gameList.addGame(gameRecord);
        user.updatePlayer(gameRecord);
    }

    // EFFECTS: initialize user inputs for creating new game record
    private void initializeGameInput() {
        input4 = new Scanner(System.in);
        input5 = new Scanner(System.in);
        input6 = new Scanner(System.in);
        input7 = new Scanner(System.in);
        input8 = new Scanner(System.in);
        input9 = new Scanner(System.in);
        input10 = new Scanner(System.in);
        input11 = new Scanner(System.in);
        input12 = new Scanner(System.in);
        input13 = new Scanner(System.in);
        input14 = new Scanner(System.in);
        input15 = new Scanner(System.in);
        input16 = new Scanner(System.in);
    }

    // EFFECTS: print all the game records
    private void doViewGame() {
        System.out.println(gameList.accessAll());
    }

    // MODIFIES: this
    // EFFECTS: delete a game with the id user provides
    private void doDeleteGame() {
        input17 = new Scanner(System.in);
        System.out.println("\nGame Id please");
        int gameId = input17.nextInt();
        if (gameList.deleteGame(gameId)) {
            System.out.println("\tDeleted successfully!");
        } else {
            System.out.println("\tNo record found, please try again :(");
        }
    }

    // EFFECTS: print a game record with the id user provides
    private void doIdSearchGame() {
        input18 = new Scanner(System.in);
        System.out.println("\nGame Id please");
        int gameId = input18.nextInt();
        if (gameList.accessGameRecord(gameId)) {
            System.out.println("[Id: " + gameRecord.getGameId()
                    + ", Map: " + gameRecord.getMap()
                    + ", Place: " + gameRecord.getPlace()
                    + ", My Legend: " + gameRecord.getMyLegend()
                    + ", Weapon 1: " + gameRecord.getWeapon1()
                    + ", Weapon 2: " + gameRecord.getWeapon2()
                    + ", Shield Level: " + gameRecord.getShieldLevel()
                    + ", My Damage: " + gameRecord.getMyDamage()
                    + ", Squad Legend 1: " + gameRecord.getSquadLegend1()
                    + ", Squad Legend 2: " + gameRecord.getSquadLegend2()
                    + ", My Kill: " + gameRecord.getMyKill()
                    + ", Squad Kill: " + gameRecord.getSquadKill()
                    + ", Squad Damage: " + gameRecord.getSquadDamage()
                    + ", My Number of Death: " + gameRecord.getNumberOfDeath() + "] ");
        } else {
            System.out.println("\tNo record found, please try again :(");
        }
    }

    // MODIFIES: print all the game records with the legend name user provides
    private void doLegendSearchGame() {
        input19 = new Scanner(System.in);
        System.out.println("\nLegend name please");
        String legendName = input19.next();
        if (!gameList.accessLegendRecord(legendName).isEmpty()) {
            LinkedList<Game> linkedList = gameList.accessLegendRecord(legendName);
            for (Game gameRecord : linkedList) {
                System.out.println("[Id: " + gameRecord.getGameId()
                        + ", Map: " + gameRecord.getMap()
                        + ", Place: " + gameRecord.getPlace()
                        + ", Weapon 1: " + gameRecord.getWeapon1()
                        + ", Weapon 2: " + gameRecord.getWeapon2()
                        + ", Shield Level: " + gameRecord.getShieldLevel()
                        + ", My Damage: " + gameRecord.getMyDamage()
                        + ", Squad Legend 1: " + gameRecord.getSquadLegend1()
                        + ", Squad Legend 2: " + gameRecord.getSquadLegend2()
                        + ", My Kill: " + gameRecord.getMyKill()
                        + ", Squad Kill: " + gameRecord.getSquadKill()
                        + ", Squad Damage: " + gameRecord.getSquadDamage()
                        + ", My Number of Death: " + gameRecord.getNumberOfDeath() + "] ");
            }
        } else {
            System.out.println("\tCannot find any records regarding to the legend:(");
        }
    }

    // EFFECTS: print all the game records with the weapon name user provides
    private void doWeaponSearchGame() {
        input20 = new Scanner(System.in);
        System.out.println("\nWeapon name please");
        String weaponName = input20.next();
        if (!gameList.accessWeaponRecord(weaponName).isEmpty()) {
            LinkedList<Game> linkedList = gameList.accessWeaponRecord(weaponName);
            for (Game gameRecord : linkedList) {
                System.out.println("[Id: " + gameRecord.getGameId() + ", Map: " + gameRecord.getMap()
                        + ", Place: " + gameRecord.getPlace()
                        + ", My Legend: " + gameRecord.getMyLegend()
                        + ", Weapon 1: " + gameRecord.getWeapon1()
                        + ", Weapon 2: " + gameRecord.getWeapon2()
                        + ", Shield Level: " + gameRecord.getShieldLevel()
                        + ", My Damage: " + gameRecord.getMyDamage()
                        + ", Squad Legend 1: " + gameRecord.getSquadLegend1()
                        + ", Squad Legend 2: " + gameRecord.getSquadLegend2()
                        + ", My Kill: " + gameRecord.getMyKill()
                        + ", Squad Kill: " + gameRecord.getSquadKill()
                        + ", Squad Damage: " + gameRecord.getSquadDamage()
                        + ", My Number of Death: " + gameRecord.getNumberOfDeath() + "] ");
            }
        } else {
            System.out.println("\tCannot find any records regarding to the weapon:(");
        }
    }

    // EFFECTS: print all the game records with the map name user provides
    private void doMapSearchGame() {
        input21 = new Scanner(System.in);
        System.out.println("\nMap name please");
        String mapName = input21.next();
        if (!gameList.accessMapRecord(mapName).isEmpty()) {
            LinkedList<Game> linkedList = gameList.accessMapRecord(mapName);
            for (Game gameRecord : linkedList) {
                System.out.println("[Id: " + gameRecord.getGameId()
                        + ", Place: " + gameRecord.getPlace()
                        + ", My Legend: " + gameRecord.getMyLegend()
                        + ", Weapon 1: " + gameRecord.getWeapon1()
                        + ", Weapon 2: " + gameRecord.getWeapon2()
                        + ", Shield Level: " + gameRecord.getShieldLevel()
                        + ", My Damage: " + gameRecord.getMyDamage()
                        + ", Squad Legend 1: " + gameRecord.getSquadLegend1()
                        + ", Squad Legend 2: " + gameRecord.getSquadLegend2()
                        + ", My Kill: " + gameRecord.getMyKill()
                        + ", Squad Kill: " + gameRecord.getSquadKill()
                        + ", Squad Damage: " + gameRecord.getSquadDamage()
                        + ", My Number of Death: " + gameRecord.getNumberOfDeath() + "] ");
            }
        } else {
            System.out.println("\tCannot find any records regarding to the map:(");
        }
    }

    // EFFECTS: print all the game records which have a higher or equal placement with the place user provides
    private void doPlaceSearchGame() {
        input22 = new Scanner(System.in);
        System.out.println("\nYour ideal place in a game?");
        int place = input22.nextInt();
        if (!gameList.accessTopRecord(place).isEmpty()) {
            LinkedList<Game> linkedList = gameList.accessTopRecord(place);
            for (Game gameRecord : linkedList) {
                System.out.println("[Id: " + gameRecord.getGameId() + ", Map: " + gameRecord.getMap()
                        + ", Place: " + gameRecord.getPlace()
                        + ", My Legend: " + gameRecord.getMyLegend()
                        + ", Weapon 1: " + gameRecord.getWeapon1()
                        + ", Weapon 2: " + gameRecord.getWeapon2()
                        + ", Shield Level: " + gameRecord.getShieldLevel()
                        + ", My Damage: " + gameRecord.getMyDamage()
                        + ", Squad Legend 1: " + gameRecord.getSquadLegend1()
                        + ", Squad Legend 2: " + gameRecord.getSquadLegend2()
                        + ", My Kill: " + gameRecord.getMyKill()
                        + ", Squad Kill: " + gameRecord.getSquadKill()
                        + ", Squad Damage: " + gameRecord.getSquadDamage()
                        + ", My Number of Death: " + gameRecord.getNumberOfDeath() + "] ");
            }
        } else {
            System.out.println("\tCannot find any records regarding to the place:(");
        }
    }

    private void saveGameList() {
        try {
            jsonWriter.open();
            jsonWriter.write(gameList);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadGameList() {
        try {
            gameList = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}



