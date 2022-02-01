package edu.ib;

public class ODEIntegrator{

    private double tLeft;
    private double tRight;
    private double x0;
    private String odeString;
    private ODEStep method;
    private StepHandler stepHandler;


    public ODEIntegrator(double tLeft, double tRight, double x0, String odeString, ODEStep method,
                         StepHandler stepHandler) {
        this.tLeft = tLeft;
        this.tRight = tRight;
        this.x0 = x0;
        this.odeString = odeString;
        this.method = method;
        this.stepHandler = stepHandler;
    }

    public void integrate(double h){
        double x = x0;
        double t;
        for(t = tLeft; t < tRight; t+=h){
            stepHandler.update(x,t);
            x = method.step(x,t,h,odeString);

        }
        stepHandler.update(x,t);

    }

//    //@Override
//    public double f(double x, double t) {
//        Argument xPars = new Argument("x", x);
//        Argument tPars = new Argument("t", t);
//        Expression e = new Expression(this.odeString, xPars, tPars);
//        return e.calculate();
//    }
}
