BookRatings = LOAD 'input/BX-Book-Ratings.csv' USING PigStorage(',') as (userid:chararray, isbn:int, rating:float);

Books = LOAD 'input/BX-Books.csv' USING PigStorage(',') as (isbn,title,author,year,publisher);

booksfilter = FILTER BookRatings by isbn is not null;

isbn_group = GROUP booksfilter by (isbn);

isbn_count = FOREACH isbn_group GENERATE group, COUNT(booksfilter) as counts;

isbnDistinct = DISTINCT isbn_count;

joined = JOIN isbnDistinct by group, Books by isbn;

sorted = order joined by counts desc;

top_25 = limit sorted 25;

myOutput = FOREACH top_25 GENERATE $0, $1, $3;

store myOutput into 'finalOutput5';



