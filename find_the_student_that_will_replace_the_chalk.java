class Solution {
    public int chalkReplacer(int[] chalk, int k) {
        long sum=0, K =k;
        for(int i:chalk){
            sum = sum+i;
        }
        K =  K %sum;
        for(int i=0; i<chalk.length; i++){
            if(K<chalk[i]){
                return i;
            }
            K = K-chalk[i];
        }
        return 0;
    }
}