package org.hbrs.se.ws20.uebung1.test;

import static org.junit.jupiter.api.Assertions.*;
import org.hbrs.se.ws20.uebung1.control.Translator;
import org.hbrs.se.ws20.uebung1.control.factory.Factory;
import org.hbrs.se.ws20.uebung1.view.ClientTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TranslatorTest {
    private Translator translator = null;

    @BeforeEach
    void setup(){
        translator = Factory.createGermanTranslator();
    }

    @AfterEach
    void teardown() {
        translator = null;
    }

    @Test
    void testTranslateNumber() {
        //Positivtests
        assertEquals("eins", translator.translateNumber(1), "Fehler bei translateNumber");

        //Negativtests
        assertEquals("Übersetzung der Zahl 11 nicht möglich (1.0)", translator.translateNumber(11), "Fehler bei translateNumber");
        assertEquals("Übersetzung der Zahl -1 nicht möglich (1.0)", translator.translateNumber(-1), "Fehler bei translateNumber");
        assertEquals("Übersetzung der Zahl 0 nicht möglich (1.0)", translator.translateNumber(0), "Fehler bei translateNumber");
    }
}
