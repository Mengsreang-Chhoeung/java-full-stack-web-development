package learn.java.records;

import java.io.Serializable;

public record RecordClass(int x, int y, int[] scores) implements Serializable {
    public int[] scores() {
        return this.scores.clone();
    }
}
