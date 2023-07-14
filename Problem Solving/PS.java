public class PS {

    public static void main(String[] args) {
        System.out.println(solve("abd(jnb)asdf"));
        System.out.println(solve("abdjnbasdf"));
        System.out.println(solve("dd(df)a(ghhh)"));
    }

    public static String solve(String input) {
        StringBuilder res = new StringBuilder();
        StringBuilder reversedWord = new StringBuilder();
        boolean isReversed = false;
        for (int i = 0; i < input.length(); i++) {

            if (isLowerEnglishLetter(input, i) && !isReversed) {
                res.append(input.charAt(i));
            } else if (isLowerEnglishLetter(input, i) && isReversed) {
                reversedWord.append(input.charAt(i));
            } else if (input.charAt(i) == '(') {
                isReversed = true;
                res.append(input.charAt(i));
            } else {
                isReversed = false;
                res.append(reversedWord.reverse());
                res.append(input.charAt(i));
                reversedWord = new StringBuilder();
            }
        }
        return res.toString();
    }

    public static boolean isLowerEnglishLetter(String input, int ind) {
        return input.charAt(ind) >= 'a' && input.charAt(ind) <= 'z';
    }
}