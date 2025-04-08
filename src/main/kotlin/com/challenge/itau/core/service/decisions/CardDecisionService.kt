import com.challenge.itau.core.domain.Card
import com.challenge.itau.core.domain.CardStatus
import com.challenge.itau.core.domain.CardType
import com.challenge.itau.core.domain.Customer
import com.challenge.itau.core.service.decisions.conditions.ConditionEvaluator
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import java.math.BigDecimal

/*
 * This service is responsible for making decisions about card approvals based on customer data.
 * It uses a list of evaluators to determine if a card should be approved or declined.
 *
 * @param cardType The type of card being evaluated.
 * @param evaluators A list of condition evaluators that will be used to assess the customer's eligibility.
*/

abstract class CardDecisionService(
        private val cardType: CardType,
        private val evaluators: List<ConditionEvaluator> = emptyList()
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    fun decideCard(customer: Customer): Card = runBlocking {
        val card = Card(
                cardType = cardType,
                monthlyFee = BigDecimal.ZERO,
                availableLimit = cardType.availableLimit,
                status = CardStatus.APPROVED
        )

        coroutineScope {
            val deferredEvaluations = evaluators.map { evaluator ->
                async {
                    val evaluationResult = evaluator.evaluate(customer)
                    log.info("Evaluating condition: ${evaluator::class.simpleName} for customer: $customer, result: $evaluationResult")
                    evaluationResult
                }
            }

            for (deferred in deferredEvaluations) {
                if (deferred.await() == CardStatus.DECLINED) {
                    card.status = CardStatus.DECLINED
                    break
                }
            }
        }

        return@runBlocking card
    }
}