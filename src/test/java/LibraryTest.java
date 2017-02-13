import java.util.*;
import org.junit.*;
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
    public void testInitialization() throws Exception {
        Set<String> authors = new HashSet<>();
        authors.add("J.K. Rowling");

        Set<String> titles = new HashSet<>();
        titles.add("Harry Potter");

        assertEquals(authors, library.getAuthors());
        assertEquals(titles, library.getTitles());
    }

    @Test
    public void testInitializationEmptyCollection() throws Exception {
        library = new Library(new Book[0]);
        assertTrue(library.getAuthors().isEmpty());
        assertTrue(library.getTitles().isEmpty());
    }

    @Test
    public void testAddTitleOfSameAuthor() throws Exception {
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
    public void testAddTitleOfDifferentAuthor() throws Exception {
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
    public void testAddTitleOfWithMultipleAuthors() throws Exception {
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
    public void testGetTitlesOfGivenAuthor() throws Exception {
        library.add(new Book("Fantastic Beasts", "J.K. Rowling"));
        library.add(new Book("The Elements of Style", "E.B. White", "William Strunk Jr."));

        Set<String> titles = new HashSet<>();
        titles.add("Harry Potter");
        titles.add("Fantastic Beasts");

        assertEquals(titles, library.getTitles("J.K. Rowling"));
    }

    @Test
    public void testGetTitlesOfGivenNonExistingAuthor() throws Exception {
        assertNull(library.getTitles("Eric Raymond"));
    }

    @Test
    public void testGetAuthorsOfGivenTitle() throws Exception {
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
    public void testGetAuthorsOfGivenNonExistingTitle() throws Exception {
        assertNull(library.getAuthors("Jurassic Park"));
    }

    @Test
    public void testRemoveGivenTitle() throws Exception {
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
    public void testRemoveTitlesFromGivenAuthor() throws Exception {
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
    public void AddingDupesHasNoEffect() throws Exception {
        library.add(new Book("Harry Potter", "J.K. Rowling"));

        Set<String> authors = new HashSet<>();
        authors.add("J.K. Rowling");

        Set<String> titles = new HashSet<>();
        titles.add("Harry Potter");

        assertEquals(authors, library.getAuthors());
        assertEquals(titles, library.getTitles());
    }

    @Test
    public void testRemoveAuthorWithMoreThanOneAuthors() throws Exception {
        library.add(new Book("The Art of Computer Programming", "Donald Knuth"));
        library.add(new Book("Mathematics for the Analysis of Algorithms", "Donald Knuth", "Daniel Greene"));
        library.removeByAuthor("Donald Knuth");

        Set<String> authors = new HashSet<>();
        authors.add("J.K. Rowling");
        authors.add("Daniel Greene");

        Set<String> titles = new HashSet<>();
        titles.add("Harry Potter");
        titles.add("Mathematics for the Analysis of Algorithms");

        assertEquals(authors, library.getAuthors());
        assertEquals(titles, library.getTitles());
        assertNull(library.getTitles("Donald Knuth"));
    }

    @Test
    public void testRemoveTitleWithMoreThanOneAuthors() throws Exception {
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
}
