package com.nullcognition.learningreactiveprogrammingwithjava8.chapter04;
// ersin 28/09/15 Copyright (c) 2015+ All rights reserved.


import java.util.concurrent.TimeUnit;

import rx.Observable;

public class MapAndFlatMap{

	// MAP -
	// map takes in a method, and applies the operation to each of the results of the observable,
	// producing a new stream of function applied objects

	// FLATMAP -
	// flat map is used for forking logic, where each value from the observable is turned into an
	// observable instance itself, to which its own values are emitted to the stream, which may or
	// may not have order preserved, because each value is now an observable, flatmap is used to
	// subscribe to each of the observables produced and return their values(merged into the stream)

	// ex. emitting files as the value, to which we turn into an observable, which we poll for its
	// contents. The power of flatmap even allows you to turn onNext, onError, onCompleted into
	// observable instances, returning values propagated into the stream with flatmap( (onN) -> Observable.just(onN), ...


	// CONCATMAP -
	// concatMap is like flatmap but will persist the order, where the merge operation will take 2+ streams into one
	// concat will do the same but creates the observable stream in sequential order, appending the result of stream
	// A with the stream B's results such that A,A,A,B,B,B, and not like merge which preserves time A,B,B,A,A,B

	// SWITCHMAP -
	// will switch to the most recent observables emission, unsubscribeing from the current derivative Observable
	// when the next/new begins

	public void swtich(){

		// every 40 milliseconds, a new observable will post its result at 10 millisecond intervals

		Observable<Object> obs = Observable
				.interval(40L, TimeUnit.MILLISECONDS)
				.switchMap(v ->
						Observable
								.timer(0L, 10L, TimeUnit.MILLISECONDS)
								.map(u -> "Observable <" + (v + 1) + "> : " + (v + u)));
	}

}
