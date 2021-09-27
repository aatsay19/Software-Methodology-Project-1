package songlibrary;

/**
 * This class is an enum class which defines the different genres of albums within the album collection.
 * @author Aatif Sayed. Pranav Tailor
 */
public enum Genre {
    Classical,
    Country,
    Jazz,
    Pop,
    Unknown;

    /**
     * Convert a given String (ignore alphabetical case) to one of the declared Genre enums.
     * If given String is not equivalent to an enum, convert it to the 'Unknown' enum. 
     * @param genreAsString the genre of an album, represented as a String.
     * @return an instance of one of the five Genre enums.
     */
    public static Genre stringToGenre(String genreAsString) {
        Genre stringAsGenre;
        if (genreAsString.equalsIgnoreCase("Classical")) {
            stringAsGenre = Classical;
        }
        else if (genreAsString.equalsIgnoreCase("Country")) {
            stringAsGenre = Country;
        }
        else if (genreAsString.equalsIgnoreCase("Jazz")) {
            stringAsGenre = Jazz;
        }
        else if (genreAsString.equalsIgnoreCase("Pop")) {
            stringAsGenre = Pop;
        }
        else {
            stringAsGenre = Unknown;
        }
        return stringAsGenre;
    }
    
    /**
     * Convert a Genre object to a String.
     * @param stringAsGenre the genre as a Genre object.
     * @return the genre as a String.
     */
    public String genreToString(Genre stringAsGenre) {
        String genreAsString = stringAsGenre.name();
        return genreAsString;
    }
}