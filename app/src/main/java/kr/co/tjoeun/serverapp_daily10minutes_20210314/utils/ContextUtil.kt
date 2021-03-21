package kr.co.tjoeun.serverapp_daily10minutes_20210314.utils

import android.content.Context

class ContextUtil {
    companion object {
//        메모장 이름에 대응되는 개념. (환경설정 파일 이름)
        private val prefName = "Daily10MinutePref"

//        저장할 데이터 항목명 변수들.
        private val Is_AUTO_LOGIN = "IS_AUTO_LOGIN"
        private val TOKEN = "TOKEN"

//        토큰값 저장/조회 기능
        fun setToken(context: Context, token : String) {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putString(TOKEN, token).apply()
        }

        fun getToken(context: Context) : String {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

            return pref.getString(TOKEN, "")!!
        }


//        자동로그인 여부 저장(set) / 확인(get) 2가지 기능.

        fun setAutoLogin(context : Context, autoLogin : Boolean) {
//            메모장 파일을 열어주자.
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

//            열린 파일에 -> 자동로그인 항목을 저장하자.
            pref.edit().putBoolean(Is_AUTO_LOGIN, autoLogin).apply()
        }

//        자동로그인 저장된 값 확인 기능
        fun getAutoLogin(context: Context) : Boolean {
//            메모장 파일을 열어보자
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

//            자동로그인 항목에 저장된 항목을 결과로 리턴.
            return pref.getBoolean(Is_AUTO_LOGIN)
        }
    }
}