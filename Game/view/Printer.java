package Game.view;

import Game.Core.Day;
import Game.settings;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class Printer {
    static Input input = new Input();
    static String splitter = "\n_______________________________________________\n";
    public static void print(String str){
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8), true);
        pw.println(str);
    }
    public static void print_day_header(Day day) {
       print(String.format("\n%sДень %s%s", splitter, day.getDay_number(), splitter));
    }

    private static void print_act_choices(String choices) {
        int i = 0;
        for(String choice: choices.split("\n"))
        {
            print(String.format("\t%d) %s",++i,choice));
        }
    }

    public static String format_text(String text){
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
