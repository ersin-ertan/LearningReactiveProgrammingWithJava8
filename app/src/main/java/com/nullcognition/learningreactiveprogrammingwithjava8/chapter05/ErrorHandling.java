package com.nullcognition.learningreactiveprogrammingwithjava8.chapter05;
// ersin 29/09/15 Copyright (c) 2015+ All rights reserved.


import rx.Observable;

public class ErrorHandling{

	// terminate the chain of actions, once in onError, you can't go back, but you can execute backup logic
	// with return, resume, retry based operators

	// doOnTerminate() runs BEFORE the Subscriber, finallyDo() runs AFTER the Subscriber

	public void onErrorReturn(){

		// will throw NumberFormatException on three, thus will return -1 and then onComplete

		Observable<Integer> numbers = Observable
				.just("1", "2", "three", "4", "5")
				.map(Integer::parseInt)
				.onErrorReturn(e -> -1);
	}

	public void onExceptionResumeNext(){ // reacts only to exceptions

		// will switch to a new Observable stream onException

		Observable<Integer> defaultOnError = Observable.just(5, 4, 3, 2, 1);

		Observable<Integer> numbers = Observable
				.just("1", "2", "three", "4", "5")
				.map(Integer::parseInt)
				.onExceptionResumeNext(defaultOnError);
	}

	public void onErrorResumeNext(){ // reacts to any kind of error

		Observable<Integer> defaultOnError = Observable.just(5, 4, 3, 2, 1);

		Observable<Integer> numbers = Observable.just("1", "2", "three", "4", "5")
		                                        .doOnNext(s -> { assert !"three".equals(s);})
		                                        .map(Integer::parseInt)
		                                        .onErrorResumeNext(defaultOnError);

	}

	// doOnNext - is a side effect generator, which does not change the emission value, and can be used to contain logging code

	// doOnError()	and doOnCompleted() operators

	// finallyDo()  executes the function passed to it when there is an error or when the Observable instance has completed.


}
