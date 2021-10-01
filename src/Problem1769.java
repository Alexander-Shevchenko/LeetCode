// 1769. Minimum Number of Operations to Move All Balls to Each Box

import java.util.Arrays;
public class Problem1769 {
    static class Solution {
        public int[] minOperations(String boxes) {
            if (boxes.isEmpty())
                return null;

            final int len = boxes.length();
            int[] moves = new int[len];

            int leftBalls  = 0; // balls left from the inspected bucket
            int leftMoves  = 0; // moves left balls should to make to reach the current inspected bucket

            int rightBalls = 0; // balls right from the inspected bucket (all here initially)
            int rightMoves = 0; // moves right balls should to make to reach the current inspected bucket

            for (int i = 1; i < len; ++i) {
                if (boxes.charAt(i) == '1') {
                    ++rightBalls;
                    rightMoves += i;
                }
            }

            for (int i = 0; i < len; ++i) {
                moves[i] = leftMoves + rightMoves;

                if (i == len - 1)
                    break;

                // Actualize moves and balls for the next step

                // The current bucket ball now becomes on the left side of a bucket with 1 move from it.
                if (boxes.charAt(i) == '1') {
                    ++leftBalls;
                }

                // Each left ball should now do an extra move to reach the (next) bucket
                leftMoves += leftBalls;

                // Each right ball now one move closer to the inspected bucket
                rightMoves -= rightBalls;

                // The next bucket ball is moving from right side to the current bucket, so does not count in moves
                if (boxes.charAt(i + 1) == '1') {
                    --rightBalls;
                }
            }

            return moves;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().minOperations("110")));
        System.out.println(Arrays.toString(new Solution().minOperations("001011")));
    }
}
