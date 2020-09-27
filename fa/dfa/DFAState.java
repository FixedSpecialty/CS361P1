package fa.dfa;

import java.util.*;


public class DFAState extends fa.State{
    
    private boolean finals;
    private boolean init;
    private HashMap<Character, String> transitions;

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
        transitions = new HashMap<Character, String>();
       // transitions = new HashSet<Pair<Character, String>>();
    }
    
    public void addTransition(char c, String a){
        transitions.put(c,a);
    }

    public void setTransitions(HashMap<Character, String> tran){
        transitions = tran;
    }

    public HashMap<Character, String> getTransitions(){
        return transitions;
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
    public String getTransitionTo(char tranChar){
        return transitions.get(tranChar);
    }
}
