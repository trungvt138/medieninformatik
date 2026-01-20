/***********************************************
 * Datei:            Stammbaum_Duck.pl         *
 * Autorin:          Sabine Schumann           *
 * erstellt am:      01.09.2015                *
 * letzte Aenderung: 03.11.2022                *
 * von:              Sabine Schumann           *
 ***********************************************/

/* maennliche Enten und Gaense der Familie */
mann(tick_duck).
mann(trick_duck).
mann(track_duck).
mann(donald_duck).
mann(dagobert_duck).
mann(degenhard_duck).
mann(diethelm_duck).
mann(jakob_duck).
mann(dietbert_duck).
mann(david_duck).
mann(gruben-gustel_duck).
mann(kaeptn-david-fuerchtegott_duck).
mann(gustav_gans).
mann(dussel_duck).
mann(wastel_duck).
mann(franz_gans).
mann(general-golo_gans).
mann(teddy_duck).
mann(gangolf_gans).
mann(willibald_wasserhuhn).
mann(emanuel_erpel).
mann(emelrich_erpel).
mann(hilmar_duck).
mann(emil-erasmus_erpel).
mann(unbekannter_vater).

/* weibliche Enten und Gaense der Familie */
frau(daisy_duck).
frau(della_duck).
frau(mathilda_duck).
frau(dortel_duck).
frau(dankrade_duck).
frau(minchen_matz).
frau(daphne_duck).
frau(gretchen_gogel).
frau(wilhelmine_erpel).
frau(dorette_duck).
frau(wilberta_wasserhuhn).
frau(gunhilda_gans).
frau(unbekannte_frau_von_emil-erasmus).
frau(unbekannte_frau_von_david-fuerchtegott).

/* elternteil_von(Elternteil, Kind) */
elternteil_von(della_duck, tick_duck).
elternteil_von(della_duck, trick_duck).
elternteil_von(della_duck, track_duck).
elternteil_von(unbekannter_vater, tick_duck).
elternteil_von(unbekannter_vater, trick_duck).
elternteil_von(unbekannter_vater, track_duck).
 
elternteil_von(dortel_duck,    della_duck).
elternteil_von(dortel_duck,    donald_duck).
elternteil_von(degenhard_duck, della_duck).
elternteil_von(degenhard_duck, donald_duck).

elternteil_von(dankrade_duck, dortel_duck).
elternteil_von(dankrade_duck, dagobert_duck).
elternteil_von(dankrade_duck, mathilda_duck).
elternteil_von(dietbert_duck, dortel_duck).
elternteil_von(dietbert_duck, dagobert_duck).
elternteil_von(dietbert_duck, mathilda_duck).

elternteil_von(gruben-gustel_duck, dietbert_duck).
elternteil_von(gruben-gustel_duck, jakob_duck).
elternteil_von(gruben-gustel_duck, diethelm_duck).
elternteil_von(minchen_matz,       dietbert_duck).
elternteil_von(minchen_matz,       jakob_duck).
elternteil_von(minchen_matz,       diethelm_duck).

elternteil_von(general-golo_gans, gustav_gans).
elternteil_von(daphne_duck,       gustav_gans).

elternteil_von(gretchen_gogel, dussel_duck).
elternteil_von(gretchen_gogel, wastel_duck).
elternteil_von(teddy_duck,     dussel_duck).
elternteil_von(teddy_duck,     wastel_duck).

elternteil_von(wilhelmine_erpel, franz_gans).
elternteil_von(gangolf_gans,     franz_gans).

elternteil_von(wilberta_wasserhuhn, wilhelmine_erpel).
elternteil_von(wilberta_wasserhuhn, willibald_wasserhuhn).
elternteil_von(emanuel_erpel,       wilhelmine_erpel).
elternteil_von(emanuel_erpel,       willibald_wasserhuhn).

elternteil_von(dorette_duck, teddy_duck).
elternteil_von(dorette_duck, daphne_duck).
elternteil_von(dorette_duck, degenhard_duck).
elternteil_von(hilmar_duck,  teddy_duck).
elternteil_von(hilmar_duck,  daphne_duck).
elternteil_von(hilmar_duck,  degenhard_duck).

elternteil_von(gunhilda_gans,  dorette_duck).
elternteil_von(gunhilda_gans,  emanuel_erpel).
elternteil_von(emelrich_erpel, dorette_duck).
elternteil_von(emelrich_erpel, emanuel_erpel).

elternteil_von(unbekannte_frau_von_emil-erasmus, emelrich_erpel).
elternteil_von(emil-erasmus_erpel,               emelrich_erpel).

elternteil_von(unbekannte_frau_von_david-fuerchtegott, gruben-gustel_duck).
elternteil_von(unbekannte_frau_von_david-fuerchtegott, david_duck).
elternteil_von(kaeptn-david-fuerchtegott_duck,         gruben-gustel_duck).
elternteil_von(kaeptn-david-fuerchtegott_duck,         david_duck).

/*X ist Vater von Y, wenn X männlich und X ein Elternteil von Y ist*/
vater(X, Y):- mann(X), elternteil_von(X, Y).

/*X ist Mutter von Y, wenn X weiblich und X ein Elternteil von Y ist*/
mutter(X, Y):- frau(X), elternteil_von(X, Y).

/*X ist Oma von Y, wenn X die Mutter von Z und Z ein Elternteil von Y*/
oma(X, Y):-  mutter(X, Z), elternteil_von(Z, Y).

/*X ist Opa von Y, wenn X der Vater von Z und Z ein Elternteil von Y*/
opa(X, Y):- vater(X, Z), elternteil_von(Z, Y).

/*X ist Vorfahre von Y, wenn X entweder Elternteil von Y oder Vorfahre der Eltern von Y*/
vorfahre(X, Y):- elternteil_von(X, Y).
vorfahre(X, Y):- elternteil_von(Z, Y), vorfahre(X, Z).

/*Hilfsregel: X ist Geschwister von Y, wenn X und Y gemeinsame Eltern haben.*/
geschwister(X, Y):- vater(Z, X), vater(Z, Y), mutter(V, X), mutter(V, Y), not(X = Y).

/*X ist Vollbruder von Y, wenn X männlich ist, 
 * X und Y gemeinsamen Eltern haben und X und Y unterschieden sind.*/
vollbruder(X, Y):- mann(X), geschwister(X, Y).

/*X ist Vollschwester von Y, wenn X weiblich ist, 
 * X und Y gemeinsamen Eltern haben und X und Y unterschieden sind.*/
vollschwester(X, Y):- frau(X), geschwister(X, Y).

/*X ist Onkel von Y, wenn X der Vollbruder der Eltern von Y ist.*/
onkel(X, Y):- vollbruder(X, Z), elternteil_von(Z, Y).

/*X ist Tante von Y, wenn X der Vollschwester der Eltern von Y ist.*/
tante(X, Y):- vollschwester(X, Z), elternteil_von(Z, Y).

/*Hilfsregel: X ist Verwandter von Y, wenn X entweder Onkel oder Tante von Y ist*/
verwandter(X, Y):- onkel(Z, X), elternteil_von(Z, Y).
verwandter(X, Y):- tante(Z, X), elternteil_von(Z, Y).

/*X ist Cousin von Y, wenn X Verwandter von Y und X männlich ist.*/
cousin(X, Y):- verwandter(X, Y), mann(X).

/*X ist Cousine von Y, wenn X Verwandter von Y und X weiblich ist.*/
cousine(X, Y):- verwandter(X, Y), frau(X).
