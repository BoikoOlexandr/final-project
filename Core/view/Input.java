package Core.view;

import Core.Command.QuitCommand;

import java.util.Objects;
import java.util.Scanner;

import static Core.view.Printer.print;

public class Input {

    public void get_empty_input() {
        Scanner in = new Scanner(System.in);
        print("<<<Нажмите Enter>>>");
        execute_command(in.nextLine());
    }

    private void execute_command(String command){
        command = command.trim().toLowerCase();
        if(Objects.equals(command, "e") || Objects.equals(command, "q")){
            new QuitCommand().execute();
        }
    }

}
