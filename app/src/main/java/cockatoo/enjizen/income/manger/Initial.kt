package cockatoo.enjizen.income.manger

import android.content.ContentValues
import cockatoo.enjizen.income.R
import cockatoo.enjizen.income.constant.BankInitialsConstant
import cockatoo.enjizen.income.constant.DBContract
import cockatoo.enjizen.income.manger.db.DBHelper
import cockatoo.enjizen.income.model.Bank

class Initial {

    fun bank(){
       val cursor = DBHelper.getInstance().getAll(DBContract.BankEntry.TABLE_NAME.value)

        if(!cursor!!.moveToFirst()) {
            val resource = Contextor.getInstance().context!!.resources
            val bankList = ArrayList<Bank>()
            bankList.add(Bank(1, resource.getString(R.string.bank_bbl), BankInitialsConstant.BBL.name, BankInitialsConstant.BBL.logo))
            bankList.add(Bank(2, resource.getString(R.string.bank_kbank), BankInitialsConstant.KBANK.name, BankInitialsConstant.KBANK.logo))
            bankList.add(Bank(3, resource.getString(R.string.bank_ktb), BankInitialsConstant.KTB.name, BankInitialsConstant.KTB.logo))
            bankList.add(Bank(4, resource.getString(R.string.bank_tmb), BankInitialsConstant.TMB.name, BankInitialsConstant.TMB.logo))
            bankList.add(Bank(5, resource.getString(R.string.bank_scb), BankInitialsConstant.SCB.name, BankInitialsConstant.SCB.logo))
            bankList.add(Bank(6, resource.getString(R.string.bank_bay), BankInitialsConstant.BAY.name, BankInitialsConstant.BAY.logo))
            bankList.add(Bank(7, resource.getString(R.string.bank_kkp), BankInitialsConstant.KKP.name, BankInitialsConstant.KKP.logo))
            bankList.add(Bank(8, resource.getString(R.string.bank_cimb_thai), BankInitialsConstant.CIMBTHAI.name, BankInitialsConstant.CIMBTHAI.logo))
            bankList.add(Bank(9, resource.getString(R.string.bank_tisco), BankInitialsConstant.TISCO.name, BankInitialsConstant.TISCO.logo))
            bankList.add(Bank(10, resource.getString(R.string.bank_tbank), BankInitialsConstant.TBANK.name, BankInitialsConstant.TBANK.logo))
            bankList.add(Bank(11, resource.getString(R.string.bank_uob), BankInitialsConstant.UOB.name, BankInitialsConstant.UOB.logo))
            bankList.add(Bank(12, resource.getString(R.string.bank_tcd), BankInitialsConstant.TCD.name, BankInitialsConstant.TCD.logo))

            val values = ArrayList<ContentValues>()
            bankList.forEach {
                val v = ContentValues()
                v.put(DBContract.BankEntry.COLUMN_ID.value, it.id)
                v.put(DBContract.BankEntry.COLUMN_NAME.value, it.name)
                v.put(DBContract.BankEntry.COLUMN_INITIALS.value, it.initials)
                v.put(DBContract.BankEntry.COLUMN_LOGO.value, it.logo)
                values.add(v)
            }

        DBHelper.getInstance().insert(DBContract.BankEntry.TABLE_NAME.value, values)
        }


    }

}