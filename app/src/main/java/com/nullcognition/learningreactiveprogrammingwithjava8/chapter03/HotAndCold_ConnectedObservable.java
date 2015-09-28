package com.nullcognition.learningreactiveprogrammingwithjava8.chapter03;
// ersin 27/09/15 Copyright (c) 2015+ All rights reserved.


import com.nullcognition.learningreactiveprogrammingwithjava8.Util;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.observables.ConnectableObservable;

import static com.nullcognition.learningreactiveprogrammingwithjava8.Util.*;

public class HotAndCold_ConnectedObservable{ // observable instances


	// create, just, from are inactive until a subscriber subscribes, don't emit
	// Cold Observables produce notifications on demand, and for every Subscriber, they produce independent notifications

	// hot observables produce notifications continuously, regardless of who has subscribed, producing the same notifications
	// for every subscriber


	// ConnectedObservable: are inactive until connect() is called, becoming hot
	// can be created from any observable by calling publish(), turning cold to hot

	public void connectableObservable(){

		Observable<Long> interval = Observable.interval(100L, TimeUnit.MILLISECONDS);

		ConnectableObservable<Long> published = interval.publish();

		Subscription s1 = subscribePrint(published, "first");
		Subscription s2 = subscribePrint(published, "second");
		published.connect();
		Subscription s3 = null;

		try{
			Thread.sleep(500L);
			s3 = subscribePrint(published, "third");
			Thread.sleep(500L);
		}
		catch(InterruptedException ignored){}

		s1.unsubscribe();
		s2.unsubscribe();
		if(s3 != null){ s3.unsubscribe(); }
	}
}
// first two subs will see values then third will join in to see the same values, but not the ones prior
// lest you use replay() instead of publish, which will synchronously replay all emitted values to new subscribers

// publish can also append the .refCount() method, which will nullify the need to connect, and will begin emitting on
// the first subscription, likewise, it will count how many references it has and stop when all subs have unsubbed

// emissions will restart upon re subscription to an empty refcounted observable

// share can be used in place of the publish().refCount() combination
