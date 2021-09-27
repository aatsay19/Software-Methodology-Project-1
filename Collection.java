package songlibrary;

/**
 * This class defines the Collection abstract data type; an instance of Collection can hold a list of Album objects. 
 * @author Aatif Sayed, Pranav Tailor
 */
public class Collection {
    
    private Album[] albums;
    private int numAlbums;  // number of albums currently in the collection
    
    /**
     * Default constructor to instantiate an empty collection with a capacity of INITIAL_ARRAY_SIZE (or 8).
     */
    public Collection() {
        albums = new Album[Constants.INITIAL_ARRAY_SIZE];
        numAlbums = 0;  // Collection is initially empty 
    }
    
    /**
     * A private helper method to find an album in the collection.
     * @param album the album that the user wants to search for.
     * @return the index at which the album was found in the collection; if not found, return NOT_FOUND (or -1).
     */
    private int find(Album album) {
        if (numAlbums == 0) {
            return Constants.NOT_FOUND;
        }
        for (int i = 0; i < albums.length; i++) {
            if (albums[i] != null && albums[i].equals(album)) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }
    
    /**
     * A private helper method to automatically increase the capacity of the collection by ARRAY_SIZE_INCREMENT (or 4).
     */
    private void grow() {
        Album[] newAlbumArray = new Album[numAlbums + Constants.ARRAY_SIZE_INCREMENT];
        for (int i = 0; i < numAlbums; i++) {
            newAlbumArray[i] = albums[i];
        }
        albums = newAlbumArray;
    }
    
    /**
     * This method adds an album to the collection.
     * @param album the album to be added to the collection.
     * @return true if the album was added successfully, false otherwise.
     */
    public boolean add(Album album) {
        if (find(album) != Constants.NOT_FOUND) {
            return false;
        }
        if (numAlbums == albums.length) {
           grow(); 
        }
        albums[numAlbums] = album;
        numAlbums++;
        return true;
    }
    
    /**
     * This method removes an album from the collection.
     * @param album the album to be removed from the collection.
     * @return true if the album was removed successfully, false otherwise.
     */
    public boolean remove(Album album) {
        if (numAlbums == 0) {
            return false;
        }
        for (int i = 0; i < albums.length; i++) {
            if (albums[i] != null && albums[i].equals(album)) {
                albums[i] = null;
                for (int j = i + 1; j < albums.length; j++) {
                    albums[j - 1] = albums[j];
                }
                return true;
            }
        }
        return false;
    }
    
    /**
     * This method lends out an album by setting the albums isAvailable property to false.
     * @param album the album to be lent out.
     * @return true if the album was lent successfully, false otherwise.
     */
    public boolean lendingOut(Album album) {
        int index = find(album);
        if (index != Constants.NOT_FOUND) {
            if (albums[index].getAvailability() == false) {
                return false;
            }
            albums[index].setAvailability(false);
            return true;
        }
        return false;
    }
    
    /**
     * This method returns an album by setting the albums isAvailable property to true.
     * @param album the album that is being returned.
     * @return true if the album was returned successfully, false otherwise.
     */
    public boolean returnAlbum(Album album) {
        int index = find(album);
        if (index != Constants.NOT_FOUND) {
            if (albums[index].getAvailability() == true) {
                return false;
            }
            albums[index].setAvailability(true);
            return true;
        }
        return false;
    }
    
    /**
     * Print out the list of albums in the collection.
     * If the collection is empty, print a message letting the user know.
     */
    public void print() {
        if (numAlbums == 0) {
            System.out.println("The collection is empty!");
            return;
        }
        System.out.println("*List of albums in the collection.");
        for (Album album : albums) {
            if (album != null) {
                System.out.println(album.toString());
            }
        }
        System.out.println("*End of list");
    }
    
    /**
     * Print out the list of albums in the collection sorted by release date from oldest to newest.
     * Albums with the same release date may be printed in a different order than expected.
     */
    public void printByReleaseDate() {
        if (numAlbums == 0) {
            System.out.println("The collection is empty!");
            return;
        }
        System.out.println("*Album collected by the release dates.");
        sortByReleaseDate(albums);
        for (Album album : albums) {
            if (album != null) {
                System.out.println(album.toString());
            }
        }
        System.out.println("*End of list");
    }
    
    /**
     * Print out the list of albums in the collection sorted by genre in alphabetical order.
     * Albums with the same genre may be printed in a different order than expected.
     */
    public void printByGenre() {
        if (numAlbums == 0) {
            System.out.println("The collection is empty!");
            return;
        }
        System.out.println("*Album collected by genre.");
        sortByGenre(albums);
        for (Album album : albums) {
            if (album != null) {
                System.out.println(album.toString());
            }
        }
        System.out.println("*End of list");
    }
    
    /**
     * Private helper method to sort the list of albums according to release date (from oldest to newest).
     * @param listOfAlbums the list of albums to be sorted represented as an array of Album objects.
     */
    private void sortByReleaseDate(Album[] listOfAlbums) {
        for (int i = 0; i < albums.length - 1; i++) {
            for (int j = i + 1; j < albums.length; j++) {
                if (albums[i] != null && albums[j] != null && 
                            albums[i].getReleaseDate().compareTo(albums[j].getReleaseDate()) > 0) {
                    Album temp = albums[i];
                    albums[i] = albums[j];
                    albums[j] = temp;
                }
            }
        }
    }
    
    /**
     * Private helper method to sort the list of albums alphabetically according to genre.
     * @param listOfAlbums the list of albums to be sorted represented as an array of Album objects.
     */
    private void sortByGenre(Album[] listOfAlbums) {
        for (int i = 0; i < albums.length - 1; i++) {
            for (int j = i + 1; j < albums.length; j++) {
                if (albums[i] != null && albums[j] != null && 
                            albums[i].getGenre().compareTo(albums[j].getGenre()) > 0) {
                    Album temp = albums[i];
                    albums[i] = albums[j];
                    albums[j] = temp;
                }
            }
        }
    }
}