package Game.Core;

import java.util.Comparator;

public class DaySort implements Comparator<Day> {

    @Override
    public int compare(Day o1, Day o2) {
        return o1.get_day_number() - o2.get_day_number();
    }
}