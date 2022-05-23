data class Player(val name: String) {
    fun advance(num: Int) {
        if (num !in 1..6) throw IllegalArgumentException("can only advance within a dice roll range 1..6")
        currentPosition += num
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
