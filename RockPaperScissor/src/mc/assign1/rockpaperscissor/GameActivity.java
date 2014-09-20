package mc.assign1.rockpaperscissor;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity {

	private static final int ROCK = 0;
	private static final int PAPER = 1;
	private static final int SCISSOR = 2;
	private static String WIN = "win";
	private static String LOSS = "loss";
	private static String DRAW = "draw";
	private String userName;
	private String eMail;
	DBDelegate dbDelegate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		dbDelegate = new DBDelegate(getApplicationContext());
		Button rock = (Button) findViewById(R.id.button1);
		Button paper = (Button) findViewById(R.id.button2);
		Button scissor = (Button) findViewById(R.id.button3);

		final TextView win = (TextView) findViewById(R.id.textView2);
		final TextView loss = (TextView) findViewById(R.id.textView1);
		final TextView draw = (TextView) findViewById(R.id.textView3);
		userName = this.getIntent().getExtras().getString("userName");
		eMail = this.getIntent().getExtras().getString("eMail");
		// dbDelegate.insert(userName.getText().toString(),
		// eMail.getText().toString());

		rock.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(getApplicationContext(), getGameResult(ROCK),
						Toast.LENGTH_SHORT).show();
				dbDelegate.getStats(userName, win, loss, draw);

			}
		});
		paper.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(getApplicationContext(), getGameResult(PAPER),
						Toast.LENGTH_SHORT).show();
				dbDelegate.getStats(userName, win, loss, draw);

			}
		});
		scissor.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(getApplicationContext(), getGameResult(SCISSOR),
						Toast.LENGTH_SHORT).show();
				dbDelegate.getStats(userName, win, loss, draw);
			}
		});

	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
	}

	public String getGameResult(int input) {
		int systemChoice = new Random().nextInt() % 3;
		String result = null;
		switch (input) {
			case ROCK :
				if (systemChoice == PAPER)
					result = LOSS;
				else if (systemChoice == SCISSOR)
					result = WIN;
				else
					result = DRAW;
				break;
			case PAPER :
				if (systemChoice == SCISSOR)
					result = LOSS;
				else if (systemChoice == ROCK)
					result = WIN;
				else
					result = DRAW;
				break;
			case SCISSOR :
				if (systemChoice == ROCK)
					result = LOSS;
				else if (systemChoice == PAPER)
					result = WIN;
				else
					result = DRAW;
				break;
		}
		if (result != null)
			dbDelegate.updateDB(this.userName, this.eMail, result);
		return result;

	}
}
