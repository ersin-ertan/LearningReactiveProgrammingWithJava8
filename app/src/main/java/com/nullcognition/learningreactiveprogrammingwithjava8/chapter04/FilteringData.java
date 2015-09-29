package com.nullcognition.learningreactiveprogrammingwithjava8.chapter04;
// ersin 28/09/15 Copyright (c) 2015+ All rights reserved.


import com.nullcognition.learningreactiveprogrammingwithjava8.Util;

import rx.Observable;

public class FilteringData{

	// .filter() creates a new observable source, which emits value which comply
	// to the condition set inside the filter

	public void filter(){
		Observable<Integer> numbers = Observable
				.just(1, 13, 32, 45, 21, 8, 98, 103, 55);
		Observable<Integer> filter = numbers
				.filter(n -> n % 2 == 0);
		Util.subscribePrint(filter, "Filter");
	}
}
// takeLast(4) will create an observable which only emits the last four values of an observable
// takeLastBuffer(4) same as above but emitted as single list containing the four values

// take(N) takes the first N values
// takeFirst() - error if no emission
// firstOrDefault

// last() will only return the value prior to onComplete, if no emission, exception will be thrown onError
// lastOrDefault("defaultValue") same as above, but initialized with default value protecting it from an onError

// skipLast(2) will display all but the last two values
// skip(2) will skip the first two values

// elementAt in the stream
// elementOrDefault if no value, will default

// distinct will not repeat elements
// distinctUntilChanged only the previous must be distinct to be emitted, will skip repetitions that are adjacent in the stream

// ofType for class type emissions
