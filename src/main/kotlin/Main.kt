import kotlin.math.pow
import kotlin.math.sqrt

class Point(val x: Double, val y: Double)

class Triangle(val point1: Point, val point2: Point, val point3: Point) {
    fun circumcenter(): Point {
        val a = point2.x - point1.x
        val b = point2.y - point1.y
        val c = point3.x - point1.x
        val d = point3.y - point1.y
        val e = a * (point1.x + point2.x) + b * (point1.y + point2.y)
        val f = c * (point1.x + point3.x) + d * (point1.y + point3.y)
        val g = 2.0 * (a * (point3.y - point2.y) - b * (point3.x - point2.x))

        val centerX = (d * e - b * f) / g
        val centerY = (a * f - c * e) / g

        return Point(centerX, centerY)
    }

    fun circumradius(center: Point): Double {
        val radius1 = sqrt((center.x - point1.x).pow(2) + (center.y - point1.y).pow(2))
        val radius2 = sqrt((center.x - point2.x).pow(2) + (center.y - point2.y).pow(2))
        val radius3 = sqrt((center.x - point3.x).pow(2) + (center.y - point3.y).pow(2))

        // Using max() to find the longest distance from the center to a vertex
        return maxOf(radius1, radius2, radius3)
    }
}

fun main() {
    println("Введите координаты вершин треугольника:")
    val point1 = enterPoint("1")
    val point2 = enterPoint("2")
    val point3 = enterPoint("3")

    val triangle = Triangle(point1, point2, point3)
    val circumcenter = triangle.circumcenter()
    val circumradius = triangle.circumradius(circumcenter)

    println("Центр описанной окружности: (${circumcenter.x}, ${circumcenter.y})")
    println("Радиус описанной окружности: $circumradius")
}

fun enterPoint(label: String): Point {
    println("Введите координаты точки $label (x y):")
    val (x, y) = readLine()!!.split(" ").map { it.toDouble() }
    return Point(x, y)
}