package cockatoo.enjizen.income.extension


    fun String.citizenIdFormat(): String{
        var result = ""

        for (i in 0 until this.length) {
            when (i) {
                1 -> result += "-${this[i]}"
                5 -> result += "-${this[i]}"
                10 -> result += "-${this[i]}"
                12 -> result += "-${this[i]}"
                else -> result += this[i]
            }
        }

        return result
    }

