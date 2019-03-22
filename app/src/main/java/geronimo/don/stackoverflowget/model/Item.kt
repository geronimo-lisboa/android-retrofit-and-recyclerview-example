package geronimo.don.stackoverflowget.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Item {

    @SerializedName("tags")
    @Expose
    var tags: List<String>? = null
    @SerializedName("owner")
    @Expose
    var owner: Owner? = null
    @SerializedName("is_answered")
    @Expose
    var isAnswered: Boolean? = null
    @SerializedName("view_count")
    @Expose
    var viewCount: Int? = null
    @SerializedName("answer_count")
    @Expose
    var answerCount: Int? = null
    @SerializedName("score")
    @Expose
    var score: Int? = null
    @SerializedName("last_activity_date")
    @Expose
    var lastActivityDate: Int? = null
    @SerializedName("creation_date")
    @Expose
    var creationDate: Int? = null
    @SerializedName("question_id")
    @Expose
    var questionId: Int? = null
    @SerializedName("link")
    @Expose
    var link: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("last_edit_date")
    @Expose
    var lastEditDate: Int? = null
    @SerializedName("accepted_answer_id")
    @Expose
    var acceptedAnswerId: Int? = null
    @SerializedName("bounty_amount")
    @Expose
    var bountyAmount: Int? = null
    @SerializedName("bounty_closes_date")
    @Expose
    var bountyClosesDate: Int? = null
    @SerializedName("closed_date")
    @Expose
    var closedDate: Int? = null
    @SerializedName("closed_reason")
    @Expose
    var closedReason: String? = null

}

