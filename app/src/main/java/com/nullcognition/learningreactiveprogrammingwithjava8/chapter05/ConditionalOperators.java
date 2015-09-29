package com.nullcognition.learningreactiveprogrammingwithjava8.chapter05;
// ersin 29/09/15 Copyright (c) 2015+ All rights reserved.


import rx.Observable;

public class ConditionalOperators{

	// conditions may block emissions until another stream emits, or will block emissions if another does emit
	// the ability to control when an Observable emits is done via conditions

	public void amb(){
		// take from two up to nine source Observable instances or an Iterable instance of the Observable instances
		// emits from the first source that sends any kind of data, be it: OnError , OnCompleted, or onNext
		// good for caching values when the source does not matter, thus the first stream to emit will be observed
		// also an instance form ambWith(), instead of writing Observable.amb(o1,o2) you could also write o1.ambWith(o2) for the same effect.
	}

	// takeUntil - takeWhile - skipUntil - skipWhile
	public void whileAndUntil(){

		// given a source, sourceObservable.takeUntil(source2Observable)
		// will take/emit values of the source observable until the other observable emits

		// while will ensure a condition is met for the emission to occur
		// the condition can be another observable, or a logic operation on the values being emitted
		// like someObservable.takeWhile(emissionOfWords -> emissionOfWords.length() > 2)

		// skip has the same semantics applied to it
	}

	public void defaultEmpty(){
		Observable<Object> test = Observable
				.empty()
				.defaultIfEmpty(5); // if the source is empty will default the value
	}


}
