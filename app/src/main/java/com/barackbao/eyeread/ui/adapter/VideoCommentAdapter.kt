package com.barackbao.eyeread.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.barackbao.eyeread.mvp.model.bean.Item
import com.barackbao.eyeread.ui.customviews.EndListView
import com.barackbao.eyeread.ui.customviews.VideoCommentDetailTitleView
import com.barackbao.eyeread.ui.customviews.VideoCommentDetailView

/**
 * Created by BarackBao on 2018/3/15.
 */
class VideoCommentAdapter : RecyclerView.Adapter<VideoCommentAdapter.ViewHolder>() {

    //热门，最新评论
    private val TYPE_COMMENT_TITLE = 0
    private val TYPE_COMMENT = 1
    private val TYPE_END = 2

    val data by lazy { ArrayList<Item>() }


    override fun getItemViewType(position: Int): Int {
        if (data[position].data == null) {
            return TYPE_END
        }
        when (data[position].type) {
            "reply" -> {
                return TYPE_COMMENT
            }
            "leftAlignTextHeader" -> {
                return TYPE_COMMENT_TITLE
            }
        }
        return super.getItemViewType(position)
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var itemView: View? = null
        when (viewType) {
            TYPE_COMMENT -> {
                itemView = VideoCommentDetailView(parent?.context)
            }
            TYPE_COMMENT_TITLE -> {
                itemView = VideoCommentDetailTitleView(parent?.context)
            }
            TYPE_END -> {
                itemView = EndListView(parent?.context)
            }
        }
        return ViewHolder(itemView)

    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val itemView = holder?.itemView
        when (getItemViewType(position)) {
            TYPE_COMMENT_TITLE -> {
                (itemView as VideoCommentDetailTitleView).setText(data[position])
            }
            TYPE_COMMENT -> {
                (itemView as VideoCommentDetailView).setData(data[position])
            }
        }
    }

    //加载更多
    fun addData(items: ArrayList<Item>) {
        data.addAll(items)
        notifyDataSetChanged()
    }

    fun addData(item: Item) {
        if (data[data.size - 1].data == null) { //数据为空时
            return
        }
        data.add(item)
        notifyDataSetChanged()
    }

    fun setData(items: ArrayList<Item>) {
        data.clear()
        notifyDataSetChanged()
        addData(items)
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}