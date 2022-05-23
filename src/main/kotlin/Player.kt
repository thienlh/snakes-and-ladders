data class Player(val name: String) {
    fun advance(num: Int) {
        if (num < 1) throw IllegalArgumentException("can only advance in positive step")
        currentPosition += num
    }

    fun moveTo(num: Int) {
        currentPosition = num
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
