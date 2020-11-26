import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.util.HashMap;

public class SlangWords {
    public static HashMap<String, String> m = new HashMap<String, String>();

    public static void main(String[] args) {
        try {
            File f = new File("slang.txt");
            FileReader fr = new FileReader(f);

            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                    if (line.contains("`")) {
                    String[] s = line.split("`");
                    m.put(s[0], s[1]);
                }
            }

            fr.close();
            br.close();
        }
        catch (Exception ex) {
            System.out.println("Loi doc file: "+ex);
        }

        for (String i : m.keySet()) {
            System.out.println(i + ": " + m.get(i));
        }
    }
}
