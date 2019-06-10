package es.orricoquiles.animajava;

import static java.lang.Math.*;

public enum Interpolacion {
    LINEAR(new CalculadoraFraccion() {
        @Override
        public double calculaFraccion(double fraccion) {
            return fraccion;
        }
    }),
    FRENAR(new CalculadoraFraccion() {
        @Override
        public double calculaFraccion(double x) {
            double factor = 2.0;
            if (factor == 1.0)
                return (1.0 - (1.0 - x) * (1.0 - x));
            else
                return (1.0 - pow((1.0 - x), 2 * factor));
        }
    }),
    ANTICIPAR(new CalculadoraFraccion() {
        @Override
        public double calculaFraccion(double x) {
            double tension = 2.0;
            return x * x * ((tension + 1) * x - tension);
        }
    }),
    ACELERAFRENA(new CalculadoraFraccion() {
        @Override
        public double calculaFraccion(double x) {
            return (cos((x + 1) * PI) / 2.0) + 0.5;
        }
    }),
    PASOSUAVE(new CalculadoraFraccion() {
        @Override
        public double calculaFraccion(double x) {
            return x * x * (3 - 2 * x);
        }
    }),
    MUELLE(new CalculadoraFraccion() {
        @Override
        public double calculaFraccion(double x) {
            double factor = 0.4;
            return pow(2, -10 * x) * sin((x - factor / 4) * (2 * PI) / factor) + 1;
        }
    }),
    REBOTE(new CalculadoraFraccion() {
        @Override
        public double calculaFraccion(double x) {


            if (x < 0.3535)
                return bounce(x);
            else if (x < 0.7408)
                return bounce(x - 0.54719) + 0.7;
            else if (x < 0.9644)
                return bounce(x - 0.8526) + 0.9;
            else
                return 1;
        }

        double bounce(double t) {
            return t * t * 8;
        }
    }

    );

    private final CalculadoraFraccion calculadora;

    Interpolacion(CalculadoraFraccion calculadoraFraccion) {
        this.calculadora = calculadoraFraccion;
    }

    public double opera(double fraccion) {
        return this.calculadora.calculaFraccion(fraccion);
    }
}

interface CalculadoraFraccion {
    double calculaFraccion(double x);
}
