package com.example.daggerwithhilt.local

import com.example.daggerwithhilt.model.Blog
import com.example.daggerwithhilt.util.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor() : EntityMapper<BlogCacheEntity, Blog> {

    override fun mapFromEntity(entity: BlogCacheEntity): Blog {
        return Blog(
            id= entity.id,
            title = entity.title,
            body = entity.body,
            image = entity.image,
            category = entity.categeory
        )
    }

    override fun mapToEntity(domainModel: Blog): BlogCacheEntity {
        return BlogCacheEntity(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            image = domainModel.image,
            categeory = domainModel.category
        )
    }

    fun mapFromEntityList(entities: List<BlogCacheEntity>) : List<Blog> {
        return entities.map { mapFromEntity(it) }
    }


}