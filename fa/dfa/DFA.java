package fa.dfa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import fa.State;
import javafx.util.Pair;

public class DFA implements DFAInterface {

	private String initial;
	private Set<DFAState> states;
	private Set<Character> alphabet;
	
	public DFA(){
	
	}


	public void addFinalState(String nextToken) {		
		DFAState newState = new DFAState(nextToken);
		newState.setFinal(true);
		states.add(newState);
	}

	public void addStartState(String startStateName) {
		DFAState newState = new DFAState(startStateName);
		newState.setInit(true);
		states.add(newState);
		initial = startStateName;
	}

	public void addState(String nextToken) {
		DFAState newState = new DFAState(nextToken);
		states.add(newState);
	}

	public void addTransition(String valueOf, char c, String valueOf2) {
		alphabet.add(c);
		Iterator<DFAState> iter = states.iterator();
		DFAState currState;
		while(iter.hasNext()){
			currState  = iter.next();
			if(currState.getName() == valueOf){
				currState.addTransition(c, valueOf2);
			}
		}
	}

	public DFA complement() {
		DFA dfa = this;
		Set<? extends State> states = dfa.getStates();
		Iterator<? extends State> iter = states.iterator();
		DFAState curr;
		while(iter.hasNext()){
			curr = (DFAState)iter.next();
			if(curr.isFinal()){
				curr.setFinal(false);
			}
			else{
				curr.setFinal(true);
			}
		}
		return dfa;
	}

	public boolean accepts(String input) {

		return false;
	}

	@Override
	public Set<? extends State> getStates() {

		return states;
	}

	@Override
	public Set<? extends State> getFinalStates() {

		return null;
	}

	@Override
	public State getStartState() {

		return null;
	}

	@Override
	public Set<Character> getABC() {

		return null;
	}

	@Override
	public State getToState(DFAState from, char onSymb) {

		return null;
	}

}
