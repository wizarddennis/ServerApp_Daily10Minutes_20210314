package kr.co.tjoeun.serverapp_daily10minutes_20210314

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.tjoeun.serverapp_daily10minutes_20210314.datas.Project

class ViewMemberListActivity : BaseActivity() {

    lateinit var mProject : Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_member_list)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {

    }

    override fun setValues() {

        mProject = intent.getSerializableExtra("project") as Project

    }
}