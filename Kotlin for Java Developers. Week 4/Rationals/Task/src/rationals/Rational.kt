package rationals

import java.math.BigInteger

class Rational(private val numerator: BigInteger, private val denominator:BigInteger):Comparable<Rational>
{
    init {
        if(this.denominator.toInt()==0)
            throw IllegalArgumentException("denominator cannot be 0")
    }
    fun numerator():BigInteger
    {
        return numerator
    }
    fun denominator():BigInteger
    {
        return denominator
    }


operator fun plus(otro:Rational): Rational {
    var num=this.numerator*otro.denominator+this.denominator*otro.numerator
    var den=this.denominator*otro.denominator
    return Rational(num, den)
 }

    operator fun minus(otro: Rational): Rational {
        var num=this.numerator*otro.denominator-this.denominator*otro.numerator
        var den=this.denominator*otro.denominator
        return Rational(num, den)
    }

    operator fun times(otro: Rational): Rational {
        var num=this.numerator*otro.numerator
        var den=this.denominator*otro.denominator
        return Rational(num, den)
    }

    operator fun div(otro: Rational): Rational {
        var num=this.numerator*otro.denominator
        var den=this.denominator*otro.numerator
        return Rational(num, den)
    }

    operator fun unaryMinus(): Rational {
        var num=-this.numerator
        return Rational(num, this.denominator)
    }

    override operator fun compareTo(twoThirds: Rational): Int {
        var denominador=this.denominator*twoThirds.denominator
        var primero=this.numerator*twoThirds.denominator
        var segundo=twoThirds.numerator*this.denominator

        return primero.compareTo(segundo)
    }
    private fun normalizar(): Rational
    {
        val divisor = numerator.gcd(denominator )
        return if (denominator>0.toBigInteger())
            Rational((numerator /divisor),(denominator /divisor))
        else
            Rational((-numerator /divisor),(-denominator /divisor))
    }
    override fun equals(comparar: Any?): Boolean
    {
        if(comparar !is Rational )
            return false
        else
        {
            var primero=this.normalizar()
            var segundo=comparar.normalizar()
            return primero.numerator==segundo.numerator&&primero.denominator==segundo.denominator
        }




    }

    override fun toString(): String {
        var a = this.normalizar()
        return if (a.denominator==1.toBigInteger())
            a.numerator.toString()
        else
            this.normalizar().numerator.toString()+"/"+this.normalizar().denominator.toString()
    }




}



infix fun Int.divBy(den:Int): Rational = Rational(this.toBigInteger(),den.toBigInteger())

infix fun Long.divBy(den:Long): Rational = Rational(this.toBigInteger(),den.toBigInteger())

infix fun BigInteger.divBy(den:BigInteger): Rational = Rational(this,den)

fun String.toRational(): Rational{
    var arreglo=this.split("/")
    var num=arreglo[0]
    if (arreglo.size>1)
    { var den=arreglo[1]
    return Rational(num.toBigInteger(),den.toBigInteger())
    }
    else
        return Rational(num.toBigInteger(),1.toBigInteger())
}
fun main() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}



