package com.mishrole.undercontrol.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mishrole.undercontrol.entity.response.APIResponse;

public class Constant {
	public static final String APP_SECRET_KEY = "und3rctrlk3y";
	public static final String APP_CONFIG_CLIENT = "und3rctrl";
	public static final String APP_CLIENT_PASSWORD = "und3rctrlp4ss";
	
	public static final String APP_RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApcoBkJCdPIAXI8tQVvg6\r\n"
			+ "hih4HE2MoVWXUlOTYf2vuyMakh6pv7/SxAJpX9dmWNiKIo54nkVeyXMdr+XroxVZ\r\n"
			+ "Couw/A7sEnYIry4anSTDht9XLUSPa+cAQhVjsWtKLNNfZBgkeWim/7SjoPmDfxVk\r\n"
			+ "YkPI/sHVFeKgfw/s5T29kovzEOXSZY7IQS/yjBwuFklwPyK+Yxyb9j74lm6fXWOY\r\n"
			+ "kqSAfdWNRGJNdwm6+7e1FmqDpMPekqDhgXrWs1I2By43qAu05R6sXcjBMi1hkCN2\r\n"
			+ "EtiQzLoCRBW7VBOkPvPyZdXHbiPlNG6OkfOPZ9KcpCoGYHzD/0rCW+0kHls0hGw6\r\n"
			+ "BwIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";
	
	public static final String APP_RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEogIBAAKCAQEApcoBkJCdPIAXI8tQVvg6hih4HE2MoVWXUlOTYf2vuyMakh6p\r\n"
			+ "v7/SxAJpX9dmWNiKIo54nkVeyXMdr+XroxVZCouw/A7sEnYIry4anSTDht9XLUSP\r\n"
			+ "a+cAQhVjsWtKLNNfZBgkeWim/7SjoPmDfxVkYkPI/sHVFeKgfw/s5T29kovzEOXS\r\n"
			+ "ZY7IQS/yjBwuFklwPyK+Yxyb9j74lm6fXWOYkqSAfdWNRGJNdwm6+7e1FmqDpMPe\r\n"
			+ "kqDhgXrWs1I2By43qAu05R6sXcjBMi1hkCN2EtiQzLoCRBW7VBOkPvPyZdXHbiPl\r\n"
			+ "NG6OkfOPZ9KcpCoGYHzD/0rCW+0kHls0hGw6BwIDAQABAoIBAH1ZaatzG99Vfalr\r\n"
			+ "nLfVAPLUgFPt8mk9PKdbV3DcjVSxqvJVysSW9XWL2T5ErzZ3qqGMFPidvJgVuJl4\r\n"
			+ "/puUlXkau1sosScYJWLGOgscc9Vy4gfw8XuE8bYFVBYd57xSqKoWcuV00ec+HgWl\r\n"
			+ "Ad5BP1/onny38zOT4QOQ9+0vrDiy7dYYhYdMXXQDYmVX4VsPruw/2Vr1a5Hjn3BR\r\n"
			+ "ECsOB29kHkCjqYt1hy7OLy5+7RrkzBRgOV6mciHapHgdnW7qRCCvQZ7/Rfm0nrNu\r\n"
			+ "Ry0dte5wjDCEC5ZTeLiBo1XlgIETriRTiGUPQW34IGrES7TloGEAgHTLrdSh3QSj\r\n"
			+ "hsPOkEECgYEA2CJKy6/bfaV5OWo/CmbFUnspNut2bHBiPb2s8GsqNidUZTA/NWwN\r\n"
			+ "Q7EceLaiZYHgNuPk+cJhWVH8R2mMhDoBNspdniBpq6xAwFX39lHys6alzGwtAKFA\r\n"
			+ "HavmuEmz0OggJGKYVDTBiH9NjVd+JYb9fZ++LLsuTr5a8DnYHLC6tqECgYEAxF52\r\n"
			+ "GUisq14hlS+YPJvNCOtimaTMqDP+mq5ZJOD88LW4vQHeebMmNjpuqAyqaO7C8Gsw\r\n"
			+ "i3i+865fMV/s4PkbcU1sB6PO2FwLhv65t4CFuxyGXc7zRPu3ftrhvGoBtqruFXMf\r\n"
			+ "a3JCCmlVIExO33AeYx4eCvX0Bsn6U/kswrLSt6cCgYBlwn+Etx+YYLhal/Nmakyt\r\n"
			+ "MN+Ow/3VwHQArbUm3M3bAVly6uxXsq/sFw9YpmR/dk6iiVndXe+HplopJUDLYKQB\r\n"
			+ "r/Wz5X7bHRa/d7pvdtXWT7fwL15g1YribXULf2fyC9hWSTV/L/eO1KEm0vLJqGzh\r\n"
			+ "ILzhQp/i4yzdvd5KY8sFAQKBgFeO3w3rD8LhmAvTSwhLWCMQAckMaIzfkjkaYHlH\r\n"
			+ "MMGL5bGxMagGL5WifFDGRP3A6H8W7HBkCJDTQXLcmNy/dnMQ2IiXi9WBRUqOLfUa\r\n"
			+ "g4ao82jErEgoDOutYVYH1nfCw6Ig0POx4KyPxvs9yzVp4sLDXRX6ETMtwEbNJh4y\r\n"
			+ "ai6lAoGAA3upvcXxVRJJNo80Pm5xmGTTVAo2+xzLVHYuboE+Qw0UviD/0XwZfviX\r\n"
			+ "J/qS2/KjD49u3Ty+7YcEYbPdDYSifzGhq5GJ1psXJDzs6r2miaUvkjsl+VlwecJH\r\n"
			+ "ngzRKIYduV6yFLB/DE40kMDh7MX2NSWK0bDJTPwiOir3j2RJX5s=\r\n"
			+ "-----END RSA PRIVATE KEY-----";
	
	public static ResponseEntity<?> responseMessageErrors(HttpStatus status, String title, String detail, List<?> errors) {
		return ResponseEntity
				.status(status)
				.header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
				.body(new APIResponse(title, detail, errors));
	}
	
	public static ResponseEntity<?> responseMessageError(HttpStatus status, String title, String detail, String error, String element) {
		List<Map<String, Object>> errors = new ArrayList<Map<String, Object>>();
		Map<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put(element, error);
		errors.add(wrapper);
		
		return ResponseEntity
				.status(status)
				.header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
				.body(new APIResponse(title, detail, errors));
	}
	
	public static ResponseEntity<?> responseMessage(HttpStatus status, String title, Object data) {
		return ResponseEntity
				.status(status)
				.header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
				.body(new APIResponse(title, data));
	}
	
}
