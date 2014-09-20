package mc.assign1.rockpaperscissor;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GameActivity extends Activity {

	private static final int ROCK = 0;
	private static final int PAPER = 1;
	private static final int SCISSOR = 2;
	private static String WIN = "win";
	private static String LOSS = "loss";
	private static String DRAW = "draw";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		Button rock = (Button) findViewById(R.id.button1);
		Button paper = (Button) findViewById(R.id.button2);
		Button scissor = (Button) findViewById(R.id.button3);

		rock.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(getApplicationContext(), getGameResult(ROCK),
						Toast.LENGTH_SHORT).show();

			}
		});
		paper.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(getApplicationContext(), getGameResult(PAPER),
						Toast.LENGTH_SHORT).show();

			}
		});
		scissor.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(getApplicationContext(), getGameResult(SCISSOR),
						Toast.LENGTH_SHORT).show();

			}
		});

	}
	public String getGameResult(int input) {
		int systemChoice = new Random().nextInt() % 3;
		switch (input) {
			case ROCK :
				if (systemChoice == PAPER)
					return LOSS;
				else if (systemChoice == SCISSOR)
					return WIN;
				else
					return DRAW;
			case PAPER :
				if (systemChoice == SCISSOR)
					return LOSS;
				else if (systemChoice == ROCK)
					return WIN;
				else
					return DRAW;
			case SCISSOR :
				if (systemChoice == ROCK)
					return LOSS;
				else if (systemChoice == PAPER)
					return WIN;
				else
					return DRAW;
		}
		return "Invalid";

	}
}
