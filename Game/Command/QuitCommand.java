package Game.Command;

public class QuitCommand implements Command {
    @Override
    public void execute(String command) {
        System.exit(0);
    }
}
