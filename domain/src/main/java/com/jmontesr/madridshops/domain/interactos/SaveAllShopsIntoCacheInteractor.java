package com.jmontesr.madridshops.domain.interactos;

import com.jmontesr.madridshops.domain.model.Shops;

/**
 * Created by pepillo on 22/6/17.
 */

public interface SaveAllShopsIntoCacheInteractor {
    void execute(Shops shops, Runnable completion);

}
