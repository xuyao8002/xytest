package com.xuyao.test.io.zip;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIP {

    public static byte[] compress(Object object) throws IOException {
        if(object != null){
            String jsonStr = JSON.toJSONString(object);
            byte[] bytes = jsonStr.getBytes();
            ByteArrayOutputStream bos = null;
            GZIPOutputStream gos = null;
            try{
                bos = new ByteArrayOutputStream();
                gos = new GZIPOutputStream(bos);
                gos.write(bytes);
                gos.finish();
                return bos.toByteArray();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                IOUtils.close(bos);
                IOUtils.close(gos);
            }
        }
        return null;
    }

    public static <T> T uncompress(byte[] bytes, Class clazz) throws IOException {
        if(bytes != null){
            ByteArrayOutputStream bos = null;
            ByteArrayInputStream bis = null;
            GZIPInputStream gis = null;
            int n;
            byte[] buff = new byte[4096];
            try{
                bos = new ByteArrayOutputStream();
                bis = new ByteArrayInputStream(bytes);
                gis = new GZIPInputStream(bis);
                while((n = gis.read(buff, 0, 4096)) > 0){
                    bos.write(buff, 0, n);
                }
                return JSON.parseObject(bos.toByteArray(), clazz);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                IOUtils.close(bos);
                IOUtils.close(bis);
                IOUtils.close(gis);
            }
        }
        return null;
    }

}
