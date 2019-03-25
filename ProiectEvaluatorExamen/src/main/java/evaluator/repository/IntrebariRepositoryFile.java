package evaluator.repository;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.model.Intrebare;
import evaluator.validator.IValidator;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;

public class IntrebariRepositoryFile extends InMemoryRepository<Intrebare> {
    private String fName;

    public IntrebariRepositoryFile(IValidator<Intrebare> v, String fName) {
        super(v);
        this.fName = fName;
        loadIntrebariFromFile();
    }

    @Override
    public Intrebare add(Intrebare entity) throws DuplicateIntrebareException, InputValidationFailedException {
        try {
            super.add(entity);
        } catch (Exception e) {
            if (e instanceof InputValidationFailedException) {
                throw (InputValidationFailedException) e;
            } else if (e instanceof DuplicateIntrebareException) {
                throw new DuplicateIntrebareException("Exista deja aceasta intrebare!");
            }
        }
        writeToFile(entity);
        return entity;
    }

    private void loadIntrebariFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(fName))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] intrebareDePrelucrat = line.split("#");
                if (intrebareDePrelucrat.length != 6)
                    throw new Exception("Linia nu este valida!");
                Intrebare t = new Intrebare(intrebareDePrelucrat[0], intrebareDePrelucrat[1], intrebareDePrelucrat[2],
                        intrebareDePrelucrat[3], Integer.parseInt(intrebareDePrelucrat[4]), intrebareDePrelucrat[5]);
                super.add(t);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeToFile(Intrebare x) {
        try (FileWriter fw = new FileWriter(fName, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(x.intrebareToLine());
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }


    public boolean exists(Intrebare i) {
        for (Intrebare intrebare : entities)
            if (intrebare.equals(i))
                return true;
        return false;
    }

    public Intrebare pickRandomIntrebare() {
        Random random = new Random();
        return entities.get(random.nextInt(entities.size()));
    }

    public int getNumberOfDistinctDomains() {
        return getDistinctDomains().size();

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

    public void setIntrebari(List<Intrebare> intrebari) {
        this.entities = intrebari;
    }

}
