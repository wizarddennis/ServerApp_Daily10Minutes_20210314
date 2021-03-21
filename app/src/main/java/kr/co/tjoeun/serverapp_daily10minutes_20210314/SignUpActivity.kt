package kr.co.tjoeun.serverapp_daily10minutes_20210314

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_up.*
import kr.co.tjoeun.serverapp_daily10minutes_20210314.utils.ServerUtil
import org.json.JSONObject

class SignUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        checkEmailBtn.setOnClickListener {
//
            val inputEmail = emailEdt.text.toString()

//            서버에 -> /email

        }

        signUpBtn.setOnClickListener {
//            이메일 / 비번 / 닉네임 -> 서버 회원가입기능에 전송.
            val email = emailEdt.text.toString()
            val pw = pwEdt.text.toString()
            val nick = nicknameEdit.text.toString()

//            서버 - 회원가입 기능에 전송 : ServerUtil 에 회원가입 함수 필요
            ServerUtil.putRequestSignUp(email, pw, nick, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {
// 회원가입 결과에 따른 UI 반영 필요
//                    code : 200 이면 가입성공 -> 토스트 + 회원가입 화면 종료.
//                    그외 값 : 서버가 주는 실패 사유를 -> 토스트

                    val code = json.getInt("code")

                    runOnUiThread {
                        if(code == 200) {

//                        성공 처리 가입한 사람의 이름을 추출해서 환영메시지
//                            json > data{} > user {} > "nick_name" String 추출
                            val dataObj = json.getJSONObject("data")
                            val userObj = dataObj.getJSONObject("user")
                            val userName =userObj.getString("nick_name")

                            Toast.makeText(mContext, "${userName}님 환영합니다.", Toast.LENGTH_SHORT).show()

                            finish()

                        }
                        else {
//                        실패 처리.  서버가 주는 message 스트링을 토스트로 출력
                            val message = json. getString("message")
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()

                        }
                    }
                }

            })

        }

    }

    override fun setValues() {

    }


}