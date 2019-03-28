package cockatoo.enjizen.income.ui.router

import android.app.Activity
import android.content.Intent
import android.util.Log
import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.constant.TransitionScreenType

open class BaseRouter {
   private val TAG = BaseRouter::class.java.simpleName

    fun goto(activity: Activity, intent: Intent, tranSit: TransitionScreenType = TransitionScreenType.PUSH, isCloseAllScreen: Boolean? = false, isForResult: Boolean? = false, requestCode: Int? = 0 ){
        if (isCloseAllScreen!!)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)


        if (isForResult!!) {
            Log.i(TAG, "start activity for result")
            activity.startActivityForResult(intent, requestCode!!)
        } else {
            Log.i(TAG, "start activity")
            activity.startActivity(intent)
        }

        if(TransitionScreenType.POPUP == tranSit)
            activity.overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
        else if(TransitionScreenType.PUSH == tranSit)
            activity.overridePendingTransition(R.anim.slide_from_left, R.anim.slide_from_right)
    }
}