package com.tsystems.javaschool.tasks.pyramid;

import java.util.*;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */

    public int[][] buildPyramid(List<Integer> inputNumbers) {
        int heightOfPyramid = 0;
        int widthOfPyramid;
        int sum = 1;
        int enumOfElements = 1;
        int[][] arrayForPyramid;

        for (int i = 2; i < inputNumbers.size(); i++) {
            sum += i;
            if (inputNumbers.size() == sum) {
                heightOfPyramid = i;
                break;
            }
        }

        if (inputNumbers.contains(null)) {
            throw new CannotBuildPyramidException("Null elements found");
        }
        widthOfPyramid = heightOfPyramid * 2 - 1;

        try {
            arrayForPyramid = new int[heightOfPyramid][widthOfPyramid];
        } catch (NegativeArraySizeException errSize) {
            throw new CannotBuildPyramidException("Incorrect parameters (width/height): " +
                    widthOfPyramid + "/" + heightOfPyramid + "" + " found");
        }
        Collections.sort(inputNumbers);
        ArrayList<Integer> tempInputList = new ArrayList<>(inputNumbers);

        for (int i = 0; i < heightOfPyramid; i++) {
            for (int j = 0; j < enumOfElements; j++) {
                int indexOfNextElement;
                int firstElementInLineIndex = heightOfPyramid - enumOfElements;
                indexOfNextElement = firstElementInLineIndex + j * 2;
                arrayForPyramid[i][indexOfNextElement] = tempInputList.get(j);
            }
            enumOfElements++;
            for (int k = 0; k <= i; k++) {
                tempInputList.remove(0);
            }
        }
        return arrayForPyramid;
    }
}