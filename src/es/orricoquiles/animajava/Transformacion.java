package es.orricoquiles.animajava;


public abstract class Transformacion {
    int frameInicial;
    int frameFinal;

    Interpolacion interpolacion;

    public Transformacion(double inicio, double duracion, Interpolacion interpolacion) {
        this.frameInicial = (int) Math.round(inicio * Configuracion.getFps());
        ;
        this.frameFinal = (int) Math.round(frameInicial + duracion * Configuracion.getFps() - 1);
        this.interpolacion = interpolacion;
        //System.out.println(this.frameInicial + " hasta el " + this.frameFinal);
    }

    public abstract void transforma(Sprite sprite, int frameActual);

    public double interpola(double valorInicial, double valorFinal, int frameActual) {
        double fraccion = (frameActual - frameInicial) / (double) (frameFinal - frameInicial);
        return ((valorFinal - valorInicial) * interpolacion.opera(fraccion) + valorInicial);
    }
}
