package com.nullcognition.learningreactiveprogrammingwithjava8.chapter03;
// ersin 27/09/15 Copyright (c) 2015+ All rights reserved.


import java.util.Iterator;

import rx.Observable;
import rx.Subscriber;

public class ObservableCreate{

	<T> Observable<T> basicObserver(final Iterable<T> iterable){

		return Observable.create(new Observable.OnSubscribe<T>(){
			@Override public void call(Subscriber<? super T> subscriber){
				try{
					Iterator<T> iterator = iterable.iterator();
					while(iterator.hasNext()){
						subscriber.onNext(iterator.next());
					}
					subscriber.onCompleted();
				}
				catch(Exception e){
					subscriber.onError(e);
				}
			}
		});
	}

	<T> Observable<T> withUnsubscribeFeatures(final Iterable<T> iterable){

		return Observable.create(new Observable.OnSubscribe<T>(){
			@Override public void call(Subscriber<? super T> subscriber){
				try{
					Iterator<T> iterator = iterable.iterator();
					while(iterator.hasNext()){
						subscriber.onNext(iterator.next());

						// it is possible for a single result to be emitted when unsubscribed
						// if done after the isSubscribed check which is prior to onNext
						if(subscriber.isUnsubscribed()){
							return;
						}
						subscriber.onNext(iterator.next());
					}
					if(!subscriber.isUnsubscribed()){
						subscriber.onCompleted();
					}
				}
				catch(Exception e){
					if(!subscriber.isUnsubscribed()){
						subscriber.onError(e);
					}
				}
			}
		});
	}
}

// types of subscribes: if they do not have an onError method implementation, then the will throw an OnErrorNotImplementedException
// subscribe() // used to trigger OnSubscribe.call, ignores emissions from Observable

// subscribe(Action1<? super T>) subscribes to onNext(), ignores onComplete

// subscribe(Observer<? super T>) uses the observers parameter methods

// subscribe(Subscriber<? super T>) Subscriber implementation of the Observer interface is used to observe notifications, providing advanced functionality

// like unsubscribe, backpressure. ensuring that the subscriber instance passed sees an Observable instance e, complying with the RxContract:
// messages sent to instances of the observer interface follow the syntax of onNext*(onCompleted | onError) by using a wrapper around the passed
// subscriber instance- SafeSubscriber

// unsafeSubscribe(Subscriber<? super T>) same as ^ but without the RxContract protection mostly for implementing custom operators, without overhead of
// subscribe() method protections - Observable observation via this method is discouraged


// Subscriber instances have a void add(Subscription s) method. Every subscription passed to it will be automatically
// 	unsubscribed when the Subscriber is unsubscribed. This way, we can add additional actions to the Subscriber
// instance; for example, actions that should be executed at unsubscribing - pg43
