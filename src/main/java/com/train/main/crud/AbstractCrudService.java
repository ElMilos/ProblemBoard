package com.train.main.crud;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.cglib.core.internal.Function;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractCrudService<T, ID> {
    protected abstract JpaRepository<T, ID> repo();
    protected void validateBeforeCreate(T t) {}
    protected void validateBeforeUpdate(ID id, T t) {}

    public T create(T t){ validateBeforeCreate(t); return repo().save(t); }
    public T update(ID id, Function<T, T> updater){
        var cur = repo().findById(id).orElseThrow(() -> new EntityNotFoundException());
        var mod = updater.apply(cur);
        validateBeforeUpdate(id, mod);
        return repo().save(mod);
    }
    public T get(ID id){ return repo().findById(id).orElseThrow(EntityNotFoundException::new); }
}
