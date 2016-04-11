package com.example.guessinggame_cak;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.guessinggame_cak.R;

/*
	Settings pane for the main activity
	Generally focuses on changing variables that affect the game
*/

public class SettingsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		initRangeButtons();
		initSettings();
		initBackButton();
		initLoseButton();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void initSettings() {
		int gameRange = getValueDefault("rangeKey", 100);

		RadioButton rb10 = (RadioButton) findViewById(R.id.radio10);
		RadioButton rb100 = (RadioButton) findViewById(R.id.radio100);
		RadioButton rb1000 = (RadioButton) findViewById(R.id.radio1000);

		if (gameRange == 10) {
			rb10.setChecked(true);
		}
		else if (gameRange == 100) {
			rb100.setChecked(true);
		}
		else {
			rb1000.setChecked(true);
		}			

	}

	public int getValueDefault(String key, int defaultInt)
	{
		SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);

		return prefs.getInt(key, defaultInt);
	}

	public void storeValue(int value, String key)
	{
		SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);

		Editor edit = prefs.edit();
		edit.putInt(key, value);
		edit.commit();
	}

	private void initRangeButtons() {
		RadioGroup rgSortBy = (RadioGroup) findViewById(R.id.radioGroupRange);
		rgSortBy.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				RadioButton rb10 = (RadioButton) findViewById(R.id.radio10);
				RadioButton rb100 = (RadioButton) findViewById(R.id.radio100);

				if (rb10.isChecked()) {
					storeValue(10, "rangeKey");
				}
				else if (rb100.isChecked()) {
					storeValue(100, "rangeKey");
				}
				else {
					storeValue(1000, "rangeKey");
				}			
			}		
		});
	}

	private void initLoseButton() {
		final Button btnLoseToggle = (Button) findViewById(R.id.btnLoseToggle);
		btnLoseToggle.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				int loseDisabled = getValueDefault("loseStateKey", 0);

				if (loseDisabled == 0)
				{
					storeValue(1, "loseStateKey");
					Toast toast = Toast.makeText(getApplicationContext(), "Losing disabled.", Toast.LENGTH_SHORT);
					toast.show();
				}
				else
				{
					storeValue(0, "loseStateKey");
					Toast toast = Toast.makeText(getApplicationContext(), "Losing enabled!", Toast.LENGTH_SHORT);
					toast.show();
				}

			}
		});
	}

	private void initBackButton() {
		final Button btnSettings = (Button) findViewById(R.id.btnReturn);
		btnSettings.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
	}

}