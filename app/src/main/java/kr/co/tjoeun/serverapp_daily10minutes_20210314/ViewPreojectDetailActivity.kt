package kr.co.tjoeun.serverapp_daily10minutes_20210314

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_view_preoject_detail.*
import kr.co.tjoeun.serverapp_daily10minutes_20210314.datas.Project

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

    }

    override fun setValues() {

//      들어오는 Intent를 통해서 프로젝트 정보 저장
        mProject = intent.getSerializableExtra("projectInfo") as Project

//      프로젝트 제목/이미지 표시.
        projectTitleTxt.text = mProject.title
        Glide.with(mContext).load(mProject.imageUrl).into(projectImg)


    }
}