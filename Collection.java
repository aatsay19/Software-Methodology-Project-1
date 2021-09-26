package songlibrary;

public class Collection {
    
    private Album[] albums;
    private int numAlbums;  // number of albums currently in the collection
    
    public Collection() {
        albums = new Album[Constants.INITIAL_ARRAY_SIZE];
        numAlbums = 0;
    }
    
    // Find the album index, or return NOT_FOUND
    private int find(Album album) {
        if (numAlbums == 0) {
            return Constants.NOT_FOUND;
        }
        for (int i = 0; i < albums.length; i++) {
            if (albums[i] != null && albums[i].getTitle().equals(album.getTitle()) && albums[i].getArtist().equals(album.getArtist())) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }
    
    // Increase the capacity of the array list by 4
    private void grow() {
        Album[] newAlbumArray = new Album[numAlbums + Constants.ARRAY_SIZE_INCREMENT];
        for (int i = 0; i < numAlbums; i++) {
            newAlbumArray[i] = albums[i];
        }
        albums = newAlbumArray;
    }
    
    public boolean add(Album album) {
        if (find(album) != -1) {
            return false;
        }
        if (numAlbums == albums.length) {
           grow(); 
        }
        albums[numAlbums] = album;
        numAlbums++;
        return true;
    }
    
    public boolean remove(Album album) {
        if (numAlbums == 0) {
            return false;
        }
        for (int i = 0; i < albums.length; i++) {
            if (albums[i] != null && albums[i].getTitle().equals(album.getTitle()) && albums[i].getArtist().equals(album.getArtist())) {
                albums[i] = null;
                for (int j = i + 1; j < albums.length; j++) {
                    albums[j - 1] = albums[j];
                }
                return true;
            }
        }
        return false;
    }
    
    // Set to not available
    public boolean lendingOut(Album album) {
        int index = find(album);
        if (index != -1) {
            if (albums[index].getAvailability() == false) {
                return false;
            }
            albums[index].setAvailability(false);
            return true;
        }
        return false;
    }
    
    // Set to available
    public boolean returnAlbum(Album album) {
        int index = find(album);
        if (index != -1) {
            if (albums[index].getAvailability() == true) {
                return false;
            }
            albums[index].setAvailability(true);
            return true;
        }
        return false;
    }
    
    // Display the list without specifying the order
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
    
    private void sortByReleaseDate(Album[] listOfAlbums) {
        for (int i = 0; i < albums.length - 1; i++) {
            for (int j = i + 1; j < albums.length; j++) {
                if (albums[i] != null && albums[j] != null && albums[i].getReleaseDate().compareTo(albums[j].getReleaseDate()) > 0) {
                    Album temp = albums[i];
                    albums[i] = albums[j];
                    albums[j] = temp;
                }
            }
        }
    }
    
    private void sortByGenre(Album[] listOfAlbums) {
        for (int i = 0; i < albums.length - 1; i++) {
            for (int j = i + 1; j < albums.length; j++) {
                if (albums[i] != null && albums[j] != null && albums[i].getGenre().compareTo(albums[j].getGenre()) > 0) {
                    Album temp = albums[i];
                    albums[i] = albums[j];
                    albums[j] = temp;
                }
            }
        }
    }
    
}