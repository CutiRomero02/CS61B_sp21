package gitlet;

// TODO: any imports you need here
import java.io.File;
import java.io.Serializable;
import java.util.Map;

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author Tommy Ma
 */
public class Commit implements Serializable {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    private Commit parent;
    private String timestamp;
    private Map<String, File> files;

    /** The message of this Commit. */
    private String message;

    /* TODO: fill in the rest of this class. */
    public Commit(Commit parent, String timestamp, Map<String, File> files, String message) {
        this.parent = parent;
        this.timestamp = timestamp;
        this.files = files;
        this.message = message;
    }

    public Commit getParent() {
        return parent;
    }

    public static Commit initialCommit(String timestamp) {
        return new Commit(null, timestamp, null, "initial commit");
    }
}
