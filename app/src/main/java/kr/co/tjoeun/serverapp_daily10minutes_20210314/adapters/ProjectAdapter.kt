package kr.co.tjoeun.serverapp_daily10minutes_20210314.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kr.co.tjoeun.serverapp_daily10minutes_20210314.R
import kr.co.tjoeun.serverapp_daily10minutes_20210314.datas.Project

class ProjectAdapter(
    val mContext  : Context,
    val resId : Int,
    val mList : List<Project> ) : ArrayAdapter<Project> (mContext, resId, mList) {

    val inflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView

        if(tempRow == null) {
            tempRow = inflater.inflate(R.layout.project_list_item, null)
        }

//        진짜 한칸을 만든다.
        val row = tempRow!!
        return row
    }
}