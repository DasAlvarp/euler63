import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by alvaro on 11/6/14.
 */

public class Driver {

    public static final int LINES = 100;
    public static Node[][] nodeTemp = new Node[LINES][LINES];

    public static void main(String[] args) throws FileNotFoundException {


        File f = new File("data.txt");
        Scanner scanMan = new Scanner(f);

        for (int x = 0; x < LINES; x++) {
            for (int y = 0; y < x + 1; y++) {
                System.out.println(x + " WRITING " + y);
                if (scanMan.hasNextInt()) {
                    Node t = new Node();
                    t.val = scanMan.nextInt();
                    nodeTemp[x][y] = t;
                }

            }
        }


        Node root = recuriveFill(nodeTemp[0][0], 0, 0);

        Node t = root;

        System.out.println(root.total + ", " + greedyBest(root, 0));


    }


    public static Node recuriveFill(Node head, int depth, int leftiness) {


        if (depth == LINES - 1 || leftiness == LINES - 1)
        {
            return null;
        }
        else
        {
            //System.out.println(depth + ", " + leftiness + ", " + head.val);
            head.lChild = recuriveFill(nodeTemp[depth + 1][leftiness], depth + 1, leftiness);
            head.rChild = recuriveFill(nodeTemp[depth + 1][leftiness + 1], depth + 1, leftiness + 1);


            if (head.lChild == null || head.rChild == null)
            {
                head.total = head.val;
            }
            else
            {

                if (head.lChild.total > head.rChild.total)
                {
                    head.total = head.lChild.total + head.val;
                   // System.out.println("Right");
                }
                else
                {
                    head.total = head.rChild.total + head.val;
                    //System.out.println("Left");
                }


            }


            System.out.println(depth + " DOING RECURSIVE THINGS " + leftiness);
            return head;
        }
    }


    public static int greedyBest(Node head, int total)
    {
        int tot = total;
        if(head.lChild == null || head.rChild == null)
        {
            return tot + 75;
        }
        else if(head.lChild.val > head.rChild.val) {
            tot += head.lChild.val;
            tot = greedyBest(head.lChild, tot);
            System.out.println("Right");
        }
        else
        {
            tot += head.rChild.val;
            tot = greedyBest(head.rChild, tot);
            System.out.println("Left");

        }

        return tot;

    }


}
