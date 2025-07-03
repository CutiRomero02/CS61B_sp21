package gitlet;

import java.io.File;
import java.util.*;


import static gitlet.Utils.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author Tommy Ma
 */
public class Repository {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /* Maps the SHA-1 Hash to each commit. */
    private Map<String, Commit> commits = new TreeMap<>();


    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    /** The file that stores the tree of commits. */
    public static final File COMMIT_FILE = join(GITLET_DIR, "commits.txt");

    /* TODO: fill in the rest of this class. */

    public static void init() {
        boolean repoCreated = GITLET_DIR.mkdir();
        if (!repoCreated) {
            throw new GitletException("A Gitlet version-control system already exists in the current directory.");
        }
        Commit commit = Commit.initialCommit(Time.getUnixEpoch());
        String commitHash = Utils.sha1(commit);

    }

    public void addToCommits(String commitHash, Commit commit) {
        commits.put(commitHash, commit);
    }

    public Map<String, Commit> getCommits() {
        return commits;
    }

    public void setCommits(Map<String, Commit> commits) {
        this.commits = commits;
    }
}
