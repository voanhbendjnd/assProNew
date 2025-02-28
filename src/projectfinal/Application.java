/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectfinal;

/**
 *
 * @author Vo Anh Ben - CE190709
 */
import Utils.Intro;
import VIew.CreateAccount;
import VIew.Menu;

/**
 *
 * @author Vo Anh Ben - CE190709
 */
public class Application {
    public static void main(String[] args) throws InterruptedException {
        // new Intro().IntroWelCome();
        // new Intro().IntroTo();
        // new Intro().IntroGROUP3();
        new Menu().getMenuNotLogin();

    }
}
