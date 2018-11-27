GameLearn

Windows
1) Simple path to install JDK
https://www.maketecheasier.com/run-java-program-from-command-prompt/

2) Open GameLearn folder wherever it is saved and navigate down to adventure folder, copy the filepath (X)

3) Open command prompt and type in cd X

4) Type in javac -cp . Adventure.java characters\*.java equip\*.java Locations\*.java

5) From the GameLearn folder navigate down to the src folder, copy the filepath (Y)

6)Now to run the game any number of times type in: 

java -cp Y com.adventure.Adventure com.adventure.characters.* com.adventure.equip.* com.adventure.Locations.*


MAC

1) Open GameLearn folder wherever it is saved and navigate down to adventure folder, copy the filepath (X)

2) Open command prompt and type in cd X

3) Type in javac -cp . Adventure.java characters/*.java equip/*.java Locations/*.java\

4) From the GameLearn folder navigate down to the src folder, copy the filepath (Y)

5)Now to run the game any number of times type in: 

java -cp Y com.adventure.Adventure com.adventure.characters.* com.adventure.equip.* com.adventure.Locations.*

