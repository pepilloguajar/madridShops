package com.jmontesr.madridshops.domain.interactos;


import android.support.annotation.Nullable;

public interface GetAllShopsInteractor {

    public void execute(GetAllShopsInteractorCompletion completion, @Nullable final InteractorErrorCompletion onError);

}
