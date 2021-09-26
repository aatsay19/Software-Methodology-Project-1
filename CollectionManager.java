package songlibrary;

import java.util.Scanner;

public class CollectionManager {
    
    Scanner userInput;
    Collection collection;
    
    public CollectionManager() {
        userInput = new Scanner(System.in);
        collection = new Collection();
    }
    
    public void run() {
        System.out.println("Collection Manager starts running.");
        while (userInput.hasNext()) {
            String[] commandTokens = userInput.nextLine().split(",");
            executeCommand(commandTokens);
            if (commandTokens[0].equals("Q")) {
                userInput.close();
                break;
            }
        }
        System.out.println("Collection Manager terminated.");
    }
    
    // Executes command if valid, otherwise prints "Invalid command!"
    private void executeCommand(String[] commandTokens) {
        //Album newAlbum = new Album(commandTokens[1], commandTokens[2], Genre.stringToGenre(commandTokens[3]), new Date(commandTokens[4]));
        switch (commandTokens[0]) {
            case "A" : 
                Album newAlbum = new Album(commandTokens[1], commandTokens[2], Genre.stringToGenre(commandTokens[3]), new Date(commandTokens[4]));
                if (newAlbum.getReleaseDate().isValid()) {
                    if (collection.add(newAlbum)) {
                        System.out.println(newAlbum.toString() + " >> added.");
                    }
                    else {
                        System.out.println(newAlbum.toString() + " >> is already in the collection.");
                    }
                }
                else {
                    System.out.println("Invalid date!");
                }
                break;
            case "D" :
                Album newAlbum2 = new Album(commandTokens[1], commandTokens[2]);
                if (collection.remove(newAlbum2)) {
                    System.out.println(newAlbum2.getTitle() + "::" + newAlbum2.getArtist() + " >> deleted.");
                }
                else {
                    System.out.println(newAlbum2.getTitle() + "::" + newAlbum2.getArtist() + " >> is not in the collection.");
                }
                break;
            case "L" :
                Album newAlbum3 = new Album(commandTokens[1], commandTokens[2]);
                if (collection.lendingOut(newAlbum3)) {
                    System.out.println(newAlbum3.getTitle() + "::" + newAlbum3.getArtist() + " >> lending out and set to not available.");
                }
                else {
                    System.out.println(newAlbum3.getTitle() + "::" + newAlbum3.getArtist() + " >> is not available.");
                }
                break;
            case "R" :
                Album newAlbum4 = new Album(commandTokens[1], commandTokens[2]);
                if (collection.returnAlbum(newAlbum4)) {
                    System.out.println(newAlbum4.getTitle() + "::" + newAlbum4.getArtist() + " >> returning and set to available.");
                }
                else {
                    System.out.println(newAlbum4.getTitle() + "::" + newAlbum4.getArtist() + " >> return cannot be completed.");
                }
                break;
            case "P" : collection.print();
                break;
            case "PD" : collection.printByReleaseDate();
                break;
            case "PG" : collection.printByGenre();
                break;
            case "Q" : 
                break; 
            default : System.out.println("Invalid command!");
        }
    }
    
}