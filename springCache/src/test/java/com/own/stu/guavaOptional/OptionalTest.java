package com.own.stu.guavaOptional;

import com.google.common.base.Optional;
import org.junit.Test;

import java.util.Set;

/**
 * Created by bf50 on 2016/2/19.
 */
public class OptionalTest {
    @Test
    public void testOptional() throws Exception {
        Optional<Integer> possible=Optional.of(6);
        Optional<Integer> absentOpt=Optional.absent();
        Optional<Integer> NullableOpt=Optional.fromNullable(null);
        Optional<Integer> NoNullableOpt=Optional.fromNullable(10);
        if(possible.isPresent()){
            System.out.println("possible isPresent:"+possible.isPresent());
            System.out.println("possible value:"+possible.get());
        }
        if(absentOpt.isPresent()){
            System.out.println("absentOpt isPresent:"+absentOpt.isPresent()); ;
        }
        if(NullableOpt.isPresent()){
            System.out.println("fromNullableOpt isPresent:"+NullableOpt.isPresent()); ;
        }
        if(NoNullableOpt.isPresent()){
            System.out.println("NoNullableOpt isPresent:"+NoNullableOpt.isPresent()); ;
        }
    }

    @Test
    public void testMethodReturn() {
        Optional<Long> value = method();
        if(value.isPresent()==true){
            System.out.println("获得返回值: " + value.get());
        }else{
            System.out.println("获得返回值: " + value.or(-12L));
        }

        System.out.println("获得返回值 orNull: " + value.orNull());

        Optional<Long> valueNoNull = methodNoNull();
        if(valueNoNull.isPresent()==true){
            Set<Long> set=valueNoNull.asSet();
            System.out.println("获得返回值 set 的 size : " + set.size());
            System.out.println("获得返回值: " + valueNoNull.get());
        }else{
            System.out.println("获得返回值: " + valueNoNull.or(-12L));
        }

        System.out.println("获得返回值 orNull: " + valueNoNull.orNull());
    }

    private Optional<Long> method() {
        return Optional.fromNullable(null);
    }
    private Optional<Long> methodNoNull() {
        return Optional.fromNullable(15L);
    }

    @Test
    public void testOwn(){
        Optional<String> str1 = Optional.of("String");
        System.out.println(str1.get());

//        Optional<String> str2 = Optional.of(null);

        Optional<String> str3 = Optional.absent();

        Optional<String> str4 = Optional.fromNullable(null);
        System.out.println(str4.isPresent());
//        System.out.println(str4.get());

        String xx = str1.or("xxx");
        System.out.println(xx);

        String xxx = str4.orNull();
        System.out.println(xxx);

        Set<String> set = str1.asSet();
        System.out.println(set);
    }
}
