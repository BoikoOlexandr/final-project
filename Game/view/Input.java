package Game.view;

import Game.Command.ChoiseCommand;
import Game.Command.NextCommand;
import Game.Command.QuitCommand;
import Game.Core.Act.Act;
import Game.Core.Day;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Scanner;
@Component
public class Input {

    private final Day day;
    private Act act;
    private String prompt;

    private int offset=1;
    private Scanner in = new Scanner(System.in);
    private String users_chose;
    public Input(Act act, Day day){
        this.act = act;
        this.day = day;

    }
    public void input() throws Exception {
        prompt = act.get_prompt();
        if(prompt != null) {
            Printer.print(prompt);
        }
        users_chose = in.nextLine().toLowerCase().trim();
        this.call_command();
    }

    public void input(int offset) throws Exception {
        this.offset = offset;
        this.input();
    }

    private void call_command() throws Exception {
        if(Objects.equals(users_chose, "q")){
            new QuitCommand().execute(users_chose, day);
        }else if(Objects.equals(act.get__input_type(), "choise") && is_valid_didgit()){
            new ChoiseCommand().execute(String.format("%s %d", users_chose, act.get_choise_count()), day);
        } else if (Objects.equals(act.get__input_type(), "choise")) {
            this.input();
        } else {
            new NextCommand().execute(offset, day);
        }
    }

    private boolean is_valid_didgit(){
        if(Objects.equals(users_chose, ""))
        {
            return false;
        }
        for(char ch: users_chose.toCharArray()){
            if(!Character.isDigit(ch)){
                return false;
            }
        }
        if(Integer.parseInt(users_chose) <= act.get_choise_count() && Integer.parseInt(users_chose)>0) {
            return true;
        }else {
            return false;
        }
    }



}
