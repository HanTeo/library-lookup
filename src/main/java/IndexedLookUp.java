import java.util.Set;

public interface IndexedLookUp {

    void add(Book book);

    void removeByTitle(String title);

    void removeByAuthor(String author);

    Set<String> getAuthors();

    Set<String> getAuthors(String title);

    Set<String> getTitles();

    Set<String> getTitles(String author);
}
