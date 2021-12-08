package frc.robot.util.stateMachine;

import java.lang.reflect.Method;
import java.lang.annotation.Annotation;
import java.util.HashMap;

/**
 * Stinky State Machine Master Class (SSMMC)
 * @author Willow
 */
public class StateMachine {

  private HashMap<String, Method> states;
  private HashMap<String, Method> runStates;
  private String state = "defaultState";

  public StateMachine() {
    states = new HashMap<String, Method>();
    runStates = new HashMap<String, Method>();
    Method[] methods= this.getClass().getMethods(); //obtain all method objects
    for(Method method : methods){
      Annotation[] annotations = method.getDeclaredAnnotations();
      for(Annotation annotation : annotations){
        // Gathers each method that has InitState annotations
        if(annotation instanceof InitState){
          InitState myAnnotation = (InitState) annotation;
          states.put(myAnnotation.name(), method);
        }
        // Gathers each method that has RunState annotations
        if(annotation instanceof RunState){
          RunState myAnnotation = (RunState) annotation;
          runStates.put(myAnnotation.name(), method);
        }
      }
    }
  }

  /**
  * Calls the function associated with the current state.
  * <p>Run this method periodically.</p>
  */
  public void run(){
    String name = this.state;
    runState(name);
  }

  /**
  * Sets the current state and initializes it.
  * @param name : String <i>name of the state</i>
  */
  public void setState(String name){
    state = name;
    initState(name);
  }

  /**
  * Sets the current state.
  * @param name : String <i>name of the state</i>
  * @param init : boolean <i>initialize the state</i>
  */
  public void setState(String name, boolean init){
    state = name;
    if(init) initState(name);
  }

  // THESE TRY{} ARE MANDITORY FOR THE CODE TO COMPILE (I'm pretty sure) BUT MAY CAUSE PROBLEMS LATER
  // BECAUSE IT THROWS ERRORS WHEN IT CAN'T FIND "name" IN THE HASHMAP. POTENTIALLY WE
  // COULD HAVE A FUCTION DEFINED IN THE SUBCLASS THAT IS A 'base' STATE THAT, FOR THE EXAMPLE OF DRIVE CODE
  // COULD BE SETTING THE SPEED AND TURN-RATE TO 0 SO THAT IF AN ERROR OCCURS IT STOPS MOVING INSTEAD OF 
  // POTENTIALLY CAUSING PROBLEMS BY HALTING PERIODIC CHANGES (I hope this made sense)

  // You could probabaly do another Annotation @ErrorState() that is run instead of @RunState(name)
  // In case of NullPointerException


  /**
   * Calls annotated <i>@InitState</i> method of <i>name</i> state.
   * @param name : String <i>name of state</i>
   */
  public void initState(String name){
    try {
      Object returnObject = states.get(name).invoke(this);
    } catch (Exception e){
      System.out.println(e + ": state-> " + name);
    }
  }

  /**
   * Calls annotated <i>@RunState</i> method of <i>name</i> state.
   * @param name : String <i>name of state</i>
   */
  public void runState(String name){
    try {
      Object returnObject = runStates.get(name).invoke(this);
    } catch (Exception e){
      System.out.println(e + ": runState-> " + name);
    }
  }
}