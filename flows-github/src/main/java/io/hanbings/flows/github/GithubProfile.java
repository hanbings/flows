package io.hanbings.flows.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.hanbings.flows.common.interfaces.Profile;

@SuppressWarnings("SpellCheckingInspection")
public record GithubProfile(
        @JsonProperty("gists_url")
        @SerializedName("gists_url")
        String gistsUrl,
        @JsonProperty("repos_url")
        @SerializedName("repos_url")
        String reposUrl,
        @JsonProperty("following_url")
        @SerializedName("following_url")
        String followingUrl,
        @JsonProperty("twitter_username")
        @SerializedName("twitter_username")
        String twitterUsername,
        @JsonProperty("bio")
        @SerializedName("bio")
        String bio,
        @JsonProperty("created_at")
        @SerializedName("created_at")
        String createdAt,
        @JsonProperty("login")
        @SerializedName("login")
        String login,
        @JsonProperty("type")
        @SerializedName("type")
        String type,
        @JsonProperty("blog")
        @SerializedName("blog")
        String blog,
        @JsonProperty("subscriptions_url")
        @SerializedName("subscriptions_url")
        String subscriptionsUrl,
        @JsonProperty("updated_at")
        @SerializedName("updated_at")
        String updatedAt,
        @JsonProperty("site_admin")
        @SerializedName("site_admin")
        boolean siteAdmin,
        @JsonProperty("company")
        @SerializedName("company")
        Object company,
        @JsonProperty("id")
        @SerializedName("id")
        int id,
        @JsonProperty("public_repos")
        @SerializedName("public_repos")
        int publicRepos,
        @JsonProperty("gravatar_id")
        @SerializedName("gravatar_id")
        String gravatarId,
        @JsonProperty("email")
        @SerializedName("email")
        Object email,
        @JsonProperty("organizations_url")
        @SerializedName("organizations_url")
        String organizationsUrl,
        @JsonProperty("hireable")
        @SerializedName("hireable")
        Object hireable,
        @JsonProperty("starred_url")
        @SerializedName("starred_url")
        String starredUrl,
        @JsonProperty("followers_url")
        @SerializedName("followers_url")
        String followersUrl,
        @JsonProperty("public_gists")
        @SerializedName("public_gists")
        int publicGists,
        @JsonProperty("url")
        @SerializedName("url")
        String url,
        @JsonProperty("received_events_url")
        @SerializedName("received_events_url")
        String receivedEventsUrl,
        @JsonProperty("followers")
        @SerializedName("followers")
        int followers,
        @JsonProperty("avatar_url")
        @SerializedName("avatar_url")
        String avatarUrl,
        @JsonProperty("events_url")
        @SerializedName("events_url")
        String eventsUrl,
        @JsonProperty("html_url")
        @SerializedName("html_url")
        String htmlUrl,
        @JsonProperty("following")
        @SerializedName("following")
        int following,
        @JsonProperty("name")
        @SerializedName("name")
        String name,
        @JsonProperty("location")
        @SerializedName("location")
        String location,
        @JsonProperty("node_id")
        @SerializedName("node_id")
        String nodeId
) implements Profile {
    record Wrong(
            @JsonProperty("message")
            @SerializedName("message")
            String message,
            @JsonProperty("documentation_url")
            @SerializedName("documentation_url")
            String documentationUrl
    ) implements Profile.Wrong {

    }
}