import kotlin.random.Random
import kotlin.random.nextInt

data class Move(val player: Player, val rolledNumber: Int = Random.nextInt(1..6)) {
    init {
        if (rolledNumber !in 1..6) throw IllegalArgumentException("rolledNumber can only within 1..6")
    }
}
