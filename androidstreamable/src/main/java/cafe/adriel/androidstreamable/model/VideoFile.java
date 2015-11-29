package cafe.adriel.androidstreamable.model;

import org.json.JSONException;
import org.json.JSONObject;

import cafe.adriel.androidstreamable.AndroidStreamableUtil;

public class VideoFile {
	private String url;
	private String width;
	private String height;

	public static VideoFile fromJson(JSONObject json) throws JSONException {
		VideoFile videoFile = new VideoFile();
		if(json.has("url")) {
			videoFile.setUrl(AndroidStreamableUtil.fixUrl(json.getString("url")));
		}
		if(json.has("width")) {
			videoFile.setWidth(json.getString("width"));
		}
		if(json.has("height")) {
			videoFile.setHeight(json.getString("height"));
		}
		return videoFile;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}
}