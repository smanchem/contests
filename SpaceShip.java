/**
 * Created by sandeepmanchem on 10/18/16.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class SpaceShip {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int n = Integer.parseInt(line);
        Spaceship ships[] = new Spaceship[n];
        int i = 0;
        while ((line = br.readLine()) != null) {
            String spaceshipString[] = line.split(" ");
            ships[i] = new Spaceship(Integer.parseInt(spaceshipString[0]), Long.parseLong(spaceshipString[1]), Long.parseLong(spaceshipString[2]));
            i++;
        }

        IntervalTree iTree = new IntervalTree();
        for (i = 0; i < n; i++) {
            System.out.println(ships[i].spaceshipId + " " + ships[i].interval.start);
            iTree.root = iTree.insertNode(iTree.root, ships[i].interval);
        }

        // Score and sort each spaceship
        Spaceship sortedShips[] = new Spaceship[n];
        for (i = 0; i < n; i++) {
            ships[i].score = iTree.score(iTree.root, ships[i].interval);
            sortedShips = insertIntoSortedShips(sortedShips, ships[i], n);
        }

        // Print Scores
        StringBuilder sb = new StringBuilder();
        for (i = 0; i < n; i++) {
            sb.append(sortedShips[i].spaceshipId + " " + sortedShips[i].score);
        }
        System.out.println(sb);
    }

    public static Spaceship[] insertIntoSortedShips(Spaceship[] sortedShips, Spaceship ship, int n) {
        Spaceship[] newSortedShips = new Spaceship[n];
        if (sortedShips.length == 0) {
            newSortedShips[0] = ship;
            return newSortedShips;
        }

        boolean inserted = false;

        for (int i = 0, j = 0; i < sortedShips.length; i++) {
            if ((!inserted && ship.score > sortedShips[i].score) || inserted) {
                newSortedShips[j++] = sortedShips[i];
            } else if (!inserted && ship.score == sortedShips[i].score) {
                if (ship.spaceshipId < sortedShips[i].spaceshipId) {
                    newSortedShips[j++] = ship;
                    newSortedShips[j++] = sortedShips[i];
                    inserted = true;
                } else {
                    newSortedShips[j++] = sortedShips[i];
                    newSortedShips[j++] = ship;
                    inserted = true;
                }
            } else if (!inserted && ship.score < sortedShips[i].score) {
                newSortedShips[j++] = ship;
                newSortedShips[j++] = sortedShips[i];
                inserted = true;
            }
        }
        return newSortedShips;
    }
}
    class Spaceship {
        public int spaceshipId;
        public Interval interval;
        public int score;

        public Spaceship(int spaceshipId, long start, long end) {
            spaceshipId = spaceshipId;
            System.out.println(spaceshipId);
            interval = new Interval(start, end);
        }

        public int getSpaceshipId() {
            return spaceshipId;
        }
    }

    class Interval {
        public long start;
        public long end;

        public Interval(long start, long end) {
            start = start;
            end = end;
        }

        public Interval(Interval interval) {
            interval = interval;
        }
    }

    class IntervalNode {
        public Interval interval;
        public IntervalNode left, right;

        public IntervalNode(long start, long end) {
            interval = new Interval(start, end);
            left = null;
            right = null;
        }
    }

    class IntervalTree {
        public IntervalNode root;

        public IntervalNode insertNode(IntervalNode root, Interval interval) {
            if (root == null) {
                IntervalNode iNode = new IntervalNode(interval.start, interval.end);
                System.out.println(interval.start);
                return iNode;
            }
            if (root.interval.start < interval.start) {
                root.right = insertNode(root.right, interval);
            } else {
                root.left = insertNode(root.left, interval);
            }
            return root;
        }

        // i1 contains i2
        public boolean contains(Interval i1, Interval i2) {
            if (i1.start < i2.start && i1.end > i2.end) {
                return true;
            } else {
                return false;
            }
        }

        public int score(IntervalNode node, Interval interval) {
            int count = 0;
            if (node == null)
                return 0;

            if (node.interval.start < interval.start)
                return 0;

            if (node.interval.end > interval.end)
                return 0;

            if (contains(interval, node.interval)) {
                count++;
                count += score(node.left, interval);
                count += score(node.right, interval);
                return count;
            } else {
                count += score(node.left, interval);
                count += score(node.right, interval);
                return count;
            }
        }
    }
}
