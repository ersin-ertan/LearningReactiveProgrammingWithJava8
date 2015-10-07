package com.nullcognition.practice0.p00;
// ersin 07/10/15 Copyright (c) 2015+ All rights reserved.


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.ConnectableObservable;

public class P00{

	public void just(){

		Observable<Object> objectObservable = Observable.just("a", new Object(), 3);
		objectObservable.subscribe();

		Observable.just(new Object())
		          .map(new Func1<Object, Object>(){
			          @Override public Object call(final Object o){
				          // for each object on apply this method and return the modified object
				          return null;
			          }
		          });

		objectObservable.subscribe(new Action1<Object>(){
			@Override public void call(final Object o){ }
		});

		objectObservable.subscribe(new Observer<Object>(){
			@Override public void onCompleted(){}
			@Override public void onError(final Throwable e){ }
			@Override public void onNext(final Object o){ }
		});


		objectObservable.subscribe(new Subscriber<Object>(){
			@Override public void onCompleted(){ }
			@Override public void onError(final Throwable e){ }
			@Override public void onNext(final Object o){ }
		});
	}

	public void from(){

		List<String> list = Arrays.asList("a", "b");

		Observable<String> observable = Observable.from(list);


		Subscription sub = observable.subscribe(new Subscriber<String>(){
			@Override public void onCompleted(){

			}
			@Override public void onError(final Throwable e){

			}
			@Override public void onNext(final String s){

			}
		});

		sub.unsubscribe();

		observable.subscribe(
				this::print,
				throwable -> {},
				() -> {}
		);


	}

	public String print(String input){ return input; }

	public void factory(){
		Observable.interval(500L, TimeUnit.SECONDS);
		Observable.timer(44L, TimeUnit.SECONDS);
		Observable.error(new Exception());
		Observable.empty();
		Observable.never();
		Observable.range(2, 5);
	}

	public void createObserable(){

		Observable<Object> obs = Observable.create(new Observable.OnSubscribe<Object>(){

			@Override public void call(final Subscriber<? super Object> subscriber){ }
		});

		obs = Observable.create(subscriber -> {
			subscriber.onStart();
			subscriber.onCompleted();
			subscriber.onError(new Throwable());

			// would be done in the for loop
			subscriber.onNext(new Object());

			subscriber.setProducer(new Producer(){
				@Override public void request(final long n){ }
			});

			subscriber.add(new Subscription(){
				@Override public void unsubscribe(){
				}
				@Override public boolean isUnsubscribed(){
					return false;
				}
			});

		});
	}

	public void hotCold(){

		Observable<Long>            interval             = Observable.interval(34L, TimeUnit.SECONDS);
		ConnectableObservable<Long> inactiveUntilConnect = interval.publish();

		Subscription s = inactiveUntilConnect.subscribe(new Action1<Long>(){
			@Override public void call(final Long aLong){

			}
		});
		// these two are the same
		s = inactiveUntilConnect.subscribe(aLong -> {});

		// can even have onSubscribe code to run

		int willConnectWhenThisManySubscribersSub = 3;
		inactiveUntilConnect.autoConnect(willConnectWhenThisManySubscribersSub);

		inactiveUntilConnect.connect();

		inactiveUntilConnect.connect(new Action1<Subscription>(){
			@Override public void call(final Subscription subscription){
				// this action receives the connection subscription before the sub to source occurs
				// allowing caller to sync disconnect, a sync source
			}
		});

		inactiveUntilConnect.refCount(); // returns observable which stays connected so long as there is at least
		// one observable

		inactiveUntilConnect.repeat(3); // will repeat the sequence 3 times

		inactiveUntilConnect.replay(); // Returns a ConnectableObservable that shares a single subscription to
		// the underlying Observable that will replay all of its items and notifications to any future Observer
		// . A Connectable Observable resembles an ordinary Observable, except that it does not begin
		// emitting items when it is subscribed to, but only when its connect method is calledReturns a
		// ConnectableObservable that shares a single subscription to the underlying Observable that
		// will replay all of its items and notifications to any future Observer. A Connectable
		// Observable resembles an ordinary Observable, except that it does not begin emitting items
		// when it is subscribed to, but only when its connect method is called
	}
}
