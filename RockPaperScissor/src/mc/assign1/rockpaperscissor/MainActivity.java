package mc.assign1.rockpaperscissor;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	DBDelegate dbDelegate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dbDelegate = new DBDelegate(getApplicationContext());
		Button okButton = (Button) findViewById(R.id.ok);
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

					Toast.makeText(getApplicationContext(),
							dbDelegate.getCount() + " record(s) present",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
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
}
