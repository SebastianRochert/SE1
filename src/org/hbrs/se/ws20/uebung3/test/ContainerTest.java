package src.org.hbrs.se.ws20.uebung3.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.org.hbrs.se.ws20.uebung3.control.Container;
import src.org.hbrs.se.ws20.uebung3.control.ContainerException;
import src.org.hbrs.se.ws20.uebung3.control.Member;
import src.org.hbrs.se.ws20.uebung3.control.MemberDef;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContainerTest {
    private Member x = null;
    private Member y = null;
    private Member z = null;
    private Member a = null;
    private Container con = null;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    @BeforeEach
    void setup() {
        x = new MemberDef(1);
        y = new MemberDef(2);
        z = new MemberDef(3);
        a = new MemberDef(10);
        con = Container.getInstance();

        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void teardown() {
        x = null;
        y = null;
        z = null;
        //con = null;
        Container.deleteInstance();
        System.setOut(originalOut);
    }

    @Test
    void testContainer() {
        /*
        test von size() und addMember()
        Die Methoden contains() und getMember() werden in den Methoden addMember() und deleteMember() verwenden und mit dem Testen dieser Methoden abgedeckt
         */
        try { //Immer Try/Catch benutzen
            assertEquals(0, con.size(), "Fehler, neuer Container sollte leer (0) sein!");
            con.addMember(x);
            con.addMember(y);
            con.addMember(z);
            assertEquals(3, con.size(), "Size() sollte nach einfügen eines Objektes '3' ergeben!");
            con.addMember(a);
            assertEquals(4, con.size(), "Size() sollte nach einfügen eines Objektes '3' ergeben!");
        } catch (ContainerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testContainer2() throws ContainerException {
        //testContainer erweitert um das Ausführen der Methode deleteMember
        con.addMember(x);
        con.addMember(y);
        con.addMember(z);
        assertEquals("Der Member mit der ID: 1 wurde erfolgreich gelöscht!", con.deleteMember(1), "Rückgabe der deleteMember Methoe war falsch!");
        assertEquals(2, con.size(),"Size() sollte nach löschen eines Objektes '2' ergeben!");
        assertEquals("Member ist nicht in der Liste enthalten!", con.deleteMember(10),"Löschen eines nicht vorhanden Member Objekts hat nicht das gewünschte Ergebnis geliefert!" );
        assertEquals(2, con.size(), "Size() sollte nach löschen eines Objektes '2' ergeben!");
        con.deleteMember(3);
        assertEquals(1, con.size(), "Size() sollte nach löschen eines Objektes '1' ergeben!");
    }

    @Test
    void testAddMemberException() throws ContainerException{
        //Prüfen ob eine CointainerException geworfen wird wenn ein Duplicat hinzugefügt werden soll über addMember
        con.addMember(x);
        assertThrows(ContainerException.class, () -> con.addMember(x), "Es wurde keine Exception geworfen als ein Duplikat eingefügt wurde!");
    }

    @Test
    void testDump() throws ContainerException{
        //Testen von dump() nur nach hinzufügen von Member Objekten
        con.addMember(x);
        con.addMember(y);
        con.addMember(z);
        con.dump();
        assertEquals("Alle IDs der aktuell abgespeicherten Objekte im Container:\nMember (ID = 1)\nMember (ID = 2)\nMember (ID = 3)\n", outContent.toString(), "Die Ausgabe der dump() Methode ist falsch!");
    }

    @Test
    void testDump2() throws ContainerException{
        //Testen von dump() auch nach löschen von Member Objekten
        con.addMember(x);
        con.addMember(y);
        con.addMember(z);
        con.deleteMember(3);
        con.dump();
        assertEquals("Alle IDs der aktuell abgespeicherten Objekte im Container:\nMember (ID = 1)\nMember (ID = 2)\n", outContent.toString(), "Die Ausgabe der dump() Methode ist falsch!");
    }
}
