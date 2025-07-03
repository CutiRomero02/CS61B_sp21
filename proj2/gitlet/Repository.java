package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.*;


import static gitlet.Utils.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author Tommy Ma
 */
public class Repository implements Serializable {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /* Maps the SHA-1 Hash to each commit. */
    CommitTree commitTree = new CommitTree();


    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    /** The file that stores the tree of commits. */
    public static final File REPO_INFO = join(GITLET_DIR, "info.txt");

    /* TODO: fill in the rest of this class. */

    public void init() {
        boolean repoCreated = GITLET_DIR.mkdir();
        if (!repoCreated) {
            throw new GitletException("A Gitlet version-control system already exists in the current directory.");
        }
        Commit commit = Commit.initialCommit(Time.getUnixEpoch());
        String commitHash = Utils.sha1(commit);

    }

}
