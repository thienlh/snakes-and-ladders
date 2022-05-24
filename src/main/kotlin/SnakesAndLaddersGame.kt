import kotlin.random.Random
import kotlin.random.nextInt

class SnakesAndLaddersGame(
    internal val numSquares: Int,
    internal val numLadders: Int,
    internal val numSnakes: Int,
    internal val numPlayers: Int
) {
    internal constructor(numSquares: Int, tunnels: Set<Tunnel>) : this(numSquares, 0, 0, 1) {
        this.tunnels = tunnels
    }

    private var tunnels: Set<Tunnel>
    internal val ladders: List<Tunnel>
    internal val snakes: List<Tunnel>
    internal val players: List<Player>
    internal val moves: MutableList<Move>
    private var nextPlayerIndex = 0
    var winner: Player? = null

    init {
        if (numPlayers < 1) throw IllegalArgumentException("should have at least 1 player")
        if (numSquares < 10) throw IllegalArgumentException("should have at least 10 squares")
        if (numLadders < 0) throw IllegalArgumentException("should not have negative numLadders")
        if (numSnakes < 0) throw IllegalArgumentException("should not have negative numSnakes")
        if ((numLadders + numSnakes) * 2 >= numSquares - 2) throw IllegalArgumentException("(numSnakes + numLadders) * 2 must be smaller than numSquares - 2")
        this.tunnels = Tunnel.make(numLadders, numSnakes, numSquares)
        this.ladders = tunnels.filter { it.type == TunnelType.LADDER }
        this.snakes = tunnels.filter { it.type == TunnelType.SNAKE }
        this.players = Player.make(numPlayers)
        this.moves = mutableListOf()
    }

    fun nextMove() {
        val player = players[nextPlayerIndex]
        val move = Move(player, Random.nextInt(1..6))
        move(move)
    }

    internal fun move(move: Move) {
        val player = move.player
        if (player.currentPosition + move.rolledNumber > numSquares) {
            return
        } else {
            player.advance(move.rolledNumber)
        }

        // TODO: refactoring
        while (tunnels.any { it.start == player.currentPosition }) {
            val tunnel = tunnels.find { it.start == player.currentPosition }
            player.moveTo(tunnel!!.end)
        }

        moves += move

        if (player.currentPosition == numSquares) {
            winner = player
            return
        }

        if (nextPlayerIndex == numPlayers - 1) nextPlayerIndex = 0 else nextPlayerIndex++
    }
}