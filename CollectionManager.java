package songlibrary;

import java.util.Scanner;

/**
 * This class defines the Album Collection Manager abstract data type which can manage an album collection.
 * @author Aatif Sayed, Pranav Tailor
 */
public class CollectionManager {
    
    Scanner userInput;  // will be used to scan, store, and refer to user input
    Collection collection;
    
    /**
     * Default constructor to instantiate a collection manager which accepts user input and instantiates a Collection object.
     */
    public CollectionManager() {
        userInput = new Scanner(System.in);
        collection = new Collection();
    }
    
    /**
     * This method runs the collection manager which continually takes user commands until the command "Q" is received.
     * Takes a line of user input and creates an array of Strings containing a command and data by parsing comma-delimited lines.
     */
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
    
    /**
     * Private helper method to check if user command is valid, execute command if possible, and print to the console.
     * If command is invalid, print a message letting the user know.
     * @param commandTokens an array of Strings containing a command and relevant data found by parsing comma-delimited lines.
     */
    private void executeCommand(String[] commandTokens) {
        switch (commandTokens[0]) {
            case "A" : executeCommandA(commandTokens);
                break;
            case "D" : executeCommandD(commandTokens);
                break;
            case "L" : executeCommandL(commandTokens);
                break;
            case "R" : executeCommandR(commandTokens);
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
    
    /**
     * Private helper method to execute and print the results of the 'A' or add command.
     * @param commandTokens an array of Strings containing a command and relevant data found by parsing comma-delimited lines.
     */
    private void executeCommandA(String[] commandTokens) {
        Album newAlbum = new Album(commandTokens[1], commandTokens[2], 
                                    Genre.stringToGenre(commandTokens[3]), new Date(commandTokens[4]));
        if (newAlbum.getReleaseDate().isValid()) {
            if (collection.add(newAlbum))
                System.out.println(newAlbum.toString() + " >> added.");
            else
                System.out.println(newAlbum.toString() + " >> is already in the collection.");
        }
        else
            System.out.println("Invalid date!");
    }
    
    /**
     * Private helper method to execute and print the results of the 'D' or remove command.
     * @param commandTokens an array of Strings containing a command and relevant data found by parsing comma-delimited lines.
     */
    private void executeCommandD(String[] commandTokens) {
        Album newAlbum = new Album(commandTokens[1], commandTokens[2]);
        if (collection.remove(newAlbum))
            System.out.println(newAlbum.getTitle() + "::" + newAlbum.getArtist() + " >> deleted.");
        else
            System.out.println(newAlbum.getTitle() + "::" + newAlbum.getArtist() + " >> is not in the collection.");
    }
    
    /**
     * Private helper method to execute and print the results of the 'L' or lend command.
     * @param commandTokens an array of Strings containing a command and relevant data found by parsing comma-delimited lines.
     */
    private void executeCommandL(String[] commandTokens) {
        Album newAlbum = new Album(commandTokens[1], commandTokens[2]);
        if (collection.lendingOut(newAlbum))
            System.out.println(newAlbum.getTitle() + "::" + newAlbum.getArtist() + " >> lending out and set to not available.");
        else
            System.out.println(newAlbum.getTitle() + "::" + newAlbum.getArtist() + " >> is not available.");
    }
    
    /**
     * Private helper method to execute and print the results of the 'R' or return command.
     * @param commandTokens an array of Strings containing a command and relevant data found by parsing comma-delimited lines.
     */
    private void executeCommandR(String[] commandTokens) {
        Album newAlbum = new Album(commandTokens[1], commandTokens[2]);
        if (collection.returnAlbum(newAlbum))
            System.out.println(newAlbum.getTitle() + "::" + newAlbum.getArtist() + " >> returning and set to available.");
        else
            System.out.println(newAlbum.getTitle() + "::" + newAlbum.getArtist() + " >> return cannot be completed.");
    }
}