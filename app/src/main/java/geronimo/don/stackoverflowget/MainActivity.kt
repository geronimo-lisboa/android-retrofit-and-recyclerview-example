package geronimo.don.stackoverflowget

import android.Manifest
import android.content.pm.PackageManager
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.recyclerview.widget.DividerItemDecoration



class MainActivity : AppCompatActivity() {
    private lateinit var mRetrofit: Retrofit
    private lateinit var mInterface: MyRestInterface

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            INTERNET_PERMISSION->{
                if(grantResults.size >0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Log.d("TESTE", "Permissáo concedida")
                    //getStackOverflowData()
                }
                else{
                    Toast.makeText(this, "SEM PERMISSÁO DE INTERNET", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun getStackOverflowData(tagged:String){
        if(!checkInternetPermission()){
            Toast.makeText(this, "SEM PERMISSÁO DE INTERNET", Toast.LENGTH_LONG).show()
        }else{
            val call = mInterface.doGet(tagged)
            DoAsync{
                val response = call.execute()
                val resultData = response.body()
                resultData?.items?.let{
                    adapter.setItems(it)
                }?:kotlin.run {
                    Toast.makeText(this, "SEM RESULTADOS", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun initRetrofit(){
        //Construçao do RETROFIT
        mRetrofit = Retrofit.Builder()
            .baseUrl("https://api.stackexchange.com/2.2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mInterface=mRetrofit.create(MyRestInterface::class.java)
    }

    private fun checkInternetPermission():Boolean{
        val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
        if(permissionCheck!= PackageManager.PERMISSION_GRANTED){
            Log.d("TESTE", "sem permissao")
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET), INTERNET_PERMISSION)
            return false;
        }else{
            return true;
        }
    }

    private fun setCallbacks(){
        btnBuscar.setOnClickListener({v->
            val tagged = edtBusca.text.toString()
            getStackOverflowData(tagged)
        })
    }

    private lateinit var adapter:MyAdapter

    private fun initRecyclerView(){
        adapter = MyAdapter(this)


        recyclerView.adapter = adapter
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRetrofit()
        checkInternetPermission()
        initRecyclerView()
        setCallbacks()
    }
    companion object {
        val INTERNET_PERMISSION = 999
    }
}
class DoAsync(val action:()->Unit): AsyncTask<Void, Unit, Unit>(){
    init{
        execute()
    }
    override fun doInBackground(vararg p0: Void?) {
        action()
    }
}

//-----------------------------------com.example.Example.java-----------------------------------
//
//package com.example;
//
//import java.util.List;
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//public class Example {
//
//@SerializedName("items")
//@Expose
//private List<Item> items = null;
//@SerializedName("has_more")
//@Expose
//private Boolean hasMore;
//@SerializedName("quota_max")
//@Expose
//private Integer quotaMax;
//@SerializedName("quota_remaining")
//@Expose
//private Integer quotaRemaining;
//
//public List<Item> getItems() {
//return items;
//}
//
//public void setItems(List<Item> items) {
//this.items = items;
//}
//
//public Boolean getHasMore() {
//return hasMore;
//}
//
//public void setHasMore(Boolean hasMore) {
//this.hasMore = hasMore;
//}
//
//public Integer getQuotaMax() {
//return quotaMax;
//}
//
//public void setQuotaMax(Integer quotaMax) {
//this.quotaMax = quotaMax;
//}
//
//public Integer getQuotaRemaining() {
//return quotaRemaining;
//}
//
//public void setQuotaRemaining(Integer quotaRemaining) {
//this.quotaRemaining = quotaRemaining;
//}
//
//}
//-----------------------------------com.example.Item.java-----------------------------------
//
//package com.example;
//
//import java.util.List;
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//public class Item {
//
//@SerializedName("tags")
//@Expose
//private List<String> tags = null;
//@SerializedName("owner")
//@Expose
//private Owner owner;
//@SerializedName("is_answered")
//@Expose
//private Boolean isAnswered;
//@SerializedName("view_count")
//@Expose
//private Integer viewCount;
//@SerializedName("answer_count")
//@Expose
//private Integer answerCount;
//@SerializedName("score")
//@Expose
//private Integer score;
//@SerializedName("last_activity_date")
//@Expose
//private Integer lastActivityDate;
//@SerializedName("creation_date")
//@Expose
//private Integer creationDate;
//@SerializedName("question_id")
//@Expose
//private Integer questionId;
//@SerializedName("link")
//@Expose
//private String link;
//@SerializedName("title")
//@Expose
//private String title;
//@SerializedName("last_edit_date")
//@Expose
//private Integer lastEditDate;
//@SerializedName("accepted_answer_id")
//@Expose
//private Integer acceptedAnswerId;
//@SerializedName("bounty_amount")
//@Expose
//private Integer bountyAmount;
//@SerializedName("bounty_closes_date")
//@Expose
//private Integer bountyClosesDate;
//@SerializedName("closed_date")
//@Expose
//private Integer closedDate;
//@SerializedName("closed_reason")
//@Expose
//private String closedReason;
//
//public List<String> getTags() {
//return tags;
//}
//
//public void setTags(List<String> tags) {
//this.tags = tags;
//}
//
//public Owner getOwner() {
//return owner;
//}
//
//public void setOwner(Owner owner) {
//this.owner = owner;
//}
//
//public Boolean getIsAnswered() {
//return isAnswered;
//}
//
//public void setIsAnswered(Boolean isAnswered) {
//this.isAnswered = isAnswered;
//}
//
//public Integer getViewCount() {
//return viewCount;
//}
//
//public void setViewCount(Integer viewCount) {
//this.viewCount = viewCount;
//}
//
//public Integer getAnswerCount() {
//return answerCount;
//}
//
//public void setAnswerCount(Integer answerCount) {
//this.answerCount = answerCount;
//}
//
//public Integer getScore() {
//return score;
//}
//
//public void setScore(Integer score) {
//this.score = score;
//}
//
//public Integer getLastActivityDate() {
//return lastActivityDate;
//}
//
//public void setLastActivityDate(Integer lastActivityDate) {
//this.lastActivityDate = lastActivityDate;
//}
//
//public Integer getCreationDate() {
//return creationDate;
//}
//
//public void setCreationDate(Integer creationDate) {
//this.creationDate = creationDate;
//}
//
//public Integer getQuestionId() {
//return questionId;
//}
//
//public void setQuestionId(Integer questionId) {
//this.questionId = questionId;
//}
//
//public String getLink() {
//return link;
//}
//
//public void setLink(String link) {
//this.link = link;
//}
//
//public String getTitle() {
//return title;
//}
//
//public void setTitle(String title) {
//this.title = title;
//}
//
//public Integer getLastEditDate() {
//return lastEditDate;
//}
//
//public void setLastEditDate(Integer lastEditDate) {
//this.lastEditDate = lastEditDate;
//}
//
//public Integer getAcceptedAnswerId() {
//return acceptedAnswerId;
//}
//
//public void setAcceptedAnswerId(Integer acceptedAnswerId) {
//this.acceptedAnswerId = acceptedAnswerId;
//}
//
//public Integer getBountyAmount() {
//return bountyAmount;
//}
//
//public void setBountyAmount(Integer bountyAmount) {
//this.bountyAmount = bountyAmount;
//}
//
//public Integer getBountyClosesDate() {
//return bountyClosesDate;
//}
//
//public void setBountyClosesDate(Integer bountyClosesDate) {
//this.bountyClosesDate = bountyClosesDate;
//}
//
//public Integer getClosedDate() {
//return closedDate;
//}
//
//public void setClosedDate(Integer closedDate) {
//this.closedDate = closedDate;
//}
//
//public String getClosedReason() {
//return closedReason;
//}
//
//public void setClosedReason(String closedReason) {
//this.closedReason = closedReason;
//}
//
//}
//-----------------------------------com.example.Owner.java-----------------------------------
//
//package com.example;
//
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//public class Owner {
//
//@SerializedName("reputation")
//@Expose
//private Integer reputation;
//@SerializedName("user_id")
//@Expose
//private Integer userId;
//@SerializedName("user_type")
//@Expose
//private String userType;
//@SerializedName("profile_image")
//@Expose
//private String profileImage;
//@SerializedName("display_name")
//@Expose
//private String displayName;
//@SerializedName("link")
//@Expose
//private String link;
//@SerializedName("accept_rate")
//@Expose
//private Integer acceptRate;
//
//public Integer getReputation() {
//return reputation;
//}
//
//public void setReputation(Integer reputation) {
//this.reputation = reputation;
//}
//
//public Integer getUserId() {
//return userId;
//}
//
//public void setUserId(Integer userId) {
//this.userId = userId;
//}
//
//public String getUserType() {
//return userType;
//}
//
//public void setUserType(String userType) {
//this.userType = userType;
//}
//
//public String getProfileImage() {
//return profileImage;
//}
//
//public void setProfileImage(String profileImage) {
//this.profileImage = profileImage;
//}
//
//public String getDisplayName() {
//return displayName;
//}
//
//public void setDisplayName(String displayName) {
//this.displayName = displayName;
//}
//
//public String getLink() {
//return link;
//}
//
//public void setLink(String link) {
//this.link = link;
//}
//
//public Integer getAcceptRate() {
//return acceptRate;
//}
//
//public void setAcceptRate(Integer acceptRate) {
//this.acceptRate = acceptRate;
//}
//
//}