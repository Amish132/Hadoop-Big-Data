UserAge_temp = LOAD 'input/BX-Users.csv' USING PigStorage(',') as (userid:chararray, area:chararray, city:chararray, country:chararray, age:int);

UserAge = FOREACH UserAge_temp GENERATE userid, REPLACE(country,'\\"',''),age;

UserFilter = FILTER UserAge BY (age is not null) AND (age < 100) AND (age > 12); 

AgeGroup = GROUP UserFilter by (age);

AgeCount = FOREACH AgeGroup GENERATE group, COUNT(UserFilter) as counts;

SortedByAge = ORDER AgeCount BY group;

STORE SortedByAge into 'AgeOutput2';
