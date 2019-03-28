package cockatoo.enjizen.income.constant

class DBContract {

   enum class AccountEntry(val columns: String) {
        TABLE_NAME("account"),
        COLUMN_ID("id"),
        COLUMN_ACCOUNT_NUMBER("number"),
        COLUMN_NAME("name"),
        COLUMN_BALANCE("balance")
    }
}