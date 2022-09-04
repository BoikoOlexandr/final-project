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
        String formatted_text = "";
        for (String paragraph: get_paragraphs(text)){
            formatted_text+="\t";
            formatted_text+=set_row_length(paragraph);
            formatted_text+="\n";
        }
        return formatted_text;
    }


    private static String[] get_paragraphs(String text){
        return text.split("\n");
    }

    private static String set_row_length(String paragraph){
        StringBuffer sb = new StringBuffer(paragraph);
        int offset = settings.sheet_length;
        while(true){

            offset = find_space(sb, offset);

            if (offset >= sb.length())break;

            sb.setCharAt(offset,'\n');

            offset += settings.sheet_length;

        }
        return sb.toString();
    }

    private static int find_space(StringBuffer sb, int offset){
        int spase_pos = offset;
        while (spase_pos < sb.length()){
            if(sb.charAt(spase_pos) == ' ')break;
            spase_pos++;
        }
        return spase_pos;
    }

}
