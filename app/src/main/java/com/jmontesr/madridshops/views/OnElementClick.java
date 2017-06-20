package com.jmontesr.madridshops.views;

import android.support.annotation.NonNull;

/**
 * Created by pepillo on 16/6/17.
 */

public interface OnElementClick<T> {

    void clickedOn(@NonNull T element, int position);

}
