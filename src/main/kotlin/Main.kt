import kotlin.random.Random

const val day: Int = 24 * 60 * 60
const val hour: Int = 60 * 60
const val min: Int = 60

fun main(args: Array<String>) {
    println("Задача №1. Только что:")
    println("был(а)" + agoToText(5))
    println("был(а)" + agoToText(58 * min))
    println("был(а)" + agoToText(2 * min))
    println("был(а)" + agoToText(1 * hour))
    println("был(а)" + agoToText(2 * hour))
    println("был(а)" + agoToText(3 * hour))
    println("был(а)" + agoToText(12 * hour))
    println("был(а)" + agoToText(1 * day))
    println("был(а)" + agoToText(2 * day))
    println("был(а)" + agoToText(3 * day))
    println("Задача №2. Разная комиссия:")
    println("Укажите сумму перевода:")
    val amount = readln().toInt()
    println(agoToCard(amount))
}
// Задача №1. Только что
fun agoToText(count: Int): String {
    var text: String = ""

    when {
        count >= 3 * day -> text = " Давно"
        count >= 2 * day -> text = " Позавчера"
        count >= 1 * day -> text = " Вчера"
        count >= 2 * hour -> {
            val h: Int = count / hour
            text = " в сети $h ${hoursToText(h)} назад"
        }
        count >= 1 * hour -> text = " 1 час назад"
        count >= 2 * min -> {
            val m: Int = count / min
            text = " в сети $m ${minutesToText(m)} назад"
        }
        else -> text = " только что"
    }
    return text
}

fun hoursToText(count: Int): String {
    return when {
        count % 10 == 1 && count % 100 != 11 -> "час"
        count % 10 in 2..4 && (count % 100 < 10 || count % 100 >= 20) -> "часа"
        else -> "часов"
    }
}

fun minutesToText(count: Int): String {
    return when {
        count % 10 == 1 && count % 100 != 11 -> "минуту"
        count % 10 in 2..4 && (count % 100 < 10 || count % 100 >= 20) -> "минуты"
        else -> "минут"
    }
}

//

// Задача №2. Разная комиссия
fun agoToCard(count: Int): Double {
    val proz = 0.6
    var am: Double = 0.0
    val proz2 = (1 + 0.75 / 100) * count
    println("Укажите тип карты (По умолчанию VK Pay):")
    val card = readln()
    println("Исчерпан ли лимит по месяцу: true:да, false:нет")
    val v1 = readln().toBoolean()



    when {
        (card == "Maestro" || card == "MasterCard") && count > 600000 && !v1 -> println("Ошибка ограничения")
        (card == "Maestro" || card == "MasterCard") && count > 150000 && v1 -> println("Ошибка ограничения")
        (card == "Visa" || card == "Мир") && (count > 600000 && !v1 || count > 150000 && v1) -> println("Ошибка ограничения")
        (card == "" || card == "VK Pay") && ((count > 40000 && !v1) || (count > 15000 && v1)) -> println("Ошибка ограничения")
        card == "MasterCard" && count in 300..75000 -> am = count.toDouble()
        card == "Maestro" && count in 300..75000 -> am = count.toDouble()
        card == "MasterCard" && (count < 300 || count > 75000) -> am = (1 + proz / 100) * count + 20
        card == "Maestro" && (count < 300 || count > 75000) -> am = (1 + proz / 100) * count + 20
        (card == "Visa" || card == "Мир") && proz2 <= 35 -> am = 35.0
        (card == "Visa" || card == "Мир") && proz2 > 35 -> am = (1 + 0.75 / 100) * count
        (card == "" || card == "VK Pay") && count <= 40000 -> am = count.toDouble()
        (card == "" || card == "VK Pay") && count <= 15000 -> am = count.toDouble()
    }

    return am

}
