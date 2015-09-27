package com.nullcognition.learningreactiveprogrammingwithjava8.chapter03;
// ersin 26/09/15 Copyright (c) 2015+ All rights reserved.

// Observable factory methods: just, from, create
// observers and subscribers
// hot and cold observables, connectable observables
// what and when of subjects
// creating observables like: Observable.create(OnSubscribe<T>), and others

import android.util.Log;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscription;

// from takes, and is useful for collections an itterables:
// public static final <T> Observable<T> from( java.util.concurrent.Future<? extends T> future)
// public static final <T> Observable<T> from( java.util.concurrent.Future<? extends T> future, long timeout,java.util.concurrent.TimeUnit unit)
// public static final <T> Observable<T> from( java.util.concurrent.Future<? extends T> future, rx.Scheduler scheduler)
// public static final <T> Observable<T> from( @NotNull java.lang.Iterable<? extends T> iterable)
// public static final <T> Observable<T> from( @NotNull T[] array)


public class RxFrom{

	public static final String TAG = "RxFrom";

	public void from(){
		List<String> stringsList = Arrays.asList("a", "b", "c", "d", "e");

		Observable<String> stringObservable = Observable.from(stringsList);

		Subscription subscription = stringObservable.subscribe(RxFrom::logger);

		subscription.isUnsubscribed();
		// subscription assignment not required, just used for additional expression

		// elements in the source list are emitted upon subscription, per subscriber
		stringObservable.subscribe(
				RxFrom::logger, // would have been aString -> RxFrom.logger(aString),
				throwable -> {},
				() -> {}
		);

		stringObservable.subscribe(RxFrom::specialLogger);
	}

	public static void logger(String text){ Log.d(TAG, text);}

	public static void specialLogger(String text){ Log.d("Special", text);}

	// any class that implements iterable can be passed in, including any java collection
	// and file systems that do, but multiple subscribes may throw errors to prevent concurrent modification

}
