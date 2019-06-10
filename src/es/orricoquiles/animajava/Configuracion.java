package es.orricoquiles.animajava;

public class Configuracion {
    private static int fps = 30;
    private static Configuracion configuracion;
    private static Visualizacion visualizacion;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private Configuracion() {
    }

    public static Visualizacion getVisualizacion() {
        return visualizacion;
    }

    public static void setVisualizacion(Visualizacion visualizacion) {
        Configuracion.visualizacion = visualizacion;
    }

    public static Configuracion getSingletonInstance() {
        if (configuracion == null) {
            configuracion = new Configuracion();
        }

        return configuracion;
    }

    public static int getFps() {
        return fps;
    }

    public static void setFps(int fps) {
        Configuracion.fps = fps;
    }

    public static void setResolucion(Visualizacion v) {
        Configuracion.visualizacion = v;
    }
}
