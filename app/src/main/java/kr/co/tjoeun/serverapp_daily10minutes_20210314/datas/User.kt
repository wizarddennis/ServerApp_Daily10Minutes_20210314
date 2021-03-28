package kr.co.tjoeun.serverapp_daily10minutes_20210314.datas

import okhttp3.internal.userAgent
import org.json.JSONObject
import java.io.Serializable

class User : Serializable {
    var id = 0
    var email = ""
    var nickName = ""

    val profileImgUrls = ArrayList<String>()

    companion object {
        fun getUserDataFromJson(jsonObj : JSONObject) : User {

            val userData = User()

//            userData의 변수들에 값 대입
            userData.id = jsonObj.getInt("id")
            userData.email = jsonObj.getString("email")
            userData.nickName = jsonObj.getString("nick_name")

            val pfImgArr =  jsonObj.getJSONArray("profile_images")

            for (index in 0 until pfImgArr.length()) {
                val imgObj = pfImgArr.getJSONObject(index)

                val imgUrl = pfImgArr.getString(index)

                userData.profileImgUrls.add(imgUrl)
            }


            return  userData

        }
    }
}