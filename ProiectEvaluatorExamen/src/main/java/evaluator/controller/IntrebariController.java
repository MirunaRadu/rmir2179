package evaluator.controller;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.model.Intrebare;
import evaluator.model.Statistica;
import evaluator.model.Test;
import evaluator.repository.MockRepository;

import java.util.ArrayList;
import java.util.List;

public class IntrebariController {

    private MockRepository intrebariRepository;

    public IntrebariController(MockRepository repo) {
        this.intrebariRepository = repo;
        //intrebariRepository = new IntrebariRepositoryFile(new IntrebareValidation(), "src/main/resources/intrebari.txt");
    }

    public Intrebare addNewIntrebare(Intrebare intrebare) throws InputValidationFailedException {

        try {
            intrebariRepository.add(intrebare);
        } catch (DuplicateIntrebareException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return intrebare;
    }

    public MockRepository getIntrebariRepository() {
        return intrebariRepository;
    }

    public Test createNewTest() throws NotAbleToCreateTestException {

        if (intrebariRepository.getIntrebari().size() < 5)
            throw new NotAbleToCreateTestException("Nu exista suficiente intrebari pentru crearea unui test!(5)");

        if (intrebariRepository.getNumberOfDistinctDomains() < 5)
            throw new NotAbleToCreateTestException("Nu exista suficiente domenii pentru crearea unui test!(5)");

        List<Intrebare> testIntrebari = new ArrayList<>();
        List<String> domenii = new ArrayList<>();
        Intrebare intrebare;
        Test test = new Test();

        while (testIntrebari.size() != 5) {
            intrebare = intrebariRepository.pickRandomIntrebare();

            if (!testIntrebari.contains(intrebare)) {
                if (!domenii.contains(intrebare.getDomeniu())) {
                    testIntrebari.add(intrebare);
                    domenii.add(intrebare.getDomeniu());
                }
            }

        }
        test.setIntrebari(testIntrebari);
        return test;

    }
    public void addIntrebari() {
        try {
            this.addNewIntrebare(new Intrebare("Ct e 2+2?", "1)4", "2)5", "3)5", 2, "Matematica"));
            this.addNewIntrebare(new Intrebare("ttt e 3+2?", "1)5", "2)5", "3)5", 1, "Matematica"));
        } catch (InputValidationFailedException e) {
            e.printStackTrace();
        }
    }

    public void addIntrebariOk() {
        try {
            this.addNewIntrebare(new Intrebare("ttteew e 3+2?", "1)5", "2)5", "3)5", 1, "Alt alt domeniu"));
            this.addNewIntrebare(new Intrebare("DSFSttt e 3+2?", "1)5", "2)5", "3)5", 1, "Alt alt domeniu2"));
            this.addNewIntrebare(new Intrebare("Ct e 2+2e?", "1)4", "2)5", "3)5", 2, "Alt domeniu"));
        } catch (InputValidationFailedException e) {
            e.printStackTrace();
        }


    }

    public Statistica getStatistica() throws NotAbleToCreateStatisticsException {

        if (intrebariRepository.getIntrebari().isEmpty())
            throw new NotAbleToCreateStatisticsException("Repository-ul nu contine nici o intrebare!");

        Statistica statistica = new Statistica();
        for (String domeniu : intrebariRepository.getDistinctDomains()) {
            int numarDeIntrebariPerDomeniu = 0;
            for (Intrebare intr : intrebariRepository.getIntrebari()) {
                if (intr.getDomeniu().equals(domeniu))
                    numarDeIntrebariPerDomeniu++;
            }
            statistica.add(domeniu, numarDeIntrebariPerDomeniu);
        }

        return statistica;
    }

}
