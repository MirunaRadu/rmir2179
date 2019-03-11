package evaluator.repository;

import evaluator.validator.IValidator;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository<E> implements IRepository<E> {

    protected List<E> entities = new ArrayList<>();
    protected IValidator<E> vali;

    public InMemoryRepository(IValidator<E> v) {
        vali = v;
    }

    @Override
    public E add(E entity) throws Exception {
        vali.validate(entity);
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
