package aspectMatlab;
import natlab.*;
import static aspectMatlab.AspectsParser.Terminals.*;
%%
%%
%layout aspects_matlab

%option class "%class AspectsScanner"

%component aspect
%component aspect_action
%component aspects_base
%component aspect_pattern

%start aspects_base

%%

%%inherit helper_beaver

//// Leading whitespace ////////////////////////////////////////////////////////

%%embed
%name aspect_leading_ws
%host aspects_base
%guest leading_ws
%start WHITE_SPACE
%end END_LEADING_WS

%%embed
%name aspects
%host aspects_base
%guest aspect
%start START_ASPECT
%end END
%pair OPEN_END, END
%pair PATTERNS, END
%pair ACTIONS, END

%%embed
%name patterns
%host aspect
%guest aspect_pattern
%start PATTERNS
%end END

%%embed
%name actions
%host aspect
%guest aspect_action
%start ACTIONS
%end END
%pair OPEN_END, END

/*
%%embed
%name patterns2
%host aspect
%guest aspect_pattern
%start [BEFORE AFTER AROUND]
%end COLON
*/

%%embed
%name string_aspect
%host aspect, aspect_pattern, aspect_action
%guest string
%start START_STRING
%end END_STRING

%%embed
%name aspect_comma_terminator
%host aspect, aspect_pattern, aspect_action
%guest comma_terminator
%start START_COMMA_TERMINATOR
%end END_COMMA_TERMINATOR

%%embed
%name aspect_semicolon_terminator
%host aspect, aspect_pattern, aspect_action
%guest semicolon_terminator
%start START_SEMICOLON_TERMINATOR
%end END_SEMICOLON_TERMINATOR

%%embed
%name aspect_eof_error
%host aspect
%guest aspects_base
%start EOF_ERROR
%end <ANY> //NB: never happens

%%inherit natlab

%replace base, aspects_base
