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

    enum class IncomeEntry(val value: String){
        TABLE_NAME("income"),
        COLUMN_ID("id"),
        COLUMN_DETAIL("detail"),
        COLUMN_MONEY_INCOME("money_income"),
        COLUMN_ACCOUNT_ID("account_Id"),
        COLUMN_CREATED_DATE("created_date")
    }

    enum class OutcomeEntry(val value: String){
        TABLE_NAME("outcome"),
        COLUMN_ID("id"),
        COLUMN_DETAIL("detail"),
        COLUMN_MONEY_OUTCOME("money_outcome"),
        COLUMN_ACCOUNT_ID("account_Id"),
        COLUMN_CREATED_DATE("created_date")
    }
}