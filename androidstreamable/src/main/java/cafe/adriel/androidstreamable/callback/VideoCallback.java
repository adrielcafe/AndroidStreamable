package cafe.adriel.androidstreamable.callback;

import cafe.adriel.androidstreamable.model.Video;

public interface VideoCallback {
	void onSuccess(int statusCode, Video video);
	void onFailure(int statusCode, Throwable error);
}