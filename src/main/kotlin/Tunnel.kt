import kotlin.random.Random
import kotlin.random.nextInt

data class Tunnel(internal val start: Int, internal val end: Int) {
    internal val type =
        if (start > end) TunnelType.SNAKE else if (end > start) TunnelType.LADDER else throw IllegalArgumentException("tunnel can not have start equals end")

    companion object {
        fun make(numLadders: Int, numSnakes: Int, limit: Int): Set<Tunnel> {
            val tunnels = mutableSetOf<Tunnel>()
            val occupiedSquares = mutableSetOf<Int>()

            for (i in 0 until numLadders) {
                val start = Random.nextInt(2 until limit - 1)
                var end = Random.nextInt(start + 1 until limit)
                while (occupiedSquares.contains(end)) {
                    end = Random.nextInt(start + 1 until limit)
                }
                tunnels += Tunnel(start, end)

                occupiedSquares += end
            }

            for (i in 0 until numSnakes) {
                var start = Random.nextInt(3 until limit)
                while (occupiedSquares.contains(start)) {
                    start = Random.nextInt(3 until limit)
                }
                val end = Random.nextInt(2 until start)
                tunnels += Tunnel(start, end)

                occupiedSquares += start
            }

            return tunnels
        }
    }

}
