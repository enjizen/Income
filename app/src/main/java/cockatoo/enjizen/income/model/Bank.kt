package cockatoo.enjizen.income.model

data class Bank(val id: Int
                , val name: String
                , val initials: String
                , val logo: String){

    override fun toString(): String {
        return name
    }
}