package com.iot.serial;

import javax.smartcardio.CardTerminal;
import javax.smartcardio.TerminalFactory;
import java.util.List;

public class ICDemo {
    /**
     * main function, listing all the PC/SC readers connected to your PC
     */
    public static void main(String[] args) {

        // show the list of available terminals
        TerminalFactory factory = TerminalFactory.getDefault();

        // list of readers (empty)
        List<CardTerminal> terminals;
        try {
            // get list of readers form the terminal
            terminals = factory.terminals().list();

            // print list of readers to the console.
            System.out.println(terminals.toString());

        } catch (Exception e) {
            // Print Stack-Trace in case of an error
            e.printStackTrace();
        }
    }

}
