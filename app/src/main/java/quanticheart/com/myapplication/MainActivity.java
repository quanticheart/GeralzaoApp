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
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import maripoppis.com.connection.Connect;
import maripoppis.com.connection.Model.Object;
import maripoppis.com.connection.Model.Player;
import maripoppis.com.connection.Model.WSResponse;
import quanticheart.com.baseproject.BaseProject.BaseActivity;
import quanticheart.com.baseproject.Utils.GlideUtil;

public class MainActivity extends BaseActivity implements Connect.ConnectCallback,
        BaseActivity.BaseConectionStatusCallback, BaseActivity.BaseRefreshInterface {

    //LinearLayout
    @BindView(R.id.containerManagerLayout)
    LinearLayout containerManagerLayout;

    //ImageView
    @BindView(R.id.soccerImg)
    CircleImageView soccerImg;

    //TextView
    @BindView(R.id.soccerName)
    TextView soccerName;

    @BindView(R.id.soccerCountry)
    TextView soccerCountry;

    @BindView(R.id.soccerPosition)
    TextView soccerPosition;

    @BindView(R.id.soccerPoints)
    TextView soccerPoints;

    @BindView(R.id.soccerRatingWinnerPosition)
    TextView soccerRatingWinnerPosition;

    @BindView(R.id.soccerRatingDisputedPosition)
    TextView soccerRatingDisputedPosition;

    //RatingBar
    @BindView(R.id.soccerRatingWinner)
    RatingBar soccerRatingWinner;

    @BindView(R.id.soccerRatingDisputed)
    RatingBar soccerRatingDisputed;

    //Connection
    private Connect connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBaseContantView(R.layout.activity_main);

        ButterKnife.bind(this);

        initInterface();
        initVars();
        initActions();
        initConnection();

    }

    private void initInterface() {
        BaseActivity.setBaseConectionStatusCallback(this);
        BaseActivity.setBaseRefreshInterface(this);

    }

    private void initVars() {
        soccerRatingDisputed.setEnabled(false);
        soccerRatingWinner.setEnabled(false);
    }

    private void initActions() {
        setToolbar(getString(R.string.app_name));
        setRefreshLayout();
    }

    //==============================================================================================
    //
    // Conection's App
    //
    //==============================================================================================

    private void initConnection() {
        Connect.setCallback(this);
        connect = new Connect(activity);
        getConnection();
    }

    private void getConnection() {
        connect.getDataFrom(Connect.ConnectionType.GET_JSON, false);
    }

    private void setDataInXml(WSResponse response) {

        Player player = response.getObject().get(0).getPlayer();
        containerManagerLayout.setVisibility(View.VISIBLE);

        //Image
        GlideUtil.initGlide(activity, player.getImg(), soccerImg);

        //text
        soccerName.setText(player.getName());
        soccerCountry.setText(player.getCountry());
        soccerPosition.setText(player.getPos());
        soccerPoints.setText(String.valueOf(player.getPercentual()));

        //RatingBar
        soccerRatingWinner.setMax(player.getBarras().getCopasDoMundoVencidas().getMax().intValue());
        soccerRatingWinner.setRating(player.getBarras().getCopasDoMundoVencidas().getPla().floatValue());
        soccerRatingWinnerPosition.setText(ProjectUtils.getPosition(player.getBarras().getCopasDoMundoVencidas().getPos()));
//
        soccerRatingDisputed.setMax(player.getBarras().getCopasDoMundoDisputadas().getMax().intValue());
        soccerRatingDisputed.setRating(player.getBarras().getCopasDoMundoDisputadas().getPla().floatValue());
        soccerRatingDisputedPosition.setText(ProjectUtils.getPosition(player.getBarras().getCopasDoMundoDisputadas().getPos()));

    }

    private void cleanContainer() {
        containerManagerLayout.setVisibility(View.GONE);
    }

    //==============================================================================================
    //
    // Conection's Interface
    //
    //==============================================================================================

    @Override
    public void ConnectionSuccess(WSResponse response, Connect.ConnectionType connectionTypeID) {

        switch (connectionTypeID) {
            case GET_JSON:
                setDataInXml(response);
                break;
        }
    }

    @Override
    public void ConnectionError(int statusType, Connect.ConnectionType connectionTypeID) {
        cleanContainer();
        if (statusType == Connect.STATUS_FAIL) {
            showSnackBar("Falha ao Conectar");
        }

    }

    @Override
    public void ConnectionFail(Throwable t) {
        showSnackBar(t.getMessage());
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
