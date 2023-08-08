package apimanga.example.manga.enums;

public enum Category {
    SHOUJO("Shoujo"),
    SHONEN("Shonen"),
    SEINEN("Seinen"),
    JOSEI("Josei"),
    FANTASY("Fantasy"),
    SCIFI("Sci-Fi"),
    ROMANCE("Romance"),
    HORROR("Horror"),
    COMEDY("Comedy"),
    DRAMA("Drama");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
