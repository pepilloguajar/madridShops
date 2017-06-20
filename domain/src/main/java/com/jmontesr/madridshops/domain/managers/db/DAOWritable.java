package com.jmontesr.madridshops.domain.managers.db;

import android.support.annotation.NonNull;

import com.jmontesr.madridshops.domain.model.Shop;

/**
 * Created by pepillo on 13/6/17.
 */


// DAO: Data Access Object
public interface DAOWritable<T> {
    long insert(@NonNull final T element);
    long update(final long id, final T element);
    long delete(final long id);
    long delete(T element);
    void deleteAll();
    long delete(String where, String... whereClause);

}
