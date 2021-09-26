package songlibrary;

public enum Genre {
    Classical,
    Country,
    Jazz,
    Pop,
    Unknown;

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
    
    public String genreToString(Genre stringAsGenre) {
        String genreAsString = stringAsGenre.name();
        return genreAsString;
    }
}