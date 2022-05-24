import kotlin.random.Random
import kotlin.random.nextInt

class SnakesAndLaddersGame(
    internal val numSquares: Int,
    internal val numLadders: Int,
    internal val numSnakes: Int,
    internal val players: List<Player>
) {
    internal constructor(numSquares: Int, tunnels: Set<Tunnel>, players: List<Player>) : this(
        numSquares, 0, 0, players
    ) {
        this.tunnels = tunnels
    }

    constructor(numSquares: Int, numLadders: Int, numSnakes: Int, numPlayers: Int) : this(
        numSquares, numLadders, numSnakes, Player.make(numPlayers)
    ) {
        if (numPlayers < 1) throw IllegalArgumentException("should have at least 1 player")
    }

    private var tunnels: Set<Tunnel>
    internal val ladders: List<Tunnel>
    internal val snakes: List<Tunnel>
    internal val moves: MutableList<Move>
    internal var nextPlayerIndex = 0
    private var numRounds = 1
    var winner: Player? = null
    private val numPlayers = players.size

    init {
        if (numSquares < 10) throw IllegalArgumentException("should have at least 10 squares")
        if (numLadders < 0) throw IllegalArgumentException("should not have negative numLadders")
        if (numSnakes < 0) throw IllegalArgumentException("should not have negative numSnakes")
        if ((numLadders + numSnakes) * 2 >= numSquares - 2) throw IllegalArgumentException("(numSnakes + numLadders) * 2 must be smaller than numSquares - 2")
        this.tunnels = Tunnel.make(numLadders, numSnakes, numSquares)
        this.ladders = tunnels.filter { it.type == TunnelType.LADDER }
        this.snakes = tunnels.filter { it.type == TunnelType.SNAKE }
        this.moves = mutableListOf()
        println("Game generated with $numSquares squares, ${players.size} players (${players.map { it.name }}), $numLadders ladders ($ladders), $numSnakes snakes ($snakes)")
    }

    fun nextMove() {
        val player = players[nextPlayerIndex]
        val rolledNumber = Random.nextInt(1..6)
        println("${player.name} (${player.currentPosition}) rolled [$rolledNumber]")
        val move = Move(player, rolledNumber)
        move(move)
    }

    // TODO: Consider letting Player class handles the moving logic, this class should only handles cycle between players
    internal fun move(move: Move) {
        val player = move.player
        val rolledNumber = move.rolledNumber
        val rolledMoreThanNeededToWin = player.currentPosition + rolledNumber > numSquares

        if (rolledMoreThanNeededToWin) {
            println("${player.name} rolled [$rolledNumber], but need a [${numSquares - player.currentPosition}] to win the game.")
            proceedRound()
            return
        } else {
            player.advance(rolledNumber)
        }

        while (player.cameAcrossATunnel()) player.travel()

        record(move)

        if (player.hasWon()) {
            winner = player
            println("${player.name} won!")
            println("Game finished after $numRounds rounds.")
            return
        }

        proceedRound()
    }

    private fun proceedRound() {
        when {
            isEndOfRound() -> startANewRound()
            else -> nextPlayer()
        }
    }

    private fun record(move: Move) {
        moves += move
    }

    private fun Player.hasWon() = currentPosition == numSquares

    private fun Player.cameAcrossATunnel() = tunnels.any { it.start == currentPosition }

    private fun Player.travel() {
        val tunnel = tunnels.find { it.start == currentPosition }!!
        when (tunnel.type) {
            TunnelType.SNAKE -> println("Oops! $name got eaten by a snake ($tunnel)")
            TunnelType.LADDER -> println("Yay! $name climbed a ladder ($tunnel)")
        }
        travelTo(tunnel.end)
    }

    private fun isEndOfRound() = nextPlayerIndex == numPlayers - 1

    private fun startANewRound() {
        nextPlayerIndex = 0
        numRounds++
    }

    private fun nextPlayer() {
        nextPlayerIndex++
    }
}

