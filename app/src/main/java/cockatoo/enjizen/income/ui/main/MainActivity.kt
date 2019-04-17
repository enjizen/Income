package cockatoo.enjizen.income.ui.main

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.constant.IntentPasswordMode
import cockatoo.enjizen.income.constant.PasswordMode
import cockatoo.enjizen.income.ui.account.account.AccountActivity
import cockatoo.enjizen.income.ui.base.BaseActivity
import cockatoo.enjizen.income.ui.channel.VerifyChannelPresenter
import cockatoo.enjizen.income.ui.channel.VerifyChannelView
import cockatoo.enjizen.income.ui.password.PasswordActivity
import cockatoo.enjizen.income.ui.password.authentication.AuthenticationFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_toolbar.view.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, VerifyChannelView {


    private lateinit var actionbarDrawerToggle: ActionBarDrawerToggle

    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.contentContainer, MainFragment.newInstance()).commit()

        setupToolbar()
        navView.setNavigationItemSelectedListener(this)
        presenter = MainPresenter(this)

        presenter.checkSetupPassworded()

    }

    private fun setupToolbar() {
        setSupportActionBar(mainToolBar.toolBar)

        actionbarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer)

        drawerLayout.addDrawerListener(actionbarDrawerToggle)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        actionbarDrawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        actionbarDrawerToggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (actionbarDrawerToggle.onOptionsItemSelected(item)) {
            presenter.checkSetupPassworded()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navAccount -> {
                startActivity(Intent(this, AccountActivity::class.java))
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_from_right)
            }
            R.id.navSetPassword -> {
                val intent = Intent(this, PasswordActivity::class.java)
                intent.putExtra(IntentPasswordMode.MODE.value, PasswordMode.CREATE.value)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_from_right)
            }
            R.id.navChangePassword -> {
                val intent = Intent(this, PasswordActivity::class.java)
                intent.putExtra(IntentPasswordMode.MODE.value, PasswordMode.CHANGE.value)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_from_right)
            }

        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun haveNotSetPassword() {
        navView.post {
            navView.menu.findItem(R.id.navSetPassword).isVisible = true
            navView.menu.findItem(R.id.navChangePassword).isVisible = false
        }
    }

    override fun passwordAlreadySet() {
        navView.post {
            navView.menu.findItem(R.id.navSetPassword).isVisible = false
            navView.menu.findItem(R.id.navChangePassword).isVisible = true
        }
    }

}
