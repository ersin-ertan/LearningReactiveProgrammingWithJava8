package com.nullcognition.learningreactiveprogrammingwithjava8.chapter04;
// ersin 28/09/15 Copyright (c) 2015+ All rights reserved.


import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.schedulers.Timestamped;

public class OtherOperators{

	List<Number> list = Arrays.asList(1, 2, 3);

	public void cast(){
		Observable<Integer> iObs = Observable
				.from(list)
				.cast(Integer.class); // short for x -> Class.cast(x)
	}

	// timeInterval and timestamp operators work on the immediate scheduler, using milliseconds

	public void timestamp(){
		// adds a timestamp to each emitted value by transforming it into an instance of the Timestamped<T> class
		// Timestamps : Timestamped(timestampMillis = 1431184924388, value = 1)
		Observable<Timestamped<Number>> timestamp = Observable
				.from(list)
				.timestamp();
	}

	public void timeInterval(){
		// transforms a value to an instance of the TimeInterval<T>, representing the elapsed time since the
		// previous emission. Good for generating statistics
		// Time intervals : TimeInterval [intervalInMilliseconds=142, value=1]

	}
}
