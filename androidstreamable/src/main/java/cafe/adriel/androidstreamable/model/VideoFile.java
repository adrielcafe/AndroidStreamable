package cafe.adriel.androidstreamable.model;

import org.json.JSONObject;

public class VideoFile {
	private String format;
	private String url;
	private String width;
	private String height;

	public static VideoFile fromJson(JSONObject json, String format){
		try {
			VideoFile videoFile = new VideoFile();
			videoFile.setFormat(format);
			videoFile.setUrl(json.getString("url"));
			if(json.has("width")) {
				videoFile.setWidth(json.getString("width"));
			}
			if(json.has("height")) {
				videoFile.setHeight(json.getString("height"));
			}
			return videoFile;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
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