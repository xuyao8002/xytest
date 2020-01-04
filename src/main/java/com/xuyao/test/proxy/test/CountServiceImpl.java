package com.xuyao.test.proxy.test;

/**
 * @author yao
 * @Date 2019/8/19
 */
public class CountServiceImpl implements CountService {

    private int count = 0;

    @Override
    public int count() {
        return count ++;
    }
}
