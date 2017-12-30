package com.howtoprogram.rxjava2;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RxJavaFlatMapTest {

    @Test
    public void flatMapObservableTest() {

        List<String> sentences = new ArrayList<>();
        sentences.add("Ladybug!  Ladybug!");
        sentences.add("Fly away home.");
        sentences.add("Your house is on fire.");
        sentences.add("And your children all gone.");

        Observable.fromIterable(sentences)
                .flatMap(s -> Observable.fromArray(s.split(" ")))
                .blockingSubscribe(System.out::println);

//        Observable.fromIterable(sentences)
//                .flatMap(s -> Observable.fromArray(s.split(" ")), (s1, s2) -> findOccurrences(s1, s2))
//                .blockingSubscribe(System.out::println);

    }
    @Test
    public void flatMapFlowableTest() {

        List<String> sentences = new ArrayList<>();
        sentences.add("Fly away home.");
        sentences.add("One plus one, two for life");
        sentences.add("Over and over again");

        Flowable.fromIterable(sentences)
                .flatMap(s -> Flowable.fromArray(s.split(" ")))
                .blockingSubscribe(System.out::println);


//        Flowable.fromIterable(sentences)
//                .flatMap(s -> Flowable.fromArray(s.split(" ")), (s1, s2) -> findOccurrences(s1, s2))
//                .blockingSubscribe(System.out::println);

    }

    private static String findOccurrences(String str, String findStr) {

        int lastIndex = 0;
        int count = 0;

        while (lastIndex != -1) {

            lastIndex = str.indexOf(findStr, lastIndex);

            if (lastIndex != -1) {
                count++;
                lastIndex += findStr.length();
            }
        }
        return String.format("'%s' appears %d times in '%s' ", findStr, count, str);
    }

}
