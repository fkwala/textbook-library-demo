package persistence;

import java.io.PrintWriter;

// Represents data of TextBooks that can be saved to file
public interface Savable {

    // MODIFIES: printWriter
    // EFFECTS: writes the savable to printWriter
    void save(PrintWriter printWriter);
}
