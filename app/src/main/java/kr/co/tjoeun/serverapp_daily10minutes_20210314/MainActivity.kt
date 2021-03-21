package kr.co.tjoeun.serverapp_daily10minutes_20210314

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.tjoeun.serverapp_daily10minutes_20210314.adapters.ProjectAdapter
import kr.co.tjoeun.serverapp_daily10minutes_20210314.datas.Project
import kr.co.tjoeun.serverapp_daily10minutes_20210314.utils.ContextUtil
import kr.co.tjoeun.serverapp_daily10minutes_20210314.utils.ServerUtil
import org.json.JSONObject

class MainActivity : BaseActivity() {

    val mProjectList = ArrayList <Project> ()

    lateinit var mAdapter : ProjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        logoutBtn.setOnClickListener {
//            로그인? id/pw => 서버에 회원이 맞나? 요청 => 성공시 토큰을 기기에 저장

//            로그아웃? => 기기에 저장된 토큰을 날리는 작업.
            ContextUtil.setToken(mContext, "")

//            다시 로그인 화면으로 이동.
            val myIntent = Intent(mContext, LoginActivity::class.java)
            startActivity(myIntent)

            finish()
        }

    }

    override fun setValues() {
        mAdapter = ProjectAdapter(mContext, R.layout.project_list_item, mProjectList)
        projectListView.adapter = mAdapter



//        메인화면에 들어오면 => 프로젝트 목록이 뭐뭐있는지 서버에 요청(ServerUtil 함수 추가)
//        받아온 결과를 분석해서=> Proejct() 형태로 만들어서 => mProjectList에 add 해주자.

        ServerUtil.getRequestProjectList (mContext, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {
                val dataObj = json.getJSONObject("data")

//                data 내부의 [  ] 배열을 가져오는 코드.
                val projectsArr = dataObj.getJSONArray("projects")

//                projects [  ]  => 이름표가 아니라, 순서대로 하나씩 추출.
//                첫번째[0] ~  마지막[갯수직전]까지 반복적으로 하나씩 추출. => Kotlin 반복문 활용.
                for ( i in  0 until projectsArr.length()) {
//                    Log.d("프로젝트목록반복", "${i}번째")

//                    {} 프로젝트 정보 덩어리 JSONObject 추출
                    val projectObj = projectsArr.getJSONObject(i)

//                    projectObj > Project 클래스 형태로 가공.
                    val project = Project()

//                    {} 내부의 데이터를  => 데이터클래스의 변수에 옮겨적자.
                    project.id = projectObj.getInt("id")
                    project.title = projectObj.getString("title")

//                    왼쪽 벼누 : 데이터클래스에 만든 변수 Vs. 오른쪽 이름표 : 서버가 내려주는 이름표.
                    project.imageUrl = projectObj.getString("img_url")

//                    가공된 데이터를 목록에 추가.
                    mProjectList.add(project)
                }

//                for문 끝나면 => 모든 프로젝트가 목록에 추가된 상태.
//                목록 변경이 생겼으니 => 어댑터 새로고침 (UI 변경) 필요
                runOnUiThread {
                    mAdapter.notifyDataSetChanged()
                }
            }
        })


    }


}