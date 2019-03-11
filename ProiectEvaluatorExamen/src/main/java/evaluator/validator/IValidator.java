package evaluator.validator;

public interface IValidator<T> {
    void validate(T entity) throws Exception;
}


