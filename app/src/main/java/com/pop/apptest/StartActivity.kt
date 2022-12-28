package com.pop.apptest

import android.app.AlertDialog
import android.app.Application
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.pop.apptest.databinding.ActivityStartBinding
import com.pop.apptest.databinding.CustomDialogLayoutBinding
import com.pop.apptest.iftest.Config
import com.pop.apptest.iftest.PrViewModel
import com.pop.apptest.pr.PrViewModelFactory
import com.pop.apptest.pr.models.Line
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.*
import kotlin.collections.ArrayList
import okhttp3.OkHttpClient as OkHttpClient1

class StartActivity : AppCompatActivity() {

    private lateinit var viewModel : PrViewModel
    private lateinit var binding: ActivityStartBinding

    fun doTest01() {




        //binding.edittextTime.text.substring(IntRange(1,2))

        //val nowTime = LocalTime.of()

        val stringFlow : Flow<String> = flow {
            for(i in 1..5) {
                delay(100)
                emit(i.toString())
            }
        }

        suspend fun getData(param : String) : String {
            delay(100)
            return "param $param " + SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Date(System.currentTimeMillis()))
        }

        var array = ArrayList<String>()

        Log.d(Config.LOG_TAG, "lifecycleScope.launch start...")

        lifecycleScope.launch {

            //Log.d(Config.LOG_TAG, getData("test "))

            launch {
                stringFlow.collect {
                    Log.d(Config.LOG_TAG, "flow test => $it")
                }
            }


            listOf("str1", "str2").asFlow()
                .map{ param -> getData(param) }
                .onCompletion { Log.d(Config.LOG_TAG, "OnComletion called....") }
                .collect{
                    array.add(it)
                    Log.d(Config.LOG_TAG, "array changed...${array.count().toString()}")
                        }
                //.collect{ param -> Log.d(Config.LOG_TAG, "asFlow() => $param") }

        }

        Log.d(Config.LOG_TAG, "lifecycleScope.launch end...")

        Log.d(Config.LOG_TAG, "array is ${array.toString()}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_start)

        doTest01()

        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loadingView.visibility = View.GONE

        viewModel = ViewModelProvider(this, PrViewModelFactory()).get(PrViewModel::class.java)


        Log.d(Config.LOG_TAG, "Version : ${BuildConfig.VERSION_NAME}")
        Log.d(Config.LOG_TAG, "root dir : ${Environment.getRootDirectory().absolutePath}")
        Log.d(Config.LOG_TAG, "Cache dir : ${Environment.getDownloadCacheDirectory().absolutePath}")
        Log.d(Config.LOG_TAG, "Cache dir : ${Environment.DIRECTORY_DOWNLOADS}")

        lifecycleScope.launch {

        }

        

        viewModel.loading.observe(this@StartActivity) { isLoading ->
            isLoading.let {
                binding.loadingView.visibility = if (it) View.VISIBLE else View.GONE
            }
        }

        viewModel.timesByWo.observe(this@StartActivity){ item ->

           item.forEach {
                //binding.mem.text = it.DEPT_NAME
               binding.mem.append("Total Count is ${item.count()}  First Item is ${it.TMEM_NAME} " + "\n")
               return@observe
            }
        }

        viewModel.workerByTime.observe(this@StartActivity){ item ->

           item.forEach {
                //binding.mem.text = it.DEPT_NAME
               binding.mem.append("Total Count is ${item.count()}  First Item is ${it.EMPL_NAME} " + "\n")
               return@observe
            }
        }

        viewModel.linesWithPlant.observe(this@StartActivity) { item ->
            item.forEach {
                //binding.mem.text = it.DEPT_NAME
                
                if (it is Line) {
                    binding.mem.append("Total Count is ${item.count()}  First Item is ${it.DEPT_NAME} " + "\n")
                    return@observe
                }
            }
        }




//        binding.mem.doOnTextChanged { text, start, before, count ->  }

        var dataArr = arrayOf("배", "귤", "사과", "자두", "복숭아")
        var dataArr2 = intArrayOf(
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground
        )

        binding.apply {
            this.btnTest01.setOnClickListener {
                Toast.makeText(this@StartActivity, Application.getProcessName(), Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@StartActivity, MainActivity::class.java))
            }

            this.btnTest02.setOnClickListener {
                //startActivity(Intent(this@StartActivity, MainActivity3::class.java))

                var builder = AlertDialog.Builder(this@StartActivity)
                builder.setTitle("커스텀 리스트 다이얼로그")
                var idx = 0
                var list = ArrayList<HashMap<String, Any>>()
                while (idx < dataArr2.size) {
                    var map = HashMap<String, Any>()
                    map.put("key1", dataArr[idx])
                    map.put("key2", dataArr2[idx])
                    list.add(map)
                    idx++
                }

                var keys = arrayOf("key1", "key2")
                var ids = intArrayOf(R.id.itm_text1, R.id.itm_img1)

                var adapter = SimpleAdapter(this@StartActivity, list, R.layout.custom_adapter_items, keys, ids)


                var listener = object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        Log.d(Config.LOG_TAG, dataArr[which])
                    }

                }

                //builder.setAdapter(adapter, null)
                builder.setAdapter(adapter, listener)

                builder.setPositiveButton("확인", null)

                builder.setNegativeButton("취소", null)

                builder.show()
            }

            this.btnTest03.setOnClickListener {
            }

            this.btnTest04.setOnClickListener {
                //viewModel.getLineInfo()
                viewModel.getTimesByWo("2000", "CP04", "20220915")
            }

            this.btnTest05.setOnClickListener {
                //viewModel.getLineInfo()
                viewModel.getWorkersByTime("2000", "CP04", "20220915")
            }

            this.btnTest0501.setOnClickListener {
                //viewModel.changeLineName(0, "TEST LINES")
                viewModel.getAllInfo("2000", "CP04", "20220915")

//                CoroutineScope(Dispatchers.Default).launch {
//                    val deff1 = async(Dispatchers.IO) {
//                        "test...."
//                    }
//
//                    Log.d(Config.LOG_TAG, deff1.await())
//                }
            }


            this.btnTest06.setOnClickListener {
                //viewModel.getLineInfo()
                //viewModel.getWorkersByTime("2000", "CP04", "20220915")

                var list = arrayOf<String>("ALL", "2000", "2001", "2002")

                val builder = AlertDialog.Builder(this@StartActivity)

                builder.setTitle("공장선택")
                    .setItems(list, DialogInterface.OnClickListener { dialogInterface, i ->
                        viewModel.plant.value = if(i==0) "" else list[i]
                    })
                    .setNegativeButton("취소", DialogInterface.OnClickListener { p1, p2 -> Toast.makeText(this@StartActivity, "cancel", Toast.LENGTH_SHORT).show() })


                builder.show()
            }

            this.btnTest07.setOnClickListener {
                val times = arrayListOf<Pair<String, String>>(
                    Pair("0850", "1055"), Pair("1056", "1259"), Pair("1300", "1541"), Pair("1542", "1825")
                )


                Log.d(Config.LOG_TAG, "hour is ${binding.edittextTime.text.substring(0 until 2)}, min is ${binding.edittextTime.text.substring(2)},")

                val userTime = LocalTime.of(
                    binding.edittextTime.text.substring(0 until 2).toInt(),
                    binding.edittextTime.text.substring(2).toInt(),
                    0)

                Log.d(Config.LOG_TAG, userTime.toString())

                times.forEach { time ->
                    val firstTime = LocalTime.of(
                        time.first.substring(0 until 2).toInt(),
                        time.first.substring(2).toInt(),
                        0)
                    val secondTime = LocalTime.of(
                        time.second.substring(0 until 2).toInt(),
                        time.second.substring(2).toInt(),
                        0)

                    userTime.isAfter(firstTime).also {
                        if (it) {
                            Log.d( Config.LOG_TAG, "$firstTime ~ $secondTime : 수정가능! $userTime" )
                        } else {
                            Log.d( Config.LOG_TAG, "$firstTime ~ $secondTime : 수정불가! $userTime" )
                        }
                    }


                    //Log.d(Config.LOG_TAG, secondTime.toString())
                }


            }

            this.btnTest0201.setOnClickListener {

                val binding = CustomDialogLayoutBinding.inflate(LayoutInflater.from(applicationContext))
                val builder = AlertDialog.Builder(this@StartActivity).apply {
                    setView(binding.root)
                    setPositiveButton("ok", DialogInterface.OnClickListener { dialogInterface, i ->
//                        dialogInterface.dismiss()
                    } )
                    setNegativeButton("cancel", DialogInterface.OnClickListener { dialogInterface, i ->
                        dialogInterface.dismiss()
                    })
                }
                val dialog = builder.create()

                val list = listOf<NameInfo>(NameInfo("홍길동", "용접"),
                    NameInfo("01공정", "메인탱크조립"),
                    NameInfo("02공정", "메인탱크조립"),
                    NameInfo("03공정", "용접"),
                    NameInfo("04공정", "TEST01"),
                    NameInfo("05공정", "포장"),
                    NameInfo("06공정", "포장"),
                    NameInfo("07공정", "물류"),
                    NameInfo("08공정", "TEST01"),
                    NameInfo("09공정", "포장"),
                    NameInfo("10공정", "포장"),
                    NameInfo("11공정", "물류"),
                )

                val adapter = CustomDialogAdapter(list
                ) { nameInfo, position, adapter ->
                    Toast.makeText(
                        this@StartActivity,
                        "Clicked ${nameInfo.process}",
                        Toast.LENGTH_SHORT
                    ).show()

                    dialog.dismiss()
                }

                binding.checkboxBatch.setOnCheckedChangeListener { compoundButton, b ->
                    binding.buttonInit.isEnabled = b
                    adapter.changeMode(b)
                }

                binding.recyclerview.adapter = adapter
                dialog.show()




            }

        }



//        binding.btnTest01.setOnClickListener {
//            startActivity(Intent(this@StartActivity, MainActivity::class.java))
//        }
    }

    fun isSettingProcess(from : String, to : String, time : String) : Boolean {
        return false
    }
}