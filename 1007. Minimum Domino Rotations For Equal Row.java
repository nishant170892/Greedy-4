class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {

        if(tops == null || tops.length == 0 || bottoms == null || bottoms.length == 0) return -1;

        // We checked for the first value
        int value = findMinRotations(tops, bottoms, tops[0]);

        // If we get a minimum value, in both the cases the minimum value will be same if we could succesfully find the rotations. 
        // But if we couldn't find the rotations by choosing the 1st element of tops array then choose the first element of bottoms
        // array. We can't directly return -1. Bcoz this case will give us valid answer in 2nd case.
        // tops = [1,2,1,1,1,2,2,2] and bottoms = [2,1,2,2,2,2,2,2] the above case will give us -1 bcoz at index 5 both the values are
        // 2 in both arrays.

        if(value == -1) return findMinRotations(tops, bottoms, bottoms[0]);

        // This will execute if value was not -1. We found the minimum in the first case itself
        return value;
    }

    private int findMinRotations(int[] tops, int[] bottoms, int target)
    {
        int topRotations, bottomRotations;
        topRotations = bottomRotations = 0;

        for(int i = 0; i < tops.length; i++)
        {
            if(tops[i] != target && bottoms[i] != target) return -1;

            if(tops[i] != target) ++topRotations;

            if(bottoms[i] != target) ++bottomRotations;
        }

        return Math.min(topRotations, bottomRotations);
    }
}
