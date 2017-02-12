public class Book {
    private final String title;
    private final String[] authors;

    public Book(String title, String[] authors) {

        this.title = title;
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public String[] getAuthors() {
        return authors;
    }
}
