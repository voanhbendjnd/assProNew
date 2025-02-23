/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SetupFile;

/**
 *
 * @author Vo Anh Ben - CE190709
 */
import java.io.File;

public class CreateFile {
    public static void main(String[] args) {
        try {
            File myFile = new File("orderUser.txt");
            if (myFile.createNewFile()) {
                System.out.println("File with " + myFile.getName());
            } else {
                System.out.println("File already exist");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}