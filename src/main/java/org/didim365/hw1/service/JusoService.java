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

    /**
     * 검색 창에 입력받은 후도로명 주소 검색 API를 사용하여 주소를 검색합니다.
     * @param searchJuso 검색하고자 하는 주소
     * @return 도로명 주소, 지번주소, 우편번호 JusoResponseDto 객체의 리스트를 반환
     * @throws IOException 주소 검색 중 발생한 입출력 예외 처리
     */
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

    /**
     * 도로명 주소 API 호출 후 검색 결과 totalCount 값을 받기 위한 메소드
     * @param searchJuso 도로명 주소
     * @return totalCount 검색 결과 갯수
     * @throws IOException 주소 검색 중 발생한 입출력 예외 처리
     */
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

    /**
     * 주소 검색 기록을 저장
     * 이미 해당 주소에 대한 검색 기록이 있을 경우 검색 횟수를 업데이트하고,
     * 없을 경우 새로운 검색 기록을 저장
     *
     * @param memNb 사용자의 회원 번호
     * @param searchAddr 저장할 주소 검색 기록
     */
    public void saveSearchJuso(String memNb, String searchAddr) {
        log.info(searchAddr + " : " + memNb);
        Integer searchCount = jusoMapper.checkJusoSearchCount(memNb, searchAddr);

        if(searchCount != null){ // 이미 검색이력이 있는 경우
            jusoMapper.updateSearchCount(memNb, searchAddr);
        }else{
            SearchJuso searchJuso = new SearchJuso(memNb, searchAddr);
            jusoMapper.saveSearchAddr(searchJuso);
        }
    }


    /**
     * 사용자 주소 검색 기록을 조회합니다.
     *
     * @param memNb 사용자의 회원 번호
     * @return 검색했던 주소, 검색 횟수, 검색했던 시간 JusoSearchHisDto 객체의 리스트를 반환합니다.
     */
    public List<JusoSearchHisDto> checkUserSearchHistory(String memNb) {
        return jusoMapper.findUserSearchHistory(memNb);
    }


    /**
     * 사용자의 주소 검색 기록 중 특정 주소 검색 기록을 삭제
     *
     * @param memNb 회원 번호
     * @param searchJusoHistory 삭제할 주소 검색 기록
     */
    public void deleteUserSearchHistory(String memNb, String searchJusoHistory){
        jusoMapper.deleteUserSearchHistory(memNb, searchJusoHistory);
    }

}
