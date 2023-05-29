package me.dio.credit.application.system.service


import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import jakarta.persistence.*
import me.dio.credit.application.system.entity.Address
import me.dio.credit.application.system.entity.Credit
import me.dio.credit.application.system.entity.Customer
import me.dio.credit.application.system.enummeration.Status
import me.dio.credit.application.system.exception.BusinessException
import me.dio.credit.application.system.repository.CreditRepository
import me.dio.credit.application.system.repository.CustomerRepository
import me.dio.credit.application.system.service.CustomerServiceTest
import me.dio.credit.application.system.service.impl.CreditService
import me.dio.credit.application.system.service.impl.CustomerService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

//@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CreditServiceTest {

    @MockK
    lateinit var creditRepository: CreditRepository

    @MockK
    lateinit var customerService: CustomerService

    @InjectMockKs
    lateinit var creditService: CreditService

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }


    @Test
    fun `should save a new credit`(){
        //given
        val fakeCustomer:Customer = CustomerServiceTest.buildCustomer()
        val fakeCredit:Credit = buildCredit(customer = fakeCustomer)

        every{ creditRepository.save(any())} returns fakeCredit
        every { customerService.findById(any()) } returns fakeCustomer
        //when

        val actualCredit:Credit = creditService.save(fakeCredit)

        //then
        Assertions.assertThat(actualCredit).isNotNull()
        Assertions.assertThat(actualCredit).isSameAs(fakeCredit)
        verify(exactly = 1){ creditRepository.save(fakeCredit) }
    }

    @Test
    fun `should throw BusinessException due excessive months on dayFirstInstallment`(){
        //given
        val fakeCustomer:Customer = CustomerServiceTest.buildCustomer()
        val fakeCredit:Credit = buildCredit(
                customer = fakeCustomer,
                dayFirstInstallment = LocalDate.now().plusMonths(3)
        )
        //when
        //then
        Assertions.assertThatExceptionOfType(me.dio.credit.application.system.exception.BusinessException::class.java)
                .isThrownBy { creditService.save(fakeCredit) }
                .withMessage("Invalid Date")
        //verify(exactly = 1){ creditService.save(fakeCredit) }
    }

    companion object {
        private fun buildCredit(
                creditValue: BigDecimal = BigDecimal.valueOf(100.0),
                dayFirstInstallment: LocalDate = LocalDate.now().plusMonths(2),
                numberOfInstallments: Int = 15,
                customer: Customer = CustomerServiceTest.buildCustomer()
        ): Credit = Credit(
                creditValue = creditValue,
                dayFirstInstallment = dayFirstInstallment,
                numberOfInstallments = numberOfInstallments,
                customer = customer
        )
    }

}