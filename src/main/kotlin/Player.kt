data class Player(val name: String) {
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
