package kr.co.tjoeun.serverapp_daily10minutes_20210314.datas

import org.json.JSONObject
import java.io.Serializable

class Project : Serializable {

    var id = 0 // Int라는 명시.
    var title = "" // String 이라는 명시.
    var imageURL = "" // 그림파일 경로 (String) 저장 변수.
    var description = ""
    var ongoingUsersCount = 0

    var myLastStatus : String? = null // null이 될 수도 있는 String. 기본값은 null
    var proofMethod = ""

    //    하나의 프로젝트에 => 여러개의 태그 목록이 있다.
//    하나의 프로젝트 변수에 => 여러개의 태그(String) 변수가 있다. => ArrayList<String> 형태로 표현하자.
    val hashTags = ArrayList<String>()


//    기능 추가. JSONObject 하나를 적당히 넣으면 => 함수 내부에서 가공해서 => Project 로 내보내주는 기능.
//    어떤 프로젝트 객체가 실행하느냐는 의미가 없다. 기능만 잘 수행되면 됨.
//    companion object 이용해서, Project.기능() 과 같이 코딩되도록 지원.

    companion object {

        fun getProjectDataFromJson(jsonObj : JSONObject) : Project {

            val projectData = Project()

//            jsonObj 내용 분석 (파싱) => projectData의 하위 항목들 채우기.
            projectData.id = jsonObj.getInt("id")
            projectData.title = jsonObj.getString("title")
            projectData.imageURL = jsonObj.getString("img_url")
            projectData.description = jsonObj.getString("description")
            projectData.ongoingUsersCount = jsonObj.getInt("ongoing_users_count")

//            projectData.proofMethod = jsonObj.get

//            나의참가상태 : JSON에서 null로 담겨있을 수 있다. => null인지 확인하고 동작시켜야 안전함.
            if (!jsonObj.isNull("my_last_status")) {

//                null 이 아닐때만 파싱하자.
                projectData.myLastStatus = jsonObj.getString("my_last_status")
            }

//            태그목록(JSONArray)을 파싱 => String만 추출해서 해쉬태그목록에 담아주자.
            val tagsArr = jsonObj.getJSONArray("tags")

            for (index   in   0 until tagsArr.length()) {
                val tagObj = tagsArr.getJSONObject(index)
//                중괄호 {  } 안에서 => title String만 꺼내서 태그목록으로 추가.
                val tagTitle = tagObj.getString("title")
                projectData.hashTags.add(tagTitle)
            }

//            완성된 projectData가 결과로 나가도록
            return projectData
        }

    }




}
