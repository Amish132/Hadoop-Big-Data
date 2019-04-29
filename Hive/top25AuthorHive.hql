-- Loading the Data

CREATE TABLE IF NOT EXISTS BookData (ISBN STRING, BookTitle STRING, BookAuthor STRING, YearOfPublication INT, Publisher STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY '\;' STORED AS TEXTFILE; LOAD DATA INPATH 'input/BX-BooksCorrected.txt' OVERWRITE INTO TABLE BookData;

-- Eliminating Incorrect Data
INSERT OVERWRITE TABLE BookData SELECT BookData.* FROM BookData WHERE YearOfPublication >0;

-- Displaying the output
SELECT BookAuthor, COUNT(BookTitle) AS Count FROM BookData GROUP BY BookAuthor ORDER BY Count DESC LIMIT 25;

-- Storing the output in a file
INSERT OVERWRITE DIRECTORY '/home/cloudera/Desktop/BigData/Hive/' SELECT Publisher, COUNT(BookTitle) AS Count FROM BookData GROUP BY Publisher ORDER BY Count DESC LIMIT 25;
