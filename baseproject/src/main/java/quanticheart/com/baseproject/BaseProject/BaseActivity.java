/*
 *
 *  *                                     /@
 *  *                      __        __   /\/
 *  *                     /==\      /  \_/\/
 *  *                   /======\    \/\__ \__
 *  *                 /==/\  /\==\    /\_|__ \
 *  *              /==/    ||    \=\ / / / /_/
 *  *            /=/    /\ || /\   \=\/ /
 *  *         /===/   /   \||/   \   \===\
 *  *       /===/   /_________________ \===\
 *  *    /====/   / |                /  \====\
 *  *  /====/   /   |  _________    /      \===\
 *  *  /==/   /     | /   /  \ / / /         /===/
 *  * |===| /       |/   /____/ / /         /===/
 *  *  \==\             /\   / / /          /===/
 *  *  \===\__    \    /  \ / / /   /      /===/   ____                    __  _         __  __                __
 *  *    \==\ \    \\ /____/   /_\ //     /===/   / __ \__  ______  ____ _/ /_(_)____   / / / /__  ____ ______/ /_
 *  *    \===\ \   \\\\\\\/   ///////     /===/  / / / / / / / __ \/ __ `/ __/ / ___/  / /_/ / _ \/ __ `/ ___/ __/
 *  *      \==\/     \\\\/ / //////       /==/  / /_/ / /_/ / / / / /_/ / /_/ / /__   / __  /  __/ /_/ / /  / /_
 *  *      \==\     _ \\/ / /////        |==/   \___\_\__,_/_/ /_/\__,_/\__/_/\___/  /_/ /_/\___/\__,_/_/   \__/
 *  *        \==\  / \ / / ///          /===/
 *  *        \==\ /   / / /________/    /==/
 *  *          \==\  /               | /==/
 *  *          \=\  /________________|/=/
 *  *            \==\     _____     /==/
 *  *           / \===\   \   /   /===/
 *  *          / / /\===\  \_/  /===/
 *  *         / / /   \====\ /====/
 *  *        / / /      \===|===/
 *  *        |/_/         \===/
 *  *                       =
 *  *
 *  * Copyright(c) Developed by John Alves at 2018/11/7 at 8:53:5 for quantic heart studios
 *
 */

package quanticheart.com.baseproject.BaseProject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Objects;
import quanticheart.com.baseproject.BaseProject.BroadCast.MyReceiver;
import quanticheart.com.baseproject.BaseProject.BroadCast.SystemUtil;
import quanticheart.com.baseproject.BaseProject.Helpers.WizardHelper;
import quanticheart.com.baseproject.R;
import quanticheart.com.baseproject.Utils.GlideUtil;

public abstract class BaseActivity extends AppCompatActivity implements SystemUtil.ConnectionVerify {

    //init
    public Activity activity;

    //For mensagens
    private static Snackbar snackbar = null;

    //add container view
    private FrameLayout container;

    //SwipeRefreshLayout
    private SwipeRefreshLayout refresh;

    //==============================================================================================
    //
    // onCreate
    //
    //==============================================================================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baseproject);

        initVars();
        initActions();

    }

    //==============================================================================================
    //
    // Set Container in FrameLayout
    //
    //==============================================================================================

    public void setBaseContantView(int layout) {
        View view = getLayoutInflater().inflate(layout, null);
        container.addView(view);
    }

    //==============================================================================================
    //
    // Init Methods
    //
    //==============================================================================================

    private void initVars() {

        //init
        activity = BaseActivity.this;

        //add container view
        container = findViewById(R.id.container);

        //Toolbar
        toolbarContainer = findViewById(R.id.toolbarContainer);

        //SwipeRefreshLayout
        refresh = findViewById(R.id.refresh);
        blockRefreshLayout();

    }

    private void initActions() {

    }

    //==============================================================================================
    //
    // init Wizard
    //
    //==============================================================================================

    public void setWizard(Class classAfterWizard) {
        new WizardHelper(activity);
    }

    //==============================================================================================
    //
    // Toolbar Setup
    //
    //==============================================================================================

    private ConstraintLayout toolbarContainer;
    private Toolbar toolbar;
    private ImageView toolbarImg;
    private TextView toolbarText;

    public void setToolbar(String titleToolbar) {
        setupActionBar();
        setToolbarText(titleToolbar);
    }

    public void setToolbarImage(int drawableForToolbar) {
        setupActionBar();
        GlideUtil.initGlide(activity, drawableForToolbar, toolbarImg);
    }

    public void notAnimatorLayout() {
        if (toolbarContainer != null) {
            AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbarContainer.getLayoutParams();
            params.setScrollFlags(0);
        }
    }

    private void setToolbarText(String textForToolbar) {
        toolbarText.setText(textForToolbar);
    }

    private void setupActionBar() {
        //ToolBar
        toolbar = findViewById(R.id.main_toolbar);
        toolbarImg = findViewById(R.id.toolbarImg);
        toolbarText = findViewById(R.id.toolbarText);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
    }

    //==============================================================================================
    //
    // SwipeRefreshLayout
    //
    //==============================================================================================

    @SuppressLint("ClickableViewAccessibility")
    public void setRefreshLayout() {
        unlockRefreshLayout();

        refresh.setColorSchemeColors(getColor(R.color.colorPrimary));
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mBaseRefreshInterface != null) {
                    mBaseRefreshInterface.refreshInterface();
                }
            }
        });
    }

    //Interface
    //==============================================================================================
    private static BaseRefreshInterface mBaseRefreshInterface;

    public static void setBaseRefreshInterface(BaseRefreshInterface baseRefreshInterface) {
        mBaseRefreshInterface = baseRefreshInterface;
    }

    public interface BaseRefreshInterface {
        void refreshInterface();
    }

    //Utils
    //==============================================================================================

    public void blockRefreshLayout() {
        if (refresh != null) {
            refresh.setEnabled(false);
        }
    }

    public void unlockRefreshLayout() {
        if (refresh != null) {
            refresh.setEnabled(true);
        }
    }

    public void showRefresh() {
        if (refresh != null) {
            refresh.setRefreshing(true);
        }
    }

    public void hideRefresh() {
        if (refresh != null) {
            refresh.setRefreshing(false);
        }
    }


    //==============================================================================================
    //
    // SnackBar
    //
    //==============================================================================================

    public void showSnackBar(String text) {
        snackbar = Snackbar.make(container, text, Snackbar.LENGTH_LONG);
        snackbar.show();
    }


    //==============================================================================================
    //
    // @OnBackPressed
    //
    //==============================================================================================

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //==============================================================================================
    //
    // Systems Verifications
    //
    //==============================================================================================

    private MyReceiver connectionReceiver;
    public static boolean connected = false;
    public static boolean showSnackbar = true;

    private void connectionReceiverRegister() {

        new SystemUtil(activity);
        SystemUtil.setConnectionVerify(this);

        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        connectionReceiver = new MyReceiver();
        registerReceiver(connectionReceiver, filter);

    }

    //==============================================================================================
    //
    // @Override connection
    //
    //==============================================================================================

    @Override
    public void ConnectionOK() {
        connected = true;
        if (!verifySnackbar()) {
            clearSnackbar();
        }
        callConectionInterface(connected);
    }


    @Override
    public void ConnectionFail() {
        connected = false;
        if (verifySnackbar() && showSnackbar) {
            showSnackbar();
        }
        callConectionInterface(connected);
    }

    //Interface
    //==============================================================================================

    private void callConectionInterface(boolean connected) {
        if (conectionCallback != null) {
            conectionCallback.ConectionStatus(connected);
        }
    }

    public static void setBaseConectionStatusCallback(BaseConectionStatusCallback baseConectionStatusCallback) {
        conectionCallback = baseConectionStatusCallback;
    }

    private static BaseConectionStatusCallback conectionCallback;

    public interface BaseConectionStatusCallback {
        void ConectionStatus(boolean statusConection);
    }

    //Utils
    //==============================================================================================

    private void showSnackbar() {
        snackbar = Snackbar.make(Objects.requireNonNull(activity.getCurrentFocus()), R.string.msg_no_connection, Snackbar.LENGTH_INDEFINITE);
        snackbar.show();
    }

    private void clearSnackbar() {
        snackbar.dismiss();
        snackbar = null;
    }

    private boolean verifySnackbar() {
        return snackbar == null;
    }

    public void dontShowSnackbarConnection() {
        showSnackbar = false;
    }

    //==============================================================================================
    //
    // @Override Life Cycle Activity
    //
    //==============================================================================================

    @Override
    protected void onPause() {
        super.onPause();
        if (connectionReceiver != null) {
            unregisterReceiver(connectionReceiver);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        connectionReceiverRegister();
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}

