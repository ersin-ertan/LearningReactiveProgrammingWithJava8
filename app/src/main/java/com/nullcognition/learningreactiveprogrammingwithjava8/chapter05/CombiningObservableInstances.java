package com.nullcognition.learningreactiveprogrammingwithjava8.chapter05;
// ersin 29/09/15 Copyright (c) 2015+ All rights reserved.


import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import rx.Observable;

public class CombiningObservableInstances{

	/*

Combining the Observable instances using operators such as
combineLatest() , merge() , concat() , and zip()

Creating dependencies between the Observable instances using conditional
operators such as takeUntil() , skipUntil() , and amb()

Error handling using operators such as retry() , onErrorResumeNext() ,
and onErrorReturn()

	*/

	public void zip(){
		Observable<Integer> zip = Observable
				.zip(
						Observable.just(1, 3, 4),
						Observable.just(5, 2, 6),
						(a, b) -> a + b
				);
		// zips stream one's values the correspondiing value in the other stream


		// intervaled zip
		Observable<String> timedZip = Observable
				.zip(
						Observable.from(Arrays.asList("Z", "I", "P", "P")),
						Observable.interval(300L, TimeUnit.MILLISECONDS),
						(value, i) -> value
				);

		// or

		Observable<String> timedZipWith = Observable
				.from(Arrays.asList("Z", "I", "P", "P"))
				.zipWith(
						Observable.interval(300L, TimeUnit.MILLISECONDS),
						(value, skip) -> value
				); // for the same functionality
	}

	public void combineLatest(){

		// given three observable sources, s1, s2, s3
		// which are zipWith an interval, combineLatest would combine the latest value of either stream one or stream two
		// to produce the next emission

//		Observable<String> combined = Observable
//				.combineLatest(
//						s1s, s2s, s3s,
//						(s1, s2, s3) ->
//								s1 + " " + s2 + s3);
	}

	public void merge(){
		// merges the values of two or more streams into one retaining their temporal position
	}

	public void concat(){
		// like merge but will output all of the streams values sequentially, then append the next streams values
	}


}
