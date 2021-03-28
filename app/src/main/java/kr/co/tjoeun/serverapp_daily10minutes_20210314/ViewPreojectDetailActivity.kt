package kr.co.tjoeun.serverapp_daily10minutes_20210314

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_view_preoject_detail.*
import kr.co.tjoeun.serverapp_daily10minutes_20210314.datas.Project
import kr.co.tjoeun.serverapp_daily10minutes_20210314.utils.ServerUtil
import org.json.JSONObject

class ViewPreojectDetailActivity : BaseActivity() {

//    상세화면에서 프로젝트 정보는 여러 함수가 공유해서 사용해야 함.
//    그런 변수는 멤버변수로 만드는게 편함
//    변수에 객체를 담는건 => onCreate 이후에 => lateinit var  사용하자.

    lateinit var mProject : Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_preoject_detail)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        참가신청 버튼이 눌리면 => 신청 API 호출

        applyBtn.setOnClickListener {

            ServerUtil.postRequestApplyProject(mContext, mProject.id, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {

//                    402 -
//                    403 - 권한 없는데 신청하는 경우.
//                    404 -
//                    500 - 서버 내부 로직 에러
                    val code = json.getInt("code")

                    if(code == 200) {
//                        정상 신청 완료 => 서버가 최신 상태값 다시 내려줌 => 다시 파싱해서 ui 반영.

//                        신청시 처리방안
//                        1) 참여 인원수 재확인 (서버에서 다시 확인)
//                        2) 신청하기 버튼 대신, 포기하기 버튼으로 대체.

                        val dataObj = json.getJSONObject("data")
                        val projectObj = dataObj.getJSONObject("project")

//                        projectObj 하나의 프로젝트 정보를 담고있는 JSONObject.
//                        Project 클래스의 파싱 기능에 집어걶기 적당함
                        val projectData = Project.getProjectDataFromJson(projectObj)

//                        화면에 뿌려지는 프로젝트 : mProject 갱신
                        mProject = projectData

//                        UI 상에서도 문구 반영
                        runOnUiThread {
                            memberCountTxt.text = "${mProject.ongoingUsersCount}명"
                        }


                    }
                    else {
                        val message = json.getString("message")

                        runOnUiThread {
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }


            } )

        }

    }

    override fun setValues() {

//      들어오는 Intent를 통해서 프로젝트 정보 저장
        mProject = intent.getSerializableExtra("projectInfo") as Project

        refreshUI()
    }



//        서버에서 받은 데이터 (mProject) 를 기반으로 => UI 새로 반영 함수.

    fun refreshUI() {
        //      프로젝트 제목/이미지 표시.
        projectTitleTxt.text = mProject.title
        Glide.with(mContext).load(mProject.imageUrl).into(projectImg)

        projectDescTxt.text = mProject.description

//        참여 인원 수 반영 : ?명 양식으로 가공
        memberCountTxt.text = "${mProject.ongoingUsersCount}명"

//        내가 이 프로젝트에 참여중인가?
//        참가상태 == "ONGOING"이면 참여중, 그 외 모든 경우 참여 X.
        if(mProject.myLastStatus == "ONGOING") {
//            참여하기 버튼 숨김
//            포기하기 버튼 보여주기
            applyBtn.visibility = View.GONE
            giveUpBtn.visibility = View.VISIBLE
        }
        else {

//            참여하기 보여주기, 포기하기 숨겨주기
            applyBtn.visibility = View.VISIBLE
            giveUpBtn.visibility = View.GONE

        }
    }
}