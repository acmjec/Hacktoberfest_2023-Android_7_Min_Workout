package dev.panwar.a7minutesworkout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import dev.panwar.a7minutesworkout.databinding.ActivityExerciseBinding
import dev.panwar.a7minutesworkout.databinding.DialogCustomBackConfirmationBinding
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private  var binding: ActivityExerciseBinding?=null
    private var restTimer: CountDownTimer?=null //time for rest...here we take 10 sec rest...means 10000millis
    private var restProgress=0 //how much second rest completed

    private var restTimerDuration:Long=1
    private var exerciseTimerDuration:Long=1

    private var exerciseTimer: CountDownTimer?=null
    private var exerciseProgress=0

    private var exerciseList: ArrayList<ExerciseModel>?=null
    private var currentExercisePosition=-1 //0th exercise

    //for TextToSpeech
    private var tts:TextToSpeech?=null

//    for play sound after every exercise
    private var player:MediaPlayer?=null

    // Declaring an exerciseAdapter object which will be initialized later.
    private var exerciseAdapter: ExerciseStatusAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)
//        setContentView(R.layout.activity_exercise)
//for supporting toolbar
        setSupportActionBar(binding?.toolbarExercise)

     //for showing back button on toolbar
        if (supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

//        for working of back button pressed...when back button is pressed
            binding?.toolbarExercise?.setNavigationOnClickListener {
                customDialogForBackButton()
            }

        exerciseList=Constants.defaultExerciseList()

//        write this goto...hover on this and in more actions select 2nd option
        tts= TextToSpeech(this,this)

        setupRestView()

        // START
        // setting up the exercise recycler view
        setupExerciseStatusRecyclerView()
        // END


    }
// whenever back button is pressed...this is bottom back button not the toolbar back button
    override fun onBackPressed() {
        customDialogForBackButton()
    }

    private fun customDialogForBackButton() {
        val customDialog=Dialog(this@ExerciseActivity)
//        custom dialog back confirmation is separate xml.....for that we need to apply binding again...DialogCustomBackConfirmationBinding is auto created after we make it's xml file
        val dialogBinding=DialogCustomBackConfirmationBinding.inflate(layoutInflater)
//        for showing custom dialog above current activity
        customDialog.setContentView(dialogBinding.root)
//       to make user can't cancel this dialog on clicking empty area
        customDialog.setCanceledOnTouchOutside(false)
        dialogBinding.tvYes.setOnClickListener {
            this@ExerciseActivity.finish()
            customDialog.dismiss()
        }
        dialogBinding.tvNo.setOnClickListener {
           customDialog.dismiss()
        }
//        to finally show custom dialog
        customDialog.show()
    }

    // function for timer of rest
    private fun setRestProgressBar(){
        //at start progress of Progressbar
        binding?.progressBar?.progress=restProgress
        restTimer=object :CountDownTimer(restTimerDuration*1000,1000) {
            //total 10sec at 1 sec interval
//            at every second what to do
//            these both functions are inbuilt of CountDownTimer class whose object we have created
            override fun onTick(p0: Long) {
                restProgress++
                binding?.progressBar?.progress=10-restProgress
                binding?.tvTimer?.text=(10-restProgress).toString()
            }

            override fun onFinish() {
                currentExercisePosition++
                 // setting that next exercise is selected
                exerciseList!![currentExercisePosition].setIsSelected(true)
//                we are saying the Adapter that some data has been changed/updated so update the view....it will run all 3 methods of Exercise status updater class once again
                exerciseAdapter!!.notifyDataSetChanged()

               setupExerciseView()
            }

        }.start()

    }

    private fun setupRestView(){

        try {
//            putting path of sound...
            val soundURI = Uri.parse("android.resource://dev.panwar.a7minutesworkout/" + R.raw.press_start)
//            giving path to MediaPlayer to to play
            player = MediaPlayer.create(applicationContext, soundURI)
            player?.isLooping = false // Sets the player to be looping or non-looping.
            player?.start() // Starts Playback.
        } catch (e: Exception) {
            e.printStackTrace()
        }

//        starting 7 lines are just opposite of Setup Exercise view..restricting visibility of exercise view and turning on visibility of rest view
        binding?.flRestView?.visibility= View.VISIBLE
        binding?.tvTitle?.visibility=View.VISIBLE
        binding?.tvUpcomingExercise?.visibility=View.VISIBLE
        binding?.tvNextExercise?.visibility=View.VISIBLE
        binding?.tvExerciseName?.visibility=View.INVISIBLE
        binding?.flExerciseView?.visibility=View.INVISIBLE
        binding?.ivImage?.visibility=View.INVISIBLE

        if (restTimer!=null){
            restTimer?.cancel()
            restProgress=0
        }
        binding?.tvNextExercise?.text=exerciseList!![currentExercisePosition+1].getName()

        setRestProgressBar()

    }

    private fun setupExerciseView(){
//        restricting visibility of rest view and turning on visibility of exercise view
        binding?.tvUpcomingExercise?.visibility=View.INVISIBLE
        binding?.tvNextExercise?.visibility=View.INVISIBLE
        binding?.flRestView?.visibility= View.INVISIBLE
        binding?.tvTitle?.visibility=View.INVISIBLE
        binding?.tvExerciseName?.visibility=View.VISIBLE
        binding?.flExerciseView?.visibility=View.VISIBLE
        binding?.ivImage?.visibility=View.VISIBLE

        if (exerciseTimer!=null){
            exerciseProgress=0
            exerciseTimer?.cancel()
        }
//setting up the image...get image function we created in Exercise Model class
//        !! because exercise list is nullable....but we know it cannot be null
        speakOut(exerciseList!![currentExercisePosition].getName())
        binding?.ivImage?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.tvExerciseName?.text=exerciseList!![currentExercisePosition].getName()

        setExerciseProgressBar()
    }

    override fun onDestroy() {
        if (restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }
        if (exerciseTimer!=null){
            exerciseProgress=0
            exerciseTimer?.cancel()
        }
// so that it do not keep on speaking long sentences even after activity is destroyed

        if (tts != null) {
            tts?.stop()
            tts?.shutdown()
        }

        if(player != null){
            player!!.stop()
        }

        super.onDestroy()
        binding=null
    }

// function for timer of Exercise
    private fun setExerciseProgressBar(){
        //at start progress of Progressbar
        binding?.progressBarExercise?.progress=exerciseProgress
        exerciseTimer=object :CountDownTimer(exerciseTimerDuration*1000,1000) {
            //total 10sec at 1 sec interval
//            at every second what to do
//            these both functions are inbuilt of CountDownTimer class whose object we have created
            override fun onTick(p0: Long) {
                exerciseProgress++
                binding?.progressBarExercise?.progress=30-exerciseProgress
                binding?.tvTimerExercise?.text=(30-exerciseProgress).toString()

            }

            override fun onFinish() {



//                if all exercises not completed
                if(currentExercisePosition<exerciseList?.size!!-1){
                    exerciseList!![currentExercisePosition].setIsSelected(false)
                    exerciseList!![currentExercisePosition].setIsCompleted(true)
//                we are saying the Adapter that some data has been changed/updated so update the view....it will run all 3 methods of Exercise status updater class once again
                    exerciseAdapter!!.notifyDataSetChanged()
                    setupRestView()
                }
                else{
//                    closes the current activity i.e. exercise activity...finish function closes current activity and moves to prev open activity....but here we are moving to finish activity by using intent
                    finish()
                    val intent=Intent(this@ExerciseActivity,FinishActivity::class.java)
                    startActivity(intent)
                }
            }

        }.start()

    }

    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported!")
            }

        } else {
            Log.e("TTS", "Initialization Failed!")
        }
    }

    private fun speakOut(text: String) {
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }


    /**
     * Function is used to set up the recycler view to UI and assigning the Layout Manager and Adapter Class is attached to it.
     */
    // TODO(Step 2 : Binding adapter class to recycler view and setting the recycler view layout manager and passing a list to the adapter.)
    // START
    private fun setupExerciseStatusRecyclerView() {

        // Defining a layout manager for the recycle view
        // Here we have used a LinearLayout Manager with horizontal scroll.
//        reverse layout false means we we want layout in normal sequence not in reverse sequence
        binding?.rvExerciseStatus?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//         everytime we make any change to Recycler view we need to call Exercise StatusAdapter and then  Adapter class is attached to recycler view
        // As the adapter expects the exercises list and context so initialize it passing it.
        exerciseAdapter = ExerciseStatusAdapter(exerciseList!!)

        // Adapter class is attached to recycler view
        binding?.rvExerciseStatus?.adapter = exerciseAdapter
    }
    // END


}