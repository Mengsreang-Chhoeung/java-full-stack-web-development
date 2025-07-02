package learn.java.records;

import java.util.Objects;

public class NormalClass {
    private final int x;
    private final int y;

    public NormalClass(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof NormalClass))
            return false;
        NormalClass normalClass = (NormalClass) o;
        return this.x == normalClass.x && this.y == normalClass.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    @Override
    public String toString() {
        return "NormalClass{" +
                "x=" + this.x +
                ", y=" + this.y +
                "}";
    }
}
