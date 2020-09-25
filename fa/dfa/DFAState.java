package fa.dfa;

import java.util.*;
import javafx.util.Pair;

public class DFAState extends fa.State{
    
    private boolean finals;
    private boolean init;
    private HashMap<Character, DFAState> transitions;

    /**
     * Constructor. Used to set up state with name as well as whether
     * this is a final state and or initial state.
     * 
     * @param token - name of state.
     */
    public DFAState(String token){
        name = token;
        init = false;
        finals = false;
       // transitions = new HashSet<Pair<Character, String>>();
    }
    
    public void addTransition(char c, DFAState a){
//Pair<Character, String> pair = new Pair<Character, String>(c,a);
        transitions.put(c,a);
    }

    public DFAState getTransitionTo(char c){
//        for(Pair<Character, String> p : transitions){
//            if(p.getKey().equals(c)){
//                return p.getValue();
//            }
//        }
//        return null;
//        
        DFAState retVal = transitions.get(c);
        if(retVal == null)
        {
        	System.exit(2);
        }
        return retVal;
    }

    public boolean isInit(){
        return init;
    }

    public void setInit(boolean bool){
        init = bool;
    }


    public boolean isFinal()
    {
        return finals;
    }    

    public void setFinal(boolean bool){
        finals = bool;
    }
}
