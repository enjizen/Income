package cockatoo.enjizen.income.ui.main

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.constant.IncomeMode
import cockatoo.enjizen.income.constant.IntentKey
import cockatoo.enjizen.income.constant.PasswordMode
import cockatoo.enjizen.income.ui.account.account.AccountActivity
import cockatoo.enjizen.income.ui.base.BaseActivity
import cockatoo.enjizen.income.ui.inoutcome.InOutComeActivity
import cockatoo.enjizen.income.ui.main.home.HomeFragment
import cockatoo.enjizen.income.ui.main.home.HomePresenter
import cockatoo.enjizen.income.ui.main.home.HomeView
import cockatoo.enjizen.income.ui.main.other.MoreFragment
import cockatoo.enjizen.income.ui.password.PasswordActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_toolbar.view.*
import com.google.android.material.bottomnavigation.BottomNavigationView



class MainActivity : BaseActivity(),
    HomeView {


    private lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){

            supportFragmentManager.beginTransaction()
                .add(R.id.contentContainer, HomeFragment.newInstance(), HomeFragment.TAG)
                .commit()

            val moreFragment = MoreFragment.newInstance()

            supportFragmentManager.beginTransaction()
                .add(R.id.contentContainer, moreFragment, MoreFragment.TAG)
                .detach(moreFragment)
                .commit()
        }
        presenter = HomePresenter(this)

       fabOutcome.setOnClickListener {
            val intent = Intent(this, InOutComeActivity::class.java)
            intent.putExtra(IntentKey.INCOME_MODE.value, IncomeMode.ADD.value)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_from_right)
            fabMenu.close(true)
        }

        fabIncome.setOnClickListener {
            val intent = Intent(this, InOutComeActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_from_right)
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





    override fun haveNotSetPassword() {

    }

    override fun passwordAlreadySet() {

    }

}
