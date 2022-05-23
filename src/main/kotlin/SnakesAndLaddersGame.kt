class SnakesAndLaddersGame(
    internal val numSquares: Int,
    internal val numLadders: Int,
    internal val numSnakes: Int,
    internal val numPlayers: Int
) {
    private val tunnels: Set<Tunnel>
    internal val ladders: List<Tunnel>
    internal val snakes: List<Tunnel>
    internal val players: List<Player>

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
    }
}