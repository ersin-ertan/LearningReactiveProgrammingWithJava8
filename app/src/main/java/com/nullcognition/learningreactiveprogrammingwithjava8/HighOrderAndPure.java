package com.nullcognition.learningreactiveprogrammingwithjava8;
// ersin 26/09/15 Copyright (c) 2015+ All rights reserved.


import rx.functions.Func1;

public class HighOrderAndPure{ // functions

	// pure functions are those which are idempotent in the computer science sense,
	// allowing for the function to be called repeatedly, returning the same result
	// regardless of time, and only dependant on input, producing no side effects

	public double pureFunc(double input){
		// for input, always return the same output
		return Math.cos(input);
	}

	// higher order functions, or fluent operators, often return itself, or a class
	// which allows for chaining multiple operation on the functions result. Higher
	// order functions have at least one param of type function, or is one of which
	// returns a function.

	public <T, R> int highOrderSummation(Func1<T, Integer> func1, Func1<R, Integer> func2, T data1, R data2){
		return func1.call(data1) + func2.call(data2);
	}

	// or

	public Func1<String, String> greet(String greeting){
		return (String name) -> greeting + " " + name;
	}

	// or

	public Func1<String, String> greet2(String greeting){
		return new Func1<String, String>(){
			@Override public String call(final String name){
				return greeting + " " + name;
			}
		};
	}

	public void usingGreeting(String input){

		String result =
				greet("greeting") // is the function that has the append
						.call("Tom"); // call returns string
	}
	// still a bit confusing
}
