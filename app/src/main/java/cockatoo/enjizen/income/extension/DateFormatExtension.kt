package cockatoo.enjizen.income.extension

import java.text.SimpleDateFormat
import java.util.*


val dateFormatDB = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("th","TH"))

fun Date.dateThaiFullFormatter(): String {
    val formatter = SimpleDateFormat("วันที่ dd MMMM YYYY", Locale("th", "TH"))
    return formatter.format(this)
}

fun Date.dateFormatSaveDB(): String{
    return dateFormatDB.format(this)
}

fun String.dateDBToDate() : Date{
    return dateFormatDB.parse(this)
}