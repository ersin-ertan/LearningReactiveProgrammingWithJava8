package com.nullcognition.learningreactiveprogrammingwithjava8.chapter03;
// ersin 27/09/15 Copyright (c) 2015+ All rights reserved.


import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;

public class ObservableFactoryMethods{

	// to be used in conjunction with operators like flatmap and zip

	public <T> void subscribePrint(Observable<T> observable, String name){
		observable.subscribe(
				(next) -> Log.d("RXX", name + next.toString()),
				(error) -> Log.d("Rxx", error.getMessage() + name),
				() -> Log.d("RXX", "onCompelete")
		);
	}

	public void interval(){

		// run on computation thread, for continuous logging
		subscribePrint(Observable.interval(500L, TimeUnit.MILLISECONDS), "stringName"); // start delay, then frequency

		// emits 0 after delay then completes
		subscribePrint(Observable.timer(10L, TimeUnit.MILLISECONDS), "stringName"); // delay, emmit, end
		subscribePrint(Observable.error(new Exception("exception")), "stringName");

		subscribePrint(Observable.empty(), "Empty Observable"); // send no emissions other than onComplete
		subscribePrint(Observable.never(), "Never Observable"); // sends nothing
		subscribePrint(Observable.range(1, 3), "Range Observable"); // send sequential numbers, starting from one, of quantity 3

	}


}
