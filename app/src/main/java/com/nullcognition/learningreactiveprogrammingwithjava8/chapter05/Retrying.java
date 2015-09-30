package com.nullcognition.learningreactiveprogrammingwithjava8.chapter05;
// ersin 29/09/15 Copyright (c) 2015+ All rights reserved.


import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

// *** this is complex, spend more time with this ***


public class Retrying{

	// if getting data from an unknown source, an error returned could stop the whole chain
	// thus retry if the values are invalid

	// retry() if an error occurs, the subscriber will unsubscribe then resubscribe starting over again
	// and will retry indefinitely if another error occurs, thus give a retry limit with an int argument

	public void retry(){

		// or with
		// public final Observable<T> retry(rx.functions.Func2<java.lang.Integer, java.lang.Throwable, java.lang.Boolean> predicate)
		Observable.just(1).retry().subscribe(); // like retry and the predicate bust return true for the type of throwable exception for ^

		// for delayed retries use retryWhen()

		Observable<Object> when = Observable.create(new ErrorEmitter())
		                                    .retryWhen(attempts -> attempts.flatMap(error -> {
			                                    if(error instanceof ArithmeticException){
				                                    System.err.println("Delaying...");
				                                    return Observable.timer(1L, TimeUnit.SECONDS);
			                                    }
			                                    return Observable.error(error);
		                                    }))
		                                    .retry((attempts, error) -> {
			                                    return (error instanceof CloneNotSupportedException) && attempts < 3;
		                                    });
	}

	// retryWhen() operator returns an Observable instance, emitting the OnError() or OnCompleted() notifications, the notification
	// is propagated, and if there is no other retry/resume, the onError() or onCompleted() methods of the subscribers are called.
	// Otherwise, the subscribers will be resubscribed to the source observable.


	private class ErrorEmitter implements Observable.OnSubscribe<Object>{

		@Override public void call(final Subscriber<? super Object> subscriber){

		}
	}
}
