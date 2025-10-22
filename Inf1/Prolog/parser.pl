/***********************************************
 * Datei:            parser.pl                 *
 * Autor:            Kai von Luck              *
 * letzte Aenderung: 06.11.2018                *
 * von:              Sabine Schumann           *
 ***********************************************/

:- consult(scanner).

%----------------------------------------------------------
% Aufruf des Parsers (syntax) mit vorgeschaltetem Scanner
%----------------------------------------------------------
parser :-
    scanner(List),
    syntax(List,[]), !.

%----------------------------------------------------------
% Grammatik
%----------------------------------------------------------
syntax          --> start, declarations, statements, stop.

declarations    --> declaration, escape, declarations.
declarations    --> declaration, escape.

statements      --> statement, escape, statements.
statements      --> statement, escape.

declaration     --> type, variable.

statement       --> assignment.

assignment      --> variable, becomes, value.
assignment      --> variable, becomes, expression.

expression      --> bracketleft, item, operation, item, bracketright.
expression      --> item.

item            --> variable.
item            --> value.


escape          --> [';'].

operation       --> ['+'].
operation       --> ['-'].
operation       --> ['*'].
operation       --> ['/'].

bracketleft     --> ['('].
bracketright    --> [')'].

becomes         --> [=].

type            --> [integer].
type            --> [real].

variable        --> [_].

value           --> [1].
value           --> [2].
value           --> [1.0].
value           --> [2.0].

start           --> [begin].
stop            --> [end], [!].