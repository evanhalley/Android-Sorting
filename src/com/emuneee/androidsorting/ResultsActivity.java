/*
 * Copyright 2012 emuneee apps
 * http://emuneee.com/apps
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.emuneee.androidsorting;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Displays the sorting results
 * @author Evan
 *
 */
public class ResultsActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		final TextView textViewDuration;
		final TextView textViewSteps;
		final TextView textViewIntegers;
		final Bundle extras;
		final int[] sortedIntegers;
		final StringBuilder integerString;
		
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.results_activity);
	    extras = getIntent().getExtras();
	    textViewDuration = (TextView) findViewById(R.id.textViewDuration);
	    textViewSteps = (TextView) findViewById(R.id.textViewSteps);
	    textViewIntegers = (TextView) findViewById(R.id.textViewSortedIntegers);
	    
	    integerString = new StringBuilder();
	    sortedIntegers = extras.getIntArray(SortingActivity.INTEGERS);
	    for(int i = 0; i < sortedIntegers.length; i++) {
	    	integerString.append(sortedIntegers[i]);
	    	if(i + 1 < sortedIntegers.length) integerString.append(", ");
	    }
	    
	    textViewDuration.setText("Duration (ms): " + extras.getLong(SortingActivity.DURATION));
	    textViewSteps.setText("Steps: " + extras.getLong(SortingActivity.NUMBER_OF_STEPS));
	    textViewIntegers.setText(integerString.toString());
	}
}
