# SPDemo
一个开箱即用的简易基于 SharedPreferences的快速存储类，目前支持boolean，int，string等基本类型的存储

**用法示例**
1.新建一个类继承SPModel
```
 data class SomeSave(var boolean: Boolean = false, var num: Int? = 0, var string: String? = "") : SPModel() 
 （注：这是kotlin的写法，需要赋予默认值，来实现空参的构造函数，如果是java的写法的话，需要一个空参的构造函数？
```
2.调用方式
```
    存储数据：如果继承了BaseApp可以调用本方法
    SomeSave(false, 10, "测试").save()
    否则调用
    SomeSave(false, 10, "测试").save().save(context)
    
    获取数据：如果继承了BaseApp可以调用本方法存储数据
    SPModel.getStore(SomeSave::class.java)
    否则调用
    SPModel.getStore(SomeSave::class.java, this)
    
```
3.用法实例请参考Demo工程
