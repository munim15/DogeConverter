package com.example.dogeconverter;


import android.os.Bundle;
import android.app.Dialog;
import android.widget.Button;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.view.View.OnClickListener;
import org.json.*;


public class MainActivity extends Activity {
	
	private EditText value1;
	//private EditText value2;
	private TextView value2;
	private TextView value3;
	private double val1;
	private static double btcRate = 0.0012;
	private static double dogeRate = 740;
	private static double btc2dogeRate = 595103;
	private static final String API_URL = "https://openexchangerates.org/api/latest.json?app_id=c79fdb6d26b04d55acd1e66e190800f7";//PUT YOUR API KEY HERE
	private static final String API_URL2 = "https://api.vircurex.com/api/get_highest_bid.json?base=USD&alt=DOGE";
	private static final String API_URL3 = "https://api.vircurex.com/api/get_highest_bid.json?base=BTC&alt=DOGE";
	//private double val2;
	//private double conversionRate;
	RadioButton rb0;
	RadioButton rb1;
	RadioButton rb2;
	RadioButton rb3;
	RadioButton rb4;
	RadioButton rb5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		value1 = (EditText) findViewById(R.id.value1);
		value2 = (TextView) findViewById(R.id.value2);
		value3 = (TextView) findViewById(R.id.textView3);
		value1.addTextChangedListener(value1Listner);
		RadioGroup  group1 = (RadioGroup) findViewById(R.id.radioGroup1);
		RadioGroup  group2 = (RadioGroup) findViewById(R.id.radioGroup2);
		group1.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                	@Override
                    public void onCheckedChanged(RadioGroup group,
                            int checkedId) {
                    	updateval2();
                    }
                });
		group2.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                	@Override
                    public void onCheckedChanged(RadioGroup group,
                            int checkedId) {
                    	updateval2();
                    }
                });
		Button btn = (Button)findViewById(R.id.button1);
		
btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
					AsyncHttpClient client = new AsyncHttpClient();
					client.get(API_URL, new AsyncHttpResponseHandler() {


						@Override
						public void onFinish() {
							// TODO Auto-generated method stub
							super.onFinish();
						}

						@Override
						public void onStart() {
							// TODO Auto-generated method stub
							super.onStart();
						}
						@Override
						public void onFailure(Throwable arg0, String arg1) {
							// TODO Auto-generated method stub
							
						}
						@Override
						public void onFailure(Throwable e) {
							
													}
					
						public void onFailure(Throwable e, JSONArray errorResponse) {
							
						}

						@Override
						public void onSuccess(String response) {

							try {
								JSONObject jsonObj = new JSONObject(response);
								JSONObject ratesObject = jsonObj
										.getJSONObject("rates");

								
								btcRate = ratesObject.getDouble("BTC");
									
								     
									
								 

							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					});
					client.get(API_URL2, new AsyncHttpResponseHandler() {


						@Override
						public void onFinish() {
							// TODO Auto-generated method stub
							super.onFinish();
						}

						@Override
						public void onStart() {
							// TODO Auto-generated method stub
							super.onStart();
						}
						@Override
						public void onFailure(Throwable arg0, String arg1) {
							// TODO Auto-generated method stub
							
						}
						@Override
						public void onFailure(Throwable e) {
							
						}
					
						public void onFailure(Throwable e, JSONArray errorResponse) {
							
						}

						@Override
						public void onSuccess(String response) {

							try {
								JSONObject jsonObj = new JSONObject(response);
								dogeRate = jsonObj.getDouble("value");									
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					});
					client.get(API_URL3, new AsyncHttpResponseHandler() {


						@Override
						public void onFinish() {
							// TODO Auto-generated method stub
							super.onFinish();
						}

						@Override
						public void onStart() {
							// TODO Auto-generated method stub
							super.onStart();
						}
						@Override
						public void onFailure(Throwable arg0, String arg1) {
							// TODO Auto-generated method stub
							
						}
						@Override
						public void onFailure(Throwable e) {
							
						}
					
						public void onFailure(Throwable e, JSONArray errorResponse) {
							
						}

						@Override
						public void onSuccess(String response) {

							try {
								JSONObject jsonObj = new JSONObject(response);
								btc2dogeRate = jsonObj.getDouble("value");
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					});
					Time today = new Time(Time.getCurrentTimezone());
					today.setToNow();
					value3.setText("Last updated at " + today.format("%k:%M:%S"));
					updateval2();
			}
			
		});
	}
	private TextWatcher value1Listner = new TextWatcher() {

		@Override
		public void afterTextChanged(Editable arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			try {
				val1 = Double.parseDouble(arg0.toString());
			} catch(NumberFormatException e) {
				val1 = 0;
			}
			updateval2();
		}
		
	};
	
	private void updateval2() {
		rb0 = (RadioButton) findViewById(R.id.radio0);
		rb1 = (RadioButton) findViewById(R.id.radio1);
		rb2 = (RadioButton) findViewById(R.id.radio2);
		rb3 = (RadioButton) findViewById(R.id.radio3);
		rb4 = (RadioButton) findViewById(R.id.radio4);
		rb5 = (RadioButton) findViewById(R.id.radio5);
		double x = 0;
		if (rb0.isChecked() && rb3.isChecked()) {
			x = 1;
		}
		else if (rb1.isChecked() && rb4.isChecked()) {
			x = 1;
		}
		else if (rb2.isChecked() && rb5.isChecked()) {
			x = 1;
		}
		else if (rb0.isChecked() && rb4.isChecked()) {
			x = btcRate;
		}
		else if (rb1.isChecked() && rb3.isChecked()) {
			x = 1 / btcRate;
		}
		else if (rb0.isChecked() && rb5.isChecked()) {
			x = dogeRate;
		}
		else if (rb2.isChecked() && rb3.isChecked()) {
			x = 1 / dogeRate;
		}
		else if (rb1.isChecked() && rb5.isChecked()) {
			x = btc2dogeRate;
		}
		else if (rb2.isChecked() && rb4.isChecked()) {
			x = 1 / btc2dogeRate;
		}
		
		
		value2.setText("" + (val1 * x));
		
	}
	
	private void color(int id) {
		View someView = findViewById(R.id.button1);
		View root = someView.getRootView();
		root.setBackgroundColor(getResources().getColor(id));
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		//return true;
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_activity_actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_about:
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog);
                dialog.setTitle("Info");
                TextView text = (TextView) dialog.findViewById(R.id.text);
                text.setText("Version: 1.0 \nConversion Rates Source: VIRCUREX \nDevelopers: Hilfinger's Minions Inc.");
                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
    			dialogButton.setOnClickListener(new OnClickListener() {
    				@Override
    				public void onClick(View v) {
    					dialog.dismiss();
    				}
    			});
    			dialog.show();
	            return true;
	        case R.id.action_color1:
	        	color(android.R.color.white);
	        	return true;
	        case R.id.action_color2:
	        	color(android.R.color.holo_blue_bright);
	        	return true;
	        case R.id.action_color3:
	        	color(android.R.color.holo_red_light);
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}
