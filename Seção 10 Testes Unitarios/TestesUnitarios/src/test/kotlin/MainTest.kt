import org.example.Main
import org.example.countXO
import org.junit.jupiter.api.*

class MainTest {

    @Test
    @DisplayName("Testa a contagem de xo")
    fun testCountXO() {
        Assertions.assertTrue(countXO("ooxx"))
        Assertions.assertFalse(countXO("oooXX"))

        Assertions.assertAll(

            { Assertions.assertTrue(countXO("ooxx")) },
            { Assertions.assertTrue(countXO("ooOxxX")) }
        )
    }

    @Test
    @Disabled
    fun naoImplementada() {

    }

    @Test
    @Disabled
    fun failAll() {
         fail("Teste")
    }
}