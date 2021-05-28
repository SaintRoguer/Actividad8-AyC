class ComparadorPuntoY {

    companion object : Comparator<Punto> {
        override fun compare(o1: Punto, o2: Punto): Int = when{
            o1.y > o2.y -> 1
            o1.y == o2.y -> 0
            else -> -1
        }

    }
}