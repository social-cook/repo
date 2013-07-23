package com.socialcooking.repository.api;

import com.socialcooking.domain.Provider;

import java.util.List;

public interface ProviderRepository extends IGenericRepository<Provider> {

    public Provider findById(Long id);

    public List<Provider> findAll();

    public Provider save(Provider provider);

    public Provider update(Provider provider);

    public void delete(Provider provider);
}
