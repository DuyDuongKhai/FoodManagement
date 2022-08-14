/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DTO.Food;
import Manager.Manager;
import java.text.ParseException;
import java.util.Scanner;
/**
 *
 *
 */
public class Tester {

    public static void main(String[] args) throws ParseException {
        int choice = 0;
        boolean flag;
        Scanner sc;
        Manager list = new Manager();
        int count = 0;
        String options = "";
        String name = "";
        Food food = new Food();
        
        do {
            System.out.println("__________________________________________________________________________");
            System.out.println("*   Welcome to Food Management - @2021 by <SE150425 - Duong Khai Duy>    *");
            System.out.println("*   Select the options below:                                            *");
            System.out.println("*   1. Add a new food.                                                   *");
            System.out.println("*   2. Search a food by name.                                            *");
            System.out.println("*   3. Search a food by date.                                            *");
            System.out.println("*   4. Remove the food by ID.                                            *");
            System.out.println("*   5. Print the food list in the descending order of expried date.      *");
            System.out.println("*   6. Quit.                                                             *");
            System.out.println("__________________________________________________________________________");
            System.out.println("Input your choice: ");
            do {
                try {
                    sc = new Scanner(System.in);
                    choice = sc.nextInt();
                    flag = false;
                    if (choice < 0 || choice > 6) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println("Invalid! Please enter again: ");
                    flag = true;
                }
            } while (flag);
            switch (choice) {
                case 1:
                    list.AddFood(food);
                    count++;
                    break;

                case 2:
                    list.searchByName(name);
                    count++;
                    break;
                    case 3:
                    if (count == 0) {
                        System.out.println("No information. Please enter the Food.");
                    } else {
                        do {
                            String date = "";
//                                 if(food.getName().replace("\\s","").trim().contains(name.replace("\\s","").trim())) {
                            do {
                                System.out.println("Enter Food Date To Search: ");
                                sc = new Scanner(System.in);
                                date = sc.nextLine();
                            } while (date.isBlank());
                            if (list.countFoodSameDate(date) == 0) {
                                System.out.println(date + " Not Found!");
                            } else {
                                list.searchByDate(date);
                            }
                            do {
                                sc = new Scanner(System.in);
                                System.out.println("Do You Want To Search Another Food? (Y/N)");
                                options = sc.nextLine().toLowerCase();
                                if (options.equalsIgnoreCase("Y") || options.equalsIgnoreCase("N")) {
                                    break;
                                }
                            } while (true);
                        } while (options.equalsIgnoreCase("Y"));
                    }
                    break;


        
//                case 4:
//                    
//                    
//                    if (count == 0) {
//                        System.out.println("No information. Please enter the Food.");
//                    } else {
//                        String id = "";
//                        do {
//                            System.out.println("Enter Food Id To Remove: ");
//                            sc = new Scanner(System.in);
//                            id = sc.nextLine().toUpperCase();
//                        } while (id.isEmpty() || id.equals(""));
////                        Food food = list.Findid(id);
//                        Food food = list.Findid(id);
//                        
//                        if (food == null) {
//                            System.out.println(id + " Not Found!");
//                        } else {
//                            System.out.println(food.toString());
//                        }
//
//                        list.removeFood(id);
//                    }
//                    break;
                case 5:
                    if (count == 0) {
                        System.out.println("No information. Please enter the Food.");
                    } else {
                        list.print();
                    }
            }
        } while (choice != 6);
    }
}
