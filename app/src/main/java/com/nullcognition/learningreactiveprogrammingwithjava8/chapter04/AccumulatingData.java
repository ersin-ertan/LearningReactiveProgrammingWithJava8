package com.nullcognition.learningreactiveprogrammingwithjava8.chapter04;
// ersin 28/09/15 Copyright (c) 2015+ All rights reserved.


import com.nullcognition.learningreactiveprogrammingwithjava8.Util;

import rx.Observable;

public class AccumulatingData{

	// scan takes a single function which has 2 arguments as the parameters
	// scan(Func2), the first item of scan's result is the first item of the observable source
	// the second result is the first items result, the function applied to the first item and second item

	// in simpler terms - this is like the fibonacci sequence with three components(the start number, the function, the second number)
	// the start number (1) has the function applied to it to get the first number
	// the second number is (2) but once the first number has been computed, the new first number is the second number (2)
	// the second number is now (3)
	// in order to compute the new first number (2), we take the original first number (1) and the new first number (2) and
	// apply the function to it, func(1, 2), which will yield the newly computed first number
	// to be used as the previous number in the next round

	// scan relies on the previous scan()'s result to be a part of the computation for this scan's result
	// so you can say that 'this' result depends on the previous result
	// what was overlooked was the seeding value that is applied in turn with the previous value would be the observables second number

	// ex. function is addition
	// Original observable stream = 1,2,3,4,5
	// computation phase values round01 = {func(1, null)}, round02 = {func(2,1)}, round03 = {func(3, 3)}
	// result of computation to be passed to the next round of computation
	// round01 = 1, round02 = 3, round03 = 6
	// thus this is a recursively composeable function, in which round02 = {func(round01, 2} or = {func(func(1, null), 2)}
	// the second func param need not be sequential, and happens to correspond with the sequence round number for the example
	// it could have been results from another stream as the second number


	public void scan(){
		Observable<Integer> scan = Observable
				.range(1, 10)
				.scan((p, v) -> p + v);
		Util.subscribePrint(scan, "Sum");
		Util.subscribePrint(scan.last(), "Final sum");
	}

	// reduce() is an alias for scan(Func2).last() which would be the final result prior to onComplete

	// as a story, each time Tom meets someone, he gains access to everyone that the new person knows as well, thus
	// after meeting 3 people, who each know 2 other people, the total amount of people known to Tom is
	// 1 person met is actually 3 people because of the association, thus 3 new people would be (1+2)*3=9 people met
	// where 9 people would be the value of scan(func2).last()


	// because the first value does not have a previous value to derive from(null), a seed value may be used with scan(seedNum, Func2)
}
