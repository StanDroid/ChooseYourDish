package com.cyd.base.mapper


interface Mapper<P, R> {

    fun map(param: P): R

    fun map(params: List<P>) = params.map { map(it) }
}
