package evaluator.repository;

import evaluator.exception.InputValidationFailedException;
import evaluator.model.Intrebare;
import evaluator.validator.IValidator;

import java.util.*;

public class MockRepository extends InMemoryRepository<Intrebare> {

    public MockRepository() {
        entities.add(new Intrebare("Blabla", "1)Bla", "2)Blabla", "3)Blablabla", 1, "Random"));
        entities.add(new Intrebare("Hei", "1)hei", "2)hei", "3)heihei", 2, "Random"));
        entities.add(new Intrebare("Cat e 1+1?", "1)1", "2)2", "3)3", 3, "Matematica"));
        entities.add(new Intrebare("Cat e 2+1?", "1)1", "2)2", "3)3", 3, "Matematica"));
        //list.add(new Intrebare("Cat e 3+1?", "1)1", "2)2", "3)4", 3, "Matematica"));
    }

    public Set<String> getDistinctDomains() {
        Set<String> domains = new TreeSet<String>();
        for (Intrebare intrebre : entities)
            domains.add(intrebre.getDomeniu());
        return domains;
    }

    public List<Intrebare> getIntrebariByDomain(String domain) {
        List<Intrebare> intrebariByDomain = new LinkedList<Intrebare>();
        for (Intrebare intrebare : entities) {
            if (intrebare.getDomeniu().equals(domain)) {
                intrebariByDomain.add(intrebare);
            }
        }

        return intrebariByDomain;
    }

    public int getNumberOfIntrebariByDomain(String domain) {
        return getIntrebariByDomain(domain).size();
    }

    public List<Intrebare> getIntrebari() {
        List<Intrebare> s = new ArrayList<>();
        Iterable<Intrebare> list = getAll();
        for (Intrebare t : list)
            s.add(t);
        return s;
    }


    public Intrebare pickRandomIntrebare() {
        Random random = new Random();
        return entities.get(random.nextInt(entities.size()));
    }

    public int getNumberOfDistinctDomains() {
        return getDistinctDomains().size();

    }
}
