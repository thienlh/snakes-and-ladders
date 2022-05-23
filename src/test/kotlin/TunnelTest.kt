import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.*

internal class TunnelTest {
    @Test
    internal fun `should make correct number of ladders and snakes`() {
        val tunnels = Tunnel.make(2, 3, 50)
        assertEquals(tunnels.size, 5)
        assertEquals(tunnels.filter { it.type == TunnelType.LADDER }.size, 2)
        assertEquals(tunnels.filter { it.type == TunnelType.SNAKE }.size, 3)
    }

    @Test
    internal fun `should make sensible start and end values`() {
        val tunnels = Tunnel.make(100, 100, 9999)
        for (tunnel in tunnels) {
            assertTrue(tunnel.start > 0)
            assertTrue(tunnel.end > 0)
        }
        tunnels.filter { it.type == TunnelType.LADDER }.forEach { assertTrue(it.start < it.end) }
        tunnels.filter { it.type == TunnelType.SNAKE }.forEach { assertTrue(it.start > it.end) }
    }

    @Test
    internal fun `should never have snake head and ladder bottom at the same square`() {
        val tunnels = Tunnel.make(1000, 1000, 10000)
        val ladderBottoms = tunnels.filter { it.type == TunnelType.LADDER }.map { it.start }
        val snakeHeads = tunnels.filter { it.type == TunnelType.SNAKE }.map { it.start }

        assertTrue(Collections.disjoint(ladderBottoms, snakeHeads))
    }
}