package Core;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Printer {
    static String spliter = "\n_______________________________________________\n";
    public static void print(String str){
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8), true);
        pw.println(str);
    }
    public static void print(Act act) {
        String out = String.format("%s%s\n%s", act.header, spliter, format_text(act.text));
        print(out);
    }
    public static void print(Day day) {
        print_day_header(day);
        for(Act act: day.getAct_list()){
            print(act);
            input();
        }
    }

    private static void print_day_header(Day day) {
       print(String.format("\n%sДень %s%s", spliter, day.getDay_number(), spliter));
    }

    private static void input() {
        Scanner in = new Scanner(System.in);
        print("<<<Нажмите Enter>>>");
        in.nextLine();
    }

    private static String format_text(String text){
        StringBuilder formatted_text = new StringBuilder();
        for (String paragraph: get_paragraphs(text)){
            formatted_text.append("\t");
            formatted_text.append(set_row_length(paragraph));
            formatted_text.append("\n");
        }
        return formatted_text.toString();
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
