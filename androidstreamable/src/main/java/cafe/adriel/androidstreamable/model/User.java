package cafe.adriel.androidstreamable.model;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
	private String name;
	private String bio;
	private String photoUrl;
	private boolean isPrivate;

	public static User fromJson(JSONObject json) throws JSONException {
		User user = new User();
		user.setName(json.getString("user_name"));
		user.setBio(json.getString("bio"));
		user.setPhotoUrl(json.getString("photo_url"));
		user.setIsPrivate(json.getBoolean("is_private"));
		return user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
}