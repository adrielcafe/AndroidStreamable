package cafe.adriel.androidstreamable.callback;

import cafe.adriel.androidstreamable.model.NewVideo;

public interface NewVideoCallback {
	void onSuccess(int statusCode, NewVideo newVideo);
	void onFailure(int statusCode, Throwable error);
}