package evaluator.controller;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.model.Intrebare;
import evaluator.model.Statistica;
import evaluator.repository.MockRepository;
import org.junit.Before;
import org.junit.Test;

public class TopDown {

    private IntrebariController appController;


    @Before
    public void setUp() {
        appController = new IntrebariController(new MockRepository());
    }


    @Test
    public void A() throws DuplicateIntrebareException, InputValidationFailedException {
        appController.addNewIntrebare(new Intrebare("E=MC2?", "1)", "2)2", "3)3", 2, "Biologie"));
        assertEquals(appController.getIntrebariRepository().getIntrebari().size(), 1);
    }

    private void assertEquals(int size, int i) {
    }

    @Test
    public void B() throws NotAbleToCreateTestException, DuplicateIntrebareException, InputValidationFailedException {
        //appController.loadIntrebariFromFile("intrebari.txt");
        appController.addNewIntrebare(new Intrebare("E=MC2?", "1)", "2)2", "3)3", 2, "Fizica cuantica"));
        assertEquals(appController.createNewTest().getIntrebari().size(), 4);
    }

    @Test
    public void C() throws NotAbleToCreateStatisticsException {
        //appController.loadIntrebariFromFile("intrebari.txt");
        Statistica statistica = appController.getStatistica();
        statistica.getIntrebariDomenii().forEach((k,v)->{
            assertEquals(v.intValue(),1);
        });

    }

    @Test
    public void PA() throws DuplicateIntrebareException, InputValidationFailedException {
        appController.addNewIntrebare(new Intrebare("E=MC2?", "1)", "2)2", "3)3", 2, "Fizica"));
        appController.addNewIntrebare(new Intrebare("E=MC2?", "1)", "2)2", "3)3", 2, "Muzica"));
        appController.addNewIntrebare(new Intrebare("E=MC2?", "1)", "2)2", "3)3", 2, "Mate"));
        appController.addNewIntrebare(new Intrebare("E=MC2?", "1)", "2)2", "3)3", 2, "Romana"));
        appController.addNewIntrebare(new Intrebare("E=MC2?", "1)", "2)2", "3)3", 2, "Biologie"));
        assertEquals(appController.getIntrebariRepository().getIntrebari().size(), 9);
    }

    @Test
    public void PAB() throws DuplicateIntrebareException, InputValidationFailedException, NotAbleToCreateTestException {
        appController.addNewIntrebare(new Intrebare("E=MC2?", "1)", "2)2", "3)3", 2, "Fizica"));
        appController.addNewIntrebare(new Intrebare("E=MC2?", "1)", "2)2", "3)3", 2, "Muzica"));
        appController.addNewIntrebare(new Intrebare("E=MC2?", "1)", "2)2", "3)3", 2, "Mate"));
        appController.addNewIntrebare(new Intrebare("E=MC2?", "1)", "2)2", "3)3", 2, "Romana"));
        appController.addNewIntrebare(new Intrebare("E=MC2?", "1)", "2)2", "3)3", 2, "Biologie"));

        evaluator.model.Test test = appController.createNewTest();

        assertEquals(appController.getIntrebariRepository().getIntrebari().size(), 9);
        assertEquals(9, test.getIntrebari().size());

    }

    @Test
    public void PABC() throws DuplicateIntrebareException, InputValidationFailedException, NotAbleToCreateTestException, NotAbleToCreateStatisticsException {
        appController.addNewIntrebare(new Intrebare("E=MC2?", "1)", "2)2", "3)3", 2, "Fizica"));
        appController.addNewIntrebare(new Intrebare("E=MC2?", "1)", "2)2", "3)3", 2, "Muzica"));
        appController.addNewIntrebare(new Intrebare("E=MC2?", "1)", "2)2", "3)3", 2, "Mate"));
        appController.addNewIntrebare(new Intrebare("E=MC2?", "1)", "2)2", "3)3", 2, "Romana"));
        appController.addNewIntrebare(new Intrebare("E=MC2?", "1)", "2)2", "3)3", 2, "Biologie"));

        evaluator.model.Test test = appController.createNewTest();

        Statistica statistica = appController.getStatistica();

        assertEquals(appController.getIntrebariRepository().getIntrebari().size(), 9);
        assertEquals(9, test.getIntrebari().size());
        statistica.getIntrebariDomenii().forEach((k,v)->{
            assertEquals(v.intValue(),1);
        });
    }
}
