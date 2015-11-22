package cafe.adriel.androidstreamable;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import cafe.adriel.androidstreamable.callback.NewVideoCallback;
import cafe.adriel.androidstreamable.callback.UserCallback;
import cafe.adriel.androidstreamable.callback.VideoCallback;
import cafe.adriel.androidstreamable.model.NewVideo;
import cafe.adriel.androidstreamable.model.User;
import cafe.adriel.androidstreamable.model.Video;
import cz.msebera.android.httpclient.Header;

public abstract class AndroidStreamable {
	public static final int STATUS_VIDEO_IS_BEING_UPLOADED              = 0;
	public static final int STATUS_VIDEO_IS_BEING_PROCESSED             = 1;
	public static final int STATUS_VIDEO_HAS_AT_LEAST_ONE_FILE_READY    = 2;
	public static final int STATUS_VIDEO_IS_UNAVAILABLE_DUE_TO_AN_ERROR = 3;

	private static final String STREAMABLE_BASE_URL                     = "https://api.streamable.com";
	private static final String STREAMABLE_UPLOAD_VIDEO_ENDPOINT        = "/upload";
	private static final String STREAMABLE_IMPORT_VIDEO_ENDPOINT        = "/import?url=%s&title=%s";
	private static final String STREAMABLE_RETRIEVE_VIDEO_ENDPOINT      = "/videos/";
	private static final String STREAMABLE_RETRIEVE_AUTH_USER_ENDPOINT  = "/me";
	private static final String STREAMABLE_RETRIEVE_USER_ENDPOINT       = "/users/";

	private static final AsyncHttpClient REST_CLIENT = new AsyncHttpClient();
	private static final int DEFAULT_CONNECT_TIMEOUT = 10 * 1000;

	private static String username, password;
	private static int timeout = DEFAULT_CONNECT_TIMEOUT;

	private AndroidStreamable(){ }

	public static void setCredentials(String username, String password){
		AndroidStreamable.username = username;
		AndroidStreamable.password = password;
	}

	public static void resetCredentials(){
		AndroidStreamable.username = null;
		AndroidStreamable.password = null;
	}

	public static void setTimeout(int timeout){
		AndroidStreamable.timeout = timeout;
	}

	public static void resetTimeout(){
		AndroidStreamable.timeout = DEFAULT_CONNECT_TIMEOUT;
	}

	public static void getVideo(String shortCode, final VideoCallback callback){
		String url = STREAMABLE_BASE_URL + STREAMABLE_RETRIEVE_VIDEO_ENDPOINT + shortCode;
		if(AndroidStreamableUtil.isNotEmpty(username) && AndroidStreamableUtil.isNotEmpty(password)) {
			REST_CLIENT.setBasicAuth(username, password);
		}
		REST_CLIENT.setTimeout(timeout);
		REST_CLIENT.get(url, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				try {
					JSONObject json = new JSONObject(new String(responseBody));
					Video newVideo = Video.fromJson(json);
					callback.onSuccess(statusCode, newVideo);
				} catch (JSONException e){
					callback.onFailure(statusCode, e);
				}
			}
			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
				callback.onFailure(statusCode, error);
			}
		});
	}

	public static void importVideo(String videoUrl, String title, final NewVideoCallback callback){
		String url = String.format(STREAMABLE_BASE_URL + STREAMABLE_IMPORT_VIDEO_ENDPOINT, videoUrl, title);
		if(AndroidStreamableUtil.isNotEmpty(username) && AndroidStreamableUtil.isNotEmpty(password)) {
			REST_CLIENT.setBasicAuth(username, password);
		}
		REST_CLIENT.setTimeout(timeout);
		REST_CLIENT.get(url, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				try {
					JSONObject json = new JSONObject(new String(responseBody));
					NewVideo newVideo = NewVideo.fromJson(json);
					callback.onSuccess(statusCode, newVideo);
				} catch (JSONException e){
					callback.onFailure(statusCode, e);
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
				callback.onFailure(statusCode, error);
			}
		});
	}

	public static void uploadVideo(InputStream videoInputStream, String title, NewVideoCallback callback){
		RequestParams params = new RequestParams();
		params.put("file", videoInputStream);
		params.put("title", title);
		uploadVideo(params, callback);
	}

	public static void uploadVideo(File videoFile, String title, NewVideoCallback callback) throws FileNotFoundException {
		RequestParams params = new RequestParams();
		params.put("file", videoFile);
		params.put("title", title);
		uploadVideo(params, callback);
	}

	private static void uploadVideo(RequestParams params, final NewVideoCallback callback) {
		String url = STREAMABLE_BASE_URL + STREAMABLE_UPLOAD_VIDEO_ENDPOINT;
		if(AndroidStreamableUtil.isNotEmpty(username) && AndroidStreamableUtil.isNotEmpty(password)) {
			REST_CLIENT.setBasicAuth(username, password);
		}
		REST_CLIENT.setTimeout(timeout);
		REST_CLIENT.post(url, params, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				try {
					JSONObject json = new JSONObject(new String(responseBody));
					NewVideo newVideo = NewVideo.fromJson(json);
					callback.onSuccess(statusCode, newVideo);
				} catch (JSONException e){
					callback.onFailure(statusCode, e);
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
				callback.onFailure(statusCode, error);
			}
		});
	}

	public static void getAuthUser(final UserCallback callback){
		String url = STREAMABLE_BASE_URL + STREAMABLE_RETRIEVE_AUTH_USER_ENDPOINT;
		if(AndroidStreamableUtil.isNotEmpty(username) && AndroidStreamableUtil.isNotEmpty(password)) {
			REST_CLIENT.setBasicAuth(username, password);
		}
		REST_CLIENT.setTimeout(timeout);
		REST_CLIENT.get(url, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				try {
					JSONObject json = new JSONObject(new String(responseBody));
					User user = User.fromJson(json);
					callback.onSuccess(statusCode, user);
				} catch (JSONException e){
					callback.onFailure(statusCode, e);
				}
			}
			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
				callback.onFailure(statusCode, error);
			}
		});
	}

	public static void getUser(String username, final UserCallback callback){
		String url = STREAMABLE_BASE_URL + STREAMABLE_RETRIEVE_USER_ENDPOINT + username;
		if(AndroidStreamableUtil.isNotEmpty(username) && AndroidStreamableUtil.isNotEmpty(password)) {
			REST_CLIENT.setBasicAuth(username, password);
		}
		REST_CLIENT.setTimeout(timeout);
		REST_CLIENT.get(url, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				try {
					JSONObject json = new JSONObject(new String(responseBody));
					User user = User.fromJson(json);
					callback.onSuccess(statusCode, user);
				} catch (JSONException e){
					callback.onFailure(statusCode, e);
				}
			}
			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
				callback.onFailure(statusCode, error);
			}
		});
	}
}