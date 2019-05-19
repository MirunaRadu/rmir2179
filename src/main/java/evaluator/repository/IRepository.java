package evaluator.repository;

public interface IRepository<E> {
    /**
     * Saves the given entity
     *
     * @param entity
     * @return null if the entity was saved, otherwise (id already exists) returns the entity.
     * @throws Exception if entity is not valid
     */
    E add(E entity) throws Exception;

    /**
     * @return all entities as an iterable collection
     */
    Iterable<E> getAll();

}
