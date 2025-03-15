/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import display.Menu;
import utils.Initialize;
import utils.Intro;

/**
 *
 * @author Vo Anh Ben - CE190709
 */
public class Application {
    public static void main(String[] args) throws InterruptedException {
        Intro.MovieOpening();
        new Initialize().init();
        new Menu().getMenuNotLogin();

    }
}
