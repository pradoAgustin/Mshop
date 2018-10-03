package com.agustin.mlshop

import com.agustin.mlshop.domain.Product
import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_discount_percentage_Correct() {
        var product :Product = Product(null,"colchon",null, BigDecimal(400),"USD",2,2,null,null,null,null,
                false,null, null, null,
                null, BigDecimal(1200),null,null,null)

        assertEquals(product.getItemDiscount(),BigDecimal(67).setScale(1, RoundingMode.HALF_UP))

        product = Product(null,"colchon",null, BigDecimal(900),"USD",2,2,null,null,null,null,
                false,null, null, null,
                null, BigDecimal(1200),null,null,null)

        assertEquals(product.getItemDiscount(),BigDecimal(25).setScale(1, RoundingMode.HALF_UP) )

        product = Product(null,"colchon",null, BigDecimal(945.6),"USD",2,2,null,null,null,null,
                false,null, null, null,
                null, BigDecimal(1200),null,null,null)
        assertEquals(product.getItemDiscount(),BigDecimal(21.2).setScale(1, RoundingMode.HALF_UP) )
    }

}
