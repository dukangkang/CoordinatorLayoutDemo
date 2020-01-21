package com.docking.coordinatorlayout.tpg;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.docking.coordinatorlayout.coordinatorlayout.demo.R;

public class TPGActivity extends Activity {

	public static final String TAG = "TPGDemo";

	final static int MAX_WIDTH = 2208;
	final static int MAX_HEIGHT = 1240;

	private static final int MESSAGE_DECODE_TPG_START = 1;
	private static final int MESSAGE_DECODE_TPG_FINISH = 3;
	private static final int MESSAGE_DISPAY = 4;

	String inpath = Environment.getExternalStorageDirectory().getPath()
			+ "/TPGTest/in/";
	String TPGpath = Environment.getExternalStorageDirectory().getPath()
			+ "/TPGTest/TPG/";
	String outpath = Environment.getExternalStorageDirectory().getPath()
			+ "/TPGTest/out/";

	// FileOutputStream TPGFile = null;
	// String decFileName;
	long dec_time = 0;
	long pic_num = 0;
	private TextView mTextViewTPGTime;
	private ImageView mImageView;

	Thread tDecodeTPG;


	@SuppressLint("HandlerLeak")
	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MESSAGE_DECODE_TPG_START:
				mTextViewTPGTime.setText("TPG Decode is running");
				break;
			case MESSAGE_DECODE_TPG_FINISH:
				long time = (Long) msg.obj;
				mTextViewTPGTime
						.setText("TPG Decode Finished! Total Time: " + time
								+ "ms");
				break;
			case MESSAGE_DISPAY:
				Bitmap bitmap = (Bitmap) msg.obj;
				mImageView.setImageBitmap(bitmap);
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
    	Log.v("cxx", "onCreate[run]");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tpg_main);
		mTextViewTPGTime = (TextView) findViewById(R.id.tvTPGTime);
		mImageView = (ImageView) findViewById(R.id.preview);
		Log.v("cxx", "onCreate[11]");

		System.loadLibrary("TPGDecoder");

//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//
//		    	Log.v("cxx", "onCreate[run]");
//			}
//		}).start();

	}

	public void clickDecodeTPG(View source) {
		tDecodeTPG = new Thread() {
			@Override
			public void run() {
				decodeTPGtoRGBDir(TPGpath, outpath);
				decodeTPGtoRGBFile(TPGpath+"3.jpg", outpath);
			}
		};
		tDecodeTPG.start();
		System.out.println("TPGTest Decode: All pictures finished!");
		// decodeTPGtoRGBMultiThread(TPGpath, outpath);
	}

	public Bitmap decodeImage(String path) {

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		Bitmap bitmap = BitmapFactory.decodeFile(path, options);
		return bitmap;
	}

	public byte[] readFile(String path) {
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(path));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream(1024);

		try {
			Log.w("dkk", "Available bytes:" + in.available());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		byte[] temp = new byte[1024];
		int size = 0;
		try {
			while ((size = in.read(temp)) != -1) {
				out.write(temp, 0, size);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		byte[] content = out.toByteArray();
		return content;
	}

	public int decodeTPGtoRGB(String inpath, String outPath) {
		int status = Utils.TPG_STATUS_OK;
/*		// 闁哄倽顫夌涵锟�1闁挎稒淇洪浼村矗閺嵮冩暥閻庢稒蓱閺岀喎顕ｉ敓锟�
		byte[] content = readFile(inpath);

		Bitmap bitmap = null;

		TPGDecoder pTPG = new TPGDecoder();

		status = pTPG.parseHeader(content);
		if (Utils.TPG_STATUS_OK != status)
			return status;

		int imageMode = pTPG.getTPGType();

		if (Utils.IMAGE_MODE_Animation == imageMode ||
				Utils.IMAGE_MODE_AnimationWithAlpha == imageMode) {
			pTPG.startDecode(content);
			Bitmap bm = Bitmap.createBitmap(pTPG.getWidth(),
					pTPG.getHeight(), Bitmap.Config.ARGB_8888);
			int[] outData = new int[pTPG.getWidth() * pTPG.getHeight()];

			// int frameIndex = 0;
			int[] delayTime = new int[1];

			int frameCount = pTPG.getFrameCount();
			for (int frameIndex = 0; frameIndex < frameCount; frameIndex++) {
				long start_time = System.currentTimeMillis();
				int result = pTPG.decodeOneFrame(content, frameIndex, outData, bm,
						delayTime);
				if (Utils.TPG_STATUS_OK != result)
					break;
				long decode_time = System.currentTimeMillis() - start_time;
				// System.out.println("delayTime: " + (delayTime) +
				// "decode time:" + decode_time);
				Message message = new Message();
				message.what = MESSAGE_DISPAY;
				message.obj = bm;
				mHandler.sendMessage(message);

				if (delayTime[0] > decode_time) {
					try {
						Thread.sleep(delayTime[0] - decode_time);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			pTPG.closeDecode();
		} else {
			bitmap = pTPG.decodeTPG(content, Utils.FORMAT_RGBA, 1000);

			Message message = new Message();
			message.what = MESSAGE_DISPAY;
			message.obj = bitmap;
			mHandler.sendMessage(message);

*/
		
		// 闁哄倽顫夌涵锟�2闁挎稒淇洪浼村矗閺嶃劍鐎ù鐘冲劶閻儳顕ラ崟顒佺厵鐎殿噯鎷�
		Bitmap bitmap = null;

		TPGDecoder pTPG = new TPGDecoder();

		status = pTPG.parseHeader(inpath);
		if (Utils.TPG_STATUS_OK != status)
			return status;

		int imageMode = pTPG.getTPGType();

		if (Utils.IMAGE_MODE_Animation == imageMode
				|| Utils.IMAGE_MODE_AnimationWithAlpha == imageMode) {
			pTPG.startDecode2(inpath);
			Bitmap bm = Bitmap.createBitmap(pTPG.getWidth(),
					pTPG.getHeight(), Bitmap.Config.ARGB_8888);

			int[] delayTime = new int[1];
			int[] outData = new int[pTPG.getWidth() * pTPG.getHeight()];

			int frameCount = pTPG.getFrameCount();
			for (int frameIndex = 0; frameIndex < frameCount; frameIndex++) {
				long start_time = System.currentTimeMillis();
				int result = pTPG.decodeOneFrame2(frameIndex, outData, bm, delayTime);
				if (Utils.TPG_STATUS_OK != result) {
					pTPG.closeDecode2();
					return result;
				}
				long decode_time = System.currentTimeMillis() - start_time;

				Message message = new Message();
				message.what = MESSAGE_DISPAY;
				message.obj = bm;
				mHandler.sendMessage(message);

				if (delayTime[0] > decode_time) {
					try {
						Thread.sleep(delayTime[0] - decode_time);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			pTPG.closeDecode2();
		} else {
			bitmap = pTPG.decodeTPG2(inpath, Utils.FORMAT_RGBA, 1000);

			Message message = new Message();
			message.what = MESSAGE_DISPAY;
			message.obj = bitmap;
			mHandler.sendMessage(message);

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}

		pic_num++;
		
		return status;
	}

	public int decodeTPGtoRGBDir(String inpath, String outPath) {

		Log.w("dkk", "decodeTPGtoRGBDir inpath = " + inpath);
		int res = 0;
		File dir = new File(inpath);
		if (dir.exists()) {
			Log.w("dkk", "目录存在");
		} else {
			Log.w("dkk", "目录不存在");
		}
		File[] files = dir.listFiles();
		if (files == null) {
			return res;
		}

		File outdir = new File(outpath);
		if (!outdir.exists()) {
			outdir.mkdir();
		}

		Log.w("dkk", "decodeTPGtoRGBDir 1");
		Message msg = new Message();
		msg.what = MESSAGE_DECODE_TPG_START;
		mHandler.sendMessage(msg);

		long decTime = 0;
		for (int i = 0; i < files.length; i++) {
			if (!files[i].isDirectory()) {

				long time1 = System.currentTimeMillis();

				decodeTPGtoRGB(files[i].getAbsolutePath(), outPath);

				long time2 = System.currentTimeMillis();
				pic_num++;
				decTime += (time2 - time1);

			} else {
				String inputpath = files[i].getAbsolutePath();
				String outputpath = outpath + files[i].getName() + "/";
				decodeTPGtoRGBDir(inputpath, outputpath);
				System.out.println("Folder " + files[i].getName()
						+ " finished!");
				Log.w("dkk", "Folder " + files[i].getName()
						+ " finished!");
			}
		}

		// System.out.println("TPG Decode All Time: " + (decTime));
		// mTextViewTPGTime.setText("TPG Decode Total Time: " + (decTime)
		// + "ms");
		// System.out.println("jpeg enc All Time: " + (jpegencTime));
		// mHandler.sendEmptyMessage(MESSAGE_DECODE_TPG_FINISH);
		msg = new Message();
		msg.what = MESSAGE_DECODE_TPG_FINISH;
		msg.obj = Long.valueOf(decTime);
		mHandler.sendMessage(msg);
		return res;
	}

	public int decodeTPGtoRGBFile(String inpath, String outPath) {

		Log.w("dkk", "decodeTPGtoRGBDir inpath = " + inpath);
		int res = 0;
		File dir = new File(inpath);
		if (dir.exists()) {
			Log.w("dkk", "目录存在");
		} else {
			Log.w("dkk", "目录不存在");
		}

		File outdir = new File(outpath);
		if (!outdir.exists()) {
			outdir.mkdir();
		}

		Log.w("dkk", "decodeTPGtoRGBDir 1");
		Message msg = new Message();
		msg.what = MESSAGE_DECODE_TPG_START;
		mHandler.sendMessage(msg);

		long decTime = 0;
		long time1 = System.currentTimeMillis();

		decodeTPGtoRGB(inpath, outPath);

		long time2 = System.currentTimeMillis();
		pic_num++;
		decTime += (time2 - time1);

		// System.out.println("TPG Decode All Time: " + (decTime));
		// mTextViewTPGTime.setText("TPG Decode Total Time: " + (decTime)
		// + "ms");
		// System.out.println("jpeg enc All Time: " + (jpegencTime));
		// mHandler.sendEmptyMessage(MESSAGE_DECODE_TPG_FINISH);
		msg = new Message();
		msg.what = MESSAGE_DECODE_TPG_FINISH;
		msg.obj = Long.valueOf(decTime);
		mHandler.sendMessage(msg);
		return res;
	}

}
