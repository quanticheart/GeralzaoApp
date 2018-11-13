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
 *  * Copyright(c) Developed by John Alves at 2018/$today.mouth/13 at 1:32:30 for quantic heart studios
 *
 */

package quanticheart.com.myapplication;

import android.os.Bundle;
import android.util.Log;

import maripoppis.com.connection.Connect;
import maripoppis.com.connection.Model.WSResponse;
import quanticheart.com.baseproject.BaseProject.BaseActivity;

public class MainActivity extends BaseActivity implements Connect.ConnectCallback,
        BaseActivity.BaseConectionStatusCallback, BaseActivity.BaseRefreshInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBaseContantView(R.layout.activity_main);

        initInterface();
        initVars();
        initActions();

    }

    private void initInterface() {
        BaseActivity.setBaseConectionStatusCallback(this);
        BaseActivity.setBaseRefreshInterface(this);
    }

    private void initVars() {

    }

    private void initActions() {
        setToolbar(getString(R.string.app_name));
        setRefreshLayout();
    }

    //==============================================================================================
    //
    // Conection's Interface
    //
    //==============================================================================================

    @Override
    public void ConnectionSuccess(WSResponse response, Connect.ConnectionType connectionTypeID) {
        Log.w("TESTE", String.valueOf(response.getStatus()));
    }

    @Override
    public void ConnectionError(int statusType, Connect.ConnectionType connectionTypeID) {

    }

    @Override
    public void ConnectionFail(Throwable t) {

    }

    //==============================================================================================
    //
    // Conection's Status
    //
    //==============================================================================================

    @Override
    public void ConectionStatus(boolean statusConection) {

    }

    //==============================================================================================
    //
    // Refresh's Page Function
    //
    //==============================================================================================

    @Override
    public void refreshInterface() {
        hideRefresh();
    }
}
