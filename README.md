Database project made in Java to store song details from a file and answer queries relative to those songs. (2021)

Made by: Robert Cachapa and Inês Marques [![GitHub Repo](https://img.shields.io/badge/-black?logo=github)](https://github.com/sranene)

## Guide

1. **First step**  
    Run the code

2. **Second step**  
   Make a query about the songs ex:
   ![query](https://img.shields.io/badge/-black?logo=github](https://github.com/user-attachments/assets/af34157b-a296-4e48-998d-a50127a1cbb5)

   This query returns the amount of songs in the files that were released in the year 2000


## List of Queries

- `COUNT_SONGS_YEAR <year>`  
  Counts the total number of songs released in a given year.  
  Example: `COUNT_SONGS_YEAR 1999`

- `COUNT_DUPLICATE_SONGS_YEAR <year>`  
  Counts the number of duplicate songs in a given year.  
  Example: `COUNT_DUPLICATE_SONGS_YEAR 2005`

- `GET_ARTISTS_FOR_TAG <tag>`  
  Returns the artists associated with a given tag.  
  Example: `GET_ARTISTS_FOR_TAG rock`

- `GET_MOST_DANCEABLE <startYear> <endYear> <N>`  
  Returns the top N most danceable songs between two years.  
  Example: `GET_MOST_DANCEABLE 2000 2010 10`

- `GET_ARTISTS_ONE_SONG <startYear> <endYear>`  
  Returns artists who released only one song between two years.  
  Example: `GET_ARTISTS_ONE_SONG 1990 2000`

- `GET_TOP_ARTISTS_WITH_SONGS_BETWEEN <N> <min> <max>`  
  Returns the top N artists with a number of songs between two values.  
  Example: `GET_TOP_ARTISTS_WITH_SONGS_BETWEEN 5 10 20`

- `MOST_FREQUENT_WORDS_IN_ARTIST_NAME <N> <minLength> <maxLength>`  
  Returns the most frequent words in artist names.  
  Example: `MOST_FREQUENT_WORDS_IN_ARTIST_NAME 10 3 8`

- `GET_UNIQUE_TAGS`  
  Returns all unique tags in the system.  
  Example: `GET_UNIQUE_TAGS`

- `GET_UNIQUE_TAGS_IN_BETWEEN_YEARS <year1> <year2>`  
  Returns unique tags used between two years.  
  Example: `GET_UNIQUE_TAGS_IN_BETWEEN_YEARS 2000 2010`

- `GET_RISING_STARS <year1> <year2> <order>`  
  Returns rising artists between two years.  
  Example: `GET_RISING_STARS 2010 2020 DESC`

- `ADD_TAGS <tag1>;<tag2>;...`  
  Adds multiple tags separated by `;`.  
  Example: `ADD_TAGS rock;indie;alternative`

- `REMOVE_TAGS <tag1>;<tag2>;...`  
  Removes multiple tags separated by `;`.  
  Example: `REMOVE_TAGS pop;electronic`

- `CLEANUP`  
  Performs system cleanup operations.  
  Example: `CLEANUP`

- `GET_TOP_10_LIVELINESS_SONGS <year> <liveliness>`  
  Returns the top 10 songs with the highest liveliness in a given year.  
  Example: `GET_TOP_10_LIVELINESS_SONGS 2015 0.75`
