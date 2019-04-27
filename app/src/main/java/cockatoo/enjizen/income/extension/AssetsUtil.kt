package cockatoo.enjizen.income.extension

import cockatoo.enjizen.income.manger.Contextor


fun String.readJsonFile(): String{
        return Contextor.getInstance().context!!.assets.open(this).bufferedReader().use {
           it.readText()
        }
    }