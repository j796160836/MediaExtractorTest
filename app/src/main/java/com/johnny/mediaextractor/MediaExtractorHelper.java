package com.johnny.mediaextractor;

import android.content.Context;
import android.media.MediaExtractor;
import android.media.MediaFormat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class MediaExtractorHelper {

	private MediaExtractor extractor;
	private MediaFormat trackFormat;

	public MediaExtractor getExtractor() {
		return extractor;
	}

	public MediaFormat getTrackFormat() {
		return trackFormat;
	}

	public void parseFile(Context context) throws IOException {
		File file = new File(getFilePath(context));
		RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");

		extractor = new MediaExtractor();
		extractor.setDataSource(randomAccessFile.getFD());
		extractor.selectTrack(0);
		trackFormat = extractor.getTrackFormat(0);
	}

	public void release() {
		if (extractor != null) {
			extractor.release();
			extractor = null;
		}
	}

	private static String getFilePath(Context context) {
		return context.getFilesDir().getAbsolutePath() + File.separator + "test.mp3";
	}

	public static void copySampleFileToInternalStorage(Context context) throws IOException {
		File file = new File(getFilePath(context));
		InputStream in = context.getResources().openRawResource(R.raw.music01);
		FileOutputStream out = new FileOutputStream(file.getAbsolutePath());
		byte[] buff = new byte[1024];
		int read = 0;
		try {
			while ((read = in.read(buff)) > 0) {
				out.write(buff, 0, read);
			}
		} finally {
			in.close();
			out.close();
		}
	}

}
