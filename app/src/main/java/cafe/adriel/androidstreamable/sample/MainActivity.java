package cafe.adriel.androidstreamable.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;

import cafe.adriel.androidstreamable.AndroidStreamable;
import cafe.adriel.androidstreamable.callback.NewVideoCallback;
import cafe.adriel.androidstreamable.callback.UserCallback;
import cafe.adriel.androidstreamable.callback.VideoCallback;
import cafe.adriel.androidstreamable.model.NewVideo;
import cafe.adriel.androidstreamable.model.User;
import cafe.adriel.androidstreamable.model.Video;

public class MainActivity extends AppCompatActivity {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/*
			Optional (uncomment and set your credentials to use)
			Doc: https://streamable.com/documentation#authentication
		*/
		//AndroidStreamable.setCredentials("YOUR_STREAMABLE_USERNAME", "YOUR_STREAMABLE_PASSWORD");

		/*
			Only works with credentials (uncomment to use)
			Doc: https://streamable.com/documentation#retrieve-authenticated-user
		*/
		//exampleGetAuthUser();

		/*
			Doc: https://streamable.com/documentation#retrieve-user
		*/
		exampleGetUser();

		/*
			Doc: https://streamable.com/documentation#retrieve-video
		*/
		exampleGetVideo();

		/*
			Doc: https://streamable.com/documentation#import-video-from-url
		*/
		exampleImportVideo();

		/*
			Doc: https://streamable.com/documentation#upload-video-file
		*/
		exampleUploadVideo();
	}

	private void exampleGetVideo(){
		String shortCode = "z7sw";
		AndroidStreamable.getVideo(shortCode, new VideoCallback() {
			@Override
			public void onSuccess(int statusCode, Video video) {
				Log.i("VIDEO", GSON.toJson(video));
			}
			@Override
			public void onFailure(int statusCode, Throwable error) {
				error.printStackTrace();
			}
		});
	}

	private void exampleImportVideo(){
		String url = "https://archive.org/download/Windows7WildlifeSampleVideo/Wildlife.wmv";
		String title = "Import Example";
		AndroidStreamable.importVideo(url, title, new NewVideoCallback() {
			@Override
			public void onSuccess(int statusCode, NewVideo newVideo) {
				Log.i("IMPORTED VIDEO", GSON.toJson(newVideo));
			}
			@Override
			public void onFailure(int statusCode, Throwable error) {
				error.printStackTrace();
			}
		});
	}

	private void exampleUploadVideo(){
		InputStream is = null;
		String title = "Upload Example";
		try {
			is = getAssets().open("dp56.mp4");
		} catch (Exception e){ }
		if(is != null) {
			AndroidStreamable.uploadVideo(is, title, new NewVideoCallback() {
				@Override
				public void onSuccess(int statusCode, NewVideo newVideo) {
					Log.i("UPLOADED VIDEO", GSON.toJson(newVideo));
				}
				@Override
				public void onFailure(int statusCode, Throwable error) {
					error.printStackTrace();
				}
			});
		}
	}

	private void exampleGetAuthUser(){
		AndroidStreamable.getAuthUser(new UserCallback() {
			@Override
			public void onSuccess(int statusCode, User user) {
				Log.i("AUTH USER", GSON.toJson(user));
			}
			@Override
			public void onFailure(int statusCode, Throwable error) {
				error.printStackTrace();
			}
		});
	}

	private void exampleGetUser(){
		String username = "streamable";
		AndroidStreamable.getUser(username, new UserCallback() {
			@Override
			public void onSuccess(int statusCode, User user) {
				Log.i("USER", GSON.toJson(user));
			}
			@Override
			public void onFailure(int statusCode, Throwable error) {
				error.printStackTrace();
			}
		});
	}
}
