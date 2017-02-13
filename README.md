# library-lookup
A Java / Gradle TDD project. The demo class makes use of some Java 8 features i.e. Stream & Lambdas

## Index by Author -> Titles
Internally this is a HashMap<String, Set<String>>
- key: author name `String`. This utilises the built in String hashing function to provide a `O(n)` time complexity for lookups
- value: set of titles `HashSet<String>`. This enables title entries to be removed with `O(n)` complexity

## Indexed by Title -> Author(s)
Internally this is also a HashMap<String, Set<String>> with keys and value reversed compared to the previous index.
- key: book title `String`. This utilises the built in String hashing function to provide a `O(n)` time complexity for lookups
- value: author name `HashSet<String>`. This enables name entries to be removed with `O(n)` complexity

## Add
Each add operation will update both: 
  1. Author to Titles Index
  2. Title to Authors Index

## Remove (ByTitle & ByAuthor)
Each remove operation will also update both: 
  1. Author to Titles Index
  2. Title to Authors Index
