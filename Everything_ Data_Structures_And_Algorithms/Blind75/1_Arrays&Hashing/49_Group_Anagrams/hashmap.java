/*


Input: strs = ["eat","tea","tan","ate","nat","bat"]
                       ^


chars[] = e,a,t / t,e,a
sorted = a,e,t / a,e,t
key = aet / aet
HashMap[aet[eat,tea]]

Output: [["eat","tea","ate"],["bat"],["tan","nat"]]

Pesudo Code:
    -loop through strs[]
        -convert current str to array of chars
        -sort the array
        -covert back to string used as key for map
        -check if map does not contain the key
            -create new arraylist as value, and key as the key for map
        -get arraylist with the key, and store str into the arraylist

    return arraylist of the values of the map

    Time: O(N * K * log(K)), N = length of strs, K = max length of string in strs,
    outer for loop taks O(N)
    inside for loop, sorting takes O(K * log(K))

    Space: O (N * K)
    The total data store in the result hashmap

*/

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        if (strs.length == 0) {
            return new ArrayList();
        }

        HashMap<String, List> result = new HashMap<>();

        for (String str : strs) {
            char[] tempChar = str.toCharArray();
            Arrays.sort(tempChar);
            String key = String.valueOf(tempChar);
            if (!result.containsKey(key)) {
                result.put(key, new ArrayList());
            }
            result.get(key).add(str);
        }

        return new ArrayList(result.values());
    }
}