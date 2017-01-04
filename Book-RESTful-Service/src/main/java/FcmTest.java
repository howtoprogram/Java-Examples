
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class FcmTest {

	public static final String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

	public static void main(String[] args) throws IOException {
		try {
			String userDeviceIdKey = "EE8CED92CE4F85AF4172926FEDDD3568A5AE28CF37BB8F89645A9D5784ACD6C8";
			String authKey = "AAAA0sDON-U:APA91bH1YWRLme0Q4e_"
					+ "1aS_5pUcH2W44BodoA7Ad3zxdsFDnkUPbP4m4UFB2gT13Q2iKZsOJTuwL79khDEn8cftqSEaF_"
					+ "0Wdf7awiIa7sf6US8jgNwHEygCTaOT9Zjw0cWG3x5J0WSOEhc1d7uwHCGoss-lejgp6pQ";
			String fcmUrl = API_URL_FCM;

			URL url = new URL(fcmUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "key=" + authKey);
			conn.setRequestProperty("Content-Type", "application/json");

			JSONObject json = new JSONObject();
			json.put("to", userDeviceIdKey.trim());
			JSONObject info = new JSONObject();
			info.put("title", "Notificatoin Title"); // Notification title
			info.put("body", "Hello Test notification"); // Notification body
			json.put("notification", info);

			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(json.toString());
			wr.flush();
			conn.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
