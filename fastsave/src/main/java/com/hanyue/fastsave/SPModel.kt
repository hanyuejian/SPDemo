package com.hanyue.fastsave

import android.content.Context
import java.lang.Exception

open class SPModel {

    fun save() {
        if (BaseApp.instance != null){
            save(this, BaseApp.instance!!)
        }else{
            throw Exception("Do you have extends BaseApp?")
        }

    }

    fun save(context: Context) {
        save(this, context)
    }

    private fun save(obj: Any, context: Context) {
        val modelKey = obj.javaClass.name
        val fields = obj.javaClass.declaredFields
        modelKey.let {
            val sp = context.getSharedPreferences(modelKey, Context.MODE_PRIVATE)
            val edit = sp.edit()
            for (item in fields) {
                item.isAccessible = true

                when (item.type) {
                    Boolean::class.java -> {
                        edit.putBoolean(item.name, item.getBoolean(obj))
                    }
                    Int::class.java -> {
                        edit.putInt(item.name, item.getInt(obj))
                    }
                    String::class.java -> {
                        edit.putString(item.name, item.get(obj)?.toString())
                    }
                }

            }
            edit.apply()
        }
    }

    companion object {

        fun <T> getStore(service: Class<T>): T {

            if (BaseApp.instance!=null){
                return this.getStore(service = service, context = BaseApp.instance!!)
            }else{
                throw Exception("Do you have extends BaseApp?")
            }
        }


        fun <T> getStore(service: Class<T>, context: Context): T {

            val modelKey = service.name
            val fields = service.declaredFields

            val data = service.newInstance()

            modelKey.let {
                val sp = context.getSharedPreferences(modelKey, Context.MODE_PRIVATE)
                for (item in fields) {
                    item.isAccessible = true

                    when (item.type) {
                        Boolean::class.java -> {
                            item.set(data, sp.getBoolean(item.name, false))
                        }
                        Int::class.java -> {
                            item.set(data, sp.getInt(item.name, 0))
                        }
                        String::class.java -> {
                            item.set(data, sp.getString(item.name, ""))
                        }
                    }

                }
            }

            return data
        }

    }

}