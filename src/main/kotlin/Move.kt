data class Move(val player: Player, val rolledNumber: Int) {
    init {
        if (rolledNumber !in 1..6) throw IllegalArgumentException("rolledNumber can only within 1..6")
    }
}
