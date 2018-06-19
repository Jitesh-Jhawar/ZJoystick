/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joystick.BasicCodes;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

/**
 *
 * @author user
 */
public abstract class Joystick {

    public Joystick() {

    }
private void fish(){
    
    
    
}
    private void FRAID() {
        Controller contr[] = ControllerEnvironment.getDefaultEnvironment().getControllers();
        String output = "";
        int j = 0;
        Controller out[] = new Controller[contr.length];
        for (int i = 0; i < contr.length; ++i) {
            Controller con = contr[i];
            if (con.getType() == Controller.Type.STICK
                    || con.getType() == Controller.Type.GAMEPAD
                    || con.getType() == Controller.Type.WHEEL
                    || con.getType() == Controller.Type.FINGERSTICK) {
                out[j] = con;
                ++j;
            }
        }
        if (j > 0) {
            while (true) {
                int a = getSelectedValue();
                Controller con = out[a];
                if (!con.poll()) {
                    showControllerDisconnected(out, a);
                    break;
                }
                int xAxis = 0, yAxis = 0;
                Component com[] = con.getComponents();
                Button[] bs=new Button[com.length];
                int k=0;
                for (int i = 0; i < com.length; ++i) {
                    Component comp = com[i];
                    Component.Identifier ident = comp.getIdentifier();
                    if (ident.getName().matches("^[0-9]*$")) {//BUTTON
                        boolean Pre = (comp.getPollData() != 0.0f);//PRE =>button clicked or not
                        String buttInd = comp.getIdentifier().toString();//
                    bs[k]=new Button(buttInd,Pre);
                    ++k;
                    continue;
                    }
                }
            }
        }
    }

    public static void main(String args[]) {

    }

    public abstract int getSelectedValue();

    public abstract void showControllerDisconnected(Controller[] out, int a);

public class Button{
public Button(String name,boolean click){
this.click=click;
this.name=name;
}
public Button(String toString){
String aa[]=toString.split("-");
String name;boolean click;
name=aa[1].split(":")[1];
click=aa[2].split(":")[1].equals("0");
this.click=click;
this.name=name;
}

private final boolean click;
private final String name;
public boolean isClicked(){return click;}  
    public String getName(){return name;}
    public String toString(){
    return "Button-N:"+name+"-C:"+((click)?1:0);
    }

}
}
