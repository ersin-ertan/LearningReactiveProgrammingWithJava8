package com.nullcognition.learningreactiveprogrammingwithjava8.chapter03;
// ersin 27/09/15 Copyright (c) 2015+ All rights reserved.

// an other way to create hot observables

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

import static com.nullcognition.learningreactiveprogrammingwithjava8.Util.subscribePrint;

public class RxSubjects{

	// both an Observable instance and Observer instance, can have multiple Observer instances which receive the same notification
	// which can turn cold observable instances into hot, also give access to onNext, onError, onCompleted methods

	// when all unsubscribe, will continue to emmit

	public void subjects(){
		Observable<Long> interval = Observable.interval(100L, TimeUnit.MILLISECONDS); // (1)

		// types received, emitted
		Subject<Long, Long> publishSubject = PublishSubject.create(); // (2)
		interval.subscribe(publishSubject);

		Subscription sub1 = subscribePrint(publishSubject, "First");
		Subscription sub2 = subscribePrint(publishSubject, "Second");
		Subscription sub3 = null;
		try{
			Thread.sleep(300L);
			publishSubject.onNext(555L); // (4)
			sub3 = subscribePrint(publishSubject, "Third"); // (5)
			Thread.sleep(500L);
		}
		catch(InterruptedException e){}

		sub1.unsubscribe(); // (6)
		sub2.unsubscribe();
		if(sub3 != null){ sub3.unsubscribe();}
	}
}
// types of subjects
// PublishSubject - behaves like ConnectableObservable created using publish

// ReplaySubject - emits all items that were emitted by the source observable like ConnecteableObbservable and .replay()
// can also be size bound, time bound, can be used without a source Observable instance. all on emissions are passed on

// BehaviorSubject - upon subscription, emits the item most recently emitted by the source, or a seed if none has been
// then continues to emit the updated values from the source, like replay with buffer size of 1,

// AsyncSubject - emits last value only after source observable completes, like a promise, will complete if source
// completes without submitting a value

// try to implement them and behaviour in a method that returns type observable, easy to misuse
// if in need of a hot observable us ConnectableObservable with publish instead

// useful as 'reactive properties' - Reactive properties can be used for implementing bindings and counters, so they are
//very useful for desktop or browser applications


class ReactiveSum{

	private BehaviorSubject<Double> a = BehaviorSubject.create(0.0);
	private BehaviorSubject<Double> b = BehaviorSubject.create(0.0);
	private BehaviorSubject<Double> c = BehaviorSubject.create(0.0);
	public ReactiveSum(){ // (2)
		Observable.combineLatest(a, b, (x, y) -> x + y).subscribe(c);
	}
// c is always updated to be the latest of a + b
	// return Observable and keep the subjects encapsulated and private

	public double getA(){
		return a.getValue();
	}
	public void setA(double a){
		this.a.onNext(a);
	}
	public double getB(){
		return b.getValue();
	}
	public void setB(double b){
		this.b.onNext(b);
	}
	public double getC(){
		return c.getValue();
	}
	public Observable<Double> obsC(){
		return c.asObservable();
	}
}
