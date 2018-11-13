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
 *  * Copyright(c) Developed by John Alves at 2018/11/7 at 3:43:36 for quantic heart studios
 *
 */

package quanticheart.com.baseproject.Utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

import quanticheart.com.baseproject.R;

public class GlideUtil {

    public static void initGlide(Activity activity, String url, ImageView imageView) {
        Glide.with(activity).applyDefaultRequestOptions(getRequestOptions()).load(url).into(imageView);
    }

    public static void initGlide(Activity activity, Uri url, ImageView imageView) {
        Glide.with(activity).applyDefaultRequestOptions(getRequestOptions()).load(url).into(imageView);
    }

    public static void initGlide(Activity activity, File url, ImageView imageView) {
        Glide.with(activity).applyDefaultRequestOptions(getRequestOptions()).load(url).into(imageView);
    }

    public static void initGlide(Activity activity, Byte[] url, ImageView imageView) {
        Glide.with(activity).applyDefaultRequestOptions(getRequestOptions()).load(url).into(imageView);
    }

    public static void initGlide(Activity activity, Drawable url, ImageView imageView) {
        Glide.with(activity).applyDefaultRequestOptions(getRequestOptions()).load(url).into(imageView);
    }

    public static void initGlide(Activity activity, Object url, ImageView imageView) {
        Glide.with(activity).applyDefaultRequestOptions(getRequestOptions()).load(url).into(imageView);
    }

    public static void initGlide(Activity activity, Bitmap url, ImageView imageView) {
        Glide.with(activity).applyDefaultRequestOptions(getRequestOptions()).load(url).into(imageView);
    }

    public static void initGlide(Activity activity, Integer url, ImageView imageView) {
        Glide.with(activity).applyDefaultRequestOptions(getRequestOptions()).load(url).into(imageView);
    }

    private static RequestOptions getRequestOptions() {
        return new RequestOptions().centerCrop();
    }
}
