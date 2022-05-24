import kotlin.random.Random
import kotlin.random.nextInt

data class Tunnel(internal val start: Int, internal val end: Int) {
    internal val type =
        if (start > end) TunnelType.SNAKE else if (end > start) TunnelType.LADDER else throw IllegalArgumentException("tunnel can not have start equals end")

    companion object {
        fun make(numLadders: Int, numSnakes: Int, limit: Int): Set<Tunnel> {
            val tunnels = mutableSetOf<Tunnel>()
            val occupiedSquares = mutableSetOf<Int>()

            for (i in 1..numLadders) {
                val bottom = chooseLadderBottom(limit, occupiedSquares)
                tunnels += Tunnel(bottom, chooseLadderTop(bottom, limit))
            }

            for (i in 1..numSnakes) {
                val head = chooseSnakeHead(limit, occupiedSquares)
                tunnels += Tunnel(head, Random.nextInt(2 until head))
            }

            return tunnels
        }

        private fun chooseSnakeHead(limit: Int, occupiedSquares: MutableSet<Int>): Int {
            var head = Random.nextInt(3 until limit - 1)
            while (occupiedSquares.contains(head)) {
                head = Random.nextInt(3 until limit - 1)
            }
            occupiedSquares += head
            return head
        }

        private fun chooseLadderTop(bottom: Int, limit: Int) = Random.nextInt(bottom + 1 until limit)

        private fun chooseLadderBottom(limit: Int, occupiedSquares: MutableSet<Int>): Int {
            var bottom = Random.nextInt(2 until limit - 1)
            while (occupiedSquares.contains(bottom)) {
                bottom = Random.nextInt(2 until limit - 1)
            }
            occupiedSquares += bottom
            return bottom
        }
    }

}
