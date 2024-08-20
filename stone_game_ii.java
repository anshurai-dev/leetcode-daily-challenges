class Solution {
    public int stoneGameII(int[] piles) {
        int length = piles.length;
        int[][] dp = new int[length + 1][length + 1];
        int[] sufsum = new int[length + 1];
        
        for (int i = length - 1; i >= 0; i--) {
            sufsum[i] = sufsum[i + 1] + piles[i];
        }
        
        return helper(dp, sufsum, 0, 1);
    }
    
    private int helper(int[][] dp, int[] sufsum, int i, int M) {
        if (i == sufsum.length) return 0;
        if (2 * M >= sufsum.length - i) return sufsum[i];
        if (dp[i][M] != 0) return dp[i][M];
        
        int res = Integer.MAX_VALUE;
        for (int X = 1; X <= 2 * M; X++) {
            res = Math.min(res, helper(dp, sufsum, i + X, Math.max(X, M)));
        }
        
        dp[i][M] = sufsum[i] - res;
        return dp[i][M];
    }
}