package com.gabrielcarvalhotp.motivation.repositories

import com.gabrielcarvalhotp.motivation.enuns.CategoryPhase
import kotlin.random.Random

data class Phrase(val description: String, val category: CategoryPhase)

class Mock {
    private val listPhrases: List<Phrase> = listOf(
        Phrase("Não sabendo que era impossível, foi lá e fez.", CategoryPhase.HAPPY),
        Phrase("Você não é derrotado quando perde, você é derrotado quando desiste!", CategoryPhase.HAPPY),
        Phrase("Quando está mais escuro, vemos mais estrelas!", CategoryPhase.HAPPY),
        Phrase("Insanidade é fazer sempre a mesma coisa e esperar um resultado diferente.", CategoryPhase.HAPPY),
        Phrase("Não pare quando estiver cansado, pare quando tiver terminado.", CategoryPhase.HAPPY),
        Phrase("O que você pode fazer agora que tem o maior impacto sobre o seu sucesso?", CategoryPhase.HAPPY),
        Phrase("A melhor maneira de prever o futuro é inventá-lo.", CategoryPhase.SUNNY),
        Phrase("Você perde todas as chances que você não aproveita.", CategoryPhase.SUNNY),
        Phrase("Fracasso é o condimento que dá sabor ao sucesso.", CategoryPhase.SUNNY),
        Phrase(" Enquanto não estivermos comprometidos, haverá hesitação!", CategoryPhase.SUNNY),
        Phrase("Se você não sabe onde quer ir, qualquer caminho serve.", CategoryPhase.SUNNY),
        Phrase("Se você acredita, faz toda a diferença.", CategoryPhase.SUNNY),
        Phrase("Riscos devem ser corridos, porque o maior perigo é não arriscar nada!", CategoryPhase.SUNNY)
    )

    fun getPhrase(category: CategoryPhase): String {
        val filterPhrases = listPhrases.filter { it.category == category || category == CategoryPhase.ALL }
        return filterPhrases[Random.nextInt(filterPhrases.size)].description
    }
}