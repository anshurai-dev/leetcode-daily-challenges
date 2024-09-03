class Solution {
    public int getLucky(String s, int k) {
        StringBuilder currString = new StringBuilder();
        char str[] = s.toCharArray();
        for(var chr : str){
            int numVal = (int)(chr - 'a' + 1);
            Integer currNum = Integer.valueOf(numVal);
            currString.append(currNum.toString());
        }
        while(k -- > 0){
            currString = process(currString); 
        }
        return Integer.parseInt(currString.toString());
    }
    private StringBuilder process(StringBuilder currString){
        int digSum = 0;
        for(int indx = 0; indx < currString.length(); indx++){
            var chr = currString.charAt(indx);
            int dig = (chr - '0');
            digSum += dig;
        }
        return new StringBuilder(digSum + "");        
    }
}