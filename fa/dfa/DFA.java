package fa.dfa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;


import fa.State;

public class DFA implements DFAInterface {

	Set<DFAState> states;
	Set<Character> alphabet;
	Set<DFAState> finalStates;

	
	public DFA(){
		states = new HashSet<>();
		alphabet = new HashSet<>();
		finalStates = new HashSet<DFAState>();
	
	}

	@Override
	public void addStartState(String name) {
		DFAState newState = new DFAState(name);
		newState.setInit(true);
		states.add(newState);
	}

	public String toString() {
		String retString = "";
		retString += "Q = { ";
		for (DFAState s : states) {
			retString += s.getName() + " ";
		}
		retString += "}\nsigma = ";
		for (Character c : alphabet) {
			retString += c + " ";
		}
		retString += "}\ndelta = \n";
		for(DFAState s : states)
		{
			HashMap<Character, String> map = s.getTransitions();
			for(Entry<Character, String> e : map.entrySet())
			{
				retString+= "|"+s.getName() + "-> " + e.getKey()+ " = " + e.getValue() + "|"+"\n";
			}
		}
		retString += "\n";
		retString += "q0 = " + getStartState().getName() + "\n";
		retString += "F = {";
		for (State s : getFinalStates()) {
			retString += s.getName() + " ";
		}
		retString += "}\n\n";
		return retString;
	}
	
	public void addState(DFAState state){
		states.add(state);
	}

	@Override
	public void addState(String name) {
		DFAState newState = new DFAState(name);
		states.add(newState);
	}

	@Override
	public void addFinalState(String name) {
		// TODO Auto-generated method stub
		for(DFAState s: states){
			if(s.getName().equals(name)){
				s.setFinal(true);
				finalStates.add(s);
				return;
			}
		}
		DFAState newState = new DFAState(name);
		newState.setFinal(true);
		finalStates.add(newState);
		states.add(newState);

	}

	@Override
	public void addTransition(String fromState, char onSymb, String toState) {
		for(DFAState s : states){
			if(fromState.equals(s.getName())){
				s.addTransition(onSymb, toState);
			}
		}
		alphabet.add(onSymb);
	}

	@Override
	public Set<? extends State> getStates() {
		// TODO Auto-generated method stub
		return states;
	}

	@Override
	public Set<? extends State> getFinalStates() {
		// TODO Auto-generated method stub
		return finalStates;
	}

	@Override
	public State getStartState() {
		for(DFAState s: states){
			if(s.isInit()){
				return s;
			}
		}
		return null;
	}

	@Override
	public Set<Character> getABC() {
		// TODO Auto-generated method stub
		return alphabet;
	}

	@Override
	public DFA complement() {
		DFA dfa = new DFA();
		for(DFAState s : states){
			DFAState newState = new DFAState(s.getName());
			newState.setTransitions(s.getTransitions());
			boolean init = s.isInit();
			newState.setInit(init);
			boolean finalSt = s.isFinal();
			newState.setFinal(!finalSt);
			dfa.addState(newState);
		}
		return dfa;
	}

	@Override
	public boolean accepts(String s) {
		boolean retVal = ((DFAState) getStartState()).isFinal();
		if(s.equals("e"))
			return retVal;
		DFAState currState = (DFAState) getStartState();
		for(int i = 0 ; i < s.length(); i++){
			currState = (DFAState) getToState(currState, s.charAt(i));
			retVal = currState.isFinal();
		}
		return retVal;
	}

	@Override
	public State getToState(DFAState from, char onSymb) {
		String nextState = from.getTransitionTo(onSymb);
		for(DFAState s : states){
			if(s.getName().equals(nextState)){
				return s;
			}
		}
		return null;
	}





}
