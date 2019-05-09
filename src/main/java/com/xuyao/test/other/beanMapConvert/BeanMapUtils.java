package com.xuyao.test.other.beanMapConvert;

import com.google.common.collect.Lists;
import net.sf.cglib.beans.BeanMap;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanMapUtils {


    /**
     * map转bean
     * @param map
     * @param bean
     * @param <T>
     */
    public static <T> void mapToBean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
    }

    /**
     * map集合转bean集合
     * @param maps
     * @param clazz
     * @param <T>
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T> List<T> mapsToObjects(List<Map<String, Object>> maps, Class<T> clazz) throws InstantiationException, IllegalAccessException {
        List<T> list = Lists.newArrayList();
        if (maps != null && maps.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (int i = 0,size = maps.size(); i < size; i++) {
                map = maps.get(i);
                bean = clazz.newInstance();
                mapToBean(map, bean);
                list.add(bean);
            }
        }
        return list;
    }

}
