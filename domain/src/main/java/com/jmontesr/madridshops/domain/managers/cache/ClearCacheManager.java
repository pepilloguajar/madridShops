package com.jmontesr.madridshops.domain.managers.cache;

/**
 * Created by pepillo on 22/6/17.
 */

public interface ClearCacheManager {
    void execute(final Runnable completion);

}
