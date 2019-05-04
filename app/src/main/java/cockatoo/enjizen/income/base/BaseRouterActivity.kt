package cockatoo.enjizen.income.base

import android.app.Activity
import android.content.Intent
import android.util.Log
import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.constant.TransitionScreenType

 class BaseRouterActivity {
   private val TAG = BaseRouterActivity::class.java.simpleName

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

        when (tranSit) {
            TransitionScreenType.POPUP -> activity.overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
            TransitionScreenType.PUSH -> activity.overridePendingTransition(R.anim.slide_from_left, R.anim.slide_from_right)
            TransitionScreenType.LEAVE -> activity.overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave)
        }
    }
}