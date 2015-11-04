package cafe.adriel.androidstreamable.model;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Video {
	private String url;
	private String urlRoot;
	private String thumbnailUrl;
	private String message;
	private int status;
	private List<VideoFile> files;
	private List<String> formats;

	public static Video fromJson(JSONObject json){
		try {
			Video video = new Video();
			video.setUrl(json.getString("url"));
			video.setUrlRoot(json.getString("url_root"));
			video.setThumbnailUrl(json.getString("thumbnail_url"));
			video.setMessage(json.getString("message"));
			video.setStatus(json.getInt("status"));
			video.setFormats(new ArrayList<String>());
			video.setFiles(new ArrayList<VideoFile>());
			for(int i = 0; i < json.getJSONArray("formats").length(); i++){
				String format = json.getJSONArray("formats").getString(i);
				video.getFormats().add(format);
			}
			for(String format : video.getFormats()){
				JSONObject videoFileJson = json.getJSONObject("files").getJSONObject(format);
				VideoFile videoFile = VideoFile.fromJson(videoFileJson, format);
				video.getFiles().add(videoFile);
			}
			return video;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlRoot() {
		return urlRoot;
	}

	public void setUrlRoot(String urlRoot) {
		this.urlRoot = urlRoot;
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

	public List<String> getFormats() {
		return formats;
	}

	public void setFormats(List<String> formats) {
		this.formats = formats;
	}
}