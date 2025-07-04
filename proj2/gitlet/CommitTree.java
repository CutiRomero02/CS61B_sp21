package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.*;

public class CommitTree implements Serializable {

    /** Stores the tree of commits. */
    private Map<String, Commit> commits = new HashMap<>();
    private Commit master;

    public void getCommitsFromFile() {

    }


}
