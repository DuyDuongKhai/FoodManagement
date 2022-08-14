/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import static java.time.Year.isLeap;
import java.util.Calendar;
import java.util.Scanner;
import java.util.StringTokenizer;


/**
 *
 *
 */
public class Validate {

    public static int Inputnum() throws Exception {
        int a = -1;
        Scanner p = new Scanner(System.in);
        int f = 0;
        do {
            try {
                //System.out.println("Input a number: ");
                a = p.nextInt();
                f = 1;
            } catch (Exception e) {
                System.out.print("Input again: ");
                p.nextLine();

            }
        } while (f != 1);
        return a;
    }

    public static String inputString() throws Exception {
        String s = "";
        int f = 0;
        Scanner in = new Scanner(System.in);
        do {
            try {
                // System.out.print(" ");
                s = in.nextLine();
                if (s.equals("")) {
                    throw new Exception("Information must not empty");
                }
                f = 1;
            } catch (Exception e) {
                System.out.println("Input again: ");
                in.nextLine();
            }
        } while (f != 1);
        return s;
    }

    public static void main(String[] arr) throws Exception {
        String s;
        s = Validate.inputString();
        int a;
        a = Validate.Inputnum();
    }

    public static String InputString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static long toDate(String ymd) {
        StringTokenizer stk = new StringTokenizer(ymd, "/-");
        int y = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        int d = Integer.parseInt(stk.nextToken());
        if (!valid(y, m, d)) {
            return -1;
        }
        if (!checkDate(ymd)) {
            return -1;
        }
        Calendar cal = Calendar.getInstance();
        cal.set(y, m - 1, d);
        long t = cal.getTime().getTime();
        return t;
    }

    public static boolean valid(int y, int m, int d) {
        if (y < 0 || m < 0 || m > 12 || d < 0 || d > 31) {
            return false;
        }
        int maxD = 31;
        if (m == 4 || m == 6 || m == 9 || m == 11) {
            maxD = 30;
        } else if (m == 2) {
            if (isLeap(y)) {
                maxD = 29;
            } else {
                maxD = 28;
            }
        }
        return d <= maxD;
    }
     public static boolean checkDate(String s) {
        if (s.matches("^\\d{4}[-/]\\d{1,2}[-/]\\d{1,2}")) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean isLeap(int y) {
        boolean result = false;
        if ((y % 400 == 0) || ((y % 4 == 0) && (y % 100 != 0))) {
            result = true;
        }
        return result;
    }
    public static boolean checkUserChoice(String userChoice){
        String format = "[0,1,2,3,4,5]";
        return userChoice.matches(format);  
    }
    public static boolean checkID(String id) {
        String format = "F\\d{3}$";
         return id.matches(format);
    }

}
