package natlab.tame.builtin.shapeprop.ast;

import java.util.HashMap;
import java.util.List;

import natlab.tame.builtin.shapeprop.ShapePropMatch;
import natlab.tame.valueanalysis.components.shape.*;
import natlab.tame.valueanalysis.value.*;

public class SPFunCall<V extends Value<V>> extends SPAbstractMatchElement<V> {
	
	static boolean Debug = false;
	String funName;
	SPArglist<V> arglist;
	
	public SPFunCall(String funName, SPArglist<V> arglist) {
		this.funName = funName;
		this.arglist = arglist;
	}
	
	public ShapePropMatch<V> match(boolean isPatternSide, ShapePropMatch<V> previousMatchResult, Args<V> argValues, int Nargout) {
		/*
		 *  these function calls can be divided into two categories: 
		 *  1. functions in assignment statements;
		 *  2. functions as an assert statement itself.
		 *  P.S. assert statement functions have the ability to set matching process has error.
		 *  currently, previousScalar, previousShapeDim, add, minus, div, minimum and copy are functions in assignment;
		 *  numOutput and isequal are assert functions.
		 */
		if (funName.equals("previousScalar") && arglist==null) {
			if (Debug) System.out.println("try to get previous matched scalar's value.");
			String latestMatchedUppercase = previousMatchResult.getLatestMatchedUppercase();
			String latestMatchedLowercase = previousMatchResult.getLatestMatchedLowercase();
			if (latestMatchedUppercase.equals("$") && previousMatchResult.hasValue("$")) {
				HashMap<String, DimValue> lowercase = new HashMap<String, DimValue>();
				lowercase.put(latestMatchedLowercase, previousMatchResult.getValueOfVariable("$"));
				ShapePropMatch<V> matchResult = new ShapePropMatch<V>(previousMatchResult, lowercase, null);
	            return matchResult;	
			}
			else{
				HashMap<String, DimValue> lowercase = new HashMap<String, DimValue>();
				lowercase.put(latestMatchedLowercase, new DimValue());
				ShapePropMatch<V> matchResult = new ShapePropMatch<V>(previousMatchResult, lowercase, null);
	            return matchResult;	
			}	
		}
		else if(funName.equals("previousShapeDim")) {
			if (arglist==null) {
				if (Debug) System.out.println("try to get how many dimensions of previous matched shape has.");
				Shape<V> previousMatched = previousMatchResult.getShapeOfVariable(previousMatchResult.getLatestMatchedUppercase());
				List<DimValue> dimensions = previousMatched.getDimensions();
				int numberOfDimensions = dimensions.size();
				if (Debug) System.out.println("this matched shape has "+numberOfDimensions+" dimensions");
				HashMap<String, DimValue> lowercase = new HashMap<String, DimValue>();
				lowercase.put(previousMatchResult.getLatestMatchedLowercase(), new DimValue(numberOfDimensions, null));
				ShapePropMatch<V> matchResult = new ShapePropMatch<V>(previousMatchResult, lowercase, null);
	            return matchResult;
			}
			/*
			 *  if arglist is not empty, in this language, which means there is a number in the arglist, 
			 *  this function is trying to get the size of that specific dimension of previous matched shape. i.e. for vertcat and horzcat.
			 */
			else {
				String[] arg = arglist.toString().split(",");
				if (arg.length==1) {
					ShapePropMatch<V> arglistMatch = arglist.match(isPatternSide, previousMatchResult, argValues, Nargout);
					if (Debug) System.out.println("try to get the size of the " +arglistMatch.getLatestMatchedNumber() 
							+ " dimension of latest matched shape");
					if (arglistMatch.getShapeOfVariable(arglistMatch.getLatestMatchedUppercase())
							.getDimensions().get(arglistMatch.getLatestMatchedNumber()-1)==null) {
			            return arglistMatch;
					}
					else {
						DimValue dimNum = arglistMatch.getShapeOfVariable(arglistMatch.getLatestMatchedUppercase())
								.getDimensions().get(arglistMatch.getLatestMatchedNumber()-1);
						HashMap<String, DimValue> lowercase = new HashMap<String, DimValue>();
						lowercase.put(arglistMatch.getLatestMatchedLowercase(), new DimValue(dimNum.getIntValue(), dimNum.getSymbolic()));
						ShapePropMatch<V> matchResult = new ShapePropMatch<V>(arglistMatch, lowercase, null);
			            return matchResult;
					}
				}
				System.err.println("check your shape equation for using previousShapeDim() function.");
				return previousMatchResult;
			}
		}
		/*
		 * add function is used two places, one for adding the value of previous matched scalar to vertcat, 
		 * the other one for adding value of argument to previous matched lowercase, like n=add(k). 
		 */
		else if (funName.equals("add")) {
			if (arglist==null) {
				if (Debug) System.out.println("try to add latest matched lowercase to vertcat.");
				String latestMatchedLowercase = previousMatchResult.getLatestMatchedLowercase();
				if (previousMatchResult.hasValue(latestMatchedLowercase)) {
					previousMatchResult.addToVertcatExpr(previousMatchResult.getValueOfVariable(latestMatchedLowercase));
					return previousMatchResult;
				}
				else {
					previousMatchResult.addToVertcatExpr(new DimValue());
					return previousMatchResult;
				}				
			}
			else {
				String[] arg = arglist.toString().split("'");
				if (arg.length==1) {
					if (previousMatchResult.getValueOfVariable(previousMatchResult.getLatestMatchedLowercase()).hasIntValue() 
							&& previousMatchResult.getValueOfVariable(arg[0]).hasIntValue()) {
						HashMap<String, DimValue> lowercase = new HashMap<String, DimValue>();
						int sum = previousMatchResult.getValueOfVariable(previousMatchResult.getLatestMatchedLowercase()).getIntValue() 
								+ previousMatchResult.getValueOfVariable(arg[0]).getIntValue();
						lowercase.put(previousMatchResult.getLatestMatchedLowercase(), new DimValue(sum, null));
						ShapePropMatch<V> matchResult = new ShapePropMatch<V>(previousMatchResult, lowercase, null);
						return matchResult;
					}					
				}
				return previousMatchResult;
			}
		}
		else if (funName.equals("minus") && arglist!=null) {
			String[] arg = arglist.toString().split(",");
			if(arg.length==2){
				if (Debug) System.out.println("try to compute " + arg[0] + " - " + arg[1]);
				if (previousMatchResult.getValueOfVariable(arg[0]).hasIntValue() 
						&& previousMatchResult.getValueOfVariable(arg[1]).hasIntValue()) {
					HashMap<String, DimValue> lowercase = new HashMap<String, DimValue>();
					int minus = previousMatchResult.getValueOfVariable(arg[0]).getIntValue() 
							- previousMatchResult.getValueOfVariable(arg[1]).getIntValue() + 1;
					lowercase.put(previousMatchResult.getLatestMatchedLowercase(), new DimValue(minus, null));
					ShapePropMatch<V> matchResult = new ShapePropMatch<V>(previousMatchResult, lowercase, null);
		            return matchResult;
				}
			}
			System.err.println("cannot compute minus function in shape equation!");
			return previousMatchResult;
		}
		else if (funName.equals("div") && arglist!=null) {
			String[] arg = arglist.toString().split(",");
			if(arg.length==2){
				if (Debug) System.out.println("try to compute " + arg[0] + "/" + arg[1]);
				if (previousMatchResult.getValueOfVariable(arg[0]).hasIntValue()
						&& previousMatchResult.getValueOfVariable(arg[1]).hasIntValue()) {
					HashMap<String, DimValue> lowercase = new HashMap<String, DimValue>();
					int div = previousMatchResult.getValueOfVariable(arg[0]).getIntValue() 
							/ previousMatchResult.getValueOfVariable(arg[1]).getIntValue();
					lowercase.put(previousMatchResult.getLatestMatchedLowercase(), new DimValue(div, null));
					ShapePropMatch<V> matchResult = new ShapePropMatch<V>(previousMatchResult, lowercase, null);
		            return matchResult;
				}
			}
			System.err.println("cannot compute div function in shape equation!");
			return previousMatchResult;
		}
		else if (funName.equals("minimum") && arglist!=null) {
			String[] arg = arglist.toString().split(",");
			if (arg.length==2) {
				if (previousMatchResult.hasValue(arg[0])&&previousMatchResult.hasValue(arg[1])){
					HashMap<String, DimValue> lowercase = new HashMap<String, DimValue>();
					int f = previousMatchResult.getValueOfVariable(arg[0]).getIntValue();
					int s = previousMatchResult.getValueOfVariable(arg[1]).getIntValue();
					String result = (f<s)?arg[0]:arg[1];
					if (Debug) System.out.println("the minimum one is "+result);
					lowercase.put(previousMatchResult.getLatestMatchedLowercase(), previousMatchResult.getValueOfVariable(result));
					ShapePropMatch<V> match = new ShapePropMatch<V>(previousMatchResult, lowercase, null);
					return match;
				}
			}
			System.err.println("cannot compute minimum function in shape equation!");
			return previousMatchResult;
			
		}
		else if (funName.equals("copy") && arglist!=null) {
			String[] arg = arglist.toString().split(",");
			if (arg.length==1) {
				if (Debug) System.out.println("try to copy the shape of " + arg[0] + " to the temporary variable " + previousMatchResult.getLatestMatchedUppercase());
				HashMap<String, Shape<V>> uppercase = new HashMap<String, Shape<V>>();
				Shape<V> newShape = new ShapeFactory<V>().newShapeFromDimValues(previousMatchResult.getShapeOfVariable(arg[0]).getDimensions());
				uppercase.put(previousMatchResult.getLatestMatchedUppercase(), newShape);
				ShapePropMatch<V> matchResult = new ShapePropMatch<V>(previousMatchResult, null, uppercase);
				return matchResult;
			}
			else {
				System.err.println("check your shape equation language for using copy() function.");
				return previousMatchResult;
			}
		}
		else if (funName.equals("numOutput") && arglist!=null) {
			String[] arg = arglist.toString().split(",");
			if (arg.length==1) {
				if (Debug) System.out.println("checking whether the number of output arguments equals to " + arg[0]);
				if (Integer.parseInt(arg[0])==Nargout) return previousMatchResult;
			}
			else{
				previousMatchResult.setIsError(true);
				return previousMatchResult;
			}
		}
		else if (funName.equals("isequal") && arglist!=null) {
			String[] arg = arglist.toString().split(",");
			if (arg.length==2) {
				if (Debug) System.out.println("comparing " + previousMatchResult.getShapeOfVariable(arg[0])+" with "+previousMatchResult
						.getShapeOfVariable(arg[1]));
				Shape<V> first = previousMatchResult.getShapeOfVariable(arg[0]);
				Shape<V> second = previousMatchResult.getShapeOfVariable(arg[1]);
				if (first.equals(second)) return previousMatchResult;
			}
			else {
				previousMatchResult.setIsError(true);
				return previousMatchResult;				
			}
		}
		System.err.println("cannot find the function " + funName + "(" + (arglist==null? "" : arglist.toString()) + "), check your shape equation in builtins.csv file!");
		return previousMatchResult;
	}
	
	public String toString() {
		return funName.toString() + "(" + (arglist==null? "" : arglist.toString()) + ")";
	}
}
