package sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * We are given a matrix with R rows and C columns has cells with integer coordinates (r, c), where 0 <= r < R and 0 <= c < C.
 * <p>
 * Additionally, we are given a cell in that matrix with coordinates (r0, c0).
 * <p>
 * Return the coordinates of all cells in the matrix, sorted by their distance from (r0, c0) from smallest distance to largest distance.
 * Here, the distance between two cells (r1, c1) and (r2, c2) is the Manhattan distance, |r1 - r2| + |c1 - c2|.
 * (You may return the answer in any order that satisfies this condition.)
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: R = 1, C = 2, r0 = 0, c0 = 0
 * Output: [[0,0],[0,1]]
 * Explanation: The distances from (r0, c0) to other cells are: [0,1]
 * Example 2:
 * <p>
 * Input: R = 2, C = 2, r0 = 0, c0 = 1
 * Output: [[0,1],[0,0],[1,1],[1,0]]
 * Explanation: The distances from (r0, c0) to other cells are: [0,1,1,2]
 * The answer [[0,1],[1,1],[0,0],[1,0]] would also be accepted as correct.
 * Example 3:
 * <p>
 * Input: R = 2, C = 3, r0 = 1, c0 = 2
 * Output: [[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
 * Explanation: The distances from (r0, c0) to other cells are: [0,1,1,2,2,3]
 * There are other answers that would also be accepted as correct, such as [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]].
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= R <= 100
 * 1 <= C <= 100
 * 0 <= r0 < R
 * 0 <= c0 < C
 *
 * @author EvelynHf
 * @create 2019-09-25 20:38
 **/
public class MatrixCellsInDistanceOrder {
    public static void main(String[] args) {
        int R = 2;
        int C = 3;
        int r0 = 1;
        int c0 = 2;

        int[][] result = new MatrixCellsInDistanceOrder().allCellsDistOrder1(R, C, r0, c0);
        for (int[] cell : result) {
            System.out.println(Arrays.toString(cell));
        }
    }

    public int[][] allCellsDistOrder1(int R, int C, int r0, int c0) {
        boolean[][] visited = new boolean[R][C];
        int[][] result = new int[R * C][2];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r0, c0});
        int i = 0;
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0];
            int c = cell[1];
            if (r < 0 || r >= R || c < 0 || c >= C || visited[r][c]) {
                continue;
            }
            result[i++] = cell;
            visited[r][c] = true;
            queue.offer(new int[]{r, c - 1});
            queue.offer(new int[]{r, c + 1});
            queue.offer(new int[]{r + 1, c});
            queue.offer(new int[]{r - 1, c});
        }
        return result;
    }

    public int[][] allCellsDistOrder2(int R, int C, int r0, int c0) {
        int length = R * C;
        int[][] result = new int[length][2];
        int i = 0;
        int j = 1;
        result[i++] = new int[]{r0, c0};
        while (i < length) {
            for (int x = 0; x <= j; x++) {
                int y = j - x;
                if (r0 + x < R && c0 + y < C) {
                    result[i++] = new int[]{r0 + x, c0 + y};
                }
                if (r0 + x < R && c0 - y >= 0 && y != 0) {
                    result[i++] = new int[]{r0 + x, c0 - y};
                }
                if (x != 0 && r0 - x >= 0 && c0 + y < C) {
                    result[i++] = new int[]{r0 - x, c0 + y};
                }
                if (x != 0 && r0 - x >= 0 && c0 - y >= 0 && y != 0) {
                    result[i++] = new int[]{r0 - x, c0 - y};
                }
            }
            j++;
        }

        return result;
    }

    public int[][] allCellsDistOrder3(int R, int C, int r0, int c0) {
        int[][] result = new int[R * C][2];
        int[] distanceArr = new int[R * C + 1];
        Map<Integer, List<int[]>> distanceMap = new HashMap<>(201);
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int distance = this.calDistance(r0, c0, i, j);
                if (!distanceMap.containsKey(distance)) {
                    distanceMap.put(distance, new LinkedList<>());
                }
                distanceMap.get(distance).add(new int[]{i, j});
                distanceArr[distance]++;
            }
        }
        int i = 0;
        for (int j = 0; j < distanceArr.length; j++) {
            if (0 != distanceArr[j]) {
                for (int[] cell : distanceMap.get(j)) {
                    result[i++] = cell;
                }
            }
        }

        return result;
    }

    private int calDistance(int r0, int c0, int r1, int c1) {
        int x = Math.abs(r0 - r1);
        int y = Math.abs(c0 - c1);
        return x + y;
    }
}
