package cafe.adriel.androidstreamable.model;

import org.json.JSONObject;

public class NewVideo {
	private String shortCode;
	private int status;

	public static NewVideo fromJson(JSONObject json){
		try {
			NewVideo newVideo = new NewVideo();
			newVideo.setShortCode(json.getString("shortcode"));
			newVideo.setStatus(json.getInt("status"));
			return newVideo;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
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