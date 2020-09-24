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
		states = new HashSet<DFAState>();
		alphabet = new HashSet<Character>();
	}

	public String toString(){
		String retString = "";
		retString += "Q = {" ;
		for(DFAState s : states){
			retString += s.getName() + " ";
		}
		retString += "}\nsigma = {";
		for(Character c : alphabet){
			retString += c + " ";
		}
		retString += "}\ndelta = neeeeed tooooo addddd delllttaaaa\n\t\t\n";
		retString += "q0 = " + getStartState().getName() + "\n";
		retString += "F = {";
		for(State s : getFinalStates()){
			retString += s.getName() + " ";
		}
		retString += "}\n\n";
		return retString;
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
		for(DFAState s : states){
			String name = s.getName();
			if(name.equals(valueOf)){
				s.addTransition(c, valueOf2);
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
		DFAState init = (DFAState)getStartState();
		boolean accepts = init.isFinal();
		DFAState next = init;
		for(int i = 0; i<input.length(); i++){
			Character transition = input.charAt(i);
			String nextString = next.getTransitionTo(transition);;
			next = (DFAState) getState(nextString);
			accepts = next.isFinal();
		}
		return accepts;
	}

	@Override
	public Set<? extends State> getStates() {

		return states;
	}

	@Override
	public Set<? extends State> getFinalStates() {
		Set<DFAState> finalStates = new HashSet<DFAState>();
		for(DFAState s: states){
			if(s.isFinal()){
				finalStates.add(s);
			}
		}		
		return finalStates;
	}

	@Override
	public State getStartState() {
		for(DFAState s: states ){
			if(s.isInit()){
				return s;
			}
		}
		return null;
	}

	@Override
	public Set<Character> getABC() {

		return alphabet;
	}

	public State getState(String name){
		for(State s: states){
			if(s.getName().equals(name)){
				return s;
			}
		}
		return null;
	}

	@Override
	public State getToState(DFAState from, char onSymb) {
		String next = from.getTransitionTo(onSymb);

		return getState(next);
	}

}
