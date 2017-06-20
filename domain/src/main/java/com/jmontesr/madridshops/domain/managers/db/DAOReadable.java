package com.jmontesr.madridshops.domain.managers.db;


import android.support.annotation.Nullable;

import com.jmontesr.madridshops.domain.model.Shop;

import java.util.List;

public interface DAOReadable<T> {

    T query(final long id);
    List<T> query();
    @Nullable List<T> query(String where, String[] whereArgs, String orderBy);
    int numRecords();

}
