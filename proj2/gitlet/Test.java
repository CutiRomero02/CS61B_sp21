package gitlet;

import java.io.File;

public class Test {
    public static final File CWD = new File(System.getProperty("user.dir"));
    public static final File NEW_FILE = Utils.join(CWD, "new");

    public static void main(String[] args) {
        System.out.println(NEW_FILE.mkdir());
    }
}
