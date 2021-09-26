package songlibrary;
public class Album {
    
    private String title;
    private String artist;
    private Genre genre;  // enum class; Classical, Country, Jazz, Pop, Unknown
    private Date releaseDate;
    private boolean isAvailable;
    
    public Album(String _title, String _artist) {
        title = _title;
        artist = _artist;
        genre = null;
        releaseDate = null;
        isAvailable = true;
    }
    
    public Album(String _title, String _artist, Genre _genre, Date _releaseDate) {
        title = _title;
        artist = _artist;
        genre = _genre;
        releaseDate = _releaseDate;
        isAvailable = true;
    }
    
    @Override
    public boolean equals(Object obj) {
        // TODO
        return true;
    }
    
    // ...
    
    @Override
    public String toString() {
        String availability = "is available";
        if (!isAvailable) {
            availability = "is not available";
        }
        String albumAsString = title + "::" + artist + "::" + genre.name() + "::" + releaseDate.toString() + "::" + availability;
        return albumAsString;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getArtist() {
        return artist;
    }
    
    public Genre getGenre() {
        return genre;
    }
    
    public Date getReleaseDate() {
        return releaseDate;
    }
    
    public boolean getAvailability() {
        return isAvailable;
    }
    
    public void setGenre(String newGenre) {
        genre = Genre.stringToGenre(newGenre);
    }
    
    public void setAvailability(boolean trueOrFalse) {
        isAvailable = trueOrFalse;
    }
}