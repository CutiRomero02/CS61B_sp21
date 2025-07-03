package gitlet;

import java.io.File;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author Tommy Ma
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {
        /** If user doesn't input any arguments, throw GitletException*/
        if (args.length == 0) {
            throw new GitletException("Please enter a command.");
        }

        String firstArg = args[0];

        File inputFile = Repository.REPO_INFO;
        Repository repo = Utils.readObject(inputFile, Repository.class);
        switch(firstArg) {

            /** Wrong number or format of operands, throw Incorrect operands.
             *  Uninitialized Gitlet working directory, throw Not in an initialized Gitlet directory.*/

            case "init":
                if (args.length != 1) {
                    throw new GitletException("Incorrect operands.");
                }
                repo.init();
                Utils.writeObject(inputFile, repo);
                break;
            case "add":
                // TODO: handle the `add [filename]` command
                break;
            // TODO: FILL THE REST IN
            default:
                throw new GitletException("No command with that name exists.");

        }
    }
}
