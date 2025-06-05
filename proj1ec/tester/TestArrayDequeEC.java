package tester;

import static org.junit.Assert.*;
import org.junit.Test;
import edu.princeton.cs.introcs.StdRandom;
import student.StudentArrayDeque;

import java.util.ArrayDeque;

public class TestArrayDequeEC {

    @Test
    public void randomizedTest() {
        ArrayDequeSolution<Integer> L = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> target = new StudentArrayDeque<>();
        String error = "";
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                int randVal = StdRandom.uniform(0, 100);
                target.addLast(randVal);
                L.addLast(randVal);
                error = error + "addLast(" + randVal + ")\n";
            } else if (operationNumber == 1) {
                int randVal = StdRandom.uniform(0, 100);
                target.addFirst(randVal);
                L.addFirst(randVal);
                error = error + "addFirst(" + randVal + ")\n";
            }  else if (operationNumber == 2) {
                if (L.isEmpty()) {
                    continue;
                }
                Integer last = L.removeLast();
                Integer tlast = target.removeLast();
                error = error + "removeLast()\n";
                assertEquals(error, last, tlast);
            }  else if (operationNumber == 3) {
                if (L.isEmpty()) {
                    continue;
                }
                Integer first = L.removeFirst();
                Integer tfirst = target.removeFirst();
                error = error + "removeFirst()\n";
                assertEquals(error, first, tfirst);
            }
        }
    }
}
