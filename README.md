# Stones-Game v1.1

### General Information:
- Game board consist of 3x3 cells.
- Every cell is empty in the beginning.
- Every cell can contain a stone of color red, Green or yellow by re-clicking on it:
- Two player game, names of the players will be asked on the main menu.

Note: The game consists of Business logic. OpenJFX, MVC architecture and Java Persistence, Used GSON to store the game results using JSON formatting in game.json file.
    
### Game Goal:
- One there is same color in a row,column or diagonal same like Tic tac toe.

### Game site:
- The Game site consists of:
	-JavaDoc
	-JXR
	-Checkstyle
	-Sunfire report
	-JaCOCO report


### Game Database:
- The game will store the result of the game as follows:
	- The date and time when the game was completed.
	- Names of players
	- Name of the winning player
	- Number of Turns took by a player to win
Not:The table can be sorted by time in game play

## Requirements:

Building the project requires JDK 11 or later and [Apache Maven](https://maven.apache.org/).
Note: support added in pom.xml for JDK 16.

## App Installation Notes:
Windows:
- Use an IDE to compile to JAR and run with CMD: > java -jar <ExecutableJarFileName>.jar

Linux:
- In terminal to install use: $ mvn install, to run use: $ mvn exec:java

Note: You can see the project site in the target/site folder click in index.html to open the website. The Project site will be soon deployed. Link will be published in the About section of this repository.
