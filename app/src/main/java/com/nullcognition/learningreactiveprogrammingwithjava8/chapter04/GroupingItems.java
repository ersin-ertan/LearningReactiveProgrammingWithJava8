package com.nullcognition.learningreactiveprogrammingwithjava8.chapter04;
// ersin 28/09/15 Copyright (c) 2015+ All rights reserved.


import com.nullcognition.learningreactiveprogrammingwithjava8.Util;

import java.util.Arrays;
import java.util.List;

import rx.Observable;

public class GroupingItems{

	// items grouped by property or key - groupBy() breaks a source Observable into smaller Observable instances
	// of type GroupedObservable each with its own buffer, emitting some of the sources items depending on
	// the grouping function. -  Groups are retrieved by using the groupKey()

	public void groupBy(){

		List<String> list = Arrays.asList("a", "b", "C");

		Observable
				.from(list)
				.groupBy(
						itemInList -> itemInList.replaceAll("[^mM]", "").length(),
						itemInList -> itemInList.replaceAll("[mM]", "*")
				)
				.subscribe(
						obs -> Util.subscribePrint(obs, obs.getKey() + " occurences of 'm'")
				);
	}

	// groups occurrences of 'm' .length(), and replaces it with an '*',
}
