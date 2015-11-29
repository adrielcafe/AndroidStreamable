package cafe.adriel.androidstreamable.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cafe.adriel.androidstreamable.AndroidStreamableUtil;

public class Video {
	private String title;
	private String url;
	private String thumbnailUrl;
	private String message;
	private Map<String, VideoFile> files;
	private int status;

	public static Video fromJson(JSONObject json) throws JSONException {
		Video video = new Video();
		if(json.has("title")) {
			video.setTitle(json.getString("title"));
		}
		if(json.has("url")) {
			video.setUrl(AndroidStreamableUtil.fixUrl(json.getString("url")));
		}
		if(json.has("thumbnail_url")) {
			video.setThumbnailUrl(AndroidStreamableUtil.fixUrl(json.getString("thumbnail_url")));
		}
		if(json.has("message")) {
			video.setMessage(json.getString("message"));
		}
		if(json.has("status")) {
			video.setStatus(AndroidStreamableUtil.toInt(json.getString("status")));
		}
		Map<String, VideoFile> hash = new HashMap<>();
		for(Iterator<String> i = json.getJSONObject("files").keys(); i.hasNext(); ){
			String format = i.next();
			JSONObject videoFileJson = json.getJSONObject("files").getJSONObject(format);
			VideoFile videoFile = VideoFile.fromJson(videoFileJson);
			hash.put(format, videoFile);
		}
		video.setFiles(hash);
		return video;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Map<String, VideoFile> getFiles() {
		return files;
	}

	public void setFiles(Map<String, VideoFile> files) {
		this.files = files;
	}
}