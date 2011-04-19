package natlab.Static.ir;

import java.util.ArrayList;

import natlab.Static.toolkits.analysis.IRNodeCaseHandler;


import ast.*;

/**
 * a call is of the form
 * 
 * [lhs1,lhs2,...] = f(arg1,arg2,...)
 * 
 * where the number of left-hand-side and right-hand-side variables
 * is 0 or more
 * 
 * Note that with 0 variables on the left hand side, this becomes
 * [] = ...
 * which is invalid Matlab.
 * The pretty print method is overriden to still produce valid matlab code,
 * but analyses should be aware of this.
 * 
 * @author ant6n
 *
 */
public class IRCallStmt extends IRAbstractAssignToListStmt {
    public IRCallStmt(Name function,Expr target,Expr... args) {
        this(new NameExpr(function),
                new IRCommaSeparatedList(target),new IRCommaSeparatedList(args));
    }
    
    
    public IRCallStmt(NameExpr function,IRCommaSeparatedList targets,IRCommaSeparatedList args) {
        //set lhs
        super(targets);
        
        //set rhs
        setRHS(new ParameterizedExpr(function, args));
    }
    
    //function name get
    public NameExpr getFunctionNameExpr(){
        return ((NameExpr)(((ParameterizedExpr)getRHS()).getTarget()));
    }
    public String getFunctionName(){
        return getFunctionNameExpr().getName().getID();
    }
        
    //get arguments
    public IRCommaSeparatedList getArguments(){
         return (IRCommaSeparatedList)(((ParameterizedExpr)getRHS()).getArgList());
    }    
    
    
    @Override
    public void irAnalyize(IRNodeCaseHandler irHandler) {
        irHandler.caseIRCallStmt(this);
    }

}


