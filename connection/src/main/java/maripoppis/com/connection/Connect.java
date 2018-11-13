/*
 * Copyright (c) Developed by John Alves at 2018/10/29.
 */

package maripoppis.com.connection;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import maripoppis.com.connection.ConnectionUtils.ConnectUtils;
import maripoppis.com.connection.ConnectionUtils.LoadingUtils;
import maripoppis.com.connection.Model.WSResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static maripoppis.com.connection.ConnectionUtils.ConnectUtils.logFunction;

public class Connect {

    //Log Help
    private String connectDescription = "";
    private static ConnectCallback callback;

    //Contructor
    @SuppressLint("StaticFieldLeak")
    private static Activity activity;
    private static ConnectionType connectionTypeID;
    private static WSResponse dados = null;

    //Retrofit
    private Api apiInterface;
    private Call<WSResponse> call = null;

    /**
     * @param mActivity call WS
     */
    public Connect(Activity mActivity) {
        activity = mActivity;
        apiInterface = RestClient.getClient(activity).create(Api.class);
    }

    public enum ConnectionType {
        GET_JSON
    }

    /**
     * @param connectionTypeID connection enum for
     * @param showLoading      show Load?
     */
    public void getDataFrom(ConnectionType connectionTypeID, Boolean showLoading) {
        Connect.dados = null;
        Connect.dados = dados;
        if (showLoading) {
//            ConnectUtils.showSimpleLoadingDialog(activity);
        }
        initWsConection(connectionTypeID);
    }

    private void initWsConection(ConnectionType ID) {

        connectionTypeID = ID;
        LoadingUtils.showLoading(activity);

        switch (ID) {
            case GET_JSON:
                call = apiInterface.getData();
                break;
        }

        call.enqueue(new Callback<WSResponse>() {
            @Override
            public void onResponse(@NonNull Call<WSResponse> call, @NonNull Response<WSResponse> response) {

                if (response.isSuccessful()) {
                    callback.ConnectionSuccess(response.body(), connectionTypeID);
                    LoadingUtils.closeLoading();
                } else if (response.code() >= 400) {
                    WSResponse wsResponse = getErrorBody(Objects.requireNonNull(response.errorBody()));
                    ConnectUtils.logW("Status", String.valueOf(response.code()));
                    callback.ConnectionError(STATUS_FAIL, connectionTypeID);
                    LoadingUtils.closeLoading(activity, wsResponse, ConnectionType.GET_JSON);
                }
                // ConnectUtils.hideSimpleLoadingDialog();
            }

            @Override
            public void onFailure(@NonNull Call<WSResponse> call, @NonNull Throwable t) {
                logFunction(t, connectDescription);
                callback.ConnectionFail(t);
//                ConnectUtils.hideSimpleLoadingDialog();
            }
        });

    }

    //Utils
    // =============================================================================================

    /**
     * @param responseBody error body for Gson
     * @return WSResponse
     */
    private WSResponse getErrorBody(ResponseBody responseBody) {
        Gson gson = new GsonBuilder().create();
        try {
            return gson.fromJson(responseBody.string(), WSResponse.class);
        } catch (IOException e) {
            return new WSResponse();
        }
    }

    //Interface
    // =============================================================================================

    //Status Return WS
    public static int STATUS_FAIL = 0;
    public static int STATUS_OK = 1;

    public static void setCallback(ConnectCallback mCallback) {
        callback = mCallback;
    }

    public interface ConnectCallback {

        void ConnectionSuccess(WSResponse response, ConnectionType connectionTypeID);

        void ConnectionError(int statusType, ConnectionType connectionTypeID);

        void ConnectionFail(Throwable t);

    }

}
