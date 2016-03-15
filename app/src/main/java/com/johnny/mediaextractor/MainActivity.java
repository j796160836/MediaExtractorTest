package com.johnny.mediaextractor;

import android.media.MediaFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

	private TextView labelMediaInfo;
	private Button buttonGetMediaInfo;
	private MediaExtractorHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		labelMediaInfo = (TextView) findViewById(R.id.label_media_info);

		buttonGetMediaInfo = (Button) findViewById(R.id.button_get_media_info);
		buttonGetMediaInfo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				try {
					helper.parseFile(MainActivity.this);
					MediaFormat format = helper.getTrackFormat();
					if (format != null) {
						labelMediaInfo.setText(format.toString());
					}
					helper.release();
				} catch (IOException e) {
					e.printStackTrace();
					labelMediaInfo.setText(Log.getStackTraceString(e));
				}
			}
		});
		try {
			MediaExtractorHelper.copySampleFileToInternalStorage(this);
		} catch (IOException e) {
			e.printStackTrace();
		}

		helper = new MediaExtractorHelper();
	}
}
