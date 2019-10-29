package cn.deliver.test;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.junit.Test;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;

/**
 * @author Ming
 * @date 2019/10/17 - 23:27
 */
public class JavaTest {

    public EnumDemo color;

    @Test
    public void test(){
        color = EnumDemo.GREEN;
        System.out.println(color);
        System.out.println(color.name());
        System.out.println(color.ordinal());
        System.out.println(color.toString());

        EnumDemo[] values = EnumDemo.values();
        System.out.println(Arrays.toString(values));
    }

    @Test
    public void test2(){
        EnumSet<EnumDemo> set = EnumSet.allOf(EnumDemo.class);
        for(EnumDemo enumDemo:set){
            System.out.println(enumDemo);
        }

        EnumMap<EnumDemo,String> map = new EnumMap<EnumDemo, String>(EnumDemo.class);
        map.put(EnumDemo.RED,"red");

    }

}
