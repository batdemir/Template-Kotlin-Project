package com.batdemir.template.data.local.datasource

import com.batdemir.template.data.entities.db.Example
import com.batdemir.template.data.local.dao.ExampleDao
import javax.inject.Inject

class ExampleLocalDataSource @Inject constructor(
    private val dao: ExampleDao
) {
    fun get() = dao.get()

    fun get(id: Long) = dao.get(id)

    suspend fun add(model: Example) = dao.add(model)

    suspend fun delete(model: Example) = dao.delete(model)

    suspend fun update(model: Example) = dao.update(model)
}