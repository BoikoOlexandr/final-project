package Core;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Printer {
    public static void print(String str) throws UnsupportedEncodingException {
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8), true);
        pw.println(str);
    }
    public static void print(Act act) throws UnsupportedEncodingException {
        String out = String.format("\t %s\n\n\n%s", act.header, format_text(act.text));
        print(out);
    }
    private static String format_text(String text){
        StringBuffer sb = new StringBuffer(text);
        int string_counter = 0;
        int row_counter = 0;
        try {
            while (true) {
                string_counter++;
                row_counter++;
                if (sb.charAt(string_counter) == '\n') {
                    sb.insert(string_counter+1, '\t');
                    row_counter = 0;
                    continue;
                }
                if (row_counter == settings.sheet_length) {
                    row_counter = 0;
                    string_counter = set_ofset_to_end_of_word(sb, string_counter);
                    sb.insert(string_counter, '\n');
                }
            }
        } catch (StringIndexOutOfBoundsException ignored){}
         return sb.toString();
    }


    private static int set_ofset_to_end_of_word(StringBuffer sb, int ofset) {
        char ch = sb.charAt(ofset);
        while (ch != ' ') {
            ofset++;
            ch = sb.charAt(ofset);
        }
        sb.deleteCharAt(ofset);
        return ofset;
    }


}
