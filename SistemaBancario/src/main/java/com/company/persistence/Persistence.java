/*
Igor Rocha Matos - 202335041
João Paulo Macedo Fernandes - 202335009
Pedro Muniz Fagundes Netto Lau - 202376029
*/
package com.company.persistence;

import java.util.List;

public interface Persistence<T> {

    String DIRECTORY = "data";
    public void save(List<T> itens);
    public List<T> findAll();
    public List<T> load();
    public void add(T item);
}