/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String value = StdIn.readString();
            rq.enqueue(value);
        }

        Iterator<String> rqi = rq.iterator();
        for (int i = 0 ;i < k;i++){
            StdOut.println(rqi.next());
        }
    }
}
