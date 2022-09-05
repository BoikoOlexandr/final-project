package Core.view;

import Core.Command.QuitCommand;

import java.util.Objects;
import java.util.Scanner;

import static Core.view.Printer.print;

public class Input {

    private void input(String prompt){
        Scanner in = new Scanner(System.in);
        print(prompt);
        execute_command(in.nextLine());
    }
    public void get_empty_input() {
        input("<<<Нажмите Enter>>>");
    }

    public void get_choice(){
        input("Ведите номер одного из вариантов");
    }

    private void execute_command(String command){
        command = command.trim().toLowerCase();
        if(Objects.equals(command, "e") || Objects.equals(command, "q")){
            new QuitCommand().execute();
        }
        if(is_didgit(command)){
            print("Ok");
        }
    }

    private boolean is_didgit(String str){
        try {
        int a = Integer.parseInt(str);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }
}
