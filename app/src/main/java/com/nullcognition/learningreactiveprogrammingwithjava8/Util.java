package com.nullcognition.learningreactiveprogrammingwithjava8;
// ersin 26/09/15 Copyright (c) 2015+ All rights reserved.


import android.util.Log;

import rx.Observable;
import rx.Subscription;
import rx.observables.ConnectableObservable;

public class Util{

	public static void logger(String text){ Log.d("Logger", text);}

	public static Subscription subscribePrint(final Observable published, final String second){

		return published.subscribe(
				(onNext) -> {
					Util.logger(String.valueOf(onNext));
					Util.logger(String.valueOf(second));
				},
				(onErryor) -> {},
				() -> Util.logger("onCompleted"));
	}
}
