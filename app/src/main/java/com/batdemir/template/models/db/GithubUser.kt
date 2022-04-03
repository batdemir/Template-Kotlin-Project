package com.batdemir.template.models.db

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.batdemir.core.models.RecyclerItem
import com.google.gson.annotations.SerializedName

@Entity(tableName = "GithubUser")
data class GithubUser(
    @PrimaryKey
    @ColumnInfo(name = "github_id") override var id: Long,
    @ColumnInfo(name = "github_is_selected") override var isSelected: Boolean,
    @ColumnInfo(name = "github_login") var login: String? = null,
    @SerializedName("node_id")
    @ColumnInfo(name = "github_node_id") var nodeId: String? = null,
    @SerializedName("avatar_url")
    @ColumnInfo(name = "github_avatar_url") var avatarUrl: String? = null,
    @SerializedName("gravatar_id")
    @ColumnInfo(name = "github_gravatar_id") var gravatarId: String? = null,
    @ColumnInfo(name = "github_url") var url: String? = null,
    @SerializedName("html_url")
    @ColumnInfo(name = "github_html_url") var htmlUrl: String? = null,
    @SerializedName("followers_url")
    @ColumnInfo(name = "github_followers_url") var followersUrl: String? = null,
    @SerializedName("following_url")
    @ColumnInfo(name = "github_following_url") var followingUrl: String? = null,
    @SerializedName("gists_url")
    @ColumnInfo(name = "github_gists_url") var gistsUrl: String? = null,
    @SerializedName("starred_url")
    @ColumnInfo(name = "github_starred_url") var starredUrl: String? = null,
    @SerializedName("subscriptions_url")
    @ColumnInfo(name = "github_subscriptions_url") var subscriptionsUrl: String? = null,
    @SerializedName("organizations_url")
    @ColumnInfo(name = "github_organizations_url") var organizationsUrl: String? = null,
    @SerializedName("repos_url")
    @ColumnInfo(name = "github_repos_url") var reposUrl: String? = null,
    @SerializedName("events_url")
    @ColumnInfo(name = "github_events_url") var eventsUrl: String? = null,
    @SerializedName("received_events_url")
    @ColumnInfo(name = "github_received_events_url") var receivedEventsUrl: String? = null,
    @ColumnInfo(name = "github_type") var type: String? = null,
    @SerializedName("site_admin")
    @ColumnInfo(name = "github_site_admin") var siteAdmin: Boolean? = null,
    @ColumnInfo(name = "github_name") var name: String? = null,
    @ColumnInfo(name = "github_company") var company: String? = null,
    @ColumnInfo(name = "github_blog") var blog: String? = null,
    @ColumnInfo(name = "github_location") var location: String? = null,
    @ColumnInfo(name = "github_email") var email: String? = null,
    @ColumnInfo(name = "github_hireable") var hireable: Boolean? = null,
    @ColumnInfo(name = "github_bio") var bio: String? = null,
    @SerializedName("twitter_username")
    @ColumnInfo(name = "github_twitter_username") var twitterUsername: String? = null,
    @SerializedName("public_repos")
    @ColumnInfo(name = "github_public_repos") var publicRepos: Long? = null,
    @SerializedName("public_gists")
    @ColumnInfo(name = "github_public_gists") var publicGists: Long? = null,
    @ColumnInfo(name = "github_followers") var followers: Long? = null,
    @ColumnInfo(name = "github_following") var following: Long? = null,
    @SerializedName("created_at")
    @ColumnInfo(name = "github_created_at") var createdAt: String? = null,
    @SerializedName("updated_at")
    @ColumnInfo(name = "github_updated_at") var updatedAt: String? = null,
) : RecyclerItem, Parcelable {
    constructor(parcel: Parcel) : this(
        id = parcel.readLong(),
        isSelected = parcel.readByte() != 0.toByte(),
        login = parcel.readString(),
        nodeId = parcel.readString(),
        avatarUrl = parcel.readString(),
        gravatarId = parcel.readString(),
        url = parcel.readString(),
        htmlUrl = parcel.readString(),
        followersUrl = parcel.readString(),
        followingUrl = parcel.readString(),
        gistsUrl = parcel.readString(),
        starredUrl = parcel.readString(),
        subscriptionsUrl = parcel.readString(),
        organizationsUrl = parcel.readString(),
        reposUrl = parcel.readString(),
        eventsUrl = parcel.readString(),
        receivedEventsUrl = parcel.readString(),
        type = parcel.readString(),
        siteAdmin = parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        name = parcel.readString(),
        company = parcel.readString(),
        blog = parcel.readString(),
        location = parcel.readString(),
        email = parcel.readString(),
        hireable = parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        bio = parcel.readString(),
        twitterUsername = parcel.readString(),
        publicRepos = parcel.readValue(Long::class.java.classLoader) as? Long,
        publicGists = parcel.readValue(Long::class.java.classLoader) as? Long,
        followers = parcel.readValue(Long::class.java.classLoader) as? Long,
        following = parcel.readValue(Long::class.java.classLoader) as? Long,
        createdAt = parcel.readString(),
        updatedAt = parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeByte(if (isSelected) 1 else 0)
        parcel.writeString(login)
        parcel.writeString(nodeId)
        parcel.writeString(avatarUrl)
        parcel.writeString(gravatarId)
        parcel.writeString(url)
        parcel.writeString(htmlUrl)
        parcel.writeString(followersUrl)
        parcel.writeString(followingUrl)
        parcel.writeString(gistsUrl)
        parcel.writeString(starredUrl)
        parcel.writeString(subscriptionsUrl)
        parcel.writeString(organizationsUrl)
        parcel.writeString(reposUrl)
        parcel.writeString(eventsUrl)
        parcel.writeString(receivedEventsUrl)
        parcel.writeString(type)
        parcel.writeValue(siteAdmin)
        parcel.writeString(name)
        parcel.writeString(company)
        parcel.writeString(blog)
        parcel.writeString(location)
        parcel.writeString(email)
        parcel.writeValue(hireable)
        parcel.writeString(bio)
        parcel.writeString(twitterUsername)
        parcel.writeValue(publicRepos)
        parcel.writeValue(publicGists)
        parcel.writeValue(followers)
        parcel.writeValue(following)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GithubUser> {
        override fun createFromParcel(parcel: Parcel): GithubUser {
            return GithubUser(parcel)
        }

        override fun newArray(size: Int): Array<GithubUser?> {
            return arrayOfNulls(size)
        }
    }
}
