package com.roisoftstudio.datastructures;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class MyHashMapTest {

    @Test
    public void canAddAndRetrieveElements() {
        Map<MyBadHashClass, String> myHashMap = new MyHashMap<>();


        MyBadHashClass one = new MyBadHashClass("one");
        MyBadHashClass two = new MyBadHashClass("two");

        assertEquals(one.hashCode(), two.hashCode());

        myHashMap.put(one, "one");
        myHashMap.put(two, "two");

        assertEquals(2, myHashMap.size());
        assertEquals("one", myHashMap.get(one));
        assertEquals("two", myHashMap.get(two));
    }

    public static class MyBadHashClass {
        private String string;

        public MyBadHashClass(String string) {
            this.string = string;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyBadHashClass that = (MyBadHashClass) o;
            return Objects.equals(string, that.string);
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }
}