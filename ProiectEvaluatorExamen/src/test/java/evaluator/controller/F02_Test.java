package evaluator.controller;

import evaluator.exception.NotAbleToCreateTestException;
import evaluator.repository.MockRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class F02_Test {
    private IntrebariController intrebariController;

    @Before
    public void setUp() throws Exception {
        intrebariController = new IntrebariController(new MockRepository());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test1() {
        Boolean didThrow = false;
        try {
            intrebariController.createNewTest();
        } catch (NotAbleToCreateTestException e) {
            didThrow = true;
        } finally {
            assert didThrow; }
    }

    @Test
    public void test2() {
        intrebariController.addIntrebari();
        Boolean didThrow = false;
        try {
            intrebariController.createNewTest();
        } catch (NotAbleToCreateTestException e) {
            didThrow = true;
        }
        assert didThrow;
    }

    @Test
    public void test3() throws NotAbleToCreateTestException {
        intrebariController.addIntrebariOk();
        evaluator.model.Test test = intrebariController.createNewTest();
        assert test != null;
    }

}
