package kr.co.tjoeun.serverapp_daily10minutes_20210314.utils

import okhttp3.FormBody

class ServerUtil {
//    API를 호출해주는 함수들을 모아두기 위한 크래스.(코드 정리 차원)



//    ServerUtil.함수() 처럼, 클래이름. 만 해도 바로 사용하게 도와주는 코드
//    JAVA - static 개념에 대으되는 코드.

    companion object{
        //    호스트 주소를 편하게 입력/관리하기 위한 변수
        val HOST_URL = "http://15.164.153.174"

//      함수 작성 - 로그인 기능 담당 함수.

        fun postRequestLogin(id : String, pw : String) {
//            실제 기능 수행주소 ex. 로그인 - http://15.164.153.174/user
//            HOST_URL/user => 최종 주소 완성.

            val urlString = "${HOST_URL}/user"

//            POST 메쏘드 - formBody에 데이터 첨부.
            val formData = FormBody.Builder()
                .add("email", id)
                .add("password", pw)
                .build()




        }
    }
}