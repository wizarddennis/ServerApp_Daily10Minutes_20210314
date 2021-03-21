package kr.co.tjoeun.serverapp_daily10minutes_20210314

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import kotlinx.android.synthetic.main.activity_main.*
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
        signUpBtn.setOnClickListener {
//            이메일 / 비번 / 닉네임 -> 서버 회원가입기능에 전송.
            val email = emailEdt.text.toString()
            val pw = pwEdt.text.toString()
            val nick = nicknameEdit.text.toString()

//            서버 - 회원가입 기능에 전송 : ServerUtil 에 회원가입 함수 필요
            ServerUtil.putRequestSignUp(email, pw, nick, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {

                }

            })

        }

    }

    override fun setValues() {

    }


}