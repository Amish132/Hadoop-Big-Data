UserCountry_temp = LOAD 'input/BX-Users.csv' USING PigStorage(',') as (userid:chararray, area:chararray, city:chararray, country:chararray, age:int);

FilterCountry = FILTER UserCountry_temp BY country is not null;

UserCountry_ = FOREACH FilterCountry GENERATE userid, REPLACE(country,'\\"','') as country;

UserCountry  = FOREACH UserCountry_ GENERATE userid, REPLACE(country, ' ','') as country;

CountryGroup = GROUP UserCountry by (country);

CountryCount = FOREACH CountryGroup GENERATE group, COUNT(UserCountry) as counts;

FilterAgain = FILTER CountryCount BY counts > 5;

STORE FilterAgain into 'CountryOutput3';
