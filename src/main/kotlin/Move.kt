import kotlin.random.Random
import kotlin.random.nextInt

data class Move(val player: Player, val rolledNumber: Int = Random.nextInt(1..6))
