/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Tester;

import com.google.gson.Gson;
import com.quanlytaphoa.Model.Account;
import static java.io.File.separator;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // thêm account vào Account.json
        
        Scanner sc = new Scanner(System.in);
        Account[] ac;
        ArrayList<Account> list;
        String filePath;
        filePath = "D:" + separator + "Learning Java" + separator + "QuanLyTapHoa_DoAnJava09" + separator + "Manage Files" + separator + "Account.json";
        FileWriter fw = null;
        try {
            ac = new Account[50];
            list = new ArrayList<>();
            for (int i=0; i<4; i++)
            {
                ac[i] = new Account();
                ac[i].setUser(sc.next());
                ac[i].setPassword(sc.next());
                list.add(ac[i]);
            }
            fw = new FileWriter(filePath);
            Gson gson = new Gson();
            gson.toJson(list, fw);
        } catch (IOException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fw.flush();
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
