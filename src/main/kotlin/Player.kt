data class Player(val name: String) {
    fun advance(num: Int) {
        val lastPosition = currentPosition
        if (num < 1) throw IllegalArgumentException("can only advance in positive step")
        currentPosition += num
        println("$name moved from [$lastPosition] to [$currentPosition]")
    }

    fun travelTo(num: Int) {
        val lastPosition = currentPosition
        currentPosition = num
        println("$name moved from [$lastPosition] to [$currentPosition]")
    }

    var currentPosition: Int = 1

    companion object {
        fun make(num: Int): List<Player> {
            val players = mutableListOf<Player>()
            for (i in 0 until num) {
                players += Player("Player ${i + 1}")
            }
            return players
        }
    }

}
