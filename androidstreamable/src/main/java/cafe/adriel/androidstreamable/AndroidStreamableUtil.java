package cafe.adriel.androidstreamable;

public class AndroidStreamableUtil {

	public static boolean isNotEmpty(String str){
		return str != null && str.length() > 0;
	}

	public static String fixUrl(String url){
		if(isNotEmpty(url)) {
			if (url.startsWith("//")) {
				url = url.replace("//", "http://");
			} else if (!url.startsWith("http://")) {
				url = "http://" + url;
			}
		}
		return url;
	}

	public static int toInt(String intStr){
		try {
			return Integer.parseInt(intStr);
		} catch (Exception e){
			return -1;
		}
	}

}