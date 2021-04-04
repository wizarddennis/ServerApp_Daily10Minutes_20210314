package kr.co.tjoeun.serverapp_daily10minutes_20210314


import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_view_proof.*
import kr.co.tjoeun.serverapp_daily10minutes_20210314.datas.Project
import kr.co.tjoeun.serverapp_daily10minutes_20210314.utils.ServerUtil
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class ViewProofActivity : BaseActivity() {

//    인증을 확인하려는 날짜를 Calendar 형태로 저장해두자.
    val mProofDate = Calendar.getInstance()

//    어떤 프로젝트에 대한 인증글을 보는건지.
    lateinit var mProject : Project


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_proof)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        날짜 선택 버튼이 눌리면 => 달력을 띄우자.

        selectDateBtn.setOnClickListener {

//            1. 날짜가 선택이 되면 => 어떤 행동을 할지 가이드북 변수.

            val dateSetListener = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

//                    연 / 월 / 일을 가지고 처리할 코드 작성.

//                    1. 확인할 인증날짜를 변경 (mProofDate 반영)
                    mProofDate.set(year, month, dayOfMonth)

//                    2. 텍스튜뷰에 선택한 날짜 찍어주기
                    val sdf = SimpleDateFormat("yyyy년 M월 d일")
                    dateTxt.text = sdf.format(mProofDate.time)

//                    3. 해당 날짜의 해당 프로젝트에 맞는 인증글 목록 불러오기.(서버통신)






                }

            }

//            2. 그 가이드북을 들고, 실제로 날짜를 선택하러 가기. (날짜 선택용 팝업창 띄우기)

            val datePickerDialog = DatePickerDialog(mContext, dateSetListener,
                2021, Calendar.APRIL, 4)

            datePickerDialog.show()

        }

    }

    fun getProofListFromServer() {

//        서버에 인증글 불러오는 기능 활용.  ServerUtil 활용.

//        mProofDate => String으로 가공 (2020-06-09 등 양식) 해서 첨부.

        val serverFormat  = SimpleDateFormat("yyyy-MM-dd")

        ServerUtil.getRequestProjectProofByDate(mContext, mProject.id, serverFormat.format(mProofDate.time), object : JSONObject {

        })
    }

    override fun setValues() {

//        어떤 프로젝트를 볼건지 데이터 받아서 저장
        mProject = intent.getSerializableExtra("project") as Project

//        mProofDate에는 기본적으로 현재 일시가 기록되어있ㄷ.
//        인증 확인 날짜에 현재 일시를 반영해보자.
        
//        Calendar의 항목별 조회 기능 예시
//        dateTxt.text = "${mProofDate.get(Calendar.YEAR)}-${mProofDate.get(Calendar.MONTH+1)}-${mProofDate.get(Calendar)}"

//      SimpleDateFormat 클래스 활용 예시

//        가공해줄 양식 지정
        val sdf = SimpleDateFormat("yyyy-MM-dd")
//        val sdf = SimpleDateFormat("yyyy년 M월 dd일)

        dateTxt.text = sdf.format(mProofDate.time)
    }

}