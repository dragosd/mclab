/* The following code was generated by JFlex 1.4.1 on 4/6/12 3:18 PM */

package natlab.tame.builtin.shapeprop;

import beaver.Symbol;
import beaver.Scanner;

import natlab.tame.builtin.shapeprop.ShapePropParser.Terminals;



/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.1
 * on 4/6/12 3:18 PM from the specification file
 * <tt>shapeprop.flex</tt>
 */
class ShapePropScanner extends Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\5\1\1\1\1\1\0\1\1\1\1\16\5\4\0\1\1\3\0"+
    "\1\10\2\0\1\24\1\14\1\15\1\17\1\20\1\13\1\2\2\0"+
    "\12\3\3\0\1\21\1\12\1\16\1\0\32\6\1\22\1\0\1\23"+
    "\1\0\1\4\1\0\32\7\1\0\1\11\2\0\41\5\2\0\4\4"+
    "\4\0\1\4\2\0\1\5\7\0\1\4\4\0\1\4\5\0\27\4"+
    "\1\0\37\4\1\0\u013f\4\31\0\162\4\4\0\14\4\16\0\5\4"+
    "\11\0\1\4\21\0\130\5\5\0\23\5\12\0\1\4\13\0\1\4"+
    "\1\0\3\4\1\0\1\4\1\0\24\4\1\0\54\4\1\0\46\4"+
    "\1\0\5\4\4\0\202\4\1\0\4\5\3\0\105\4\1\0\46\4"+
    "\2\0\2\4\6\0\20\4\41\0\46\4\2\0\1\4\7\0\47\4"+
    "\11\0\21\5\1\0\27\5\1\0\3\5\1\0\1\5\1\0\2\5"+
    "\1\0\1\5\13\0\33\4\5\0\3\4\15\0\4\5\14\0\6\5"+
    "\13\0\32\4\5\0\13\4\16\5\7\0\12\3\4\0\2\4\1\5"+
    "\143\4\1\0\1\4\10\5\1\0\6\5\2\4\2\5\1\0\4\5"+
    "\2\4\12\3\3\4\2\0\1\4\17\0\1\5\1\4\1\5\36\4"+
    "\33\5\2\0\3\4\60\0\46\4\13\5\1\4\u014f\0\3\5\66\4"+
    "\2\0\1\5\1\4\20\5\2\0\1\4\4\5\3\0\12\4\2\5"+
    "\2\0\12\3\21\0\3\5\1\0\10\4\2\0\2\4\2\0\26\4"+
    "\1\0\7\4\1\0\1\4\3\0\4\4\2\0\1\5\1\4\7\5"+
    "\2\0\2\5\2\0\3\5\11\0\1\5\4\0\2\4\1\0\3\4"+
    "\2\5\2\0\12\3\4\4\15\0\3\5\1\0\6\4\4\0\2\4"+
    "\2\0\26\4\1\0\7\4\1\0\2\4\1\0\2\4\1\0\2\4"+
    "\2\0\1\5\1\0\5\5\4\0\2\5\2\0\3\5\13\0\4\4"+
    "\1\0\1\4\7\0\12\3\2\5\3\4\14\0\3\5\1\0\11\4"+
    "\1\0\3\4\1\0\26\4\1\0\7\4\1\0\2\4\1\0\5\4"+
    "\2\0\1\5\1\4\10\5\1\0\3\5\1\0\3\5\2\0\1\4"+
    "\17\0\2\4\2\5\2\0\12\3\1\0\1\4\17\0\3\5\1\0"+
    "\10\4\2\0\2\4\2\0\26\4\1\0\7\4\1\0\2\4\1\0"+
    "\5\4\2\0\1\5\1\4\6\5\3\0\2\5\2\0\3\5\10\0"+
    "\2\5\4\0\2\4\1\0\3\4\4\0\12\3\1\0\1\4\20\0"+
    "\1\5\1\4\1\0\6\4\3\0\3\4\1\0\4\4\3\0\2\4"+
    "\1\0\1\4\1\0\2\4\3\0\2\4\3\0\3\4\3\0\10\4"+
    "\1\0\3\4\4\0\5\5\3\0\3\5\1\0\4\5\11\0\1\5"+
    "\17\0\11\3\11\0\1\4\7\0\3\5\1\0\10\4\1\0\3\4"+
    "\1\0\27\4\1\0\12\4\1\0\5\4\4\0\7\5\1\0\3\5"+
    "\1\0\4\5\7\0\2\5\11\0\2\4\4\0\12\3\22\0\2\5"+
    "\1\0\10\4\1\0\3\4\1\0\27\4\1\0\12\4\1\0\5\4"+
    "\2\0\1\5\1\4\7\5\1\0\3\5\1\0\4\5\7\0\2\5"+
    "\7\0\1\4\1\0\2\4\4\0\12\3\22\0\2\5\1\0\10\4"+
    "\1\0\3\4\1\0\27\4\1\0\20\4\4\0\6\5\2\0\3\5"+
    "\1\0\4\5\11\0\1\5\10\0\2\4\4\0\12\3\22\0\2\5"+
    "\1\0\22\4\3\0\30\4\1\0\11\4\1\0\1\4\2\0\7\4"+
    "\3\0\1\5\4\0\6\5\1\0\1\5\1\0\10\5\22\0\2\5"+
    "\15\0\60\4\1\5\2\4\7\5\4\0\10\4\10\5\1\0\12\3"+
    "\47\0\2\4\1\0\1\4\2\0\2\4\1\0\1\4\2\0\1\4"+
    "\6\0\4\4\1\0\7\4\1\0\3\4\1\0\1\4\1\0\1\4"+
    "\2\0\2\4\1\0\4\4\1\5\2\4\6\5\1\0\2\5\1\4"+
    "\2\0\5\4\1\0\1\4\1\0\6\5\2\0\12\3\2\0\2\4"+
    "\42\0\1\4\27\0\2\5\6\0\12\3\13\0\1\5\1\0\1\5"+
    "\1\0\1\5\4\0\2\5\10\4\1\0\42\4\6\0\24\5\1\0"+
    "\2\5\4\4\4\0\10\5\1\0\44\5\11\0\1\5\71\0\42\4"+
    "\1\0\5\4\1\0\2\4\1\0\7\5\3\0\4\5\6\0\12\3"+
    "\6\0\6\4\4\5\106\0\46\4\12\0\51\4\7\0\132\4\5\0"+
    "\104\4\5\0\122\4\6\0\7\4\1\0\77\4\1\0\1\4\1\0"+
    "\4\4\2\0\7\4\1\0\1\4\1\0\4\4\2\0\47\4\1\0"+
    "\1\4\1\0\4\4\2\0\37\4\1\0\1\4\1\0\4\4\2\0"+
    "\7\4\1\0\1\4\1\0\4\4\2\0\7\4\1\0\7\4\1\0"+
    "\27\4\1\0\37\4\1\0\1\4\1\0\4\4\2\0\7\4\1\0"+
    "\47\4\1\0\23\4\16\0\11\3\56\0\125\4\14\0\u026c\4\2\0"+
    "\10\4\12\0\32\4\5\0\113\4\3\0\3\4\17\0\15\4\1\0"+
    "\4\4\3\5\13\0\22\4\3\5\13\0\22\4\2\5\14\0\15\4"+
    "\1\0\3\4\1\0\2\5\14\0\64\4\40\5\3\0\1\4\3\0"+
    "\2\4\1\5\2\0\12\3\41\0\3\5\2\0\12\3\6\0\130\4"+
    "\10\0\51\4\1\5\126\0\35\4\3\0\14\5\4\0\14\5\12\0"+
    "\12\3\36\4\2\0\5\4\u038b\0\154\4\224\0\234\4\4\0\132\4"+
    "\6\0\26\4\2\0\6\4\2\0\46\4\2\0\6\4\2\0\10\4"+
    "\1\0\1\4\1\0\1\4\1\0\1\4\1\0\37\4\2\0\65\4"+
    "\1\0\7\4\1\0\1\4\3\0\3\4\1\0\7\4\3\0\4\4"+
    "\2\0\6\4\4\0\15\4\5\0\3\4\1\0\7\4\17\0\4\5"+
    "\32\0\5\5\20\0\2\4\23\0\1\4\13\0\4\5\6\0\6\5"+
    "\1\0\1\4\15\0\1\4\40\0\22\4\36\0\15\5\4\0\1\5"+
    "\3\0\6\5\27\0\1\4\4\0\1\4\2\0\12\4\1\0\1\4"+
    "\3\0\5\4\6\0\1\4\1\0\1\4\1\0\1\4\1\0\4\4"+
    "\1\0\3\4\1\0\7\4\3\0\3\4\5\0\5\4\26\0\44\4"+
    "\u0e81\0\3\4\31\0\11\4\6\5\1\0\5\4\2\0\5\4\4\0"+
    "\126\4\2\0\2\5\2\0\3\4\1\0\137\4\5\0\50\4\4\0"+
    "\136\4\21\0\30\4\70\0\20\4\u0200\0\u19b6\4\112\0\u51a6\4\132\0"+
    "\u048d\4\u0773\0\u2ba4\4\u215c\0\u012e\4\2\0\73\4\225\0\7\4\14\0"+
    "\5\4\5\0\1\4\1\5\12\4\1\0\15\4\1\0\5\4\1\0"+
    "\1\4\1\0\2\4\1\0\2\4\1\0\154\4\41\0\u016b\4\22\0"+
    "\100\4\2\0\66\4\50\0\15\4\3\0\20\5\20\0\4\5\17\0"+
    "\2\4\30\0\3\4\31\0\1\4\6\0\5\4\1\0\207\4\2\0"+
    "\1\5\4\0\1\4\13\0\12\3\7\0\32\4\4\0\1\4\1\0"+
    "\32\4\12\0\132\4\3\0\6\4\2\0\6\4\2\0\6\4\2\0"+
    "\3\4\3\0\2\4\3\0\2\4\22\0\3\5\4\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\1\1\3\1\1\1\4\1\5"+
    "\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15"+
    "\1\16\1\17\1\20\1\21\1\22\1\23\1\24";

  private static int [] zzUnpackAction() {
    int [] result = new int[23];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\25\0\52\0\77\0\124\0\151\0\151\0\151"+
    "\0\151\0\176\0\25\0\25\0\25\0\25\0\25\0\25"+
    "\0\25\0\25\0\25\0\25\0\25\0\151\0\25";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[23];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\1\2\1\7\1\10"+
    "\1\11\1\12\1\2\1\13\1\14\1\15\1\16\1\17"+
    "\1\20\1\21\1\22\1\23\1\24\26\0\1\3\26\0"+
    "\1\5\6\0\1\25\15\0\1\5\24\0\6\26\25\0"+
    "\1\27\13\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[147];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\10\1\13\11\1\1\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[23];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /* user code: */
	private Symbol newToken(short id)
	{
		return new Symbol(id, yyline + 1, yycolumn + 1, yylength());
	}

	private Symbol newToken(short id, Object value)
	{
		return new Symbol(id, yyline + 1, yycolumn + 1, yylength(), value);
	}


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  ShapePropScanner(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  ShapePropScanner(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 1696) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzPushbackPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead < 0) {
      return true;
    }
    else {
      zzEndRead+= numRead;
      return false;
    }
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = zzPushbackPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public Symbol nextToken() throws java.io.IOException, Scanner.Exception {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = zzLexicalState;


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 10: 
          { return newToken(Terminals.RRPAREN,   yytext());
          }
        case 21: break;
        case 20: 
          { return newToken(Terminals.OROR,      yytext());
          }
        case 22: break;
        case 18: 
          { return newToken(Terminals.ARROW,     yytext());
          }
        case 23: break;
        case 8: 
          { return newToken(Terminals.COMMA,     yytext());
          }
        case 24: break;
        case 12: 
          { return newToken(Terminals.MULT,      yytext());
          }
        case 25: break;
        case 17: 
          { return newToken(Terminals.SQUOTATION,yytext());
          }
        case 26: break;
        case 11: 
          { return newToken(Terminals.QUESTION,  yytext());
          }
        case 27: break;
        case 19: 
          { return newToken(Terminals.ID,        new String(yytext()));
          }
        case 28: break;
        case 1: 
          { throw new Scanner.Exception("unexpected character '" + yytext() + "'");
          }
        case 29: break;
        case 15: 
          { return newToken(Terminals.LSPAREN,   yytext());
          }
        case 30: break;
        case 7: 
          { return newToken(Terminals.OR,        yytext());
          }
        case 31: break;
        case 14: 
          { return newToken(Terminals.EQUAL,     yytext());
          }
        case 32: break;
        case 9: 
          { return newToken(Terminals.LRPAREN,   yytext());
          }
        case 33: break;
        case 6: 
          { return newToken(Terminals.DOLLAR,    yytext());
          }
        case 34: break;
        case 3: 
          { return newToken(Terminals.NUMBER,    new Integer(yytext()));
          }
        case 35: break;
        case 4: 
          { return newToken(Terminals.UPPERCASE, new String(yytext()));
          }
        case 36: break;
        case 16: 
          { return newToken(Terminals.RSPAREN,   yytext());
          }
        case 37: break;
        case 13: 
          { return newToken(Terminals.PLUS,      yytext());
          }
        case 38: break;
        case 5: 
          { return newToken(Terminals.LOWERCASE, new String(yytext()));
          }
        case 39: break;
        case 2: 
          { /* ignore */
          }
        case 40: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
              { 	return newToken(Terminals.EOF, "end-of-file");
 }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
