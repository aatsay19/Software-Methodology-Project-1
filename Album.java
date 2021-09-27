package songlibrary;

/**
 * This class defines the abstract data type that models an album in a collection of Albums.
 * @author Aatif Sayed, Pranav Tailor
 */
public class Album {
    
    private String title;
    private String artist;
    private Genre genre;  // enum class; Classical, Country, Jazz, Pop, Unknown
    private Date releaseDate;
    private boolean isAvailable;
    
    /**
     * Parameterized constructor used to create an instance of album to be removed or searched in the collection.
     * @param _title the title of the album.
     * @param _artist the artist of the album.
     */
    public Album(String _title, String _artist) {
        title = _title;
        artist = _artist;
        genre = null;
        releaseDate = null;
        isAvailable = true;
    }
    
    /**
     * Parameterized constructor used to create a new album in the collection.
     * @param _title the album's title.
     * @param _artist the album's artist.
     * @param _genre the album's genre.
     * @param _releaseDate the album's release date.
     */
    public Album(String _title, String _artist, Genre _genre, Date _releaseDate) {
        title = _title;
        artist = _artist;
        genre = _genre;
        releaseDate = _releaseDate;
        isAvailable = true;
    }
    
    /**
     * Determines if two albums are equal based on the title and artist.
     */
    @Override
    public boolean equals(Object obj) {
        if (title.equals(((Album)obj).title) && artist.equals(((Album)obj).artist))
            return true;
        return false;
    }
    
    /**
     * Returns a textual representation of an Album object.
     */
    @Override
    public String toString() {
        String availability = "is available";
        if (!isAvailable) {
            availability = "is not available";
        }
        String albumAsString = title + "::" + artist + "::" + genre.name() + 
                                "::" + releaseDate.toString() + "::" + availability;
        return albumAsString;
    }
    
    /**
     * The getter method to return the title of the album.
     * @return the title of the album as a String.
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * The getter method to return the artist of the album.
     * @return the artist of the album as a String.
     */
    public String getArtist() {
        return artist;
    }
    
    /**
     * The getter method to return the genre of the album.
     * @return the genre of the album as a Genre object.
     */
    public Genre getGenre() {
        return genre;
    }
    
    /**
     * The getter method to return the release date of the album.
     * @return the release date of the album as a Date object.
     */
    public Date getReleaseDate() {
        return releaseDate;
    }
    
    /**
     * The getter method to return the status of the availability of the album.
     * @return the availability of the album as a boolean (true or false).
     */
    public boolean getAvailability() {
        return isAvailable;
    }
    
    /**
     * The setter method to set the genre of the album.
     * @param newGenre the genre that the user wants to change the album's genre to (represented as a String).
     */
    public void setGenre(String newGenre) {
        genre = Genre.stringToGenre(newGenre);
    }
    
    /**
     * The setter method to set the availability of the album.
     * @param trueOrFalse whether the album is available or not (true if available, false otherwise).
     */
    public void setAvailability(boolean trueOrFalse) {
        isAvailable = trueOrFalse;
    }
}