package natlab.tame.valueanalysis.basicmatrix;

import natlab.tame.classes.reference.*;
import natlab.tame.valueanalysis.ValueSet;
import natlab.tame.valueanalysis.aggrvalue.AggrValue;
import natlab.tame.valueanalysis.aggrvalue.MatrixValue;
import natlab.tame.valueanalysis.components.constant.*;
import natlab.tame.valueanalysis.components.shape.*;
import natlab.tame.valueanalysis.components.rangeValue.*;
import natlab.tame.valueanalysis.value.*;

/**
 * represents a MatrixValue that is instantiable. It stores a constant, on top
 * of the matlab class
 */
public class BasicMatrixValue extends MatrixValue<BasicMatrixValue> implements
		HasConstant, HasShape<AggrValue<BasicMatrixValue>>, HasRangeValue<AggrValue<BasicMatrixValue>> {
	
	static boolean Debug = false;
	//MatrixValue has only one protected filed, PrimitiveClassReference classRef.
	protected Constant constant;
	protected Shape<AggrValue<BasicMatrixValue>> shape;
	protected RangeValue<AggrValue<BasicMatrixValue>> rangeValue;
	// TODO -- also need complex
	static BasicMatrixValueFactory factory = new BasicMatrixValueFactory();
	static ShapePropagator<AggrValue<BasicMatrixValue>> shapePropagator = ShapePropagator
			.getInstance();

	/**
	 * Construct a BasicMatrixValue based on a Constant.
	 * Whenever we need to construct a BasicMatrixValue based on a Constant,
	 * we need to call corresponding factory method in BasicMatrixValueFactory,
	 * which is newMatrixValue.
	 * TODO, actually, we should rename newMatrixValue to newMatrixValueFromConstant.
	 */
	public BasicMatrixValue(String name, Constant constant) {
		super(name, constant.getMatlabClass());
		this.constant = constant;
		this.shape = (new ShapeFactory<AggrValue<BasicMatrixValue>>())
				.newShapeFromIntegers(constant.getShape());
		//TODO, this line may cause infinite loop.
		if (constant instanceof DoubleConstant) {
			this.rangeValue = (new RangeValueFactory<AggrValue<BasicMatrixValue>>(factory))
					.newRangeValueFromDouble(((DoubleConstant)constant).getValue());			
		}
	}

	/**
	 * Construct a BasicMatrixValue based on a PrimitiveClassReference and a Shape,
	 * for the situation, a variable which doesn't have a constant value.
	 * Whenever we need to construct a BasicMatrixValue based on a 
	 * PrimitiveClassReference and a Shape,
	 * we need to call corresponding factory method in BasicMatrixValueFactory,
	 * which is newMatrixValueFromClassAndShape.
	 */
	public BasicMatrixValue(
			String name,
			PrimitiveClassReference aClass,
			Shape<AggrValue<BasicMatrixValue>> shape,
			RangeValue<AggrValue<BasicMatrixValue>> rangeValue) {
		super(name, aClass);
		this.constant = null;
		this.shape = shape;
		this.rangeValue = rangeValue;
	}

	/**
	 * Construct a BasicMatrixValue based on user input Shape information.
	 * Whenever we need to construct a BasicMatrixValue based on a user input 
	 * Shape information, we need to call corresponding factory method in 
	 * BasicMatrixValueFactory, which is newMatrixValueFromInputShape.
	 */
	public BasicMatrixValue(String name, PrimitiveClassReference aClass, String shapeInfo) {
		super(name, aClass);
		this.constant = null;
		this.shape = (new ShapeFactory<AggrValue<BasicMatrixValue>>()
				.newShapeFromInputString(shapeInfo));
	}
	
	public boolean hasMatlabClass() {
		return this.classRef!=null;
	}

	/**
	 * returns true if the represented data is a constant
	 */
	public boolean isConstant() {
		return this.constant!=null;
	}

	@Override
	/**
	 * Override the getConstant method in HasConstant Interface, which 
	 * returns the constant represented by this data, or null if it is not constant
	 */
	public Constant getConstant() {
		return this.constant;
	}
	
	public boolean hasShape() {
		return this.shape!=null;
	}

	/**
	 * Always has shape information? No, if shape propagation fails or not match, 
	 * there will be no shape info from result.
	 */
	public Shape<AggrValue<BasicMatrixValue>> getShape() {
		return this.shape;
	}
	
	public boolean hasRangeValue() {
		return this.rangeValue!=null;
	}

	public RangeValue<AggrValue<BasicMatrixValue>> getRangeValue() {
		return this.rangeValue;
	}
	
	/**
	 * what's this used for?
	 */
	public void setConstantNull() {
		this.constant = null;
	}

	@Override
	/**
	 * Override the merge method in super class MatrixValue.
	 */
	public BasicMatrixValue merge(AggrValue<BasicMatrixValue> other) {
		if (!(other instanceof BasicMatrixValue))
			throw new UnsupportedOperationException(
					"can only merge a Basic Matrix Value with another Basic Matrix Value");
		/**
		 * TODO, currently, we cannot merge two matrix value with different class 
		 * information. i.e. 
		 * 		if
		 *			c='a';
		 *		else
		 *			c=12;
		 *		end
		 * what we have at the merge point is a c=[(double,12.0,[1, 1]), (char,a,[1, 1])]
		 */
		if (!other.getMatlabClass().equals(this.getMatlabClass()))
			throw new UnsupportedOperationException(
					"only Values with the same class can be merged, trying to merge :"
							+ this + ", " + other + " has failed");
		if (this.constant!=null&&((BasicMatrixValue)other).getConstant()!=null) {
			Constant result = this.constant.merge(((BasicMatrixValue)other).getConstant());
			if (result!=null) return factory.newMatrixValue(this.getSymbolic(), result);
		}
		BasicMatrixValue newMatrix;
		if (this.hasRangeValue()) {
			newMatrix = factory.newMatrixValueFromClassShapeRange(this.getSymbolic(), this.getMatlabClass(),
					this.shape.merge(((BasicMatrixValue)other).getShape())
					,this.rangeValue.merge(((BasicMatrixValue)other).getRangeValue()));
		}
		else {
			newMatrix = factory.newMatrixValueFromClassShapeRange(this.getSymbolic(), this.getMatlabClass(),
					this.shape.merge(((HasShape<AggrValue<BasicMatrixValue>>)other).getShape()), null);
		}
		return newMatrix;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BasicMatrixValue)) return false;
		BasicMatrixValue m = (BasicMatrixValue)obj;
		if (this.isConstant()) return this.constant.equals(m.getConstant());
		if ((this.shape==null)&&(((HasShape<AggrValue<BasicMatrixValue>>)m).getShape()==null)) {
			return this.getMatlabClass().equals(m.getMatlabClass());
		}
		return this.getMatlabClass().equals(m.getMatlabClass())
				&&this.shape.equals(((HasShape<AggrValue<BasicMatrixValue>>)m).getShape())
				&&this.rangeValue.equals(((HasRangeValue<AggrValue<BasicMatrixValue>>)m).getRangeValue());
	}

	@Override
	/**
	 * although there should always be mclass and shape info for each variable 
	 * after value propagation, since we need them to declare variable in back end, 
	 * actually, any component info can be null, since mclass, shape or rangeValue 
	 * propagation may not match for some reason.
	 * we should always be careful about null value!
	 */
	public String toString() {
		return "(" + (hasMatlabClass()? this.classRef : ",[mclass propagation fails]") 
				+ (isConstant()? ("," + this.constant) : "") 
				+ (hasShape()? ("," + this.shape) : ",[shape propagation fails]")
				+ (hasRangeValue()? ("," + this.rangeValue) : "")+")";
	}

	@Override
	public ValueSet<AggrValue<BasicMatrixValue>> arraySubsref(
			Args<AggrValue<BasicMatrixValue>> indizes) {
		return ValueSet.<AggrValue<BasicMatrixValue>> newInstance(
				factory.newMatrixValueFromClassShapeRange(this.getSymbolic(), this.getMatlabClass(), 
						shapePropagator.arraySubsref(this.shape, indizes), null));
	}

	@Override
	public AggrValue<BasicMatrixValue> arraySubsasgn(
			Args<AggrValue<BasicMatrixValue>> indizes,
			AggrValue<BasicMatrixValue> value) {
		return factory.newMatrixValueFromClassShapeRange(this.getSymbolic(), this.getMatlabClass(),
				shapePropagator.arraySubsasgn(this.shape, indizes, value), null);
	}

	@Override
	public Res<AggrValue<BasicMatrixValue>> cellSubsref(
			Args<AggrValue<BasicMatrixValue>> indizes) {
		throw new UnsupportedOperationException(); // TODO
	}

	@Override
	public AggrValue<BasicMatrixValue> cellSubsasgn(
			Args<AggrValue<BasicMatrixValue>> indizes,
			Args<AggrValue<BasicMatrixValue>> values) {
		throw new UnsupportedOperationException(); // TODO
	}

	@Override
	public ValueSet<AggrValue<BasicMatrixValue>> dotSubsref(String field) {
		throw new UnsupportedOperationException("cannot dot-access a matrix!");
		// return
		// ValueSet.newInstance(factory.newErrorValue("cannot dot-access a matrix"));
	}

	@Override
	public AggrValue<BasicMatrixValue> dotSubsasgn(String field,
			AggrValue<BasicMatrixValue> value) {
		throw new UnsupportedOperationException(); // TODO
	}

	@Override
	public AggrValue<BasicMatrixValue> toFunctionArgument(boolean recursive) {
		return this;
		// throw new UnsupportedOperationException(); //TODO
	}
}
