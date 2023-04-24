package eu.pl.snk.senseibunny.coroutinesdemo

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    var customProgressDialog: Dialog?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAction: Button = findViewById(R.id.action_btn)

        btnAction.setOnClickListener{

            custmDialogProgressDialogFunction()
            lifecycleScope.launch {
                execute("Task executed successfully.")
            }
        }
    }

    private suspend fun execute(result: String){ // suspend make it coroutine

        withContext(Dispatchers.IO){// here it makes the job on different thread
            for(i in 1..1000000){
                Log.e("delay",""+i)
            }

            runOnUiThread(){ //HERE PUT JOBS THAT SHOULD BE DONE IN UI THREAD, it will be done after calculation
                cancelProgressDialog()
                Toast.makeText(this@MainActivity, result, Toast.LENGTH_LONG).show()

            }
        }

    }


    private fun custmDialogProgressDialogFunction(){
        val customProgressDialog = Dialog(this)

        customProgressDialog.setContentView(R.layout.progress)

        customProgressDialog.show()
    }

    private fun cancelProgressDialog(){
        if(customProgressDialog!=null){
            customProgressDialog?.dismiss()
            customProgressDialog=null;
        }
    }


}