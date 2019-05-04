package cockatoo.enjizen.income.ui.main.other

import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.constant.MoreMenuTarget
import cockatoo.enjizen.income.constant.MoreMenuType
import cockatoo.enjizen.income.model.MoreMenu
import org.junit.Test

import org.junit.Before
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MorePresenterTest {

    @InjectMocks
    private lateinit var presenter: MorePresenter

    @Mock
    private lateinit var view: MorePresenter.MoreView

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = MorePresenter(view)
    }

    @Test
    fun verifyMenuItem_MenuAccount() {

        presenter.verifyMenuItem(MoreMenu(
            menuName = "Account",
            imageIcon = R.drawable.ic_logout,
            menuType = MoreMenuType.ITEM.type,
            target = MoreMenuTarget.ACCOUNT
        ))

        verify(view, timeout(1)).gotoAccount()
    }

    @Test
    fun verifyMenuItem_MenuSetPassword() {

        presenter.verifyMenuItem(MoreMenu(
            menuName = "set_password",
            imageIcon = R.drawable.ic_create_password,
            menuType = MoreMenuType.ITEM.type,
            target = MoreMenuTarget.SET_PASSWORD
        ))

        verify(view, timeout(1)).gotoSetPassword()
    }


    @Test
    fun verifyMenuItem_MenuChangePassword() {

        presenter.verifyMenuItem(MoreMenu(
            menuName = "change_password",
            imageIcon = R.drawable.ic_change_password,
            menuType = MoreMenuType.ITEM.type,
            target = MoreMenuTarget.CHANGE_PASSWORD
        ))

        verify(view, timeout(1)).gotoChangePassword()
    }

    @Test
    fun verifyMenuItem_MenuLogout() {

        presenter.verifyMenuItem(MoreMenu(
            menuName = "change_password",
            imageIcon = R.drawable.ic_logout,
            menuType = MoreMenuType.ITEM.type,
            target = MoreMenuTarget.LOGOUT
        ))

        verify(view, timeout(1)).gotoLogout()
    }

    @Test
    fun checkSetupPassword_NotSetPassword() {
        `when`(view.getPasswordForCheck()).thenReturn("")
        presenter.checkSetupPassword()
        verify(view, timeout(1)).haveNotSetPassword()
        verify(view, never()).passwordAlreadySet()
    }

    @Test
    fun checkSetupPassword_AlreadySet() {
        `when`(view.getPasswordForCheck()).thenReturn("111111")
        presenter.checkSetupPassword()
        verify(view,  timeout(1)).passwordAlreadySet()
        verify(view, never()).haveNotSetPassword()
    }
}