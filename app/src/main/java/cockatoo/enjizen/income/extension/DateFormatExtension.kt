package cockatoo.enjizen.income.extension

import java.text.SimpleDateFormat
import java.util.*


fun Date.dateFullFormater(): String{
    val formatter = SimpleDateFormat("วันที่ dd MMMM YYYY", Locale("th", "TH"))
    return formatter.format(this)
   }