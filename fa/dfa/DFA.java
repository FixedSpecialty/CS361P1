package fa.dfa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import fa.State;

public class DFA implements DFAInterface {
	private String initial;
	private Set<DFAState> states;
	private Set<Character> alphabet;

	public DFA() {
		states = new HashSet<DFAState>();
		alphabet = new HashSet<Character>();
	}

	public String toString() {
		String retString = "";
		retString += "Q = { ";
		for (DFAState s : states) {
			retString += s.getName() + " ";
		}
		retString += "}\nsigma = {";
		for (Character c : alphabet) {
			retString += c + " ";
		}
		retString += "}\ndelta = neeeeed tooooo addddd delllttaaaa\n\t\t\n";
		retString += "q0 = " + getStartState().getName() + "\n";
		retString += "F = {";
		for (State s : getFinalStates()) {
			retString += s.getName() + " ";
		}
		retString += "}\n\n";
		return retString;
	}

	public void addFinalState(String stateN) {
		DFAState newstate = stateCheck(stateN);
		if (newstate != null) {
			if(newstate.getName().equals(initial)){
				getStartState().setFinal(true);
			}
			else{
				System.out.println("state already exists");
			}
		} else {
			newstate = new DFAState(stateN);
			newstate.setFinal(true);
			addState(newstate);
		}
	}

	public void addStartState(String stateN) {
		DFAState newstate = stateCheck(stateN);
		if (newstate != null) {
			System.out.println("state already exists");
		} else {
			newstate = new DFAState(stateN);
			newstate.setInit(true);
			initial = stateN;
			addState(newstate);
		}
	}

	private void addState(DFAState stateN) {
		states.add(stateN);
	}

	public void addState(String stateN) {
		DFAState newstate = stateCheck(stateN);
		if (newstate != null) {
			System.out.println("state already exists");
		} else {
			newstate = new DFAState(stateN);
			addState(newstate);
		}
	}

	public void addTransition(String valueOf, char c, String valueOf2) {
		DFAState startState = stateCheck(valueOf);
		DFAState endState = stateCheck(valueOf2);
		if (startState == null) {
			System.out.println("No start state exists");
			System.exit(2);
		} else if (endState == null) {
			System.out.println("No end state exists");
			System.exit(2);
		}
		startState.addTransition(c, endState);
		if (!alphabet.contains(c)) {
			alphabet.add(c);
		}
	}

	public DFA complement() {
		DFA dfa = new DFA();
		Set<DFAState> newStates = new HashSet<DFAState>();
		
		for(DFAState s : states){
			DFAState newSta = new DFAState(s.getName());		
			newSta.setInit(s.isInit());
			newSta.setFinal(!s.isFinal());
			newSta.setTransitions(s.getTransitions());
			newStates.add(newSta);
		}
		dfa.setStates(newStates);
		return dfa;

	}

	public void setStates(Set<DFAState> sta){
		states = sta;
	}

	public boolean accepts(String input) {
		DFAState init = getStartState();
		if (input.equals("e"))
			return init.isFinal();
		boolean accepts = init.isFinal();
		DFAState next = init;
		for (int i = 0; i < input.length(); i++) {
			Character transition = input.charAt(i);
			next = next.getTransitionTo(transition);
			accepts = next.isFinal();
		}
		return accepts;

	}

	@Override
	public Set<DFAState> getStates() {

		return states;
	}

	@Override
	public Set<DFAState> getFinalStates() {
		Set<DFAState> finalStates = new HashSet<DFAState>();
		for (DFAState s : states) {
			if (s.isFinal()) {
				finalStates.add(s);
			}
		}
		return finalStates;
	}

	@Override
	public DFAState getStartState() {
		for (DFAState s : states) {
			if (s.isInit()) {
				return s;
			}
		}
		return null;
	}

	@Override
	public Set<Character> getABC() {

		return alphabet;
	}

	public State getState(String name) {
		for (State s : states) {
			if (s.getName().equals(name)) {
				return s;
			}
		}
		return null;
	}

	@Override
	public DFAState getToState(DFAState from, char onSymb) {
		DFAState next = from.getTransitionTo(onSymb);

		return next;
	}

	private DFAState stateCheck(String stateN) {
		DFAState retVal = null;
		for (DFAState state : states) {
			if (state.getName().equals(stateN)) {
				retVal = state;
				break;
			}
		}
		return retVal;

	}

}
