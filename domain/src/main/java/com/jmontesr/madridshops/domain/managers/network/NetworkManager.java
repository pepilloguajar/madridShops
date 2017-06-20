package com.jmontesr.madridshops.domain.managers.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by pepillo on 19/6/17.
 */

public interface NetworkManager {

    void getShopsFromServer(@NonNull final GetAllShopsManagerCompletion completion, @Nullable final ManagerErrorCompletion errorCompletion);

}
