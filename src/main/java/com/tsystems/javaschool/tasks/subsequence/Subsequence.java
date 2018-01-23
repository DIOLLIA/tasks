package com.tsystems.javaschool.tasks.subsequence;

import java.util.Iterator;
import java.util.List;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param subList first sequence
     * @param list    second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    public boolean find2(List subList, List list) {
        if (subList == null || list == null) {
            throw new IllegalArgumentException("One of list contains null");
        }
        if (subList.size() > list.size()) {
            return false;
        }
        if (subList.isEmpty()) {
            return true;
        }

        boolean isPossible = false;

        int j = 0;
        for (int i = 0; i < subList.size(); i++) {
            for (; j < list.size(); j++) {
                if (list.get(j).equals(subList.get(i))) {
                    if (i == subList.size() - 1) {
                        isPossible = true;
                    }
                    j++;
                    break;
                }
            }
        }
        return isPossible;
    }

    //Этот метод так же рабочий. Мне он нравится больше, так как записан более компактно
    public boolean find(List subList, List list) {
        if (subList == null || list == null) {
            throw new IllegalArgumentException("One of list contains null");
        }
        if (subList.size() > list.size()) {
            return false;
        }
        if (subList.isEmpty()) {
            return true;
        }
        Iterator iterForList = list.iterator();
        for (Object elementOfSubList : subList) {
            if (iterForList.hasNext()) {
                while (iterForList.hasNext()) {
                    if (elementOfSubList.equals(iterForList.next())) {
                        break;
                    }
                }
            } else {
                return false;
            }
        }
        return true;
    }
}