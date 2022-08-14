/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import DTO.Food;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import base.Validate;
import static DTO.FormatForm.StringID;
import static DTO.FormatForm.StringName;

/**
 *
 *
 */
public class Manager {

    ArrayList<Food> list = new ArrayList<>();

    public Manager() {
    }

    public ArrayList<Food> getList() {
        return list;
    }

    public boolean AddFood(Food food) {
        String id = "", name = "", type = "", place = "", date = "";
        String options = "";
        int count = 0;
        double weight = 0;
        boolean flag;
        Scanner sc;
        do {
            try {

                sc = new Scanner(System.in);

                System.out.println("Enter the id of Food(f0**): ");
                id = sc.nextLine().toUpperCase();
                if (id.isEmpty() || Findid(id) != null || id.matches(StringID) == false) {
                    throw new Exception();
                }
                flag = false;
            } catch (Exception e) {
                System.err.println("Invalid. Please enter again: ");
                flag = true;
            }
        } while (flag || id.isEmpty());
        do {
            try {
                sc = new Scanner(System.in);
                System.out.println("Enter the name of Food(rau, thit, trung, ca, sua): ");
                name = sc.nextLine();
                if (name.isEmpty() || name.matches(StringName) == false) {
                    throw new Exception();
                }
                flag = false;
            } catch (Exception e) {
                System.err.println("Information must not empty.");
                flag = true;
            }
        } while (flag);
        do {
            try {
                sc = new Scanner(System.in);
                System.out.println("Enter the weight of Food(0.1kg -> 100kg): ");
                weight = sc.nextDouble();
                if (weight < 0.0001 || weight > 100) {
                    throw new Exception();
                }
                flag = false;
            } catch (Exception e) {
                System.out.println("Invalid. Please enter again. ");
                flag = true;
            }

        } while (flag);
        do {
            try {
                int choice;
                sc = new Scanner(System.in);
                System.out.println("Input types of Food: ");
                System.out.println("------Types of Food--------");
                System.out.println("* 1. foods Of Meat.       *");
                System.out.println("* 2. foods Of Fish.       *");
                System.out.println("* 3. foods Of Eggs.       *");
                System.out.println("* 4. foods Of Vegetables. *");
                System.out.println("* 5. other Foods.         *");
                System.out.println("---------------------------");
                System.out.println("Input your choice: ");
                choice = sc.nextInt();
                flag = false;
                if (choice < 1 || choice > 5) {
                    throw new Exception();
                }
                switch (choice) {
                    case 1:
                        System.out.println("meat");
                        type = "meat";
                        break;
                    case 2:
                        System.out.println("fish");
                        type = "fish";
                        break;
                    case 3:
                        System.out.println("eggs");
                        type = "eggs";
                        break;
                    case 4:
                        System.out.println("vegetables");
                        type = "vegetables";
                        break;
                    case 5:
                        System.out.println("other");
                        type = "other";
                }
            } catch (Exception e) {
                System.out.println("Please enter from 1 to 5.");
                flag = true;
            }
        } while (flag);
        do {
            try {
                int choice1;
                sc = new Scanner(System.in);
                System.out.println("Enter the place of Food:  ");
                System.out.println("------Place for Food------");
                System.out.println("* 1. cooler.             *");
                System.out.println("* 2. freezer.            *");
                System.out.println("--------------------------");
                System.out.println("Input your choice: ");
                choice1 = sc.nextInt();
                flag = false;
                if (choice1 < 1 || choice1 > 2) {
                    throw new Exception();
                }
                switch (choice1) {
                    case 1:
                        System.out.println("cooler");
                        place = "cooler.";
                        break;
                    case 2:
                        System.out.println("freezer");
                        place = "freezer.";
                        break;
                }
            } catch (Exception e) {
                System.out.println("Please enter 1 or 2.");
                flag = true;
            }
        } while (flag);
        do {
            try {
                sc = new Scanner(System.in);
                System.out.println("Enter the expired date: ");
                date = sc.nextLine();
                flag = false;
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                df.setLenient(false);
                Date day = df.parse(date);
                Date today = Calendar.getInstance().getTime();
                if (day.before(today)) {
                    System.out.println("Pls! This food has expired");
                    flag = true;
                }
            } catch (ParseException e) {
                System.out.println("Pls! The date is invalid.");
                flag = true;
            }
        } while (flag);

        do {
            System.out.println("Do You Want To Add More Food? (Y/N)");
            sc = new Scanner(System.in);
            options = sc.nextLine();
            flag = false;
            if (options.equalsIgnoreCase("Y")) {
                AddFood(food);
                System.out.println("Added Successfully!");
                writeFile("lab1.txt");
                count++;

            } else if (options.equalsIgnoreCase("N")) {
                System.out.println("Canceled Add.");
            }
        } while (flag);
        food.setId(id);
        food.setName(name);
        food.setWeight(weight);
        food.setType(type);
        food.setPlace(place);
        food.setDate(date);
        return list.add(food);

    }

    public Food Findid(String id) {
        for (Food food : list) {
            if (food.getId().equals(id)) {
                return food;
            }
        }
        return null;
    }

    public void searchByName(String name) {
            int count = 0;
            Scanner sc;
            boolean flag;
            String options;
        do {
            name = "";
            
            do {
                System.out.println("Enter Food Name To Search: ");
                sc = new Scanner(System.in);
                name = sc.nextLine().toLowerCase().replaceAll("\\s\\s+", " ").trim();
            } while (name.isBlank());
            if (countFoodSameName(name) == 0) {
                System.out.println(name + " Not Found!");
            } else {
                Collections.sort(list);
                System.out.format("%-15s%c%-15s%c%-5s%c%-15s%c%-24s%c%-15s\n", "id", '|', "name", '|', "weight(kg)", '|', "type", '|', "place", '|', "date", '|');
                for (Food food : list) {

                    if (food.getName().contains(name)) {
                        System.out.println(food.toString());
                    }
                }
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

    public void searchByDate(String date) {
        Collections.sort(list);
        System.out.format("%-15s%c%-15s%c%-5s%c%-15s%c%-24s%c%-15s\n", "id", '|', "name", '|', "weight(kg)", '|', "type", '|', "place", '|', "date", '|');
        for (Food food : list) {

            if (food.getDate().contains(date)) {
                System.out.println(food.toString());
            }

        }
    }

    public int countFoodSameName(String name) {
        int foodSameName = 0;
        for (Food food : list) {
            if (food.getName().contains(name)) {
                foodSameName++;
            }
        }
        return foodSameName;
    }

    public int countFoodSameDate(String date) {
        int foodSameDate = 0;
        for (Food food : list) {
            if (food.getDate().contains(date)) {
                foodSameDate++;
            }
        }
        return foodSameDate;
    }

    public void removeFood(String id) {
        if (Findid(id) != null) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Do you want to remove! \"" + id + "\" (Y/N)");
            String input = "";
            do {
                input = sc.nextLine();
            } while (!(input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("N")));

            if (input.equalsIgnoreCase("Y")) {
                list.remove(Findid(id));
                System.out.println("Remove Successfully!");
                writeFile("lab1.txt");
            } else if (input.equalsIgnoreCase("N")) {
                System.out.println("Canceled Remove!");
            }
        }
    }

    public void print() {
        if (list.isEmpty()) {
            System.err.println("The list is empty");
        } else {
            Collections.sort(list);
            System.out.format("%-15s%c%-15s%c%-5s%c%-15s%c%-24s%c%-15s\n", "id", '|', "name", '|', "weight(kg)", '|', "type", '|', "place", '|', "date", '|');
            list.forEach(food -> {
                System.out.println(food.toString());
            });
            String fileName = "lab1.txt";
            writeFile(fileName);
        }
    }

    public void writeFile(String filename) {
        PrintWriter w = null;

        try {
            w = new PrintWriter(filename);
            for (Food food : list) {
                String tmp = food.toString();
                w.println(tmp);
                w.flush();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't Write File!");
        } finally {
            try {
                if (w != null) {
                    w.close();
                }
            } catch (Exception e) {
                System.out.println("Something Wrong!");
            }
        }
    }
}
