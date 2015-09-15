
package com.example.suwonsmartapp.androidexam.utils.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by junsuk on 15. 9. 15.. 네트워크 관련 유틸 클래스
 */
public class NetworkUtil {

    /**
     * Url Connection 객체를 생성한다
     *
     * @return
     * @throws Exception
     * @Note : url 커넥션
     */
    public static URLConnection getUrlConnection(String urlString)
            throws Exception {

        URL url = new URL(urlString); // 넘어오는 URL밎정보
        URLConnection connection = url.openConnection(); // 커넥션
        connection.setDoOutput(true);
        return connection;
    }

    /**
     * URLConnection 객체로부터 소스를 String 형태로 얻어온다
     *
     * @param connection
     * @return
     * @throws IOException
     * @Note : 커넥션된 결과값
     */
    public static String getReturnString(URLConnection connection)
            throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                connection.getInputStream(), "UTF-8")); // 반환되는 값이 UTF-8 경우

        // StringBuilder 와 같은 기능을 하는 클래스
        // StringBuilder : 스레드에 안전하지 않음, 속도 빠름
        // StringBuffer : 스레드에 안전함, 속도 느림
        StringBuffer buffer = new StringBuffer();
        String decodedString;

        while ((decodedString = in.readLine()) != null) {
            buffer.append(decodedString);

        }

        in.close();

        return buffer.toString();
    }

    /**
     * url 주소로부터 소스를 String 형태로 얻어온다
     *
     * @param url 대상 url
     * @return 소스
     * @throws Exception
     */
    public static String getReturnString(String url) throws Exception {
        return getReturnString(getUrlConnection(url));
    }
}
