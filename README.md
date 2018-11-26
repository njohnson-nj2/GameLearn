GameLearn

Windows
Simple directions to run via command line (skip step 1)
https://www.maketecheasier.com/run-java-program-from-command-prompt/

MAC   (https://stackoverflow.com/questions/2360995/how-do-i-compile-and-run-a-program-in-java-on-my-mac)
Using the Compiler
Now that you have written a simple Java program, you need to compile it. Run the Terminal app, which is located in "Applications/Utilities/Terminal.app". Type the following commands into the terminal:

cd ~

javac GameLearn.java

You just compiled your first Java application, albeit a simple one, on OSX. The process of compiling will produce a single file, called "HelloWorld.class". This file contains Java byte codes, which are the instructions that the Java Virtual Machine understands.

Running Your Program
To run the program, type the following command in the terminal.

java HelloWorld

This command will start a Java Virtual Machine and attempt to load the class called HelloWorld. Once it loads that class, it will execute the main method I mentioned earlier. You should see "Hello World!" printed in the terminal window. That's all there is to it.
