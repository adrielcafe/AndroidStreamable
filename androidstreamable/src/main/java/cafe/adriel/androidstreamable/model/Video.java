package cafe.adriel.androidstreamable.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Video {
	private String title;
	private String url;
	private String thumbnailUrl;
	private String message;
	private int status;
	private List<VideoFile> files;

	public static Video fromJson(JSONObject json) throws JSONException {
		Video video = new Video();
		if(json.has("title")) {
			video.setTitle(json.getString("title"));
		}
		if(json.has("url")) {
			video.setUrl(json.getString("url"));
		}
		if(json.has("thumbnail_url")) {
			video.setThumbnailUrl(json.getString("thumbnail_url"));
		}
		if(json.has("message")) {
			video.setMessage(json.getString("message"));
		}
		video.setStatus(json.getInt("status"));
		video.setFiles(new ArrayList<VideoFile>());
		for(Iterator<String> i = json.getJSONObject("files").keys(); i.hasNext(); ){
			String format = i.next();
			JSONObject videoFileJson = json.getJSONObject("files").getJSONObject(format);
			VideoFile videoFile = VideoFile.fromJson(videoFileJson, format);
			video.getFiles().add(videoFile);
		}
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

	public List<VideoFile> getFiles() {
		return files;
	}

	public void setFiles(List<VideoFile> files) {
		this.files = files;
	}
}