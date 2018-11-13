
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
 *  * Copyright(c) Developed by John Alves at 2018/$today.mouth/13 at 2:32:23 for quantic heart studios
 *
 */

package maripoppis.com.connection.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Barras {

    @SerializedName("Copas_do_Mundo_Vencidas")
    @Expose
    private CopasDoMundoVencidas copasDoMundoVencidas;
    @SerializedName("Copas_do_Mundo_Disputadas")
    @Expose
    private CopasDoMundoDisputadas copasDoMundoDisputadas;

    public CopasDoMundoVencidas getCopasDoMundoVencidas() {
        return copasDoMundoVencidas;
    }

    public void setCopasDoMundoVencidas(CopasDoMundoVencidas copasDoMundoVencidas) {
        this.copasDoMundoVencidas = copasDoMundoVencidas;
    }

    public CopasDoMundoDisputadas getCopasDoMundoDisputadas() {
        return copasDoMundoDisputadas;
    }

    public void setCopasDoMundoDisputadas(CopasDoMundoDisputadas copasDoMundoDisputadas) {
        this.copasDoMundoDisputadas = copasDoMundoDisputadas;
    }

}
