package fa.dfa;

import java.util.*;


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
        transitions = new HashMap<Character, DFAState>();
       // transitions = new HashSet<Pair<Character, String>>();
    }
    
    public void addTransition(char c, DFAState a){
        transitions.put(c,a);
    }

    public HashMap<Character, DFAState> getTransitions(){
        return transitions;
    }

    public void setTransitions(HashMap<Character, DFAState> tran){
        transitions = tran;
    }

    public DFAState getTransitionTo(char c){
        DFAState retVal = transitions.get(c);
        if(retVal == null)
        {
            System.out.println("DFAState - line 42");
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
