package com.aral.utility;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public class ServiceManager<T extends BaseEntity, ID> implements IService<T,ID> {
    private final MongoRepository<T,ID> repository;
    public ServiceManager(MongoRepository<T,ID> mongoRepository){
        this.repository = mongoRepository;
    }
    @Override
    public T save(T t) {
        t.setCreatedate(System.currentTimeMillis());
        t.setUpdatedate(System.currentTimeMillis());
        t.setIsactive(true);
        return repository.save(t);
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> t) {
        return repository.saveAll(t);
    }

    @Override
    public T update(T t) {
        return repository.save(t);
    }

    @Override
    public void delete(T t) {
        repository.delete(t);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }
}
