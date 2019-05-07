package evaluator.repository;

import evaluator.validator.IValidator;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository<E> implements IRepository<E> {

    protected List<E> entities = new ArrayList<>();

    public InMemoryRepository() {
    }

    @Override
    public E add(E entity) throws Exception {
        for (E ent : entities) {
            if (entity.equals(ent))
                throw new Exception("Mai exista deja o entitate!");
        }
        entities.add(entity);
        return null;
    }


    @Override
    public Iterable<E> getAll() {
        return entities;
    }

}
