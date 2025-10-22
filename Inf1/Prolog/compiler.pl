/***********************************************
 * Datei:            compiler.pl               *
 * Autor:            Kai von Luck              *
 * letzte Aenderung: 06.11.2018                *
 * von:              Sabine Schumann           *
 ***********************************************/

:- consult(scanner).

name_all_variables(_,unbound) :- !.
name_all_variables(Term,letters) :-
    numbervars(Term, 0, _).
name_all_variables(Term,FunctorName) :-
    numbervars(Term, 0, _, [functor_name(FunctorName)]).

%----------------------------------------------------------
% Aufruf des Compilers mit vorgeschaltetem Scanner
%----------------------------------------------------------
compiler(Memory,Code,FunctorName) :-
    scanner(List),
    compile(Memory,Code,List,[]), !,
    name_all_variables([Memory,Code], FunctorName).

compiler(Memory,Code) :-
    compiler(Memory,Code,address).

%----------------------------------------------------------
% Grammatik
%----------------------------------------------------------
compile(Mem,Code)
    --> start, declarations(Table,Mem), statements(Table,Code), stop.

declarations([Entry|Table],[Mem|Mems])
    --> declaration(Entry,Mem), escape, declarations(Table,Mems).
declarations([Entry],[Mem])
    --> declaration(Entry,Mem), escape.

statements(Table,[Code|Codes])
    --> statement(Table,Code), escape, statements(Table,Codes).
statements(Table,[Code])
    --> statement(Table,Code), escape.

declaration([Type,Var,Space],[alloc,Type,Space])
    --> type(Type), variable(Var).

statement(Table,Code)
    --> assignment(Table,Code).

assignment(Table,[store,Val,Space])
    --> variable(Var), becomes, value(Val,Type),
        {member([Type,Var,Space],Table)}.
assignment(Table,[store,Val,Space])
    --> variable(Var), becomes, expression(Val,Type,Table),
        {member([Type,Var,Space],Table)}.

expression([OP,Val1,Val2],Type,Table)
    --> bracketleft, item(Val1,Type,Table), operation(OP),
        item(Val2,Type,Table), bracketright.
expression(Val,Type,Table)
    --> item(Val,Type,Table).

item(Val,Type,_)
    --> value(Val,Type),!.
item([read,Space],Type,Table)
    --> variable(Var), 
        {member([Type,Var,Space],Table)}.

escape              --> [';'].

operation(plus)     --> ['+'].
operation(minus)    --> ['-'].
operation(times)    --> ['*'].
operation(div)      --> ['/'].

bracketleft         --> ['('].
bracketright        --> [')'].

becomes             --> [=].

type(integer)       --> [integer].
type(real)          --> [real].

variable(Var)       --> [Var].

value(1,integer)    --> [1].
value(2,integer)    --> [2].
value(1.0,real)     --> [1.0].
value(2.0,real)     --> [2.0].

start               --> [begin].
stop                --> [end], [!].
