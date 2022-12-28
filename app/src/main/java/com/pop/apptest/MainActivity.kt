package com.pop.apptest

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import com.pop.apptest.databinding.ActivityMainBinding
import com.pop.apptest.databinding.CustomDialogLayoutBinding
import com.pop.apptest.databinding.LayoutWorktimeBinding
import com.pop.apptest.iftest.Config
import com.pop.apptest.iftest.DoTest01
import com.pop.apptest.pr.models.ItemClickListener
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


class MainActivity : Activity() {


    private lateinit var binding : ActivityMainBinding


    companion object {
        val LOG_TAG = "TEST_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.includ01.btn01.setOnClickListener {
//            Toast.makeText(this@MainActivity, "include test....", Toast.LENGTH_SHORT).show()
//            binding.includ02.txtDesc.text = "Inclue01 texted data..."
//        }

        Log.d(MainActivity.LOG_TAG, "====================================")
        Log.d(MainActivity.LOG_TAG, "CoroutineScope start....")




        fun doAsync() : Deferred<String> = CoroutineScope(Dispatchers.Default).async {
            for (i in 1..5){
                delay(100)
            }
            "doAsync end"
        }

//        val job1 = CoroutineScope(Dispatchers.Default).launch {
//            //Log.d(MainActivity.LOG_TAG, "logging in CoroutineScope with launch.... ${doAsync().await()}")
//        }

        val bindingList = listOf<LayoutWorktimeBinding>(binding.includ01,
            binding.includ02,
            binding.includ03,
            binding.includ04,
            binding.includ05)

        bindingList.forEachIndexed{ index, subBinding ->
            subBinding.textviewTime.text = index.toString() + " - 0900"
        }

        var processArray = arrayOf<String>(
            "1공정 - 콤프조립",
            "2공정 - 메인탱크조립",
            "3공정 - 용접",
            "4공정 - 리어조립",
            "5공정 - 메인PBA조립",
            "6공정 - 충진",
            "7공정 - TEST(1)",
            "8공정 - TEST(2)",
            "9공정 - 완성(전면)",
            "10공정 - 완전(후면)",
            "11공정 - 포장",
            "12공정 - 물류",
            "13공정 - 필터조립"
            )

        var processes = arrayOf<String>(
            "1공정 - 콤프조립",
            "2공정 - 메인탱크조립",
            "3공정 - 용접"
        )
        var booleanArray = booleanArrayOf(true, false, false)



        Log.d(MainActivity.LOG_TAG, "CoroutineScope end....  ")


//        val job2 = CoroutineScope(Dispatchers.Default)
//        val result = job2.async {
//            Log.d(MainActivity.LOG_TAG, "logging in CoroutineScope with async....")
//            "Success Ansync..."
//        }




//        var array = ArrayList<Int>()
//
//        doSomething(array, param1 = {
//            Log.d(MainActivity.LOG_TAG, "Callback for start....")
//            for (i in array){
//                Log.d(MainActivity.LOG_TAG, "ArrayList<Int> is $i")
//            }
//        } )
//        //addToArr(array)
//
//        Log.d(MainActivity.LOG_TAG, "for start....")
//        for (i in array){
//            Log.d(MainActivity.LOG_TAG, "ArrayList<Int> is $i")
//        }
//

//        val stringFlow : Flow<String> = flow {
//            for (i in 0..1000){
//                emit("integer : $i")
//                delay(500)
//            }
//        }
//
//        lifecycleScope.launch {
//            stringFlow.collect {
//                Log.d(MainActivity.LOG_TAG, it)
//            }
//        }
        binding.btn1.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }

//        binding.btnOpenMainActivity3.setOnClickListener {
//            startActivity(Intent(this, MainActivity3::class.java))
//        }
//
//        binding.btnOpenMainActivity4.setOnClickListener {
//            startActivity(Intent(this@MainActivity, MainActivity4::class.java))
//        }

        val list1 = listOf<NameInfo>(NameInfo("홍길동", "용접"),
            NameInfo("홍길동1", ""),
            NameInfo("홍길동2", "메인탱크조립"),
            NameInfo("홍길동3", ""),
            NameInfo("홍길동4", "리어조립"),
            NameInfo("홍길동5", "메인PBA조립"),
            NameInfo("홍길동6", "필터조립"),
            NameInfo("홍길동7", "충진"),
            NameInfo("홍길동8", "TEST(1)"),
            NameInfo("홍길동9", "TEST(2)"),
            NameInfo("홍길동10", ""),
            NameInfo("홍길동21", ""),
            NameInfo("홍길동12", "완성-전면"),
            NameInfo("홍길동13", "완성-후면"),
            NameInfo("홍길동14", "완성-후면"),
            NameInfo("홍길동15", ""),
            NameInfo("홍길동16", "용접"),
            NameInfo("홍길동17", ""),
            NameInfo("홍길동18", "포장"),
            NameInfo("홍길동19", "포장"),
            NameInfo("홍길동20", "물류"))

        val adapter1 = com.pop.apptest.Adater(
            {nameInfo, position, adapter ->
                //Toast.makeText(this@MainActivity, "Clicked ${nameInfo.process}", Toast.LENGTH_SHORT).show()

//                val builder = AlertDialog.Builder(this@MainActivity)
//                builder.apply {
//                    setTitle("공정선택")
//                    setItems(processArray, DialogInterface.OnClickListener { dialogInterface, i ->
//                        list1.get(position).process = processArray[i].substring(6)
//                        adapter.changedData()
//                    })
//                    setNegativeButton("cancel", DialogInterface.OnClickListener { dialogInterface, i ->
//
//                    })
//                    show()
//                }

            },
            {nameInfo, position, adapter ->
                // 다이얼로그를 생성하기 위해 Builder 클래스 생성자를 이용해 줍니다.
                val builder = AlertDialog.Builder(this)

                builder.setTitle("공정삭제")
                    .setMessage("해당 공정정보를 삭제하시겠습니까?")
                    .setPositiveButton("확인",
                        DialogInterface.OnClickListener { dialog, id ->
                            list1.get(position).process = ""
                            adapter.changedData()
                        })
                    .setNegativeButton("취소",
                        DialogInterface.OnClickListener { dialog, id ->
                        })
                // 다이얼로그를 띄워주기
                builder.show()
            })
        binding.includ01.recyclerview.adapter = adapter1
        adapter1.setTodos(list1)

        val list2 = listOf<NameInfo>(NameInfo("홍길동", ""),
            NameInfo("홍길동1", ""),
            NameInfo("홍길동2", ""),
            NameInfo("홍길동3", ""),
            NameInfo("홍길동4", "리어조립"),
            NameInfo("홍길동5", ""),
            NameInfo("홍길동6", "필터조립"),
            NameInfo("홍길동7", ""),
            NameInfo("홍길동8", "용접"),
            NameInfo("홍길동9", "메인탱크조립"),
            NameInfo("홍길동10", ""),
            NameInfo("홍길동21", ""),
            NameInfo("홍길동12", "완성-전면"),
            NameInfo("홍길동13", "완성-후면"),
            NameInfo("홍길동14", "완성-후면"),
            NameInfo("홍길동15", ""),
            NameInfo("홍길동16", "용접"),
            NameInfo("홍길동17", ""),
            NameInfo("홍길동18", "포장"),
            NameInfo("홍길동19", "포장"),
            NameInfo("홍길동20", "물류"))

        val adapter2 = com.pop.apptest.Adater(
            {nameInfo, position , adapter->
                Toast.makeText(this@MainActivity, "Clicked ${nameInfo.process}", Toast.LENGTH_SHORT).show()
            },
            {nameInfo, position, adapter ->

            })
        binding.includ02.recyclerview.adapter = adapter2
        adapter2.setTodos(list2)

        val list3 = listOf<NameInfo>(NameInfo("홍길동", ""),
            NameInfo("홍길동1", ""),
            NameInfo("홍길동2", "용접"),
            NameInfo("홍길동3", "물류"),
            NameInfo("홍길동4", ""),
            NameInfo("홍길동5", "메인PBA조립"),
            NameInfo("홍길동6", "필터조립"),
            NameInfo("홍길동7", "충진"),
            NameInfo("홍길동8", ""),
            NameInfo("홍길동9", "TEST(2)"),
            NameInfo("홍길동10", "포장"),
            NameInfo("홍길동21", ""),
            NameInfo("홍길동12", ""),
            NameInfo("홍길동13", ""),
            NameInfo("홍길동14", ""),
            NameInfo("홍길동15", ""),
            NameInfo("홍길동16", "용접"),
            NameInfo("홍길동17", ""),
            NameInfo("홍길동18", "포장"),
            NameInfo("홍길동19", "포장"),
            NameInfo("홍길동20", "물류"))

        val adapter3 = com.pop.apptest.Adater(
            {nameInfo, position , adapter->
                Toast.makeText(this@MainActivity, "Clicked ${nameInfo.process}", Toast.LENGTH_SHORT).show()
            },
            {nameInfo, position, adapter ->

            })
        binding.includ03.recyclerview.adapter = adapter3
        adapter3.setTodos(list3)

        val list4 = listOf<NameInfo>(NameInfo("홍길동", "포장"),
            NameInfo("홍길동1", ""),
            NameInfo("홍길동2", "용접"),
            NameInfo("홍길동3", ""),
            NameInfo("홍길동4", "충진"),
            NameInfo("홍길동5", "충진"),
            NameInfo("홍길동6", "필터조립"),
            NameInfo("홍길동7", "충진"),
            NameInfo("홍길동8", ""),
            NameInfo("홍길동9", "TEST(2)"),
            NameInfo("홍길동10", "포장"),
            NameInfo("홍길동21", ""),
            NameInfo("홍길동12", ""),
            NameInfo("홍길동13", ""),
            NameInfo("홍길동15", ""),
            NameInfo("홍길동14", ""),
            NameInfo("홍길동16", "용접"),
            NameInfo("홍길동17", ""),
            NameInfo("홍길동18", "포장"),
            NameInfo("홍길동19", ""),
            NameInfo("홍길동20", "물류"))

        val adapter4 = com.pop.apptest.Adater(
            {nameInfo, position, adapter ->
                Toast.makeText(this@MainActivity, "Clicked ${nameInfo.process}", Toast.LENGTH_SHORT).show()
            },
            {nameInfo, position, adapter ->

            })
        binding.includ04.recyclerview.adapter = adapter4
        adapter4.setTodos(list4)



        doTest01()

    }

    fun addToArr(array: ArrayList<Int>){
        array.let {
            it.add(1000)
            it.add(2000)
        }
    }

    fun doSomething(array: ArrayList<Int>, param1 : () -> Unit) {
        doMain( param1 = object : DoTest01 {
            override fun OnEventTest01() {
                Log.d(MainActivity.LOG_TAG, "interface tested...")
            } },
            param2 = {
                Log.d(MainActivity.LOG_TAG, "2nd Param2 Tested....")
            },
            param3 = {
                Log.d(MainActivity.LOG_TAG, it)
            },
            param4 = { p1, p2 ->
                Log.d(MainActivity.LOG_TAG, p1)
                with(array){
                    this.add(p2)
                    if (p2 == 200000)  {
                        param1?.invoke()
                    }
                }
                //Log.d(MainActivity.LOG_TAG, p2.toString())
            }
        )
    }

    fun simple() : Flow<Int> = flow {
        for (i in 1..3){
            delay(200)
            emit(i)
        }
    }

    suspend fun flowMapTest(param1 : String) : Flow<Int> = flow {
        Log.d(Config.LOG_TAG, "flowMapTest Started.... $param1")
        emit(param1.toInt() + param1.toInt())
    }

    fun flowMapTest1(param1 : String) : Int {
        Log.d(Config.LOG_TAG, "flowMapTest Started.... $param1")
        return param1.toInt() + param1.toInt()
    }

    fun doTest01(): Unit {
        runBlocking<Unit> {
            withTimeoutOrNull(300) {
                simple().collect{
                    Log.d(Config.LOG_TAG, it.toString())
                }
            }

            val list = listOf<String>("1234", "5678")

            launch {
                list.forEach {
                    delay(100)
                    Log.d(Config.LOG_TAG, "foreach data -> $it")
                }

                Log.d(Config.LOG_TAG, "foreach end...")
            }

            list.asFlow().onEach { delay(100) }.map { item -> flowMapTest1(item) }.collect{ item -> Log.d(Config.LOG_TAG, " asFlow() ->  ${item.toString()}" ) }

            Log.d(Config.LOG_TAG, "asFlow End...")






        }

    }

    fun doTest02(): Unit {



    }

    fun doMain(param1 : DoTest01,
               param2 : () -> Unit,
               param3 : (String) -> Unit,
               param4 : (String, Int) -> Unit ) {
        param1?.OnEventTest01()
        param2()
        param3.invoke("callback with one param tested....")
        param4.invoke("callback with two param tested....", 10000)

        GlobalScope.launch {

            Log.d(MainActivity.LOG_TAG, "GlobalScope Start...")

            repeat(5){
                delay(1000L)
                Log.d(MainActivity.LOG_TAG, "GlobalScope is working.....$it")
            }

            param4.invoke("GlobalScope is finished...", 200000)

        }

    }



    inner class RecyclerItemClick : ItemClickListener {
        override fun onItemClick(item: NameInfo, index: Int, adapter: Adater) {
            Toast.makeText(this@MainActivity, item.process, Toast.LENGTH_SHORT).show()
        }
    }



}