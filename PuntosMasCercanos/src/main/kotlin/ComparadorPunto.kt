class ComparadorPunto<Punto>() :Comparator<Punto>{
    override fun compare(o1: Punto, o2: Punto): Int {
        var ret = -1;
        
        if(o1.x>o2.x)
            ret = 1
        else
            if(o1.x==o2.x)
                ret = 0;
        return ret
    }

}