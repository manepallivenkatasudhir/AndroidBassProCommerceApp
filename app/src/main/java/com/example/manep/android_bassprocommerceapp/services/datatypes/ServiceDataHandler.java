package com.example.manep.android_bassprocommerceapp.services.datatypes;

/**
 * Created by jgreve on 2/23/18.
 */

public interface ServiceDataHandler<T extends BasicData> {

    public void handleResponse(T data);
}
