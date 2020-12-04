package src.org.hbrs.se.ws20.uebung4.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExtendedBufferedReader {
    private BufferedReader input = null;

    public ExtendedBufferedReader() {
        input = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readLine(String output) {
        String sInput = null;

        System.out.println(output);
        try {
            sInput = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sInput;
    }

    public int readLineInt(String output) {
        String sInput = null;
        int id = 0;

        System.out.println(output);
        try {
            sInput = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        id = Integer.parseInt(sInput);
        return id;
    }
}
