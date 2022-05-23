import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals

internal class SnakesAndLaddersGameTest {
    @Test
    internal fun `should construct game with correct values`() {
        val game = SnakesAndLaddersGame(50, 5, 5, 2)
        assertEquals(game.numSquares, 50)
        assertEquals(game.numLadders, 5)
        assertEquals(game.numSnakes, 5)
        assertEquals(game.numPlayers, 2)
    }

    @Test
    internal fun `should have positive number of players`() {
        assertThrows<IllegalArgumentException> { SnakesAndLaddersGame(100, 11, 12, 0)  }
        assertThrows<IllegalArgumentException> { SnakesAndLaddersGame(100, 11, 12, -3)  }
    }

    @Test
    internal fun `should have at least 10 squares`() {
        assertThrows<IllegalArgumentException> { SnakesAndLaddersGame(0, 0, 0, 1)  }
        assertThrows<IllegalArgumentException> { SnakesAndLaddersGame(-3, 1, 1, 1)  }
        assertThrows<IllegalArgumentException> { SnakesAndLaddersGame(9, 1, 1, 1)  }
    }

    @Test
    internal fun `should never have negative number of ladders`() {
        assertThrows<IllegalArgumentException> { SnakesAndLaddersGame(20, -3, 1, 1)  }
    }
    @Test
    internal fun `should never have negative number of snakes`() {
        assertThrows<IllegalArgumentException> { SnakesAndLaddersGame(20, 1, -3, 1)  }
    }

    @Test
    internal fun `should not have too many tunnels`() {
        assertThrows<IllegalArgumentException> { SnakesAndLaddersGame(10, 10, 10, 1)  }
        assertThrows<IllegalArgumentException> { SnakesAndLaddersGame(10, 5, 5, 1)  }
        assertThrows<IllegalArgumentException> { SnakesAndLaddersGame(10, 3, 3, 1)  }
        assertThrows<IllegalArgumentException> { SnakesAndLaddersGame(10, 3, 2, 1)  }
        assertThrows<IllegalArgumentException> { SnakesAndLaddersGame(10, 2, 3, 1)  }
    }

    @Test
    internal fun `should have ladders and snakes`() {
        val game = SnakesAndLaddersGame(100, 2, 3, 2)
        assertEquals(game.ladders.size, 2)
        assertEquals(game.snakes.size, 3)
    }
}