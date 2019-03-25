package evaluator.controller;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.model.Intrebare;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class IntrebariControllerTest {

    private IntrebariController intrebariController;

    @Before
    public void setUp() throws Exception {
        intrebariController = new IntrebariController();
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test()
    public void addIntrebareECP1() {
        Intrebare intr = new Intrebare(
                "Cat e ora ?", "1)10:00", "2)11:00", "3)12:00", 2, "Random"
        );
        try {
            intrebariController.addNewIntrebare(intr);
        } catch (InputValidationFailedException e) {
            fail();
        }
    }

    @Test()
    public void addIntrebareECP3() {
        Intrebare intr = new Intrebare(
                "Buna seara?", "1)Buna", "2)Buna", "3)Buna", 1, "cevadomeniu"
        );
        try {
            intrebariController.addNewIntrebare(intr);
            fail();
        } catch (InputValidationFailedException expected) {
            assertEquals("Prima litera din domeniu nu e majuscula!", expected.getMessage());
        }
    }

    @Test
    public void addIntrebareECP5() {
        Intrebare intr = new Intrebare(
                "Test?", "1)Test1", "2)Test2", "3)Test3", 5, "Domeniu"
        );
        try {
            intrebariController.addNewIntrebare(intr);
            fail();
        } catch (InputValidationFailedException expected) {
            assertEquals("Varianta corecta nu este unul dintre caracterele {'1', '2', '3'}", expected.getMessage());
        }
    }

    @Test
    public void addIntrebareECP7() {
        Intrebare intr = new Intrebare(
                "Oare m-am plictisit?", "1)DAAA", "2)Dada", "3)Dadada", 1, ""
        );
        try {
            intrebariController.addNewIntrebare(intr);
            fail();
        } catch (InputValidationFailedException expected) {
            assertEquals("Domeniul este vid!", expected.getMessage());
        }
    }

    @Test
    public void addIntrebareBVA1() {
        Intrebare intr = new Intrebare(
                "Cat e 2+2?", "1)1", "2)2", "3)4", 3, "Matematicaaaaaaaaaaaaaaaaaaaa"
        );
        try {
            intrebariController.addNewIntrebare(intr);
        } catch (InputValidationFailedException expected) {
            fail();
        }
    }

    @Test
    public void addIntrebareBVA2() {
        Intrebare intr = new Intrebare(
                "Cat e 2+2?", "1)1", "2)2", "3)4", 0, "M"
        );
        try {
            intrebariController.addNewIntrebare(intr);
        } catch (InputValidationFailedException expected) {
            assertEquals("Varianta corecta nu este unul dintre caracterele {'1', '2', '3'}", expected.getMessage());
        }
    }

    @Test
    public void addIntrebareBVA3() {
        Intrebare intr = new Intrebare(
                "Cat e 2+2?", "1)1", "2)2", "3)4", 5, "M1"
        );
        try {
            intrebariController.addNewIntrebare(intr);
        } catch (InputValidationFailedException expected) {
            assertEquals("Varianta corecta nu este unul dintre caracterele {'1', '2', '3'}", expected.getMessage());
        }
    }

    @Test
    public void addIntrebareBVA4() {
        Intrebare intr = new Intrebare(
                "Cat e 2+2?", "1)1", "2)2", "3)4", 1, "Mate"
        );
        try {
            intrebariController.addNewIntrebare(intr);
        } catch (InputValidationFailedException expected) {
            fail();
        }
    }


}