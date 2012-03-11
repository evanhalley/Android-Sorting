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

import java.util.Calendar;
import java.util.Random;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SortingActivity extends Activity {
	public final static String INTEGERS = "integers";
	public final static String DURATION = "duration";
	public final static String NUMBER_OF_STEPS = "numberOfSteps";
	
	private enum SortingAlgorithm {
		BubbleSort
	}
	
	private SortingAlgorithm sortingAlgorithm;
	private int[] integersToSort;
	private final Random random = new Random(10000);
	private EditText textViewIntegers;
	private Spinner spinnerAlgorithms;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sorting_activity);
        
        textViewIntegers = (EditText) findViewById(R.id.editTextIntegers);
        spinnerAlgorithms = (Spinner) findViewById(R.id.spinnerSortingAlgorithms);
        spinnerAlgorithms.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, 
        			getResources().getStringArray(R.array.sorting_algorithms))); 
        
        ((Button) findViewById(R.id.buttonGenerateInts)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				int integersToGen;
				final StringBuilder integerString;
				
				integerString = new StringBuilder();
				// generate out random integers
				integersToGen = Integer.parseInt(
						((EditText) SortingActivity.this.findViewById(R.id.editTextGenerateSize))
						.getText().toString());
				integersToSort = new int[integersToGen];
				for(int i = 0; i < integersToGen; i++) {
					integersToSort[i] = random.nextInt(10000);
					integerString.append(integersToSort[i]);
					if(i + 1 < integersToGen) {
						integerString.append(", ");
					}
				}
				//populate our text box with them
				textViewIntegers.setText(integerString.toString());
			}
        });
        
        ((Button) findViewById(R.id.buttonSort)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				sortingAlgorithm = SortingAlgorithm
						.values()[spinnerAlgorithms.getSelectedItemPosition()];
				(new SortAsyncTask()).execute(0);
			}
        	
        });
    }
    
    private class SortAsyncTask extends AsyncTask<Integer, Boolean, Boolean> {
    	private Long duration;
    	private ProgressDialog dialog; 
    	private int[] sorted;
    	private String error;
    	
    	protected void onPreExecute() {
    		dialog = ProgressDialog.show(
    				SortingActivity.this, "Sorting...", "Sorting...");
    		dialog.show();
    	}
    	
		@Override
		protected Boolean doInBackground(Integer... arg0) {
			
			try {
				duration = Calendar.getInstance().getTimeInMillis(); 
				switch(sortingAlgorithm) {
					case BubbleSort:
						sorted = SortingUtils.bubbleSortInt(integersToSort);
						break;
					default:
						break;
				}
				
				duration = Calendar.getInstance().getTimeInMillis() - duration;
				return true;
			} catch (Exception e) {
				error = e.getMessage();
				return false;
			}
		}
		
		protected void onPostExecute(Boolean result) {
			final Intent intent;
			
			dialog.dismiss();
			if(result) {
				intent = new Intent(SortingActivity.this, ResultsActivity.class);
				intent.putExtra(INTEGERS, sorted);
				intent.putExtra(DURATION, duration);
				intent.putExtra(NUMBER_OF_STEPS, SortingUtils.numberOfSteps);
				SortingActivity.this.startActivity(intent);
			} else {
				Toast.makeText(SortingActivity.this, error, Toast.LENGTH_LONG).show();
			}
		}
    	
    }
}