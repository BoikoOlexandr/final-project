package Game.view;

import Game.Command.ChoiseCommand;
import Game.Command.QuitCommand;
import Game.Core.Act.Act;

import java.util.Objects;
import java.util.Scanner;

public class Input {

    private Act act;
    private String prompt;

    private Scanner in = new Scanner(System.in);
    private String users_chose;
    public Input(Act act){
        this.act = act;

    }
    public void input() throws Exception {
        prompt = act.get_prompt();
        if(prompt != null) {
            Printer.print(prompt);
            users_chose = in.nextLine().toLowerCase().trim();
            this.call_command();
        }
    }

    private void call_command() {
        if(Objects.equals(users_chose, "q")){
            new QuitCommand().execute(users_chose);
        }else if(Objects.equals(act.get__input_type(), "choise") && is_digit()){
            new ChoiseCommand().execute(users_chose);
        }
    }

    private boolean is_digit(){
        for(char ch: users_chose.toCharArray()){
            if(!Character.isDigit(ch)){
                return false;
            }
        }
        return true;
    }



}
