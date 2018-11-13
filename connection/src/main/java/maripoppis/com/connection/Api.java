/*
 * Copyright (c) Developed by John Alves at 2018/10/29.
 */

package maripoppis.com.connection;

import maripoppis.com.connection.Model.WSResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("teste.json")
    Call<WSResponse> getData();

}

