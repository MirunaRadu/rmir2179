package evaluator.controller;

import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.model.Statistica;
import evaluator.repository.MockRepository;
import org.junit.Before;
import org.junit.Test;

public class F03_Test {

    private IntrebariController controller;

    @Before
    public void setUp() throws Exception {
        controller = new IntrebariController(new MockRepository());
    }

    @Test
    public void valid() throws NotAbleToCreateStatisticsException {
        Statistica statistica = controller.getStatistica();
        assert statistica.getIntrebariDomenii().keySet().size() == 4;
    }

    @Test(expected = NotAbleToCreateStatisticsException.class)
    public void nonValid() throws NotAbleToCreateStatisticsException {
        controller.getIntrebariRepository().getIntrebari().clear();
        Statistica statistica = controller.getStatistica();
    }
}
