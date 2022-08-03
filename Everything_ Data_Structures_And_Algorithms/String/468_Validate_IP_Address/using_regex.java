/* Using regex, Time: O(1), Space: O(1) */
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Solution {

    /*
    1. Chunk contains only one digit, from 0 to 9.
    2. Chunk contains two digits. The first one could be from 1 to 9,
    and the second one from 0 to 9
    3. Chunk contains three digits, and the first one is 1. The second
    and the third ones could be from 0 to 9.
    4.Chunk contains three digits, the first one is 2 and the second one
    is from 0 to 4. Then the third one could be from 0 to 9.
    5.Chunk contains three digits, the first one is 2, and the second one
    is 5. Then the third one could be from 0 to 5.
    */
    String chunkIPv4 = "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])";
    /*
    ^ matches beginning of the string
    \\. dot character
    {3} matches three copies of regex in parenthesis
    $ matches end of string
    */
    Pattern patternIPv4 = Pattern.compile("^(" + chunkIPv4 + "\\.){3}" + chunkIPv4 + "$");

    String chunkIPv6 = "([0-9a-fA-F]{1,4})";
    Pattern patternIPv6 = Pattern.compile("^(" + chunkIPv6 + "\\:){7}" + chunkIPv6 + "$");

    public String validIPAddress(String IP) {

        if (patternIPv4.matcher(IP).matches())
            return "IPv4";

        if (patternIPv6.matcher(IP).matches())
            return "IPv6";

        return "Neither";
    }
}