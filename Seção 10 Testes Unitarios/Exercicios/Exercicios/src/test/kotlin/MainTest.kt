import org.example.portaria
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MainTest {

    @Test
    fun testPortaria() {

        Assertions.assertEquals(portaria(17, "", ""), "Negado.")
        Assertions.assertEquals(portaria(19, "", ""), "Negado.")
        Assertions.assertEquals(portaria(19, "VIP", ""), "Negado.")
        Assertions.assertEquals(portaria(19, "comum", "yy"), "Negado.")
        Assertions.assertEquals(portaria(19, "comum", "xt"), "Welcome.")
        Assertions.assertEquals(portaria(19, "premium", "xl"), "Welcome.")

    }

}