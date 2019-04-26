package cockatoo.enjizen.income.model

data class Account (val id: Int
                    , val bankId: Int
                    , val accountNumber: String
                    , val name: String
                    , val balance: Double
                    , var logo: String? = "")