package com.xuyao.test.guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

import java.nio.charset.Charset;

public class BloomFilterTest {
public static int count = 10000000;
    static BloomFilter<String> filter = BloomFilter.create(new Funnel<String>() {
        @Override
        public void funnel(String s, PrimitiveSink primitiveSink) {
            primitiveSink.putString(s, Charset.forName("UTF-8"));
        }
    }, count,0.0001);
    public static void main(String[] args) {
        long s = System.currentTimeMillis();
        int c = 0;
//        for(int i = 0; i < count; i++){
//            filter.put("xy"+i);
//        }

//        for(int i = 0; i < count; i++){
//            if(filter.mightContain("xy" + i)){
//                System.out.println("contain" + " xy" + i);
//                c++;
//            }
//        }

//--------------------------------------HashSet--------------------------------------

//        List<String> sets = new ArrayList<>();
//        for(int i = 0; i < count; i++){
//            sets.add("xy" +i);
//        }
//        for(int i = 0; i < count; i++){
//            if(sets.contains("xy"+i)){
//                System.out.println("contain " + "xy" + i);
//            }
//        }

        System.out.println("总共：" + c + ", 耗时：" + (System.currentTimeMillis() - s));
    }

}
