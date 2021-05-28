class ComparadorPunto{

    companion object : Comparator<Punto> {
        override fun compare(o1: Punto, o2: Punto): Int = when{
            o1.x > o2.x -> 1
            o1.x == o2.x -> 0
            else -> -1
        }

    }
}