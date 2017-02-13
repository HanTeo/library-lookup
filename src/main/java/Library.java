import java.util.*;

public class Library {

    private HashMap<String, Set<String>> authorsByTitles;
    private HashMap<String, Set<String>> titlesByAuthor;

    public Library(Book[] books) {
        authorsByTitles = new HashMap<>();
        titlesByAuthor = new HashMap<>();
        Arrays.stream(books).forEach(this::add);
    }

    public void add(Book book) {

        appendTitlesIndex(book);

        appendAuthorsIndex(book);
    }

    private void appendAuthorsIndex(Book book) {
        for (String author : book.getAuthors()) {
            Set<String> titles = titlesByAuthor.putIfAbsent(author, new HashSet<>());
            if (titles == null) {
                titles = getTitles(author);
            }
            titles.add(book.getTitle());
        }
    }

    private void appendTitlesIndex(Book book) {
        Set<String> authors = authorsByTitles.putIfAbsent(book.getTitle(), new HashSet<>());
        if (authors == null) {
            authors = getAuthors(book.getTitle());
        }
        Collections.addAll(authors, book.getAuthors());
    }

    public void removeByTitle(String title) {
        Set<String> authors = getAuthors(title);

        if (authors == null)
            return;

        authorsByTitles.remove(title);

        authors.forEach( author -> {
            Set<String> titles = getTitles(author);
            if(titles.size() > 1){
                titles.remove(title);
            }else{
                titlesByAuthor.remove(author, titles);
            }
        });
    }

    public void removeByAuthor(String author) {
        Set<String> titles = getTitles(author);

        if (titles == null)
            return;

        titlesByAuthor.remove(author);

        titles.forEach( title -> {
            Set<String> authors = getAuthors(title);
            if(authors.size() > 1) {
                authors.remove(author);
            } else{
                authorsByTitles.remove(title, authors);
            }
        });
    }

    public Set<String> getAuthors() {
        return titlesByAuthor.keySet();
    }

    public Set<String> getAuthors(String title) {
        return authorsByTitles.get(title);
    }

    public Set<String> getTitles() {
        return authorsByTitles.keySet();
    }

    public Set<String> getTitles(String author) {
        return titlesByAuthor.get(author);
    }
}
