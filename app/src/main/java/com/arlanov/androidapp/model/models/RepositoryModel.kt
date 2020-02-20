package com.arlanov.androidapp.model.models

import com.google.gson.annotations.SerializedName

data class RepositoryModel(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("total_count")
    val total_count: Int
)
data class Item(
    @SerializedName("description")
    val description: Any?,
    @SerializedName("forks_count")
    val forks_count: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("watchers_count")
    val watchers_count: Int
)

data class Owner(
    @SerializedName("avatar_url")
    val avatar_url: String? = ""
)

data class RepositoryModelWrapper(
    val description: String? = "",
    val forks_count: Int,
    val name: String,
    val avatar_url: String? = "",
    val watchers_count: Int
)

fun mapWrapperRepository(repositoryModel: Item): RepositoryModelWrapper{
    if (repositoryModel.description != null){
        return RepositoryModelWrapper(
            description = repositoryModel.description.toString(),
            name = repositoryModel.name,
            forks_count = repositoryModel.forks_count,
            avatar_url = repositoryModel.owner.avatar_url,
            watchers_count = repositoryModel.watchers_count
        )
    }else{
        return RepositoryModelWrapper(
            name = repositoryModel.name,
            forks_count = repositoryModel.forks_count,
            avatar_url = repositoryModel.owner.avatar_url,
            watchers_count = repositoryModel.watchers_count
        )
    }

}