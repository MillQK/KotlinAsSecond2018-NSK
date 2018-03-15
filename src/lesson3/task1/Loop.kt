@file:Suppress("UNUSED_PARAMETER")
package lesson3.task1

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    for (m in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n/2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 */
fun digitNumber(n: Int): Int {
    var num = n / 10
    var res = 1
    while (num != 0) {
        res++
        num /= 10
    }
    return res
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var a = 1
    var b = 1
    for (i in 3..n) {
        val bTmp = a + b
        a = b
        b = bTmp
    }
    return b
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    // lcm = ab/gcd

    return m * n / gcd(m, n)
}

fun gcd(m: Int, n: Int) : Int {
    var a = maxOf(m, n)
    var b = minOf(m, n)

    while (b != 0) {
        val bTmp = a % b
        a = b
        b = bTmp
    }
    return a
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) return i
    }
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    for (i in n - 1 downTo 1) {
        if (n % i == 0) return i
    }
    return 1
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean = gcd(m, n) == 1

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean =
        Math.ceil(Math.sqrt(m.toDouble())) <= Math.floor(Math.sqrt(n.toDouble()))

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    val xNormalize = (x % (2 * Math.PI) +  2 * Math.PI) % (2 * Math.PI)
    var res = 0.0
    var factN = 1
    var sign = 1
    var member = xNormalize
    while (member >= eps) {
        res += sign * member
        sign *= -1
        member *= (xNormalize * xNormalize) / (++factN * ++factN)
    }
    return res
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    val xNormalize = (x % (2 * Math.PI) +  2 * Math.PI) % (2 * Math.PI)
    var res = 0.0
    var factN = 0
    var sign = 1
    var member = 1.0
    while (member >= eps) {
        res += sign * member
        sign *= -1
        member *= (xNormalize * xNormalize) / (++factN * ++factN)
    }
    return res
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var res = 0
    var num = n
    while (num > 0) {
        res = res * 10 + (num % 10)
        num /= 10
    }
    return res
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean = n == revert(n)

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var num = Math.abs(n)

    while (num >= 10) {
        if (num % 10 != (num / 10) % 10) return true
        num /= 10
    }

    return false
}

fun tenPower(power: Int) : Int = Math.pow(10.0, power.toDouble()).toInt()

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun squareSequenceDigit(n: Int): Int {
    var count = n
    var current = 1
    while (true) {
        val square = current * current
        val squareNumCount = digitNumber(square)
        if (count - squareNumCount <= 0) {
            return if (count - squareNumCount == 0)
                square % 10
            else
                (square / tenPower(squareNumCount - count)) % 10
        }
        current++
        count -= squareNumCount
    }
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
    var count = n
    var current = 1
    while (true) {
        val fibNum = fib(current)
        val fibNumCount = digitNumber(fibNum)
        if (count - fibNumCount <= 0) {
            return if (count - fibNumCount == 0)
                fibNum % 10
            else
                (fibNum / tenPower(fibNumCount - count)) % 10
        }
        current++
        count -= fibNumCount
    }
}
