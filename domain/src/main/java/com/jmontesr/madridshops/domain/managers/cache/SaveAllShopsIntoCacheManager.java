package com.jmontesr.madridshops.domain.managers.cache;

import com.jmontesr.madridshops.domain.model.Shops;

/**
 * Created by pepillo on 22/6/17.
 */

public interface SaveAllShopsIntoCacheManager {
    void execute(Shops shops, Runnable completion);

}
