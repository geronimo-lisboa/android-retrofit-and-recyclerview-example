package geronimo.don.stackoverflowget

import android.app.Activity
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import geronimo.don.stackoverflowget.model.*
import kotlinx.android.synthetic.main.stack_overflow_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class MyAdapter(val owner:Activity):RecyclerView.Adapter<MyAdapter.StackOverflowViewHolder>() {
    private var items:List<Item> = ArrayList()

    public fun setItems(lst:List<Item>){
        items = lst;
        owner.runOnUiThread{
            notifyDataSetChanged()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StackOverflowViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.stack_overflow_item,parent, false)
        return StackOverflowViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: StackOverflowViewHolder, position: Int) {
        var item = items[position]
        var tags = ""
        item.tags!!.forEach({t->
            tags = tags +", "+ t
        })
        //Html.fromHtml(Html.fromHtml(mHtmlString).toString())
        holder.txtTags.text = tags
        holder.txtLink.text = item.link
        holder.txtTitle.text = Html.fromHtml(Html.fromHtml(item.title).toString())
        if(item.isAnswered==true){
            holder.txtIsAnswered.text ="Respondida"
        }else{
            holder.txtIsAnswered.text = "Não respondida"
        }
        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm")//SimpleDateFormat("d M Y") //SimpleDateFormat("dd/MM/yyyy hh:mm")
        val formattedDate = formatter.format(Date(item.creationDate!!.toLong()*1000))
        holder.txtCreationDate.text = formattedDate// item.creationDate.toString()
       // String dateString = formatter.format(new Date(Long.parseLong(YOUR TIMESTAMP VALUE)));


    }

    class StackOverflowViewHolder(val view:View):RecyclerView.ViewHolder(view){
        val txtIsAnswered: TextView
        val txtCreationDate:TextView
        val txtTitle:TextView
        val txtLink:TextView
        val txtTags:TextView
        init {
            txtIsAnswered = view.txtIsAnswered
            txtCreationDate = view.txtCreationDate
            txtTitle = view.txtTitle
            txtLink = view.txtLink
            txtTags = view.txtTags
        }
//“is_answered”
//“title”
//“link”
//“tags”
    }
}