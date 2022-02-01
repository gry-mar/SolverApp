package edu.ib;

import org.mariuszgromada.math.mxparser.*;

public class Euler implements ODEStep{
    @Override
    public double step(double x, double t, double h, String odeString) {
        Argument xPars = new Argument("x", x);
        Argument tPars = new Argument("t", t);
        Expression e = new Expression(odeString, xPars, tPars);
        return e.calculate()*h+x;

    }
}
