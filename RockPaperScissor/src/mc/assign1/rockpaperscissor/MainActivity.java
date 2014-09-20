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

				EditText userName = (EditText) findViewById(R.id.inputUsername);
				EditText eMail = (EditText) findViewById(R.id.inputEmail);
				if (userName.getText().length() == 0
						|| eMail.getText().length() == 0) {
					Toast.makeText(getApplicationContext(),
							"Field cannot be empty", Toast.LENGTH_SHORT).show();
				} else {
					dbDelegate.insert(userName.getText().toString(), eMail
							.getText().toString());
					startGame(userName.getText().toString(), eMail.getText()
							.toString());
				}
			}
		});
	}

	public void startGame(String userName, String eMail) {
		Intent gameIntent = new Intent(this, GameActivity.class);
		gameIntent.putExtra("userName", userName);
		gameIntent.putExtra("eMail", eMail);
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
