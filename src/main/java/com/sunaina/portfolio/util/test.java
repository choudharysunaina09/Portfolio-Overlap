package com.sunaina.portfolio.util;

/*
 array : {1,2, 3}
    arr2 {2,2,1,3} - true
    arr3{2,1,1,3} - false

    2-2 1
    1-1 0
    3-1


    compare array
 */

import java.util.HashMap;

public class test {

    public test(){
        System.out.println(compareArray());
    }
    public boolean compareArray() {
        int[] arr1 = {2,2,1,3};
        int[] arr2 = {2,1,2,3};
        if(arr2.length != arr1.length)
            return false;

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i =0 ; i<arr1.length; i++) {
            if(map.containsKey(arr1)){
                int count = map.get(arr1[i])+1;
                map.put(arr1[i], count);
            } else
                map.put(arr1[i], 1);
        }

        for(int i =0 ; i<arr2.length; i++) {
            if(map.containsKey(arr2[i])){
                int count = map.get(arr2[i]);
                if(count<=0)
                    return false;
                else
                    map.put(arr2[i], count-1);
                } else
                    return false;
        }
        return true;
    }
   /* arr2 {2,2,1,3} - true

    arr2 {2,1,2,3} - true
    arr3{2,1,1,3} - false

            2-2 1  2-0  2-1
            1-1 0  1-0  1-0
            3-1    3-0*/



}
