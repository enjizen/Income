package cockatoo.enjizen.income.constant

class DBContract {

   enum class AccountEntry(val value: String) {
        TABLE_NAME("account"),
        COLUMN_ID("id"),
        COLUMN_ACCOUNT_NUMBER("number"),
        COLUMN_NAME("name"),
        COLUMN_BALANCE("balance")
    }

    enum class BankEntry(val value: String){
        TABLE_NAME("bank"),
        COLUMN_ID("id"),
        COLUMN_NAME("name"),
        COLUMN_INITIALS("initials"),
        COLUMN_LOGO("logo")
    }
}