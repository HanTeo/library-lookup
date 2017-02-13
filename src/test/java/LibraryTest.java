import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class LibraryTest {
    private IndexedLookUp library;

    @Before
    public void setUp() throws Exception {
        Book[] books = new Book[]{
                new Book("Harry Potter", "J.K. Rowling")
        };

        library = new Library(books);
    }

    @Test
    public void initialization() throws Exception {
        Set<String> authors = new HashSet<>();
        authors.add("J.K. Rowling");

        Set<String> titles = new HashSet<>();
        titles.add("Harry Potter");

        assertEquals(authors, library.getAuthors());
        assertEquals(titles, library.getTitles());
    }

    @Test
    public void initializationWithEmptyCollection() throws Exception {
        library = new Library(new Book[0]);
        assertTrue(library.getAuthors().isEmpty());
        assertTrue(library.getTitles().isEmpty());
    }

    @Test
    public void addTitleOfSameAuthor() throws Exception {
        library.add(new Book("Fantastic Beasts", "J.K. Rowling"));

        Set<String> authors = new HashSet<>();
        authors.add("J.K. Rowling");

        Set<String> titles = new HashSet<>();
        titles.add("Harry Potter");
        titles.add("Fantastic Beasts");

        assertEquals(authors, library.getAuthors());
        assertEquals(titles, library.getTitles());
    }

    @Test
    public void addTitleOfDifferentAuthor() throws Exception {
        library.add(new Book("The Art of Computer Programming", "Donald Knuth"));

        Set<String> authors = new HashSet<>();
        authors.add("J.K. Rowling");
        authors.add("Donald Knuth");

        Set<String> titles = new HashSet<>();
        titles.add("Harry Potter");
        titles.add("The Art of Computer Programming");

        assertEquals(authors, library.getAuthors());
        assertEquals(titles, library.getTitles());
    }

    @Test
    public void addTitleOfWithMultipleAuthors() throws Exception {
        library.add(new Book("The Elements of Style", "E.B. White", "William Strunk Jr."));

        Set<String> authors = new HashSet<>();
        authors.add("J.K. Rowling");
        authors.add("E.B. White");
        authors.add("William Strunk Jr.");

        Set<String> titles = new HashSet<>();
        titles.add("Harry Potter");
        titles.add("The Elements of Style");

        assertEquals(authors, library.getAuthors());
        assertEquals(titles, library.getTitles());
    }

    @Test
    public void getTitlesOfGivenAuthor() throws Exception {
        library.add(new Book("Fantastic Beasts", "J.K. Rowling"));
        library.add(new Book("The Elements of Style", "E.B. White", "William Strunk Jr."));

        Set<String> titles = new HashSet<>();
        titles.add("Harry Potter");
        titles.add("Fantastic Beasts");

        assertEquals(titles, library.getTitles("J.K. Rowling"));
    }

    @Test
    public void getTitlesOfGivenNonExistingAuthor() throws Exception {
        assertNull(library.getTitles("Eric Raymond"));
    }

    @Test
    public void getAuthorsOfGivenTitle() throws Exception {
        library.add(new Book("Fantastic Beasts", "J.K. Rowling"));
        library.add(new Book("The Elements of Style", "E.B. White", "William Strunk Jr."));

        Set<String> fantasticBeastAuthor = new HashSet<>();
        fantasticBeastAuthor.add("J.K. Rowling");

        Set<String> elementsOfStyleAuthors = new HashSet<>();
        elementsOfStyleAuthors.add("E.B. White");
        elementsOfStyleAuthors.add("William Strunk Jr.");

        assertEquals(fantasticBeastAuthor, library.getAuthors("Fantastic Beasts"));
        assertEquals(elementsOfStyleAuthors, library.getAuthors("The Elements of Style"));
    }

    @Test
    public void getAuthorsOfGivenNonExistingTitle() throws Exception {
        assertNull(library.getAuthors("Jurassic Park"));
    }

    @Test
    public void removeGivenTitle() throws Exception {
        library.add(new Book("Fantastic Beasts", "J.K. Rowling"));
        library.add(new Book("The Elements of Style", "E.B. White", "William Strunk Jr."));
        library.removeByTitle("The Elements of Style");

        Set<String> remainingBooks = new HashSet<>();
        remainingBooks.add("Harry Potter");
        remainingBooks.add("Fantastic Beasts");

        Set<String> remainingAuthors = new HashSet<>();
        remainingAuthors.add("J.K. Rowling");

        assertEquals(remainingBooks, library.getTitles());
        assertEquals(remainingAuthors, library.getAuthors());
        assertNull(library.getAuthors("E.B. White"));
        assertNull(library.getAuthors("William Strunk Jr."));
        assertNull(library.getTitles("The Elements of Style"));
    }

    @Test
    public void removeNonExistentTitle() throws Exception {
        library.add(new Book("Fantastic Beasts", "J.K. Rowling"));
        library.add(new Book("The Elements of Style", "E.B. White", "William Strunk Jr."));
        library.removeByTitle("Machine Learning");

        Set<String> remainingBooks = new HashSet<>();
        remainingBooks.add("Harry Potter");
        remainingBooks.add("Fantastic Beasts");
        remainingBooks.add("The Elements of Style");

        Set<String> remainingAuthors = new HashSet<>();
        remainingAuthors.add("J.K. Rowling");
        remainingAuthors.add("E.B. White");
        remainingAuthors.add("William Strunk Jr.");

        assertEquals(remainingBooks, library.getTitles());
        assertEquals(remainingAuthors, library.getAuthors());
    }

    @Test
    public void removeTitlesFromGivenAuthor() throws Exception {
        library.add(new Book("Fantastic Beasts", "J.K. Rowling"));
        library.add(new Book("The Elements of Style", "E.B. White", "William Strunk Jr."));
        library.removeByAuthor("J.K. Rowling");

        Set<String> remainingBooks = new HashSet<>();
        remainingBooks.add("The Elements of Style");

        Set<String> remainingAuthors = new HashSet<>();
        remainingAuthors.add("E.B. White");
        remainingAuthors.add("William Strunk Jr.");

        assertEquals(remainingBooks, library.getTitles());
        assertEquals(remainingAuthors, library.getAuthors());
        assertNull(library.getTitles("J.K. Rowling"));
    }

    @Test
    public void removeTitlesFromNonExistentAuthor() throws Exception {
        library.add(new Book("Fantastic Beasts", "J.K. Rowling"));
        library.removeByAuthor("George Orwell");

        Set<String> remainingBooks = new HashSet<>();
        remainingBooks.add("Harry Potter");
        remainingBooks.add("Fantastic Beasts");

        Set<String> remainingAuthors = new HashSet<>();
        remainingAuthors.add("J.K. Rowling");

        assertEquals(remainingBooks, library.getTitles());
        assertEquals(remainingAuthors, library.getAuthors());
    }

    @Test
    public void addingDupesHasNoEffect() throws Exception {
        library.add(new Book("Harry Potter", "J.K. Rowling"));

        Set<String> authors = new HashSet<>();
        authors.add("J.K. Rowling");

        Set<String> titles = new HashSet<>();
        titles.add("Harry Potter");

        assertEquals(authors, library.getAuthors());
        assertEquals(titles, library.getTitles());
    }

    @Test
    public void removeAuthorForBooksWithMoreThanOneAuthorsAlsoRemovesEntryForAssociatedAuthor() throws Exception {
        library.add(new Book("The Art of Computer Programming", "Donald Knuth"));
        library.add(new Book("Mathematics for the Analysis of Algorithms", "Donald Knuth", "Daniel Greene"));
        library.removeByAuthor("Donald Knuth");

        Set<String> authors = new HashSet<>();
        authors.add("J.K. Rowling");

        Set<String> titles = new HashSet<>();
        titles.add("Harry Potter");
        titles.add("Mathematics for the Analysis of Algorithms");

        assertEquals(authors, library.getAuthors());
        assertEquals(titles, library.getTitles());
        assertNull(library.getTitles("Donald Knuth"));
    }

    @Test
    public void removeAuthorForBooksWithMoreThanOneAuthorsOnlyRemovesAssociatedTitle() throws Exception {
        library.add(new Book("The Art of Computer Programming", "Donald Knuth"));
        library.add(new Book("Mathematics for the Analysis of Algorithms", "Donald Knuth", "Daniel Greene"));
        library.add(new Book("My Thesis", "Daniel Greene"));
        library.removeByAuthor("Donald Knuth");

        Set<String> authors = new HashSet<>();
        authors.add("J.K. Rowling");
        authors.add("Daniel Greene");

        Set<String> titles = new HashSet<>();
        titles.add("Harry Potter");
        titles.add("Mathematics for the Analysis of Algorithms");
        titles.add("My Thesis");

        assertEquals(authors, library.getAuthors());
        assertEquals(titles, library.getTitles());
        assertNull(library.getTitles("Donald Knuth"));
    }

    @Test
    public void removeTitleWithMoreThanOneAuthors() throws Exception {
        library.add(new Book("The Art of Computer Programming", "Donald Knuth"));
        library.add(new Book("Mathematics for the Analysis of Algorithms", "Donald Knuth", "Daniel Greene"));
        library.removeByTitle("Mathematics for the Analysis of Algorithms");

        Set<String> authors = new HashSet<>();
        authors.add("J.K. Rowling");
        authors.add("Donald Knuth");

        Set<String> titles = new HashSet<>();
        titles.add("Harry Potter");
        titles.add("The Art of Computer Programming");

        assertEquals(authors, library.getAuthors());
        assertEquals(titles, library.getTitles());
        assertNull(library.getTitles("Daniel Greene"));
    }

    @Test
    public void removeTitleWithMoreThanOneAuthorsRetainsOtherBooks() throws Exception {
        library.add(new Book("The Art of Computer Programming", "Donald Knuth"));
        library.add(new Book("Mathematics for the Analysis of Algorithms", "Donald Knuth", "Daniel Greene"));
        library.add(new Book("My Thesis", "Daniel Greene"));
        library.removeByTitle("Mathematics for the Analysis of Algorithms");

        Set<String> authors = new HashSet<>();
        authors.add("J.K. Rowling");
        authors.add("Donald Knuth");
        authors.add("Daniel Greene");

        Set<String> titles = new HashSet<>();
        titles.add("Harry Potter");
        titles.add("The Art of Computer Programming");
        titles.add("My Thesis");

        assertEquals(authors, library.getAuthors());
        assertEquals(titles, library.getTitles());
    }
}
