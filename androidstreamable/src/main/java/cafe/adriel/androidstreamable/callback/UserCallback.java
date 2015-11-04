package cafe.adriel.androidstreamable.callback;

import cafe.adriel.androidstreamable.model.User;

public interface UserCallback {
	void onSuccess(int statusCode, User user);
	void onFailure(int statusCode, Throwable error);
}