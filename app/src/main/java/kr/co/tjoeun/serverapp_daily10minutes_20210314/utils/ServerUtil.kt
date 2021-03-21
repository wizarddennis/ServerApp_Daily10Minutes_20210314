package kr.co.tjoeun.serverapp_daily10minutes_20210314.utils

import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ServerUtil {
//    API를 호출해주는 함수들을 모아두기 위한 크래스.(코드 정리 차원)

// 화면(액티비티와) 입장에서, 서버에 다녀오면 할 행동을 적는 행동 지침
//    행동 지침을 전달하는 방식 : Interface

    interface JsonResponseHandler {
        fun onResponse (json : JSONObject)
    }


//    ServerUtil.함수() 처럼, 클래이름. 만 해도 바로 사용하게 도와주는 코드
//    JAVA - static 개념에 대으되는 코드.

    companion object{
        //    호스트 주소를 편하게 입력/관리하기 위한 변수
        val HOST_URL = "http://15.164.153.174"

//      함수 작성 - 로그인 기능 담당 함수.

        fun postRequestLogin(id : String, pw : String, handler : JsonResponseHandler?) {
//            실제 기능 수행주소 ex. 로그인 - http://15.164.153.174/user
//            HOST_URL/user => 최종 주소 완성.

            val urlString = "${HOST_URL}/user"

//            POST 메쏘드 - formBody에 데이터 첨부.
            val formData = FormBody.Builder()
                .add("email", id)
                .add("password", pw)
                .build()

//            API 요청(Request)을 어디로 어떻게 할건지 종합하는 변수.
            val request = Request.Builder()
                .url(urlString) // 어디로 가는지 명시
                .post(formData) // POST방식 - 필요데이터(formData) 들고 가도록
                .build()

//            startActivity처럼 -> 실제로 Request 를 수행하는 코드.
//            클라이언트로써 동작하도록 도와주는 라이브러리 : OkHttp
            val client = OkHttpClient()

//            클라이언트가 실제 리퀘스트 수행.
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
//                    서버 연결 자체를 실패

                }

                override fun onResponse(call: Call, response: Response) {

                    val bodyString = response.body!!.string()

//                  bodyString은 인코딩 된 상태라 읽가 어렵다.(한글깨짐)
//                  bodyString을  JSONObject 로 변환시키면 읽을 수 있게됨.

                    val jsonObj = JSONObject(bodyString)
                    Log.d("서버응답본문", jsonObj.toString())

                    // 연습 : code 에 적힌 숫자(Int)가 얼마인가?
                    val codeNum = jsonObj.getInt("code")


// 연습- 활용 : codeNum 200이면 로그인 성공, 아니면 로그인 실패. 로그 찍기
//             로그인에 실패시 => 서버에서 아려주는 실패 사유를 로그로 찍자
                    if(codeNum == 200) {
                        Log.d("로그인 결과", "성공")
                    }
                    else {
                        Log.e("로그인 결과", "실패")

//                        추가로, message로 달려있는 String을 추출.
                         val msgStr = jsonObj.getString("message")
                        Log.e("로그인실패사유", msgStr)
                    }

                }
            }

            )
        }

//        회원 가입 기능 담당 함수.
        fun putRequestSignUp(email : String, pw : String, nickname : String, handler: JsonResponseHandler?) {

//    서버에 회원가입 요청 : 어디로? URL / 어떤 데어터? 파라미터(formData) / 어떤 방식 ? PUT

//    어디로?  http://14.~~/user 형태의 주소
            val urlString = "${HOST_URL}/user"

//      어떤 데이터? 어느 위치에? - 파라미터
//      모든 파라미터를 formData에 담자.
            val formData = FormBody.Builder()
                .add("email", email)
                .add("password", pw)
                .add("nick_name", nickname)
                .build()

//    어떤방식? + 모든 정보 종합. => Request 클래스 사용.
            val request = Request.Builder()
                .url(urlString)
                .put(formData)
                .build()

//            서버로 가기 위한 준비는 끝. => 실제로 (client 클래스 도움) 출발.
            val client = OkHttpClient()

            client.newCall(request)





        }
    }
}