package io.hanbings.fluocean.github;

@SuppressWarnings("unused")
public enum GithubScope {
    NO_SCOPE(""),
    REPO("repo"),
    REPO_STATUS("repo:status"),
    REPO_DEPLOYMENT("repo_deployment"),
    PUBLIC_REPO("public_repo"),
    REPO_INVITE("repo:invite"),
    SECURITY_EVENTS("security_events"),
    ADMIN_REPO_HOOK("admin:repo_hook"),
    WRITE_REPO_HOOK("write:repo_hook"),
    READ_REPO_HOOK("read:repo_hook"),
    ADMIN_ORG("admin:org"),
    WRITE_ORG("write:org"),
    READ_ORG("read:org"),
    ADMIN_PUBLIC_KEY("admin:public_key"),
    WRITE_PUBLIC_KEY("write:public_key"),
    READ_PUBLIC_KEY("read:public_key"),
    ADMIN_ORG_HOOK("admin:org_hook"),
    GIST("gist"),
    NOTIFICATIONS("notifications"),
    USER("user"),
    READ_USER("read:user"),
    USER_EMAIL("user:email"),
    USER_FOLLOW("user:follow"),
    PROJECT("project"),
    READ_PROJECT("read:project"),
    DELETE_REPO("delete_repo"),
    WRITE_DISCUSSION("write:discussion"),
    READ_DISCUSSION("read:discussion"),
    WRITE_PACKAGES("write:packages"),
    READ_PACKAGES("read:packages"),
    DELETE_PACKAGES("delete:packages"),
    ADMIN_GPG_KEY("admin:gpg_key"),
    WRITE_GPG_KEY("write:gpg_key"),
    READ_GPG_KEY("read:gpg_key"),
    @SuppressWarnings("SpellCheckingInspection")
    CODESPACE("codespace"),
    WORKFLOW("workflow");

    final String value;

    GithubScope(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
