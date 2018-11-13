/*
 * Copyright (c) Developed by John Alves at 2018/10/29.
 */

package maripoppis.com.connection.ConnectionUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;

import maripoppis.com.connection.Connect;
import maripoppis.com.connection.Model.WSResponse;
import maripoppis.com.connection.R;
public class LoadingUtils {

    private static Dialog dialog;
    @SuppressLint("StaticFieldLeak")
    private static LinearLayout ll_load;
    @SuppressLint("StaticFieldLeak")
    private static LinearLayout ll_erro;
    @SuppressLint("StaticFieldLeak")
    private static TextView tv_erro;
    @SuppressLint("StaticFieldLeak")
    private static Button btn_erro;
    @SuppressLint("StaticFieldLeak")
    private static ImageView btn_close;

    public static void showLoading(Activity activity) {

        if (dialog == null) {
            dialog = new Dialog(activity, R.style.DialogFullscreen);
            dialog.setContentView(R.layout.activity_loading);
            Objects.requireNonNull(dialog.getWindow()).getAttributes().windowAnimations = R.style.DialogNoAnimation;
            dialog.setCancelable(false);
        }

        ll_load = dialog.findViewById(R.id.ll_load);
        ll_erro = dialog.findViewById(R.id.ll_error);
        //
        tv_erro = dialog.findViewById(R.id.tv_error);
        //
        btn_erro = dialog.findViewById(R.id.btn_error);
        btn_close = dialog.findViewById(R.id.btn_close);

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeLoading();
            }
        });

        dialog.show();

        ll_load.setVisibility(View.VISIBLE);
    }

    //==============================================================================================
    //
    //
    //
    //==============================================================================================

    public static void closeLoading() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }


    public static void closeLoading(final Activity activity, WSResponse response, Connect.ConnectionType wsTypeConection) {

        ll_erro.setVisibility(View.VISIBLE);
        ll_load.setVisibility(View.GONE);

        switch (wsTypeConection) {
            case GET_JSON:
                tv_erro.setText("Erro ao conectar");
                btn_erro.setVisibility(View.GONE);
                break;
        }
    }


    public static void closeLoadingByStatus(final Activity activity, String msg, int status) {

        ll_erro.setVisibility(View.VISIBLE);
        ll_load.setVisibility(View.GONE);

        switch (status) {
            case -3:
                tv_erro.setText("Opa! Falta validar sua conta com seu número de celular!");
                btn_erro.setText("Validar numero de Celular");
                btn_erro.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        FacebookAcountKitUtils.smsAtivation(activity);
                        closeLoading();
                    }
                });
                break;
        }

    }


}
