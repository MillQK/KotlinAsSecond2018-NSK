@file:Suppress("UNUSED_PARAMETER", "unused")
package lesson7.task1

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E
    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)
    operator fun set(cell: Cell, value: E)
}

/**
 * Простая
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
    if (height <= 0 || width <= 0) throw IllegalArgumentException("Incorrect height: $height or width: $width")

    return object : Matrix<E> {
        val elems = MutableList(height * width) { e }

        override val height: Int
            get() = height

        override val width: Int
            get() = width

        private fun toPosition(row: Int, column: Int): Int = row * width + column
        private fun checkAndGetPosition(row: Int, column: Int) : Int {
            val position = toPosition(row, column)
            if (position !in elems.indices) throw IndexOutOfBoundsException("Wrong row or column")
            return position
        }

        override fun get(row: Int, column: Int): E {
            return elems[checkAndGetPosition(row, column)]
        }

        override fun get(cell: Cell): E = get(cell.row, cell.column)

        override fun set(row: Int, column: Int, value: E) {
            elems[checkAndGetPosition(row, column)] = value
        }

        override fun set(cell: Cell, value: E) = set(cell.row, cell.column, value)
    }
}


/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height: Int, override val width: Int, init: (Int) -> E) : Matrix<E> {
    val elems = MutableList(height * width, init)

    private fun toPosition(row: Int, column: Int): Int = row * width + column
    private fun checkAndGetPosition(row: Int, column: Int) : Int {
        val position = toPosition(row, column)
        if (position !in elems.indices) throw IndexOutOfBoundsException("Wrong row or column")
        return position
    }

    override fun get(row: Int, column: Int): E {
        return elems[checkAndGetPosition(row, column)]
    }

    override fun get(cell: Cell): E = get(cell.row, cell.column)

    override fun set(row: Int, column: Int, value: E) {
        elems[checkAndGetPosition(row, column)] = value
    }

    override fun set(cell: Cell, value: E) = set(cell.row, cell.column, value)

    override fun equals(other: Any?): Boolean {
        if (other == this) return true
        if (other !is Matrix<*>) return false

        if ((height != other.height) || (width != other.width)) return false

        for (row in 0 until height) {
            for (column in 0 until width) {
                val thisElem = this[row, column]
                val otherElem = other[row, column]
                if (!(thisElem?.equals(other) ?: (otherElem == null))) {
                    return false
                }
            }
        }

        return true
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for (i in 0 until height) {
            elems.subList(i * width, (i + 1) * width).joinTo(sb, separator = " ", prefix = "[", postfix = "]")
            sb.appendln()
        }
        return sb.toString()
    }
}

