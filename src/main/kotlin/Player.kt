data class Player(val name: String) {
    // Every player starts at square 1
    var currentPosition: Int = 1

    fun advance(numSteps: Int) {
        if (numSteps < 1) throw IllegalArgumentException("can only advance in positive step")
        val lastPosition = currentPosition
        currentPosition += numSteps
        println("$name moved from [$lastPosition] to [$currentPosition]")
    }

    fun travelTo(squareNum: Int) {
        if (squareNum < 1) throw IllegalArgumentException("can only travel to positive square")
        val lastPosition = currentPosition
        currentPosition = squareNum
        println("$name moved from [$lastPosition] to [$currentPosition]")
    }

    companion object {
        fun make(num: Int): List<Player> {
            return IntRange(1, num).map { Player("Player $it") }
        }
    }

}
