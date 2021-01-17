
Find if a path exists between two cities
This application determines if two cities are connected.
Two points are considered connected if there’s a series of roads that can be traveled from one city to another.

Demo list of roads is available in a project file cities.txt located in the resource directory. File contains a list of city pairs (one pair per line, comma separated), which indicates that there’s a road between those cities.

In the example below

city Boston has exactly 2 roads connecting cities New York and Connecticut.
cities Albany is not reachable from any of other cities.
Example Cities.txt input file
Boston, New York
Boston, Connecticut
Philadelphia, Newark
Newark, Boston
Trenton, Albany

Build from source
mvn clean install
Run the application

http://localhost:8080/connected?origin=Boston&destination=Newark
Should return yes
http://localhost:8080/connected?origin=Boston&destination=Philadelphia
Should return yes
http://localhost:8080/connected?origin=Philadelphia&destination=Albany
Should return no


Swagger
http://localhost:8080/swagger-ui.html

