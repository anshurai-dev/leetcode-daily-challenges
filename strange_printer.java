class Solution {
    public int strangePrinter(String s) {
        int n = s.length();
        int[][] memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dp(s, 0, n - 1, memo);
    }
    private int dp(String s, int i, int j, int[][] memo) {
        if (i > j) return 0;
        if (memo[i][j] != -1) return memo[i][j];

        int result = dp(s, i + 1, j, memo) + 1;

        for (int k = i + 1; k <= j; ++k) {
            if (s.charAt(k) == s.charAt(i)) {
                result = Math.min(result, dp(s, i, k - 1, memo) + dp(s, k + 1, j, memo));
            }
        }

        memo[i][j] = result;
        return result;
    }
}