package mc.assign1.rockpaperscissor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private DBDelegate dbDelegate;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button okButton = (Button) findViewById(R.id.ok);
		dbDelegate = new DBDelegate(getApplicationContext());
		okButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				EditText userName = (EditText) findViewById(R.id.inputUserName);
				EditText age = (EditText) findViewById(R.id.inputAge);
				if (userName.getText().length() == 0
						|| age.getText().length() == 0) {
					Toast.makeText(getApplicationContext(),
							"Field cannot be empty", Toast.LENGTH_SHORT).show();
				} else if (!validateAge(age.getText().toString())) {
					Toast.makeText(getApplicationContext(),
							"Age contains invalid char(s)", Toast.LENGTH_SHORT)
							.show();
					age.requestFocus();

				} else {
					RadioGroup radioSex = (RadioGroup) findViewById(R.id.radioSex);
					int selectedSex = radioSex.getCheckedRadioButtonId();
					RadioButton sex = (RadioButton) findViewById(selectedSex);

					if (!dbDelegate.isUserExists(userName.getText().toString(),
							Integer.parseInt(age.getText().toString()), sex
									.getText().toString())) {

						dbDelegate.insert(userName.getText().toString(),
								Integer.parseInt(age.getText().toString()), sex
										.getText().toString());
					}
					startGame(userName.getText().toString());
				}

			}
		});
	}
	private boolean validateAge(String age) {
		for (int i = 0; i < age.length(); i++) {
			if (!Character.isDigit(age.charAt(i)))
				return false;
		}
		return true;
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
	public void startGame(String userName) {
		Intent gameIntent = new Intent(this, GameActivity.class);
		gameIntent.putExtra("userName", userName);
		startActivity(gameIntent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
	}

}
