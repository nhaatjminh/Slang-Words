import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;
import java.util.Random;
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
    public final static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    } 

    public static HashMap<String, List<String>> m = new HashMap<String, List<String>>();
    public static ArrayList<String> his = new ArrayList<String>();
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
    }

    public static void ReadFileDuplicate(String file_name) {
        try {
            File f = new File(file_name);
            FileReader fr = new FileReader(f);

            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("`")) {
                    List<String> tar = new ArrayList<String>();

                    String[] s = line.split("`");
                    if (m.containsKey(s[0])) {
                        tar = m.get(s[0]);
                        tar.add(s[1]);
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
    }

    public static void WriteHistory(String file_name) {
        try {
            File f = new File(file_name);
            FileWriter fw = new FileWriter(f);

            BufferedWriter bw = new BufferedWriter(fw);
            
            //bw.newLine();
            for (String i : his) {
                fw.write(i + "\n");
            }

            fw.close();
            bw.close();
        }
        catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
    }

    public static ArrayList<String> LoadHistory (String file_name) {
        ArrayList<String> his = new ArrayList<String>();
        try {
            File f = new File(file_name);
            FileReader fr = new FileReader(f);

            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                his.add(line);
            }

            fr.close();
            br.close();
        }
        catch (Exception ex) {
            System.out.println("Error: "+ex);
        }

        return his;
    }

    public static void WriteFile(String file_name) {
        try {
            File f = new File(file_name);
            FileWriter fw = new FileWriter(f);
            //BufferedWriter bw = new BufferedWriter(fw);
            for (String key : m.keySet()) {
                fw.write(key + "`");
                List<String> tmp = m.get(key);
                int i = 0;
                for (i = 0; i < tmp.size() - 1; i++) {
                    fw.write(tmp.get(i) + "| ");
                }
                fw.write(tmp.get(i) + "\n");
            }

            fw.close();
            //bw.close();
        }
        catch (Exception ex) {
            System.out.println("Error: huhu "+ex);
        }
    }

    public static void ShowDefinition(String slang) {
        List<String> l = m.get(slang);
        for (String s: l) {
            System.out.print(s + ", ");
        }
        System.out.print("\b\b     \n");
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
        String ignore = sc.nextLine();
        if (choice == 1) {
            Func_1();
        }
        else if (choice == 2) {
            Func_2();
        }
        else if (choice == 3) {
            Func_3();
        }
        else if (choice == 4) {
            Func_4();
        }
        else if (choice == 5) {
            Func_5();
        }
        else if (choice == 6) {
            Func_6();
        }
        else if (choice == 7) {
            Func_7();
        }
        else if (choice == 8) {
            Func_8();
        }
        else if (choice == 9) {
            Func_9();
        }
        else if (choice == 10) {
            Func_10();
        }
        else {
            clearScreen();
            WriteHistory("history.txt");
            WriteFile("newslang.txt");
            System.exit(0);
        }
    }

    public static void Func_1() {
        clearScreen();
        System.out.print("Enter a Slang word: ");
        String key = sc.nextLine();
        //String ignore = sc.nextLine();
        his.add(key);
        key = key.toUpperCase();
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
        String word = sc.nextLine();
        //String ignore = sc.nextLine();
        his.add(word);
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
                ShowDefinition(i);
            }
        }
        else {
            System.out.println("Not Found!!!");
        }

        PauseTest();
        Menu();
    }

    public static void Func_3() {
        clearScreen();

        System.out.println("History:");
        for (String i : his) {
            System.out.println("- " + i);
        }

        PauseTest();
        Menu();
    }

    public static void AddSlang(String slang, String means) {
            ArrayList<String> tmp = new ArrayList<String>();
            tmp.add(means);
            m.put(slang.toUpperCase(), tmp);
            System.out.println("Add successfully!!!");
    }

    public static void Duplicate(String slang, String means) {
        List<String> tmp = new ArrayList<String>();
        tmp = m.get(slang);
        tmp.add(means);
        m.put(slang.toUpperCase(), tmp);
        System.out.println("Add successfully!!!");
    }

    public static void Func_4() {
        clearScreen();

        System.out.print("Enter Slang word: ");
        String slang = sc.nextLine();
        //String ignore = sc.nextLine();
        System.out.print("Enter meanings: ");
        String means = sc.nextLine();
        //String ignore = sc.nextLine();
        if (m.containsKey(slang)) {
            System.out.println("This Slang word was existed, Choose what you want to: ");
            System.out.println("1. Overwrite");
            System.out.println("2. Dupicate");
            int c = sc.nextInt();
            if (c == 1) {
                AddSlang(slang, means);
            }
            else if (c == 2) {
                Duplicate(slang, means);
            }
        }
        else {
            AddSlang(slang, means);
        }
        PauseTest();
        Menu();
    }

    public static void Func_5() {
        clearScreen();

        System.out.print("Enter Slang word you want to edit: ");
        String slang = sc.nextLine();
        slang = slang.toUpperCase();
        if (!m.containsKey(slang)) {
            System.out.println("This Slang word does not exist!!");
        }
        else {
            System.out.print("Enter new Slang word: ");
            String new_slang = sc.nextLine();
            System.out.print("Enter new Meaning: ");
            String new_means = sc.nextLine();
            List<String> tmp = new ArrayList<String>();
            tmp.add(new_means);
            m.put(new_slang.toUpperCase(), tmp);
            m.remove(slang);
            System.out.println("Edit successfully!!");
        }

        PauseTest();
        Menu();
    }

    public static void Func_6() {
        clearScreen();

        System.out.print("Enter Slang word you want to delete: ");
        String slang = sc.nextLine();
        slang = slang.toUpperCase();
        if (!m.containsKey(slang)) {
            System.out.println("This Slang word does not exist!!");
        }
        else {
            System.out.println("Comfirm delete (y/n)? ");
            String choice = sc.next();
            if (choice.equals("y") || choice.equals("Y")) {
                m.remove(slang);
                System.out.println("Delete successfully!!");
            }
            else {
                System.out.println("Not delete!!");
            }
        }

        PauseTest();
        Menu();
    }

    public static void Func_7() {
        clearScreen();

        m.clear();;
        if (m.isEmpty()) {
            ReadFile("slang.txt");
            System.out.println("Reset successfully!!");
        }
        else {
            System.out.println("Reset Fail!!");
        }

        PauseTest();
        Menu();
    }

    public static String RandomKey() {
        Object[] Keys = m.keySet().toArray();
        return (String)Keys[new Random().nextInt(Keys.length)];
    }

    public static void Func_8() {
        clearScreen();
        String random_key = RandomKey();
        
        System.out.println("Ramdom Slang word today:");
        System.out.print("- " + random_key + ": ");
        ShowDefinition(random_key);

        PauseTest();
        Menu();
    }

    public static void Func_9() {
        clearScreen();

        Random r = new Random();
        List<String> choice = new ArrayList<String>();
        for(int i = 0; i < 4; i++) {
            String word = RandomKey();
            choice.add(word);
        }

        String ans_key = choice.get(r.nextInt(choice.size()));

        System.out.println(">> Slang word: " + ans_key);
        System.out.println(">> What is the definition of the above Slang word? Choose your answer (1-4)");
        int index = 1;
        for (String i : choice) {
            System.out.print(index + ". ");
            ShowDefinition(i);
            index++;
        }
        System.out.print(">>> Your answer: ");
        int input = sc.nextInt();
        if (choice.get(input - 1).equals(ans_key)){
            System.out.println(">>> Congratulation! You won the game!!!");
        }
        else {
            System.out.println(">>> You losed the game!!!");
        }

        PauseTest();
        Menu();
    }

    public static void Func_10() {
        clearScreen();

        Random r = new Random();
        List<String> choice = new ArrayList<String>();
        for(int i = 0; i < 4; i++) {
            String word = RandomKey();
            choice.add(word);
        }

        String ans_key = choice.get(r.nextInt(choice.size()));

        System.out.println(">> Definition:");
        ShowDefinition(ans_key);
        System.out.println(">> What is the Slang word of the above definition? Choose your answer (1-4)");
        int index = 1;
        for (String i : choice) {
            System.out.println(index + ". " + i);
            index++;
        }
        System.out.print(">>> Your answer: ");
        int input = sc.nextInt();
        if (choice.get(input - 1).equals(ans_key)){
            System.out.println(">>> Congratulation! You won the game!!!");
        }
        else {
            System.out.println(">>> You losed the game!!!");
        }

        PauseTest();
        Menu();
    }

    public static void main(String[] args) {
        
        ReadFile("newslang.txt");
        if (m.isEmpty()) {
            ReadFile("slang.txt");
        }
        
        his = LoadHistory("history.txt");
        Menu();
    }
}