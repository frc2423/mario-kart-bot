package frc.robot;

import frc.robot.util.stateMachine.InitState;
import frc.robot.util.stateMachine.RunState;
import frc.robot.util.stateMachine.StateMachine;

// YOU CAN DELETE

public class ExampleMachine extends StateMachine {

    private int i = 0;
  
    public ExampleMachine() {
      super("State1");
    }
  
    @InitState(name = "State1")
    public void  method1(){
      i = 0;
    }
  
    @RunState(name = "State1")
    public void method2(){
      i++;
      System.out.println("state 1 : " + i);
      if (i % 4 == 0) this.setState("State2");
    }
  
    @InitState(name = "State2")
    public void method3(){
      i = 0;
    }
  
    @RunState(name = "State2")
    public void method4(){
      i++;
      System.out.println("state 2 : " + i);
      if (i % 10 == 0) this.setState("State1");
    }
  }