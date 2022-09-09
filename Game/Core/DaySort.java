package Game.Core;

import java.util.Comparator;

public class DaySort implements Comparator<Day> {

    @Override
    public int compare(Day o1, Day o2) {
        return o1.getDay_number() - o2.getDay_number();
    }
}