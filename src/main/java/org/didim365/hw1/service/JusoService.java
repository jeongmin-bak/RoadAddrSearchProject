package org.didim365.hw1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.didim365.hw1.dto.juso.JusoResponseDto;
import org.didim365.hw1.dto.juso.JusoSearchHisDto;
import org.didim365.hw1.entity.SearchJuso;
import org.didim365.hw1.repository.JusoMapper;
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
@Slf4j(topic = "JosuService")
@RequiredArgsConstructor
public class JusoService {
    private final JusoMapper jusoMapper;
    public List<JusoResponseDto> searchJuso(String searchJuso) throws IOException {
        List<JusoResponseDto> jusoList = new ArrayList<>();
        int totalCount = getTotalCount(searchJuso);

        for (int i = 1; i < totalCount+1; i++) {
            String apiUrl = String.format("https://business.juso.go.kr/addrlink/addrLinkApi.do?currentPage=%s&countPerPage=10&keyword=", i) +
                    URLEncoder.encode(searchJuso, "UTF-8") + "&confmKey=devU01TX0FVVEgyMDI0MDMxMTEzMDA0MDExNDU4MjM=&resultType=json";
            URL url = new URL(apiUrl);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            StringBuffer sb = new StringBuffer();
            String tempStr = null;
            while (true) {
                tempStr = br.readLine();
                if (tempStr == null) break;
                sb.append(tempStr);
            }
            br.close();

            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray jusoArray = jsonObject.getJSONObject("results").getJSONArray("juso");
            for (int j = 0; j < jusoArray.length(); j++) {
                JusoResponseDto jusoResponseDto = new JusoResponseDto();
                jusoResponseDto.setZipNo(jusoArray.getJSONObject(j).getString("zipNo"));
                jusoResponseDto.setJibunAddr(jusoArray.getJSONObject(j).getString("jibunAddr"));
                jusoResponseDto.setRoadAddr(jusoArray.getJSONObject(j).getString("roadAddr"));
                jusoList.add(jusoResponseDto);
            }
        }
        return jusoList;
    }

    public List<JusoResponseDto> searchJuso(String searchJuso, int pageNum) throws IOException{
        List<JusoResponseDto> jusoList = new ArrayList<>();

        String apiUrl = String.format("https://business.juso.go.kr/addrlink/addrLinkApi.do?currentPage=%s&countPerPage=10&keyword=", pageNum) +
                URLEncoder.encode(searchJuso, "UTF-8") + "&confmKey=devU01TX0FVVEgyMDI0MDMxMTEzMDA0MDExNDU4MjM=&resultType=json";
        URL url = new URL(apiUrl);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
        StringBuffer sb = new StringBuffer();
        String tempStr = null;
        while (true) {
            tempStr = br.readLine();
            if (tempStr == null) break;
            sb.append(tempStr);
        }
        br.close();

        JSONObject jsonObject = new JSONObject(sb.toString());
        JSONArray jusoArray = jsonObject.getJSONObject("results").getJSONArray("juso");
        for (int j = 0; j < jusoArray.length(); j++) {
            JusoResponseDto jusoResponseDto = new JusoResponseDto();
            jusoResponseDto.setZipNo(jusoArray.getJSONObject(j).getString("zipNo"));
            jusoResponseDto.setJibunAddr(jusoArray.getJSONObject(j).getString("jibunAddr"));
            jusoResponseDto.setRoadAddr(jusoArray.getJSONObject(j).getString("roadAddr"));
            jusoList.add(jusoResponseDto);
        }
        return jusoList;
    }

    public int getTotalCount(String searchJuso) throws IOException{
        String jusoApiUrl = "https://business.juso.go.kr/addrlink/addrLinkApi.do?currentPage=1&countPerPage=10&keyword=";
        String secretKey = "&confmKey=devU01TX0FVVEgyMDI0MDMxMTEzMDA0MDExNDU4MjM=&resultType=json";

        String apiUrl = jusoApiUrl + URLEncoder.encode(searchJuso, "UTF-8") + secretKey;
        URL url = new URL(apiUrl);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
        StringBuffer sb = new StringBuffer();
        String tempStr = null;
        while(true){
            tempStr = br.readLine();
            if(tempStr == null) break;
            sb.append(tempStr);
        }
        br.close();
        JSONObject jsonObject = new JSONObject(sb.toString());
        String totalCount = jsonObject.getJSONObject("results").getJSONObject("common").getString("totalCount");
        return (Integer.valueOf(totalCount)/ 10)+1;
    }

    public void saveSearchJuso(String memNb, String searchAddr) {
        log.info(searchAddr + " : " + memNb);
        SearchJuso searchJuso = new SearchJuso(memNb, searchAddr);
        log.info(searchJuso.toString());
        jusoMapper.saveSearchAddr(searchJuso);
    }

    public List<JusoSearchHisDto> checkUserSearchHistory(String memNb) {
        log.info("고객번호" + " : " + memNb);
        return jusoMapper.findUserSearchHistory(memNb);
    }
}
