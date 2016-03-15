package com.johnny.mediaextractor;

import android.app.Application;
import android.media.MediaFormat;
import android.test.ApplicationTestCase;

import java.io.IOException;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class MediaExtractorHelperTest extends ApplicationTestCase<Application> {
	public MediaExtractorHelperTest() {
		super(Application.class);
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();
		MediaExtractorHelper.copySampleFileToInternalStorage(getContext());
	}

	public void testMediaExtractor() throws IOException {
		int expectSampleRate = 44100;
		String expectMine = "audio/mpeg";

		MediaExtractorHelper helper = new MediaExtractorHelper();
		helper.parseFile(getContext());
		MediaFormat format = helper.getTrackFormat();

		int sampleRate = format.getInteger(MediaFormat.KEY_SAMPLE_RATE);
		String mine = format.getString(MediaFormat.KEY_MIME);

		assertEquals(expectSampleRate, sampleRate);
		assertEquals(expectMine, mine);

		helper.release();
	}
}