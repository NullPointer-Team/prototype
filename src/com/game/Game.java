package com.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Game {
    private String currentRoom;
    private ArrayList<String> inventory;
    private Boolean gameOver = false;
    private Scanner input;

    // MAP
    private HashMap<String,HashMap<String, String>> rooms = new HashMap<>();
    private HashMap<String, String> hall = new HashMap<String, String>();
    private HashMap<String, String> kitchen = new HashMap<String, String>();
    private HashMap<String, String> diningRoom = new HashMap<String, String>();
    private HashMap<String, String> garden = new HashMap<String, String>();

    private void createMap() {
        hall.put("south", "Kitchen");
        hall.put("east", "Dining Room");
        hall.put("item", "key");

        kitchen.put("north", "Hall");
        kitchen.put("east", "Garden");
        kitchen.put("item", "monster");

        diningRoom.put("west", "Hall");
        diningRoom.put("south", "Garden");
        diningRoom.put("item", "potion");

        garden.put("north", "Dining Room");
        garden.put("west", "Kitchen");
        garden.put("item", "sword");

        rooms.put("Kitchen", kitchen);
        rooms.put("Hall", hall);
        rooms.put("Dining Room", diningRoom);
        rooms.put("Garden", garden);
    }


    private void printIntroduction() {
        System.out.println("     RPG GAME     ");
        System.out.println("------------------");
        System.out.println("     COMMANDS     ");
        System.out.println("    GO [direction]");
        System.out.println("    GET [item]    ");
        System.out.println();
    }

    private void showStatus() {
        System.out.println(" -------------------- ");
        System.out.println(" You are in the "+ currentRoom);
        showInventory();
    }

    private void showInventory() {
        if (getInventory().isEmpty()) {
            System.out.println("You have nothing in your inventory");
        } else {
            System.out.println("In your inventory, you have:     ");
            for (String item: inventory) {
                System.out.println("      -" + item);
            }
        }
    }

    public void playGame() {
        printIntroduction();
        createMap();
        currentRoom = "Dining Room";
        inventory = new ArrayList<String>();
        input = new Scanner(System.in);

        while (!gameOver) {
            showStatus();
            String move = "";
            while (move == "") {
                System.out.println("What do you want to do?");
                move = input.nextLine();
            }

            String[] moves = move.toLowerCase().split("\\s+");

            if (moves[0] == "go") {
                if (rooms.get(currentRoom).containsKey(moves[1])) {
                    currentRoom = moves[1];
                } else {
                    System.out.println("You can\'t go that way!");
                }
            }

            if (moves[0] == "get") {
                if (rooms.get(currentRoom).get("item") == moves[1]) {
                    inventory.add(moves[1]);
                    System.out.println(moves[1] + " acquired!!");
                } else {
                    System.out.println("That item is not available in this room!");
                }
            }

            if (rooms.get(currentRoom).containsKey("monster")) {
                if (inventory.contains("sword")) {
                    System.out.println("You won!! Good job!!");
                } else {
                    System.out.println("You lost!! You are dead. You are not alive");
                }
                gameOver = true;
            }
        }
    }

    public String getCurrentRoom(){
        return currentRoom;
    }

    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<String> inventory) {
        this.inventory = inventory;
    }
}
