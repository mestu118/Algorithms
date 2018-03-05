// Name of the file    :: Welcome.java
// created by          :: Sridhar Kandukuri
// modified by         :: CJCSchauble
// Date                :: 06/15/98; 09/05/03
// Description         :: to demonstrate how to use a command line argument
// To compile          :: javac Welcome.java
// To execute          :: java Welcome xxxx, where xxxx is the argument

// Command line arguments are arguments that are specified when executing
// the program from the command line.  When using Eclipse, they are called
// Program arguments.  Think of these arguments as parameters to the main
// method that are given from the command line OR as parameters to the
// program itself.

// prints a command_line argument within an enclosing String
public class Test{
	
	public static void main(String[] args){	
     System.out.println("Hello, " + args[0] + ".  Welcome to Java!!!"); 
     }
}