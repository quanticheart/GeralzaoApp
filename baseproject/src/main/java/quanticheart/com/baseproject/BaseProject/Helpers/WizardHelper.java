package quanticheart.com.baseproject.BaseProject.Helpers;/*
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
 *  * Copyright(c) Developed by John Alves at 2018/11/7 at 8:54:2 for quantic heart studios
 *
 */


import android.app.Activity;

import com.master.killercode.wizard.Splash.Create.CreateSplash;
import com.master.killercode.wizard.Splash.Shared.GetSplashStatus;
import com.master.killercode.wizard.Wizard.WizardPageModel;

import java.util.ArrayList;
import quanticheart.com.baseproject.R;

/**
 * Created by Jonh on 16/08/2018.
 */

public class WizardHelper {

    private ArrayList<WizardPageModel> wList = new ArrayList<>(); // create array for Page Wizard

    public WizardHelper(Activity activity) {

        // init e create Pages for Wizard
        WizardPageModel page1 = new WizardPageModel("Hello!");
        wList.add(page1); // add page in arrayPages for Wizard

        WizardPageModel page2 = new WizardPageModel("Bem vindo", "O ZooBook espera por você!");
        wList.add(page2);

        GetSplashStatus splashStatus = new GetSplashStatus(activity);

        if (!splashStatus.getStatusDefault()) {
            new CreateSplash().Make(activity).setTextTitle(activity.getResources().getString(R.string.app_name)).setWizardList(wList).show();
        }

    }

}

//}
