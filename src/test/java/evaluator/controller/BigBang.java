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

import static org.junit.Assert.assertEquals;

public class BigBang {

    private IntrebariController appController;


    @Before
    public void setUp() {
        appController = new IntrebariController(new MockRepository());
    }


    @Test
    public void A() throws DuplicateIntrebareException, InputValidationFailedException {
        appController.addNewIntrebare(new Intrebare("Cat face 1 + 1?","1)1","2)2","3)3",2,"Mate"));
        assertEquals(appController.getIntrebariRepository().getIntrebari().size(), 5);
    }

    @Test
    public void B() throws NotAbleToCreateTestException {
        //appController.loadIntrebariFromFile("intrebari.txt");
        appController.addIntrebariOk();
        assertEquals(appController.createNewTest().getIntrebari().size(), 5);
    }

    @Test
    public void C() throws NotAbleToCreateStatisticsException {
        //appController.loadIntrebariFromFile("intrebari.txt");
        appController.addIntrebariOk();
        Statistica statistica = appController.getStatistica();
        statistica.getIntrebariDomenii().forEach((k,v)->{
            assertEquals(v.intValue(),1);
        });

    }


    @Test
    public void integration() throws DuplicateIntrebareException, InputValidationFailedException, NotAbleToCreateTestException, NotAbleToCreateStatisticsException {
        appController.addNewIntrebare(new Intrebare("Cat face 1 + 1?","1)1","2)2","3)3",2,"Mate"));
        appController.addNewIntrebare(new Intrebare("Cat face 1 + 1?","1)1","2)2","3)3",2,"Fizica"));
        appController.addNewIntrebare(new Intrebare("Cat face 1 + 1?","1)1","2)2","3)3",2,"Astronomie"));
        appController.addNewIntrebare(new Intrebare("Cat face 1 + 1?","1)1","2)2","3)3",2,"Astrologie"));
        appController.addNewIntrebare(new Intrebare("Cat face 1 + 1?","1)1","2)2","3)3",2,"Filozofie"));

        evaluator.model.Test test = appController.createNewTest();

        Statistica statistica = appController.getStatistica();

        assertEquals(appController.getIntrebariRepository().getIntrebari().size(), 9);
        assertEquals(5, test.getIntrebari().size());
        statistica.getIntrebariDomenii().forEach((k,v)->{
            assertEquals(v.intValue(),1);
        });
    }
}