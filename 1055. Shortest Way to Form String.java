class Solution {
    public int shortestWay(String source, String target) {

        // Optimal approach

        HashMap<Character, List<Integer>> map = new HashMap();

        for(int i = 0; i < source.length(); i++)
        {
            char c = source.charAt(i);

            if(!map.containsKey(c)) map.put(c, new ArrayList());

            map.get(c).add(i);
        }

        int source_pointer = 0, target_pointer = 0;

        int count = 1;

        while(target_pointer < target.length())
        {
            char c = target.charAt(target_pointer);

            if(!map.containsKey(c)) return -1;

            List<Integer> indices = map.get(c);

            // Assume we have a list of indices as [0, 4, 6] and our source pointer is 5 we are actually trying to find 5 
            // in this array but 5 is not there. The general binary search algorithm return -1 when an element is not there
            // but this algorithm with return(-missingposition - 1). Missing position is basically where the element should have been
            // actually if that exists in array. If 5 would have existed in the array. It should have been at position 2 which
            // is after element 4. So this algorithm returns -2 - 1 = -3
            int k = Collections.binarySearch(indices, source_pointer);

            // Because now we got -3 while searching 5 we do -(-3) - 1 => gives us index 2 again
            if(k < 0)
            {
                k = -k - 1;
            }

            // Make our source pointer to 0
            if(k == indices.size())
            {
                source_pointer = 0;
                count++;
            }
            // Get the index we need to jump to
            else
            {
                source_pointer = indices.get(k);
                source_pointer++;
                target_pointer++;
            }
        }

        return count;
    }
}
