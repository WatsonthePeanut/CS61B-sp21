package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {
    @Test
    public void randomizedTest() {
        ArrayDequeSolution<Integer> correct = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> broken = new StudentArrayDeque<>();
        StringBuilder msg = new StringBuilder();
        int N = 50000;
        for (int i = 0; i < N; ++i) {
            int opNum = StdRandom.uniform(0, 4);
            if (opNum == 0) {
                Integer randNum = StdRandom.uniform(0, 100);
                correct.addFirst(randNum);
                broken.addFirst(randNum);
                msg.append("addFirst(");
                msg.append(randNum);
                msg.append(")\n");
            } else if (opNum == 1) {
                Integer randNum = StdRandom.uniform(0, 100);
                correct.addLast(randNum);
                broken.addLast(randNum);
                msg.append("addLast(");
                msg.append(randNum);
                msg.append(")\n");
            } else if (opNum == 2) {
                if (correct.size() == 0) {
                    continue;
                }
                msg.append("removeFirst()\n");
                assertEquals(msg.toString(), correct.removeFirst(), broken.removeFirst());
            } else {
                if (correct.size() == 0) {
                    continue;
                }
                msg.append("removeLast()\n");
                assertEquals(msg.toString(), correct.removeLast(), broken.removeLast());
            }
        }
    }
}
