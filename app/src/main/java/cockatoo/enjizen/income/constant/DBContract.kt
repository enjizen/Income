package cockatoo.enjizen.income.constant

object DBContract {

    const val DATABASE_INCOME = "Income.db"
    const val DATABASE_VERSION = 1

   enum class AccountEntry(val value: String) {
        TABLE_NAME("account"),
        COLUMN_ID("id"),
        COLUMN_ACCOUNT_NUMBER("number"),
        COLUMN_NAME("name"),
        COLUMN_BALANCE("balance"),
        COLUMN_BANK_ID("bank_id")
    }

    enum class BankEntry(val value: String){
        TABLE_NAME("bank"),
        COLUMN_ID("id"),
        COLUMN_NAME("name"),
        COLUMN_INITIALS("initials"),
        COLUMN_LOGO("logo")
    }
}