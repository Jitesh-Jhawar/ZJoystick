/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jinputjoysticktest;
/**
 *
 * @author user
 */
import joystick.JInputJoystick;
import net.java.games.input.Component;
import net.java.games.input.Controller;
public class JInputJoystickTest {
public JInputJoystickTest(){
          JInputJoystick joystick = new JInputJoystick(Controller.Type.STICK, Controller.Type.GAMEPAD);
// Check if the controller was found.
if( !joystick.isControllerConnected() ){
   System.out.println("No controller found!");
   // Do some stuff.
}
// Get current state of joystick! And check, if joystick is disconnected.
if( !joystick.pollController() ) {
   System.out.println("Controller disconnected!");
   // Do some stuff.
}
// Left controller joystick
int xValuePercentageLeftJoystick = joystick.getXAxisPercentage();
int yValuePercentageLeftJoystick = joystick.getYAxisPercentage();
 
// Right controller joystick
int xValuePercentageRightJoystick, yValuePercentageRightJoystick;
 
// stick type controller
if(joystick.getControllerType() == Controller.Type.STICK)
{
   // Right controller joystick
   xValuePercentageRightJoystick = joystick.getZAxisPercentage();
   yValuePercentageRightJoystick = joystick.getZRotationPercentage();
}
// gamepad type controller
else
{
   // Right controller joystick
   xValuePercentageRightJoystick = joystick.getXRotationPercentage();
   yValuePercentageRightJoystick = joystick.getYRotationPercentage();
 
   // If Z Axis exists.
   if(joystick.componentExists(Component.Identifier.Axis.Z)){
      int zAxisValuePercentage = joystick.getZAxisPercentage();
   }
}
// Left controller joystick
 xValuePercentageLeftJoystick = joystick.getX_LeftJoystick_Percentage();
 yValuePercentageLeftJoystick = joystick.getY_LeftJoystick_Percentage();
 
// Right controller joystick
 xValuePercentageRightJoystick = joystick.getX_RightJoystick_Percentage();
 yValuePercentageRightJoystick = joystick.getY_RightJoystick_Percentage();
 
// If controller is a gamepad type.
if(joystick.getControllerType() == Controller.Type.GAMEPAD)
{ // Must check if controller is a gamepad, because stick type controller also have Z axis but it's for right controller joystick.
   // If Z Axis exists.
   if(joystick.componentExists(Component.Identifier.Axis.Z)){
      int zAxisValuePercentage = joystick.getZAxisPercentage();
   }
}
float hatSwitchPosition = joystick.getHatSwitchPosition();
 
if(Float.compare(hatSwitchPosition, Component.POV.OFF) == 0){
  // Hat switch is not pressed. The same as Component.POV.CENTER
}else if(Float.compare(hatSwitchPosition, Component.POV.UP) == 0){
   // Do stuff when UP is pressed.
}else if(Float.compare(hatSwitchPosition, Component.POV.DOWN) == 0){
   // Do stuff when DOWN is pressed.
}else if(Float.compare(hatSwitchPosition, Component.POV.LEFT) == 0){
   // Do stuff when LEFT is pressed.
}else if(Float.compare(hatSwitchPosition, Component.POV.RIGHT) == 0){
   // Do stuff when RIGHT is pressed.
}else if(Float.compare(hatSwitchPosition, Component.POV.UP_LEFT) == 0){
   // Do stuff when UP and LEFT is pressed.
}else if(Float.compare(hatSwitchPosition, Component.POV.UP_RIGHT) == 0){
   // Do stuff when UP and RIGHT is pressed.
}else if(Float.compare(hatSwitchPosition, Component.POV.DOWN_LEFT) == 0){
   // Do stuff when DOWN and LEFT is pressed.
}else if(Float.compare(hatSwitchPosition, Component.POV.DOWN_RIGHT) == 0){
   // Do stuff when DOWN and RIGHT is pressed.
}
// Number of buttons.
int numberOfButtons = joystick.getNumberOfButtons();
 
// Button one on the controller.
boolean joystickButton_1 = joystick.getButtonValue(0);






}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          
new JInputJoystickTest();  
        
// TODO code application logic here
    }
    
}
