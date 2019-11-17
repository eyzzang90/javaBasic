package ch14;

import java.util.*;

public class OptionalEx0 {

    public static void main(String[] args) {

        List<Map<Integer, Object>> list = new ArrayList<>();

        for(int i=0; i<6; i++) {
            Map<Integer, Object> map = new HashMap<>();
            map.computeIfAbsent(i, k -> k%2 ==0 ? null : "상품번호"+k);        // 맵에 항목 추가

            list.add(map);
        }
        list.add(null);

        list.forEach(System.out::println);


        /** 리스트 내의 map의 key가 일치하면 보여주기. */

        int[] arr = {1,2,3};

        if(list != null) {
            for(int num : arr) {
                for(Map<Integer, Object> m : list) {
                    if(m != null) {
                        String val = (String) m.get(num);
                        if(val != null) {
                            System.out.println("val : " + val);
                        }
                    }
                }
            }
        }


        System.out.println("----------------");

        list = new ArrayList<>();

        for(int i=0; i<6; i++) {

            Map<Integer, Object> map = new HashMap<>();
            map.computeIfAbsent(i, k -> k%2 ==0 ? null : "상품번호"+k);        // 맵에 항목 추가

            Optional<Map<Integer, Object>> optionalMap = Optional.of(map);

//            list.add(map);
            list.add(optionalMap.get());
        }
        list.add(null);

        list.forEach(System.out::println);

        if(list != null) {
            for(int num : arr) {



            }
        }


    }
}
