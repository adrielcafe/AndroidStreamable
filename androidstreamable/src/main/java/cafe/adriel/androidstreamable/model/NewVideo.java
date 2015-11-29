package cafe.adriel.androidstreamable.model;

import org.json.JSONException;
import org.json.JSONObject;

public class NewVideo {
	private String shortCode;
	private int status;

	public static NewVideo fromJson(JSONObject json) throws JSONException {
		NewVideo newVideo = new NewVideo();
		if(json.has("shortcode")) {
			newVideo.setShortCode(json.getString("shortcode"));
		}
		if(json.has("status")) {
			newVideo.setStatus(json.getInt("status"));
		}
		return newVideo;
	}

	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}