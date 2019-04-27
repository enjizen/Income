package cockatoo.enjizen.income.ui.main

import android.content.Intent
import android.os.Bundle
import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.constant.IncomeMode
import cockatoo.enjizen.income.constant.IntentKey
import cockatoo.enjizen.income.constant.TransitionScreenType
import cockatoo.enjizen.income.base.BaseActivity
import cockatoo.enjizen.income.ui.inoutcome.InOutComeActivity
import cockatoo.enjizen.income.ui.main.home.HomeFragment
import cockatoo.enjizen.income.ui.main.other.MoreFragment
import cockatoo.enjizen.income.base.BaseRouterActivity
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.material.bottomnavigation.BottomNavigationView



class MainActivity : BaseActivity(){

    private lateinit var router: BaseRouterActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        router = BaseRouterActivity()

        if(savedInstanceState == null){
            initFragment()
        }

       fabOutcome.setOnClickListener {
            val intent = Intent(this, InOutComeActivity::class.java)
            intent.putExtra(IntentKey.INCOME_MODE.value, IncomeMode.ADD.value)
            router.goto(activity = this, intent = intent, tranSit = TransitionScreenType.PUSH)

            fabMenu.close(true)
        }

        fabIncome.setOnClickListener {
            val intent = Intent(this, InOutComeActivity::class.java)
            router.goto(activity = this, intent = intent, tranSit = TransitionScreenType.PUSH)
            fabMenu.close(true)
        }

        bottomNavigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.itemHome -> {
                    val homeFragment = supportFragmentManager.findFragmentByTag(HomeFragment.TAG)
                    val moreFragment = supportFragmentManager.findFragmentByTag(MoreFragment.TAG)

                    supportFragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.animation_enter, R.anim.animation_leave, R.anim.slide_from_left, R.anim.slide_from_right)
                        .attach(homeFragment!!)
                        .detach(moreFragment!!)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.itemMore -> {
                    val homeFragment = supportFragmentManager.findFragmentByTag(HomeFragment.TAG)
                    val moreFragment = supportFragmentManager.findFragmentByTag(MoreFragment.TAG)
                    supportFragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.slide_from_left, R.anim.slide_from_right, R.anim.animation_enter, R.anim.animation_leave)
                        .attach(moreFragment!!)
                        .detach(homeFragment!!)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })

    }

    private fun initFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.contentContainer, HomeFragment.newInstance(), HomeFragment.TAG)
            .commit()

        val moreFragment = MoreFragment.newInstance()

        supportFragmentManager.beginTransaction()
            .add(R.id.contentContainer, moreFragment, MoreFragment.TAG)
            .detach(moreFragment)
            .commit()
    }


}
