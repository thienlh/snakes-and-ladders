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
                var bottom = Random.nextInt(2 until limit - 1)
                while (occupiedSquares.contains(bottom)) {
                    bottom = Random.nextInt(2 until limit - 1)
                }
                val top = Random.nextInt(bottom + 1 until limit)
                tunnels += Tunnel(bottom, top)

                occupiedSquares += bottom
            }

            for (i in 0 until numSnakes) {
                var head = Random.nextInt(3 until limit)
                while (occupiedSquares.contains(head)) {
                    head = Random.nextInt(3 until limit)
                }
                val tail = Random.nextInt(2 until head)
                tunnels += Tunnel(head, tail)

                occupiedSquares += head
            }

            return tunnels
        }
    }

}
