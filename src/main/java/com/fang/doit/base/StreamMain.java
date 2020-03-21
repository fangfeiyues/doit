package com.fang.doit.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author created by fang on 2020/3/7/007 21:24
 */
public class StreamMain {

    public static void main(String[] args) {

        List<Sku> streams = new ArrayList<>();

        // 无状态中间操作：unordered(), filter(), map(), peek()
        Stream<Sku> stream = streams.stream().filter(s -> s.getId() > 0);

        // 有状态中间操作：distinct(), sorted(), limit(), skip()
        stream.sorted().collect(Collectors.toList());


        // 非短路终结操作(某些符合条件的)：forEach()遍历, reduce()归约
        streams.forEach(s -> System.out.println(s));
        Object[] arrays = stream.toArray();
//        stream.mapToDouble(s->s.getId()).map()

        Optional<Integer> optional = streams.stream().map(Sku::getId).reduce(Integer::sum);
        if (optional.isPresent()) {
            Integer sum = optional.get();
        }


        // 短路终结操作(处理完所有元素)



    }


    class Sku {

        private int id;

        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
