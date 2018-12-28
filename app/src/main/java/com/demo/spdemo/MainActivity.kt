package com.demo.spdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hanyue.fastsave.SPModel
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        content.text = getData()

        save.setOnClickListener {

            if (age.text.isNullOrEmpty()) {
                Toast.makeText(this, "请输入年龄", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (name.text.isNullOrEmpty()) {
                Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show()
            }

            //如果你继承了BaseApp,可以如下调用
            //SomeSave(beauty.isChecked, age.text.toString().toInt(), name.text.toString()).save()

            SomeSave(beauty.isChecked, age.text.toString().toInt(), name.text.toString()).save(this)

            content.text = getData()
        }

    }


    private fun getData(): String {
        //如果你继承了BaseApp,可以如下调用
        //  val save = SPModel.getStore(SomeSave::class.java)

        val save = SPModel.getStore(SomeSave::class.java, this)
        val stringBuilder = StringBuilder()
        stringBuilder.append(if (save.boolean) "美女" else "不是美女")
        stringBuilder.append(save.num)
        stringBuilder.append(save.string)
        return stringBuilder.toString()
    }

}
