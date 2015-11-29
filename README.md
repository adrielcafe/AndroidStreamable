[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AndroidStreamable-green.svg?style=true)](https://android-arsenal.com/details/1/2738)
[![JitPack](https://img.shields.io/github/release/adrielcafe/AndroidStreamable.svg?label=JitPack)](https://jitpack.io/#adrielcafe/AndroidStreamable/)

<p align="center" style="margin: 0">
  <img src="https://streamable.com/static/img/logo.png">
</p>
<h1 align="center" style="margin-top: 0">AndroidStreamable</h1>

**Unofficial** https://streamable.com API Wrapper for Android.

## Usage

### Authentication
You don't need to authenticate to use this API. If you do, all uploaded/imported videos will be saved on your Streamable account.
```java
AndroidStreamable.setCredentials("YOUR_STREAMABLE_USERNAME", "YOUR_STREAMABLE_PASSWORD");
```

### Upload a video file
Uploads a [NewVideo](https://github.com/adrielcafe/AndroidStreamable/blob/master/androidstreamable/src/main/java/cafe/adriel/androidstreamable/model/NewVideo.java) file to Streamable. Video files must be **smaller than 10GB** in size and **10 minutes in length**.
```java
InputStream is = getAssets().open("video.mp4");
/* or
File file = new File("video.mp4"); */
String title = "Upload Example";
AndroidStreamable.uploadVideo(is /* or file */, title, new NewVideoCallback() {
	@Override
	public void onSuccess(int statusCode, NewVideo newVideo) {
		// :D
	}
	@Override
	public void onFailure(int statusCode, Throwable error) {
		// :(
	}
});
```

### Import a video from a URL
Imports a [NewVideo](https://github.com/adrielcafe/AndroidStreamable/blob/master/androidstreamable/src/main/java/cafe/adriel/androidstreamable/model/NewVideo.java) to Streamable from a URL. Video files must be **smaller than 10GB** in size and **10 minutes in length**.
```java
String url = "http://site.com/video.mp4";
String title = "Import Example";
AndroidStreamable.importVideo(url, title, new NewVideoCallback() {
	@Override
	public void onSuccess(int statusCode, NewVideo newVideo) {
		// :D
	}
	@Override
	public void onFailure(int statusCode, Throwable error) {
		// :(
	}
});
```

### Retrieve a video
Retrieves a [Video](https://github.com/adrielcafe/AndroidStreamable/blob/master/androidstreamable/src/main/java/cafe/adriel/androidstreamable/model/Video.java) that has previously been uploaded or imported.
```java
String shortCode = "ifjh";
AndroidStreamable.getVideo(shortCode, new VideoCallback() {
	@Override
	public void onSuccess(int statusCode, Video video) {
		// :D
	}
	@Override
	public void onFailure(int statusCode, Throwable error) {
		// :(
	}
});
```

### Retrieve a user
Returns a [User](https://github.com/adrielcafe/AndroidStreamable/blob/master/androidstreamable/src/main/java/cafe/adriel/androidstreamable/model/User.java) object representing the requested user.
```java
String username = "streamable";
AndroidStreamable.getUser(username, new UserCallback() {
	@Override
	public void onSuccess(int statusCode, User user) {
		// :D
	}
	@Override
	public void onFailure(int statusCode, Throwable error) {
		// :(
	}
});
```

### Retrieve authenticated user
Returns a [User](https://github.com/adrielcafe/AndroidStreamable/blob/master/androidstreamable/src/main/java/cafe/adriel/androidstreamable/model/User.java) object representing the currently authenticated user.
```java
AndroidStreamable.getAuthUser(new UserCallback() {
	@Override
	public void onSuccess(int statusCode, User user) {
		// :D
	}
	@Override
	public void onFailure(int statusCode, Throwable error) {
		// :(
	}
});
```

## Video status
When retrieve a video, you can check their status this way:
```java
switch (video.getStatus()){
	case AndroidStreamable.STATUS_VIDEO_IS_BEING_UPLOADED:
		break;
	case AndroidStreamable.STATUS_VIDEO_IS_BEING_PROCESSED:
		break;
	case AndroidStreamable.STATUS_VIDEO_HAS_AT_LEAST_ONE_FILE_READY:
		break;
	case AndroidStreamable.STATUS_VIDEO_IS_UNAVAILABLE_DUE_TO_AN_ERROR:
		break;
}
```

## Example
Check out the [app module](https://github.com/adrielcafe/AndroidStreamable/tree/master/app). Watch the **console log** to see this lib in action.

## Import to your project
Put this into your `app/build.gradle`:
```
repositories {
  maven {
    url "https://jitpack.io"
  }
}

dependencies {
  compile 'com.github.adrielcafe:AndroidStreamable:0.5'
}
```

## Dependencies
* [Android Asynchronous Http Client](https://loopj.com/android-async-http/)

## Official Streamable API documentation
https://streamable.com/documentation

## Tip: Playing videos
[ExoMedia](https://github.com/brianwernick/ExoMedia) is *"a utility class that wraps the [ExoPlayer](https://github.com/google/ExoPlayer) in to a standardized View and API much like the built in Android VideoView and MediaPlayer."*

## Changelog
### 0.5
* Fix int conversion

### 0.4
* Replace `List<VideoFile>` by `Map<String, VideoFile>`, where String == video format
* Fix exception when `json.getInt("status")` == null
* Add `http://` in url when starts with `//`

### 0.3
* Add `title` for uploaded and imported videos (new in official API)
* Remove `Video.url_root` (no longer available by official API)
* Remove `Video.formats` (no longer available by official API)

### 0.2
* Replace try catch by throws
* Add `callback.onFailure()` on catches

### 0.1
* Supports authless and authenticated users
* Supports all 5 official API methods: Get Video, Import Video, Upload Video, Get User, Get Authenticated User

## License
```
The MIT License (MIT)

Copyright (c) 2015 Adriel Café

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
```
