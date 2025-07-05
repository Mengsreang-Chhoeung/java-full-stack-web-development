package learn.java.records;

public record Range(int start, int end) {
    // Customize Canonical Constructors
    // public Range(int start, int end) {
    // // add some validations
    // if (start >= end)
    // throw new Error("Invalid input, the start field need to be smaller than the
    // end field.");

    // this.start = start;
    // this.end = end;
    // }

    // Customize Compact Constructors
    public Range {
        // add some validations
        if (start >= end)
            throw new Error("Invalid input, the start field need to be smaller than the end field.");
    }

    // Customize Alternative Constructors
    public Range(int size) {
        this(0, size);
    }
}
