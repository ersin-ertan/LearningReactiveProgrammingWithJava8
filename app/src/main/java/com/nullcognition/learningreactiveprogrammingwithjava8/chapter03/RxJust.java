package com.nullcognition.learningreactiveprogrammingwithjava8.chapter03;
// ersin 26/09/15 Copyright (c) 2015+ All rights reserved.


import com.nullcognition.learningreactiveprogrammingwithjava8.Util;

import rx.Observable;

// just for up to nine parameters of the same type, with the root of Object if needed
// or a single object


public class RxJust{

	// emits parameters in onNext, and completes with onComplete

	public void just(){

		// I would assume that primitives are autoboxed
		Observable.just("a", Integer.valueOf(1), 'e', Boolean.TRUE)
		          .subscribe(); // methods for onNext, onError, and onComplete
	}

	public void printUserCredentials(){

		Observable.just(new User("john", "doe"))
		          .map(user -> user.getFirstName() + user.getLastName()) // map applies a function to each emission
				// this could be useful for generalized types like annotations(types, parameters, ...)
		          .subscribe(Util::logger);
	}


	public static class User{

		private final String firstName;
		private final String lastName;

		public User(final String firstName, final String lastName){
			this.firstName = firstName;
			this.lastName = lastName;
		}

		public String getFirstName(){return firstName;}
		public String getLastName(){return lastName;}
	}

}
