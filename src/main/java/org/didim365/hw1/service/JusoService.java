package org.didim365.hw1.service;

import lombok.RequiredArgsConstructor;
import org.didim365.hw1.dto.JusoRequestDto;
import org.didim365.hw1.dto.JusoResponseDto;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JusoService {
    public List<JusoResponseDto> searchJuso(String searchJuso) throws IOException {
        String jusoApiUrl = "https://business.juso.go.kr/addrlink/addrLinkApi.do?currentPage=1&countPerPage=10&keyword=";
        String secretKey = "&confmKey=devU01TX0FVVEgyMDI0MDMxMTEzMDA0MDExNDU4MjM=&resultType=json";

        String apiUrl = jusoApiUrl + URLEncoder.encode(searchJuso, "UTF-8") + secretKey;
        URL url = new URL(apiUrl);
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        url.openStream(),"UTF-8")); StringBuffer sb = new StringBuffer();
        String tempStr = null;
        while(true){
            tempStr = br.readLine();
            if(tempStr == null) break;
            sb.append(tempStr);
        }
        br.close();
        JSONObject jsonObject = new JSONObject(sb.toString());
        JSONArray jusoArray = jsonObject.getJSONObject("results").getJSONArray("juso");

        List<JusoResponseDto> jusoList = new ArrayList<>();
        for (int i = 0; i < jusoArray.length(); i++) {
            JusoResponseDto jusoResponseDto = new JusoResponseDto();
            String zipNo = jusoArray.getJSONObject(i).getString("zipNo");
            String jibunAddr = jusoArray.getJSONObject(i).getString("jibunAddr");
            String roadAddr = jusoArray.getJSONObject(i).getString("roadAddr");

            jusoResponseDto.setZipNo(zipNo);
            jusoResponseDto.setJibunAddr(jibunAddr);
            jusoResponseDto.setRoadAddr(roadAddr);
            System.out.println("주소 " + (i + 1) + "의 zipNo: " + zipNo);
            System.out.println("지번 " + (i + 1) + "의 jibunAddr: " + jibunAddr);
            System.out.println("도로명 " + (i + 1) + "의 roadAddr: " + roadAddr);

            jusoList.add(jusoResponseDto);

        }
        return jusoList;
    }
}
