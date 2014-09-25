package mc.assign1.rockpaperscissor;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends Activity implements OnTouchListener {

	private static final int ROCK = 0;
	private static final int PAPER = 1;
	private static final int SCISSOR = 2;
	private static String WIN = "win";
	private static String LOSS = "loss";
	private static String DRAW = "draw";
	private String userName;
	TextView Result;
	DBDelegate dbDelegate;
	TextView win;
	TextView loss;
	TextView draw;
	TextView user, opponent;
	ImageView ivopp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		dbDelegate = new DBDelegate(getApplicationContext());

		win = (TextView) findViewById(R.id.textView5);
		loss = (TextView) findViewById(R.id.textView6);
		draw = (TextView) findViewById(R.id.textView7);
		user = (TextView) findViewById(R.id.textView11);
		opponent = (TextView) findViewById(R.id.textView12);
		Result = (TextView) findViewById(R.id.textView13);
		userName = this.getIntent().getExtras().getString("userName");
		final ImageView iv = (ImageView) findViewById(R.id.imageView1);
		ivopp = (ImageView) findViewById(R.id.imageView2);
		Context cnt = null;
		dbDelegate.getStats(userName, win, loss, draw);
		iv.setOnTouchListener(new OnSwipeTouchListener(cnt) {

			public void onSwipeRight() {
				iv.setImageResource(R.drawable.paper);
				getGameResult(PAPER);
				dbDelegate.getStats(userName, win, loss, draw);
				Runnable runnable = new Runnable() {
					@Override
					public void run() {
						iv.setImageResource(R.drawable.abc_ab_solid_dark_holo);
						ivopp.setImageResource(R.drawable.opponent);
					}
				};
				iv.postDelayed(runnable, 2000);
			}
			public void onSwipeLeft() {
				iv.setImageResource(R.drawable.rock);
				getGameResult(ROCK);
				dbDelegate.getStats(userName, win, loss, draw);
				Runnable runnable = new Runnable() {
					@Override
					public void run() {
						iv.setImageResource(R.drawable.abc_ab_solid_dark_holo);
						ivopp.setImageResource(R.drawable.opponent);
					}
				};
				iv.postDelayed(runnable, 2000);
			}
			public void onSwipeBottom() {
				iv.setImageResource(R.drawable.scissors);
				getGameResult(SCISSOR);
				dbDelegate.getStats(userName, win, loss, draw);
				Runnable runnable = new Runnable() {
					@Override
					public void run() {
						iv.setImageResource(R.drawable.abc_ab_solid_dark_holo);
						ivopp.setImageResource(R.drawable.opponent);
					}
				};
				iv.postDelayed(runnable, 2000);
			}

			public boolean onTouch(View v, MotionEvent event) {
				return gestureDetector.onTouchEvent(event);
			}
		});
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		// System.out.println(event.getPointerCount());
		return super.onTouchEvent(event);
	}

	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this)
				.setMessage("Are you sure you want to exit?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								GameActivity.this.finish();
							}
						}).setNegativeButton("No", null).show();
	}

	public String getGameResult(int input) {
		int systemChoice;
		Random randomGenerator = new Random();
		systemChoice = randomGenerator.nextInt(3);
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
				if (systemChoice == ROCK) {
					result = LOSS;
				} else if (systemChoice == PAPER) {
					result = WIN;
				} else {
					result = DRAW;
				}
				break;
		}
		if (result != null) {
			displayTextboxes(input, systemChoice, result);
			dbDelegate.updateDB(this.userName, result);
		}
		return result;

	}
	private void displayTextboxes(int userinput, int oppinput, String result) {
		// TODO Auto-generated method stub
		System.out.println("User: " + userinput + " oppinput: " + oppinput
				+ " Resu: " + result);
		Result.setText(result);
		if (userinput == 0) {
			user.setText("Rock");
		} else if (userinput == 1) {
			user.setText("Paper");
		} else {
			user.setText("Scissors");
		}
		if (oppinput == 0) {
			opponent.setText("Rock");
			ivopp.setImageResource(R.drawable.rock);
		} else if (oppinput == 1) {
			opponent.setText("Paper");
			ivopp.setImageResource(R.drawable.paper);
		} else {
			opponent.setText("Scissors");
			ivopp.setImageResource(R.drawable.scissors);
		}
		if (result == WIN) {
			Result.setTextColor(Color.GREEN);
		} else if (result == LOSS) {
			Result.setTextColor(Color.RED);
		} else {
			Result.setTextColor(Color.BLUE);
		}

	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
}
