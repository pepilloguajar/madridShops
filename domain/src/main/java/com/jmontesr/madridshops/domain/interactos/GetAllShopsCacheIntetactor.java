package com.jmontesr.madridshops.domain.interactos;

/**
 * Created by pepillo on 20/6/17.
 */

public interface GetAllShopsCacheIntetactor
{
    void execute(Runnable onAllShopsAreCached, Runnable onAllShopsAreNotCached);
}
