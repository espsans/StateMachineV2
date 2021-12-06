package statemachine;

import beaver.Parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class SMTest {

    public static void main(String[] args) {

        String inFile;

        if (args.length != 1) {
            inFile = "tests/StateMachineT.txt";
        } else inFile = args[0];

        try {
            StateScanner scanner = new StateScanner(new FileReader(inFile));

            SMParser parser = new SMParser();

            Machine m = (Machine) parser.parse(scanner);

            System.out.println("Reachable:");
            for (State s : m.getStatess()) {
                s.printReachable();
            }
            System.out.println("Analysis:");
            m.errors().forEach(System.out::println);
            

        } catch (FileNotFoundException e) {
            System.err.printf("File %s not found%n", inFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Parser.Exception e) {
            System.err.println("Parsing error");
        }

    }

}
