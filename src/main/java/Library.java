import java.util.*;

class Library {
    private HashMap<String, Set<String>> authorsByTitles;
    private HashMap<String, Set<String>> titlesByAuthor;

    Library(Book[] books) {
        authorsByTitles = new HashMap<>();
        titlesByAuthor = new HashMap<>();
        Arrays.stream(books).forEach(this::add);
    }

    void add(Book book) {
        Set<String> authors = authorsByTitles.putIfAbsent(book.getTitle(), new HashSet<>());
        if (authors == null) {
            authors = getAuthors(book.getTitle());
        }
        Collections.addAll(authors, book.getAuthors());

        for (String author : book.getAuthors()) {
            Set<String> titles = titlesByAuthor.putIfAbsent(author, new HashSet<>());
            if (titles == null) {
                titles = getTitles(author);
            }
            titles.add(book.getTitle());
        }
    }

    void removeTitle(String title) {
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

    void removeByAuthor(String author) {
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

    Set<String> getAuthors() {
        return titlesByAuthor.keySet();
    }

    Set<String> getAuthors(String title) {
        return authorsByTitles.get(title);
    }

    Set<String> getTitles() {
        return authorsByTitles.keySet();
    }

    Set<String> getTitles(String author) {
        return titlesByAuthor.get(author);
    }
}
