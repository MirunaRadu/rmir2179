package evaluator.controller;

import java.util.ArrayList;
import java.util.List;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.model.Intrebare;
import evaluator.model.Statistica;
import evaluator.model.Test;
import evaluator.repository.IntrebariRepositoryFile;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.validator.IntrebareValidation;

public class IntrebariController {

    private IntrebariRepositoryFile intrebariRepository;

    public IntrebariController() {
        intrebariRepository = new IntrebariRepositoryFile(new IntrebareValidation(), "src/main/resources/intrebari.txt");
    }

    public Intrebare addNewIntrebare(Intrebare intrebare) throws DuplicateIntrebareException {

        intrebariRepository.add(intrebare);

        return intrebare;
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

            if (!testIntrebari.contains(intrebare) && !domenii.contains(intrebare.getDomeniu())) {
                testIntrebari.add(intrebare);
                domenii.add(intrebare.getDomeniu());
            }

        }

        test.setIntrebari(testIntrebari);
        return test;

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
