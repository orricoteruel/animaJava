package es.orricoquiles.animajava;

public class TransformacionDesplazamiento extends Transformacion {
    boolean establecidasIniciales = false;
    int xOriginal;
    int yOriginal;
    int incx;
    int incy;

    public TransformacionDesplazamiento(double inicio, double duracion, int incx, int incy, Interpolacion interpolacion) {
        super(inicio, duracion, interpolacion);
        this.incx = incx;
        this.incy = incy;
    }

    @Override
    public void transforma(Sprite sprite, int frameActual) {
        if (!establecidasIniciales) {
            xOriginal = sprite.x;
            yOriginal = sprite.y;
            establecidasIniciales = true;
        }
        sprite.setX((int) Math.round(interpola(xOriginal, xOriginal + incx, frameActual)));
        sprite.setY((int) Math.round(interpola(yOriginal, yOriginal + incy, frameActual)));
    }
}
