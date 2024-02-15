package id.andra.jetpack_compose_boilerplate.utils.extension

fun String.toIntOrZero(): Int {
    return try {
        this.toInt()
    } catch (e: Exception) {
        0
    }
}