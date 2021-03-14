package kr.co.tjoeun.serverapp_daily10minutes_20210314

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity  : AppCompatActivity() {

    // this 대신에 들어간 변수.
    val mContext = this

    // 추상 함수 2가지. => 물려받은 클래스들이 반드시 구현해야될 의무사항.
    abstract fun setupEvents() //버튼 클릭/ 리스트뷰 아이템 클릭등 모든 이벤트처리 모아두는 함수
    abstract fun setValues() //화면에 데이터를 보여주는 관련 코드를 모아두는 함수.
}