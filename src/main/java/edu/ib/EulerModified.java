package edu.ib;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

public class EulerModified implements ODEStep{
    @Override
    public double step(double x, double t, double h, String odeString) {

        Argument xPars = new Argument("x", x);
        Argument tPars = new Argument("t", t);
        Expression e = new Expression(odeString, xPars, tPars);

        double xMid =  e.calculate()*h/2+x;
        Argument xMidPars = new Argument("x", xMid);
        Expression e1 = new Expression(odeString, xMidPars, tPars);
        return e1.calculate()*h+x;
    }
}
