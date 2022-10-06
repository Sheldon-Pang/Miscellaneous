/*

use a delimiter outside of 256 valid ascii characters

Time: O (N)
Space: O(1) for encoding, and O(N) for decoding

*/
public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {

        if (strs.size() == 0 ) {
            return Character.toString((char)258);
        }

        String delimiter = Character.toString((char)257);
        StringBuilder sb = new StringBuilder();

        for (String str : strs) {
            sb.append(str);
            sb.append(delimiter);
        }

        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {

        String delimiter = Character.toString((char)258);
        if (s.equals(delimiter)) {
            return new ArrayList();
        }

        delimiter = Character.toString((char)257);
        return Arrays.asList(s.split(delimiter, -1));

    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));