package cockatoo.enjizen.income.ui.main

import android.content.Intent
import android.os.Bundle
import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.constant.IncomeOutcomeMode
import cockatoo.enjizen.income.constant.IntentKey
import cockatoo.enjizen.income.constant.TransitionScreenType
import cockatoo.enjizen.income.base.BaseActivity
import cockatoo.enjizen.income.ui.incomeoutcome.IncomeOutcomeActivity
import cockatoo.enjizen.income.ui.main.home.HomeFragment
import cockatoo.enjizen.income.ui.main.other.MoreFragment
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {
            initFragment()
        }

        fabOutcome.setOnClickListener {
            Intent(this, IncomeOutcomeActivity::class.java).apply {
                putExtra(IntentKey.INCOME_OUTCOME_MODE.value, IncomeOutcomeMode.ADD_OUTCOME.value)
            }.run {
                router.goto(activity = this@MainActivity, intent = this, tranSit = TransitionScreenType.PUSH)
            }
            fabMenu.close(true)
        }

        fabIncome.setOnClickListener {
            Intent(this, IncomeOutcomeActivity::class.java).apply {
                putExtra(IntentKey.INCOME_OUTCOME_MODE.value, IncomeOutcomeMode.ADD_INCOME.value)
            }.run{
                router.goto(activity = this@MainActivity, intent = this, tranSit = TransitionScreenType.PUSH)
            }

            fabMenu.close(true)
        }

        bottomNavigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->

            val homeFragment = supportFragmentManager.findFragmentByTag(HomeFragment.TAG)
            val moreFragment = supportFragmentManager.findFragmentByTag(MoreFragment.TAG)

            when (item.itemId) {
                R.id.itemHome -> {
                    supportFragmentManager.beginTransaction()
                        .setCustomAnimations(
                            R.anim.animation_enter,
                            R.anim.animation_leave,
                            R.anim.slide_from_left,
                            R.anim.slide_from_right
                        )
                        .attach(homeFragment!!)
                        .detach(moreFragment!!)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.itemMore -> {
                    supportFragmentManager.beginTransaction()
                        .setCustomAnimations(
                            R.anim.slide_from_left,
                            R.anim.slide_from_right,
                            R.anim.animation_enter,
                            R.anim.animation_leave
                        )
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
        MoreFragment.newInstance().run {
            supportFragmentManager.beginTransaction()
                .add(R.id.contentContainer, this, MoreFragment.TAG)
                .detach(this)
                .commit()
        }
    }


}
