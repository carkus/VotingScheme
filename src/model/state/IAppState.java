package model.state;

import controller.SystemController;

public interface IAppState {

   public abstract void startAction(SystemController s);
   
   public abstract void endAction(SystemController s);
   
   public abstract void updateAction(SystemController s);
   
   public abstract String getTitle();
   
   public abstract String getInstructions();
   
	
}
