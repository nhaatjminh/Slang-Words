import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.util.Scanner;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class SlangWords {

    public static void PauseTest(){
        System.out.println("Press Any Key To Continue...");
        new java.util.Scanner(System.in).nextLine();
    }

    // clear screen
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    } 

    public static HashMap<String, List<String>> m = new HashMap<String, List<String>>();
    public static Scanner sc = new Scanner(System.in);

    public static void ReadFile(String file_name) {
        try {
            File f = new File(file_name);
            FileReader fr = new FileReader(f);

            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("`")) {
                    List<String> tar = new ArrayList<String>();

                    String[] s = line.split("`");
                    if (s[1].contains("|")) {
                        String[] tmp = s[1].split("\\|");
                        for (int i = 0; i < tmp.length; i++) {
                            tmp[i] = tmp[i].trim();
                        }
                        tar = Arrays.asList(tmp);
                    }
                    else {
                        tar.add(s[1]);
                    }
                    m.put(s[0], tar);
                }
            }

            fr.close();
            br.close();
        }
        catch (Exception ex) {
            System.out.println("Error: "+ex);
        }

        // for (String i : m.keySet()) {
        //     List<String> l = m.get(i);
        //     System.out.print(i + ": ");
        //     for (String s : l) {
        //         System.out.print(s + ", ");
        //     }
        //     System.out.println();
        // }
    }

    public static void Menu() {
        clearScreen();
        System.out.println("MENU");
        System.out.println("1. Search by Slang word");
        System.out.println("2. Search by Definition");
        System.out.println("3. History");
        System.out.println("4. Add a Slang word");
        System.out.println("5. Edit a Slang word");
        System.out.println("6. Deleta a Slang word");
        System.out.println("7. Reset");
        System.out.println("8. Random 1 Slang word");
        System.out.println("9. Fun Game (Find Definition by Slang word)");
        System.out.println("10. Fun Game (Find Slang word by Definition)");
        System.out.println("11. Exit");
        System.out.print(">>> Your choice: ");
        int choice = sc.nextInt();
        if (choice == 1) {
            Func_1();
        }
        if (choice == 11) {
            clearScreen();
            System.exit(0);
        }
        if (choice == 2) {
            Func_2();
        }
    }

    public static void Func_1() {
        clearScreen();
        System.out.print("Enter a Slang word: ");
        String key = sc.next();
        if (!m.containsKey(key)) {
            System.out.println("Not Found!!!");
        }
        else {
            List<String> l = m.get(key);
            System.out.println("Definiton:");
            for (String s: l) {
                System.out.println("- " + s);
            }
        }
        PauseTest();
        Menu();
    }

    public static void Func_2() {
        clearScreen();

        ArrayList<String> slang_means = new ArrayList<String>();
        System.out.print("Enter any word to find a Slang word: ");
        String word = sc.next();
        word = word.toLowerCase();
        for (String i : m.keySet()) {
            for (String s: m.get(i)) {
                if (s.toLowerCase().contains(word)) {
                    slang_means.add(i);
                }
            }
        }

        if (!slang_means.isEmpty()) {
            System.out.println("Slang words found: ");
            for (String i : slang_means) {
                System.out.print("- " + i + ": ");
                List<String> l = m.get(i);
                for (String s: l) {
                    System.out.print(s + ", ");
                }
                System.out.println();
            }
        }
        else {
            System.out.println("Not Found!!!");
        }

        PauseTest();
        Menu();
    }

    public void Func_3() {

    }

    public void Func_4() {

    }

    public void Func_5() {

    }

    public void Func_6() {

    }

    public void Func_7() {

    }

    public void Func_8() {

    }

    public void Func_9() {

    }

    public void Func_10() {

    }

    public static void main(String[] args) {
        ReadFile("slang.txt");
        Menu();
    }
}


