package com.iot.serial;

import javax.smartcardio.*;
import java.util.List;

public class ICDemo01 {
    public static void main(String[] args) {
        TerminalFactory factory = TerminalFactory.getDefault();
        try {
            List<CardTerminal> terminal = factory.terminals().list();
            System.out.println("terminal:" + terminal);

            CardTerminal ter = terminal.get(0);

            // establish a connection with the card
            Card card = ter.connect("T=1");
            System.out.println("card: " + card);
            CardChannel channel = card.getBasicChannel();

            ResponseAPDU r = channel.transmit(new CommandAPDU(160, 242, 0, 0, 22)); //A0 F2 00 00 16
            System.out.println("response: " + r.toString());
            for(int j=0; j<8; j++) {
                System.out.print(Integer.toHexString( (int)((r.getData()[j]+256) % 256)) + " ");
            }

            System.out.print("\n");

            for(int j=0; j<card.getATR().getBytes().length; j++) {
                System.out.print(Integer.toHexString( (int)((card.getATR().getBytes()[j]+256) % 256)) + " ");
            }

            // disconnect
            card.disconnect(false);
        } catch (CardException e) {
            // TODO Auto-generated catch block
            System.out.println("connection erro,or card not inserted.");
        }
    }

}
