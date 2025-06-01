package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE

    @Test
    public void testBuggyAList() {
        AListNoResizing<Integer> model =  new AListNoResizing<>();
        BuggyAList<Integer> target = new BuggyAList<>();
        model.addLast(4);
        model.addLast(5);
        model.addLast(6);
        target.addLast(4);
        target.addLast(5);
        target.addLast(6);

        int[] m = new int[3];
        int[] t = new int[3];

        m[0] = model.removeLast();
        m[1] = model.removeLast();
        m[2] = model.removeLast();
        t[0] = target.removeLast();
        t[1] = target.removeLast();
        t[2] = target.removeLast();

        assertArrayEquals(m, t);
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> target = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                target.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int tsize = target.size();
                assertEquals(size, tsize);
                System.out.println("size: " + size);
            } else if (operationNumber == 2 && L.size() > 0) {
                int last = L.getLast();
                int tlast = target.getLast();
                assertEquals(last, tlast);
                System.out.println("Get last: " + last);
            } else if (operationNumber == 3 && L.size() > 0) {
                int remove =  L.removeLast();
                int tremove = target.removeLast();
                assertEquals(remove, tremove);
                System.out.println("removeLast(" + remove + ")");
            }
        }
    }
}
