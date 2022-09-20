package Game.Core.Act;

import Game.ORM.Table;
import Game.ORM.TableList;

import java.util.HashMap;
import java.util.Map;

public class OutputPromptList extends TableList {

    private static OutputPromptList instance;

    public Map<String, String> get_prompts() {
        return prompts;
    }

    private final Map<String, String> prompts = new HashMap<>();

    public static OutputPromptList get_instance() throws Exception {
        if (instance == null) {
            instance = new OutputPromptList();
        }
        return instance;
    }
    private OutputPromptList() throws Exception {
        super(new OutputPrompt());
        init_prompts();
    }
    private void init_prompts() throws NoSuchFieldException, IllegalAccessException {
        for(Table row: rows){
            prompts.put(
                    (String) row.getClass().getDeclaredField("name").get(row),
                    (String) row.getClass().getDeclaredField("value").get(row)
            );
        }
    }
}
